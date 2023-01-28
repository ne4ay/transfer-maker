package ua.nechay.transfermaker.logic;

import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.internal.ScheduledTask;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskPool {
    private final Map<TaskKey, ScheduledTask> tasks = new ConcurrentHashMap<>();

    @Nonnull
    public Map<TaskKey, ScheduledTask> getAllTasks() {
        return new HashMap<>(tasks);
    }

    public Optional<ScheduledFuture<?>> getTask(@Nonnull TaskKey key) {
        return Optional.ofNullable(tasks.get(key));
    }

    public Optional<ScheduledFuture<?>> addTask(@Nonnull TaskKey key, @Nonnull ScheduledTask future) {
        return Optional.ofNullable(tasks.put(key, future));
    }

    public Optional<ScheduledFuture<?>> removeTask(@Nonnull TaskKey key) {
        return Optional.ofNullable(tasks.remove(key));
    }
 }
