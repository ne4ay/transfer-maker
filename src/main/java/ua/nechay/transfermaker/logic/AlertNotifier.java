package ua.nechay.transfermaker.logic;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.dto.alert.response.NotificationWiseTaskRequestErrorTO;
import ua.nechay.transfermaker.internal.RequestError;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 28.01.2023
 */
public class AlertNotifier {
    private final RestTemplate restTemplate;
    private final String notifyingUrl;

    public AlertNotifier(@Nonnull RestTemplate restTemplate, @Nonnull String notifyingUrl) {
        this.restTemplate = restTemplate;
        this.notifyingUrl = notifyingUrl;
    }

    public void notifyAlert(@Nonnull RequestError requestError, @Nonnull TaskKey key) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        NotificationWiseTaskRequestErrorTO request = NotificationWiseTaskRequestErrorTO.create(key, requestError);
        HttpEntity<NotificationWiseTaskRequestErrorTO> requestEntity = new HttpEntity<>(request, headers);
        try {
            restTemplate.exchange(notifyingUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<NotificationWiseTaskRequestErrorTO>() {});
        } catch (Throwable e) {
            System.out.println("Unable to notify: " + e); //TODO: clean
        }
    }
}
