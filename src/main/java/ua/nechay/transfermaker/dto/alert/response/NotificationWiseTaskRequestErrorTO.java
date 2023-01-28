package ua.nechay.transfermaker.dto.alert.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.internal.RequestError;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author anechaev
 * @since 28.01.2023
 */
public class NotificationWiseTaskRequestErrorTO {
    @JsonProperty private String balanceId;
    @JsonProperty private String targetBalanceId;
    @JsonProperty private String profileId;
    @JsonProperty private Integer code;
    @JsonProperty private String message;

    public NotificationWiseTaskRequestErrorTO(String balanceId, String targetBalanceId, String profileId, Integer code, String message) {
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
        this.code = code;
        this.message = message;
    }

    public NotificationWiseTaskRequestErrorTO() {
    }

    public static NotificationWiseTaskRequestErrorTO create(@Nonnull TaskKey taskKey, @Nonnull RequestError requestError) {
        return new NotificationWiseTaskRequestErrorTO(
            taskKey.getBalanceId(),
            taskKey.getTargetBalanceId(),
            taskKey.getProfileId(),
            requestError.getCode(),
            requestError.getMessage()
        );
    }

    public String getBalanceId() {
        return balanceId;
    }

    public String getTargetBalanceId() {
        return targetBalanceId;
    }

    public String getProfileId() {
        return profileId;
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
        if (!(o instanceof NotificationWiseTaskRequestErrorTO that))
            return false;
        return Objects.equals(balanceId, that.balanceId) && Objects.equals(targetBalanceId, that.targetBalanceId)
            && Objects.equals(profileId, that.profileId) && Objects.equals(code, that.code) && Objects.equals(
            message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, targetBalanceId, profileId, code, message);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NotificationWiseTaskRequestErrorTO.class.getSimpleName() + "[", "]")
            .add("balanceId='" + balanceId + "'")
            .add("targetBalanceId='" + targetBalanceId + "'")
            .add("profileId='" + profileId + "'")
            .add("code=" + code)
            .add("message='" + message + "'")
            .toString();
    }
}
