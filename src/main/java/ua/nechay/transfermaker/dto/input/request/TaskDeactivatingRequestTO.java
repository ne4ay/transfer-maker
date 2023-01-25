package ua.nechay.transfermaker.dto.input.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author anechaev
 * @since 26.01.2023
 */
public class TaskDeactivatingRequestTO {
    @JsonProperty private String balanceId;
    @JsonProperty private String targetBalanceId;
    @JsonProperty private String profileId;

    public TaskDeactivatingRequestTO(String balanceId, String targetBalanceId, String profileId) {
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
    }

    public TaskDeactivatingRequestTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TaskDeactivatingRequestTO that))
            return false;
        return Objects.equals(balanceId, that.balanceId) && Objects.equals(targetBalanceId, that.targetBalanceId)
            && Objects.equals(profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, targetBalanceId, profileId);
    }
}
