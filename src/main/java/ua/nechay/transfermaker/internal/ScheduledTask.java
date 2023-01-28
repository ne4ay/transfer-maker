package ua.nechay.transfermaker.internal;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author anechaev
 * @since 28.01.2023
 */
public class ScheduledTask implements ScheduledFuture<Object> {

    private final ScheduledFuture<?> future;
    private final String token;
    private final LocalDateTime registered;

    public ScheduledTask(@Nonnull ScheduledFuture<?> future, @Nonnull String token, @Nonnull LocalDateTime registered) {
        this.future = future;
        this.token = token;
        this.registered = registered;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return future.getDelay(unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return future.compareTo(o);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }
}
