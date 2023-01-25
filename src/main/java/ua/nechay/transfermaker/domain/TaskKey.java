package ua.nechay.transfermaker.domain;

import ua.nechay.transfermaker.dto.input.request.TaskDeactivatingRequestTO;
import ua.nechay.transfermaker.dto.input.request.TaskRegisterRequestTO;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskKey {
    private final String balanceId;
    private final String targetBalanceId;
    private final String profileId;

    public TaskKey(@Nonnull String balanceId, @Nonnull String targetBalanceId, @Nonnull String profileId) {
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
    }

    public static TaskKey fromRequest(@Nonnull TaskRegisterRequestTO request) {
        return new TaskKey(
            request.getBalanceId(),
            request.getTargetBalanceId(),
            request.getProfileId());
    }

    public static TaskKey fromRequest(@Nonnull TaskDeactivatingRequestTO request) {
        return new TaskKey(
            request.getBalanceId(),
            request.getTargetBalanceId(),
            request.getProfileId());
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
        if (!(o instanceof TaskKey taskKey))
            return false;
        return Objects.equals(balanceId, taskKey.balanceId) && Objects.equals(targetBalanceId, taskKey.targetBalanceId)
            && Objects.equals(profileId, taskKey.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, targetBalanceId, profileId);
    }
}
