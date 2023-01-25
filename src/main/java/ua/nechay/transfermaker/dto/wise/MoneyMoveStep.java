package ua.nechay.transfermaker.dto.wise;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class MoneyMoveStep {
    @JsonProperty private long id;
    @JsonProperty private String type;
    @JsonProperty private String creationTime;
    @JsonProperty private List<MoneyTO> balancesAfter;
    @JsonProperty private String channelName;
    @JsonProperty private String channelReferenceId;
    @JsonProperty private String tracingReferenceCode;
    @JsonProperty private MoneyTO sourceAmount;
    @JsonProperty private MoneyTO targetAmount;
    @JsonProperty private MoneyTO fee;
    @JsonProperty private Double rate;

    public MoneyMoveStep(long id, String type, String creationTime, List<MoneyTO> balancesAfter, String channelName,
        String channelReferenceId,
        String tracingReferenceCode, MoneyTO sourceAmount, MoneyTO targetAmount, MoneyTO fee, Double rate)
    {
        this.id = id;
        this.type = type;
        this.creationTime = creationTime;
        this.balancesAfter = balancesAfter;
        this.channelName = channelName;
        this.channelReferenceId = channelReferenceId;
        this.tracingReferenceCode = tracingReferenceCode;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.fee = fee;
        this.rate = rate;
    }

    public MoneyMoveStep() {
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public List<MoneyTO> getBalancesAfter() {
        return balancesAfter;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelReferenceId() {
        return channelReferenceId;
    }

    public String getTracingReferenceCode() {
        return tracingReferenceCode;
    }

    public MoneyTO getSourceAmount() {
        return sourceAmount;
    }

    public MoneyTO getTargetAmount() {
        return targetAmount;
    }

    public MoneyTO getFee() {
        return fee;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MoneyMoveStep that))
            return false;
        return id == that.id && Objects.equals(type, that.type) && Objects.equals(creationTime, that.creationTime)
            && Objects.equals(balancesAfter, that.balancesAfter) && Objects.equals(channelName, that.channelName)
            && Objects.equals(channelReferenceId, that.channelReferenceId) && Objects.equals(tracingReferenceCode,
            that.tracingReferenceCode) && Objects.equals(sourceAmount, that.sourceAmount) && Objects.equals(targetAmount,
            that.targetAmount) && Objects.equals(fee, that.fee) && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, creationTime, balancesAfter, channelName, channelReferenceId, tracingReferenceCode, sourceAmount,
            targetAmount, fee, rate);
    }
}
