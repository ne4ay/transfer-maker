package ua.nechay.transfermaker.dto.wise;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author anechaev
 * @since 25.01.2023
 */
public class NumeratedMoneyTO extends MoneyTO {
    @JsonProperty private long id;

    public NumeratedMoneyTO(double value, String currency, long id) {
        super(value, currency);
        this.id = id;
    }

    public NumeratedMoneyTO() {}

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof NumeratedMoneyTO that))
            return false;
        if (!super.equals(o))
            return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
