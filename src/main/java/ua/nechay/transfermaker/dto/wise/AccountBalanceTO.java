package ua.nechay.transfermaker.dto.wise;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class AccountBalanceTO {
    @JsonProperty private Long id;
    @JsonProperty private String currency;
    @JsonProperty private String type;
    @JsonProperty private String name;
    @JsonProperty private String icon;
    @JsonProperty private String investmentState;
    @JsonProperty private MoneyTO amount;
    @JsonProperty private MoneyTO reservedAmount;
    @JsonProperty private MoneyTO cashAmount;
    @JsonProperty private MoneyTO totalWorth;
    @JsonProperty private String creationTime;
    @JsonProperty private String modificationTime;
    @JsonProperty private Boolean visible;

    public AccountBalanceTO(Long id, String currency, String type, String name, String icon, String investmentState, MoneyTO amount,
        MoneyTO reservedAmount, MoneyTO cashAmount, MoneyTO totalWorth, String creationTime, String modificationTime, Boolean visible)
    {
        this.id = id;
        this.currency = currency;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.investmentState = investmentState;
        this.amount = amount;
        this.reservedAmount = reservedAmount;
        this.cashAmount = cashAmount;
        this.totalWorth = totalWorth;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.visible = visible;
    }

    public AccountBalanceTO() {
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getInvestmentState() {
        return investmentState;
    }

    public MoneyTO getAmount() {
        return amount;
    }

    public MoneyTO getReservedAmount() {
        return reservedAmount;
    }

    public MoneyTO getCashAmount() {
        return cashAmount;
    }

    public MoneyTO getTotalWorth() {
        return totalWorth;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public Boolean getVisible() {
        return visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AccountBalanceTO that))
            return false;
        return Objects.equals(id, that.id) && Objects.equals(currency, that.currency) && Objects.equals(type,
            that.type) && Objects.equals(name, that.name) && Objects.equals(icon, that.icon) && Objects.equals(
            investmentState, that.investmentState) && Objects.equals(amount, that.amount) && Objects.equals(reservedAmount,
            that.reservedAmount) && Objects.equals(cashAmount, that.cashAmount) && Objects.equals(totalWorth,
            that.totalWorth) && Objects.equals(creationTime, that.creationTime) && Objects.equals(modificationTime,
            that.modificationTime) && Objects.equals(visible, that.visible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, type, name, icon, investmentState, amount, reservedAmount, cashAmount, totalWorth, creationTime,
            modificationTime, visible);
    }
}
