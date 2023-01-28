package ua.nechay.transfermaker.internal;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class RequestError {

    private final Integer code;
    private final String message;

    public RequestError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RequestError error))
            return false;
        return Objects.equals(code, error.code) && Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestError.class.getSimpleName() + "[", "]")
            .add("code=" + code)
            .add("message='" + message + "'")
            .toString();
    }
}
