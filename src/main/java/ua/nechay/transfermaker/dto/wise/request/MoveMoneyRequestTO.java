package ua.nechay.transfermaker.dto.wise.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.transfermaker.dto.wise.MoneyTO;

import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class MoveMoneyRequestTO {
    @JsonProperty private MoneyTO amount;
    @JsonProperty private String sourceBalanceId;
    @JsonProperty private String targetBalanceId;

    public MoveMoneyRequestTO(MoneyTO amount, String sourceBalanceId, String targetBalanceId) {
        this.amount = amount;
        this.sourceBalanceId = sourceBalanceId;
        this.targetBalanceId = targetBalanceId;
    }

    public MoveMoneyRequestTO() {
    }

    public MoneyTO getAmount() {
        return amount;
    }

    public String getSourceBalanceId() {
        return sourceBalanceId;
    }

    public String getTargetBalanceId() {
        return targetBalanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MoveMoneyRequestTO that))
            return false;
        return Objects.equals(amount, that.amount) && Objects.equals(sourceBalanceId, that.sourceBalanceId)
            && Objects.equals(targetBalanceId, that.targetBalanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, sourceBalanceId, targetBalanceId);
    }
}
