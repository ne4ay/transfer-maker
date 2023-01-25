package ua.nechay.transfermaker.logic;

import org.springframework.web.ErrorResponse;
import org.springframework.web.client.RestTemplate;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.dto.wise.AccountBalanceTO;
import ua.nechay.transfermaker.dto.wise.MoneyTO;
import ua.nechay.transfermaker.dto.wise.request.MoveMoneyRequestTO;
import ua.nechay.transfermaker.internal.Either;
import ua.nechay.transfermaker.internal.RequestError;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class BalanceCheckTask implements Runnable {

    private final TaskRestRequester requester;
    private final TaskKey taskKey;
    private final String token;

    public BalanceCheckTask(@Nonnull TaskRestRequester requester, @Nonnull TaskKey key, @Nonnull String token) {
        this.requester = requester;
        this.taskKey = key;
        this.token = token;
    }

    @Override
    public void run() {
        String profileId = taskKey.getProfileId();
        Either<RequestError, List<AccountBalanceTO>> either = requester.getBalances(profileId, token);
        if (either.isLeft()) {
            System.out.println(either.getLeft()); //TODO: clean
            return;
        }
        List<AccountBalanceTO> balances = either.getRight();
        List<RequestError> errors = balances.stream()
            .map(AccountBalanceTO::getAmount)
            .filter(Objects::nonNull)
            .filter(money -> money.getValue() != 0)
            .map(this::createRequest)
            .map(request -> requester.moveMoney(request, profileId, token))
            .filter(Either::isLeft)
            .map(Either::getLeft)
            .toList();
        for (var error : errors) {
            System.out.println(error); //TODO: clean
        }
    }

    private MoveMoneyRequestTO createRequest(@Nonnull MoneyTO money) {
        return new MoveMoneyRequestTO(money, taskKey.getBalanceId(), taskKey.getTargetBalanceId());
    }
}
