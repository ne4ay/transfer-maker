package ua.nechay.transfermaker.logic;

import ua.nechay.transfermaker.domain.TaskKey;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskPool {
    private final Map<TaskKey, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public Optional<ScheduledFuture<?>> getTask(@Nonnull TaskKey key) {
        return Optional.ofNullable(tasks.get(key));
    }

    public Optional<ScheduledFuture<?>> addTask(@Nonnull TaskKey key, @Nonnull ScheduledFuture<?> future) {
        return Optional.ofNullable(tasks.put(key, future));
    }

    public Optional<ScheduledFuture<?>> removeTask(@Nonnull TaskKey key) {
        return Optional.ofNullable(tasks.remove(key));
    }
 }
