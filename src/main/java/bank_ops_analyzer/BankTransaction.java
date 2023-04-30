package bank_ops_analyzer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BankTransaction {
    private final LocalDate ld;
    private final double amount;
    private final String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankTransaction that = (BankTransaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (ld != null ? !ld.equals(that.ld) : that.ld != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ld != null ? ld.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + ld +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
