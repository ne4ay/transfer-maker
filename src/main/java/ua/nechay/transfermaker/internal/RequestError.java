package ua.nechay.transfermaker.internal;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class RequestError {

    private String message;

    public RequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RequestError that))
            return false;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestError.class.getSimpleName() + "[", "]")
            .add("message='" + message + "'")
            .toString();
    }
}
