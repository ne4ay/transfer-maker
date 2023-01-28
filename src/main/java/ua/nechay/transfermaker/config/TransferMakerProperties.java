package ua.nechay.transfermaker.config;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.StringJoiner;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TransferMakerProperties {

    @Value("${external.wise.path}")
    private String externalWisePath;

    @Value("${wise.authorization.type}")
    private String wiseAuthorizationType;

    @Value("${task.executor.service.thread.amount:20}")
    private int taskExecutorCountOfThreads;

    @Value("${task.executor.service.period.ms:2000}")
    private long tickPeriodMs;

    @Value("${wise.rest.connection.timeout.ms:20000}")
    private int restConnectionTimeoutMs;

    @Value("${wise.rest.read.timeout.ms:20000}")
    private int restReadTimeoutMs;

    @Value("${external.exception.alerting.path}")
    private String externalExceptionAlertingPath;

    @PostConstruct
    public void init() {
        System.out.println(this); //TODO: clean
    }

    public String getExternalWisePath() {
        return externalWisePath;
    }

    public String getWiseAuthorizationType() {
        return wiseAuthorizationType;
    }

    public int getTaskExecutorCountOfThreads() {
        return taskExecutorCountOfThreads;
    }

    public long getTickPeriodMs() {
        return tickPeriodMs;
    }

    public int getRestConnectionTimeoutMs() {
        return restConnectionTimeoutMs;
    }

    public int getRestReadTimeoutMs() {
        return restReadTimeoutMs;
    }

    public String getExternalExceptionAlertingPath() {
        return externalExceptionAlertingPath;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransferMakerProperties.class.getSimpleName() + "[", "]")
            .add("externalWisePath='" + externalWisePath + "'")
            .add("wiseAuthorizationType='" + wiseAuthorizationType + "'")
            .add("taskExecutorCountOfThreads=" + taskExecutorCountOfThreads)
            .add("tickPeriodMs=" + tickPeriodMs)
            .add("restConnectionTimeoutMs=" + restConnectionTimeoutMs)
            .add("restReadTimeoutMs=" + restReadTimeoutMs)
            .add("externalExceptionAlertingPath='" + externalExceptionAlertingPath + "'")
            .toString();
    }
}
