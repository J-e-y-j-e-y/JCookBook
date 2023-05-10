package bank_ops_analyzer;

import java.time.Month;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter{
    @Override
    public boolean test(BankTransaction bt) {
        return Month.FEBRUARY.equals(bt.getLd().getMonth())
                && bt.getAmount() >= 1000;
    }
}
