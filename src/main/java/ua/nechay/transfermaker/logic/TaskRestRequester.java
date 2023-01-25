package ua.nechay.transfermaker.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.nechay.transfermaker.config.TransferMakerProperties;
import ua.nechay.transfermaker.dto.wise.AccountBalanceTO;
import ua.nechay.transfermaker.dto.wise.request.MoveMoneyRequestTO;
import ua.nechay.transfermaker.dto.wise.response.MoveMoneyResponseTO;
import ua.nechay.transfermaker.internal.Either;
import ua.nechay.transfermaker.internal.RequestError;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class TaskRestRequester {

    @Autowired private TransferMakerProperties properties;
    @Autowired private RestTemplate restTemplate;

    //TODO: return either monad
    @Nonnull
    public Either<RequestError, List<AccountBalanceTO>> getBalances(@Nonnull String profileId, @Nonnull String token) {
        HttpHeaders headers = createHeaders(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = properties.getExternalWisePath() + "/v4/profiles/" + profileId + "/balances?types=STANDARD";
        return makeRequest(() -> restTemplate.exchange(url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<AccountBalanceTO>>() {}), "request account balances");
    }

    public Either<RequestError, MoveMoneyResponseTO> moveMoney(@Nonnull MoveMoneyRequestTO request, @Nonnull String profileId, @Nonnull String token) {
        String uuid = UUID. randomUUID().toString();
        HttpHeaders headers = createHeaders(token);
        headers.add("Content-Type", "application/json");
        headers.add("X-idempotence-uuid", uuid);
        HttpEntity<MoveMoneyRequestTO> requestEntity = new HttpEntity<>(request);
        String url = properties.getExternalWisePath() + "/v2/profiles/" + profileId + "/balance-movements";
        return makeRequest(() -> restTemplate.exchange(url,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<MoveMoneyResponseTO>() {}), "move money");
    }

    private <T> Either<RequestError, T> makeRequest(@Nonnull RequestAction<T> action, String actionName) {
        ResponseEntity<T> response;
        try {
            response = action.request();
        } catch (Exception e) {
            e.printStackTrace();
            return Either.left(new RequestError("Some error happened during performing " + actionName));
        }
        if (response.getStatusCode().is2xxSuccessful()) {
            return Either.right(response.getBody());
        }
        System.out.println(response); //TODO: clean
        return Either.left(new RequestError("Non successful code has been gotten: " + response.getStatusCode() + " during " + actionName));
    }

    private HttpHeaders createHeaders(@Nonnull String token) {
        HttpHeaders headers = new HttpHeaders();
        String authorization = properties.getWiseAuthorizationType() + " " + token;
        headers.add("Authorization", authorization);
        return headers;
    }

    private interface RequestAction<T> {
        ResponseEntity<T> request() throws Exception;
    }
}
