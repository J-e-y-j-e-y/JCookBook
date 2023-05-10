package bank_ops_analyzer;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bt);
}
