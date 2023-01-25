package ua.nechay.transfermaker.dto.input.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.transfermaker.domain.TaskKey;
import ua.nechay.transfermaker.internal.RegisterState;
import ua.nechay.transfermaker.internal.TaskRegisteringResult;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskRegisterResponseTO {
    @JsonProperty private String balanceId;
    @JsonProperty private String targetBalanceId;
    @JsonProperty private String profileId;
    @JsonProperty private RegisterState state;
    @JsonProperty private String message;

    public TaskRegisterResponseTO(String balanceId, String targetBalanceId, String profileId, RegisterState state, String message) {
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
        this.state = state;
        this.message = message;
    }

    public TaskRegisterResponseTO() {
    }

    public static TaskRegisterResponseTO fromResult(@Nonnull TaskRegisteringResult result) {
        TaskKey key = result.getKey();
        return new TaskRegisterResponseTO(
            key.getBalanceId(),
            key.getTargetBalanceId(),
            key.getProfileId(),
            result.getState(),
            result.getMessage()
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
        if (!(o instanceof TaskRegisterResponseTO that))
            return false;
        return Objects.equals(balanceId, that.balanceId) && Objects.equals(targetBalanceId, that.targetBalanceId)
            && Objects.equals(profileId, that.profileId) && state == that.state && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, targetBalanceId, profileId, state, message);
    }
}
