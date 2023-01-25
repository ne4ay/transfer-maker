package ua.nechay.transfermaker.dto.input.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class TaskRegisterRequestTO {
    @JsonProperty private String token;
    @JsonProperty private String balanceId;
    @JsonProperty private String targetBalanceId;
    @JsonProperty private String profileId;

    public TaskRegisterRequestTO(String token, String balanceId, String targetBalanceId, String profileId) {
        this.token = token;
        this.balanceId = balanceId;
        this.targetBalanceId = targetBalanceId;
        this.profileId = profileId;
    }

    public TaskRegisterRequestTO() {
    }

    public String getToken() {
        return token;
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
}
