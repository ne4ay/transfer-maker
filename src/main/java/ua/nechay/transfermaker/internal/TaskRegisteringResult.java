package ua.nechay.transfermaker.internal;

import ua.nechay.transfermaker.domain.TaskKey;

import java.util.Objects;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class TaskRegisteringResult {

    private final TaskKey key;
    private final RegisterState state;
    private final String message;

    public TaskRegisteringResult(TaskKey key, RegisterState state, String message) {
        this.key = key;
        this.state = state;
        this.message = message;
    }

    public TaskKey getKey() {
        return key;
    }

    public RegisterState getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TaskRegisteringResult that))
            return false;
        return Objects.equals(key, that.key) && state == that.state && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, state, message);
    }
}
