package ua.nechay.transfermaker.dto.wise;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class MoneyTO {
    @JsonProperty private double value;
    @JsonProperty private String currency;

    public MoneyTO(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public MoneyTO() {
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MoneyTO moneyTO))
            return false;
        return Double.compare(moneyTO.value, value) == 0 && Objects.equals(currency, moneyTO.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }


}
