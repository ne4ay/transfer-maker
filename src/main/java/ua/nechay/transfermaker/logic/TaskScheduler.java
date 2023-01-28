package ua.nechay.transfermaker.logic;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.internal.ScheduledTask;
import ua.nechay.transfermaker.internal.TaskRegisteringResult;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import static ua.nechay.transfermaker.internal.RegisterState.FAILED;
import static ua.nechay.transfermaker.internal.RegisterState.SUCCESSFULLY;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class TaskScheduler {
    private static final ZoneId UTC = ZoneId.of("UTC");

    @Autowired private TaskPool taskPool;
    @Autowired private TaskExecutor taskExecutor;
    @Autowired private TaskRestRequester taskRestRequester;
    @Autowired private AlertNotifier alertNotifier;

    @Nonnull
    public TaskRegisteringResult registerTask(@Nonnull TaskKey key, @Nonnull String token) {
        Optional<? extends ScheduledFuture<?>> maybeFuture = taskPool.getTask(key);
        if (maybeFuture.isPresent()) {
            return new TaskRegisteringResult(key, SUCCESSFULLY, "Such task was already registered. Nothing done");
        }
        Optional<ScheduledFuture<?>> maybeExistedFuture = taskPool.addTask(key, registerNewTask(key, token));
        if (maybeExistedFuture.isPresent()) {
            ScheduledFuture<?> future = maybeExistedFuture.get();
            future.cancel(true);
            return new TaskRegisteringResult(key, SUCCESSFULLY, "Such task was already registered. The task was rescheduled");
        }
        return new TaskRegisteringResult(key, SUCCESSFULLY, "Successfully!");
    }

    @Nonnull
    public TaskRegisteringResult deactivateTask(@Nonnull TaskKey key) {
        Optional<ScheduledFuture<?>> maybeFuture = taskPool.removeTask(key);
        if (maybeFuture.isEmpty()) {
            return new TaskRegisteringResult(key, FAILED, "There are no such task!");
        }
        ScheduledFuture<?> future = maybeFuture.get();
        future.cancel(false);
        return new TaskRegisteringResult(key, SUCCESSFULLY, "The task has been deactivated successfully!");
    }

    private ScheduledTask registerNewTask(@Nonnull TaskKey key, @Nonnull String token) {
        ScheduledFuture<?> future = taskExecutor.registerNewTask(new BalanceCheckTask(taskRestRequester, alertNotifier, key, token));
        return new ScheduledTask(future, token, LocalDateTime.now(UTC));
    }
}
