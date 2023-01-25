package ua.nechay.transfermaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ua.nechay.transfermaker.logic.TaskExecutor;
import ua.nechay.transfermaker.logic.TaskPool;
import ua.nechay.transfermaker.logic.TaskRestRequester;
import ua.nechay.transfermaker.logic.TaskScheduler;

/**
 * @author anechaev
 * @since 25.01.2023
 */
@Configuration
public class TransferMakerBeanConfiguration {

    @Bean
    public TransferMakerProperties properties() {
        return new TransferMakerProperties();
    }

    @Bean
    public TaskPool taskPool() {
        return new TaskPool();
    }

    @Bean
    public TaskExecutor requestTaskExecutor(TransferMakerProperties properties) {
        return new TaskExecutor(properties.getTaskExecutorCountOfThreads(), properties.getTickPeriodMs());
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler();
    }

    @Bean
    public RestTemplate restTemplate(TransferMakerProperties properties) {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(properties.getRestConnectionTimeoutMs());
        httpRequestFactory.setReadTimeout(properties.getRestReadTimeoutMs());
        return new RestTemplate(httpRequestFactory);
    }

    @Bean
    public TaskRestRequester requester() {
        return new TaskRestRequester();
    }
}
