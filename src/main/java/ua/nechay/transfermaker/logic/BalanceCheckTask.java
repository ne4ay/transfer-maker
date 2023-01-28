package ua.nechay.transfermaker.logic;

import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.dto.wise.AccountBalanceTO;
import ua.nechay.transfermaker.dto.wise.MoneyTO;
import ua.nechay.transfermaker.dto.wise.request.MoveMoneyRequestTO;
import ua.nechay.transfermaker.dto.wise.response.MoveMoneyResponseTO;
import ua.nechay.transfermaker.internal.Either;
import ua.nechay.transfermaker.internal.RequestError;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class BalanceCheckTask implements Runnable {

    private final TaskRestRequester requester;
    private final AlertNotifier alertNotifier;
    private final TaskKey taskKey;
    private final String token;

    public BalanceCheckTask(@Nonnull TaskRestRequester requester, @Nonnull AlertNotifier alertNotifier,
        @Nonnull TaskKey key, @Nonnull String token)
    {
        this.requester = requester;
        this.alertNotifier = alertNotifier;
        this.taskKey = key;
        this.token = token;
    }

    @Override
    public void run() {
        String profileId = taskKey.getProfileId();
        Either<RequestError, AccountBalanceTO> either = requester.getBalance(profileId, taskKey.getBalanceId(), token);
        if (either.isLeft()) {
            RequestError requestError = either.getLeft();
            return;
        }
        AccountBalanceTO balance = either.getRight();
        MoneyTO money = balance.getAmount();
        if (money == null) {
            return;
        }
        if (money.getValue() == 0) {
            return;
        }
        System.out.println("Start to move money for: " + taskKey); //TODO: clean
        MoveMoneyRequestTO request = createRequest(money);
        Either<RequestError, MoveMoneyResponseTO> response = requester.moveMoney(request, profileId, token);
        if (response.isLeft()) {
            System.out.println("ERROR: " +response.getLeft()); //TODO: clean
        }
    }

    private MoveMoneyRequestTO createRequest(@Nonnull MoneyTO money) {
        return new MoveMoneyRequestTO(money, taskKey.getBalanceId(), taskKey.getTargetBalanceId());
    }

    private void notifyAboutError(@Nonnull RequestError requestError) {
        alertNotifier.notifyAlert(requestError, taskKey);
    }
}
