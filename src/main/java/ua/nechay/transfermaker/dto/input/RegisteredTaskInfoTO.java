package ua.nechay.transfermaker.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.transfermaker.domain.TaskKey;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author anechaev
 * @since 28.01.2023
 */
public class RegisteredTaskInfoTO {
    @JsonProperty private String balanceId;
    @JsonProperty private String targetBalanceId;
    @JsonProperty private String profileId;
    @JsonProperty private String token;
    @JsonProperty private LocalDateTime registeredDateTime;

    public RegisteredTaskInfoTO(String balanceId, String targetBalanceId, String profileId, String token, LocalDateTime dateTime) {
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
        this.token = token;
        this.registeredDateTime = dateTime;
    }

    public RegisteredTaskInfoTO() {
    }

    public static RegisteredTaskInfoTO fromKey(@Nonnull TaskKey key, @Nonnull String token, @Nonnull LocalDateTime localDateTime) {
        return new RegisteredTaskInfoTO(
          key.getBalanceId(),
          key.getTargetBalanceId(),
          key.getProfileId(),
          token,
          localDateTime
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

    public String getToken() {
        return token;
    }

    public LocalDateTime getRegisteredDateTime() {
        return registeredDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RegisteredTaskInfoTO that))
            return false;
        return Objects.equals(balanceId, that.balanceId) && Objects.equals(targetBalanceId, that.targetBalanceId)
            && Objects.equals(profileId, that.profileId) && Objects.equals(token, that.token) && Objects.equals(
            registeredDateTime, that.registeredDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, targetBalanceId, profileId, token, registeredDateTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RegisteredTaskInfoTO.class.getSimpleName() + "[", "]")
            .add("balanceId='" + balanceId + "'")
            .add("targetBalanceId='" + targetBalanceId + "'")
            .add("profileId='" + profileId + "'")
            .add("token='" + token + "'")
            .add("dateTime=" + registeredDateTime)
            .toString();
    }
}
