package ua.nechay.transfermaker.dto.wise.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.transfermaker.dto.wise.MoneyMoveStep;
import ua.nechay.transfermaker.dto.wise.MoneyTO;
import ua.nechay.transfermaker.dto.wise.NumeratedMoneyTO;

import java.util.List;
import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class MoveMoneyResponseTO {
    @JsonProperty private String id;
    @JsonProperty private String type;
    @JsonProperty private String state;
    @JsonProperty private List<NumeratedMoneyTO> balanceAfter;
    @JsonProperty private String creationTime;
    @JsonProperty private List<MoneyMoveStep> steps;
    @JsonProperty private MoneyTO sourceAmount;
    @JsonProperty private MoneyTO targetAmount;
    @JsonProperty private Double rate;
    @JsonProperty private List<MoneyTO> feeAmounts;

    public MoveMoneyResponseTO(String id, String type, String state, List<NumeratedMoneyTO> balanceAfter, String creationTime,
        List<MoneyMoveStep> steps, MoneyTO sourceAmount, MoneyTO targetAmount, Double rate, List<MoneyTO> feeAmounts)
    {
        this.id = id;
        this.type = type;
        this.state = state;
        this.balanceAfter = balanceAfter;
        this.creationTime = creationTime;
        this.steps = steps;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.rate = rate;
        this.feeAmounts = feeAmounts;
    }

    public MoveMoneyResponseTO() {
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public List<NumeratedMoneyTO> getBalanceAfter() {
        return balanceAfter;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public List<MoneyMoveStep> getSteps() {
        return steps;
    }

    public MoneyTO getSourceAmount() {
        return sourceAmount;
    }

    public MoneyTO getTargetAmount() {
        return targetAmount;
    }

    public Double getRate() {
        return rate;
    }

    public List<MoneyTO> getFeeAmounts() {
        return feeAmounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MoveMoneyResponseTO that))
            return false;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(state, that.state)
            && Objects.equals(balanceAfter, that.balanceAfter) && Objects.equals(creationTime, that.creationTime)
            && Objects.equals(steps, that.steps) && Objects.equals(sourceAmount, that.sourceAmount)
            && Objects.equals(targetAmount, that.targetAmount) && Objects.equals(rate, that.rate)
            && Objects.equals(feeAmounts, that.feeAmounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, state, balanceAfter, creationTime, steps, sourceAmount, targetAmount, rate, feeAmounts);
    }
}
