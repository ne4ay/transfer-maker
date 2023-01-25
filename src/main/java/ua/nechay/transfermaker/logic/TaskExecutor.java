package ua.nechay.transfermaker.logic;

import javax.annotation.Nonnull;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskExecutor {

    private final long periodMs;
    private final ScheduledExecutorService executorService;

    public TaskExecutor(int countOfThreads, long periodMs) {
        this.executorService = Executors.newScheduledThreadPool(countOfThreads);
        this.periodMs = periodMs;
    }

    @Nonnull
    public ScheduledFuture<?> registerNewTask(@Nonnull Runnable command) {
        return executorService.scheduleAtFixedRate(command, 0, periodMs, TimeUnit.MILLISECONDS);
    }
}
