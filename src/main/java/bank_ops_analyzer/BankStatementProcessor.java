package bank_ops_analyzer;

import lombok.AllArgsConstructor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BankStatementProcessor {
    private List<BankTransaction> bankTransactions;

    public double summarizeTransactions(BankTransactionSummarizer summarizer){
        double total = 0;
        for(BankTransaction bt : bankTransactions){
            total += summarizer.summarize(total, bt);
        }
        return total;
    }

    public double calculateTotalForCategory(String category){
        return summarizeTransactions(((accumulator, bt) ->
                                    bt.getDescription().equals(category) ?
                                    accumulator + bt.getAmount() : accumulator));
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter filter){
        List<BankTransaction> found = new ArrayList<>();

        for(BankTransaction bt : bankTransactions){
            if(filter.test(bt))
                found.add(bt);
        }
        return found;
    }
}
