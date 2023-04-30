package bank_ops_analyzer;

import lombok.AllArgsConstructor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BankStatementProcessor {
    private List<BankTransaction> bankTransactions;

    public double calculateTotalAmount(){
        double total = 0;
        for(BankTransaction bt : bankTransactions){
            total += bt.getAmount();
        }
        return total;
    }

    public List<BankTransaction> selectInMonth(Month month){
        List<BankTransaction> selected = new ArrayList<>();

        for(BankTransaction bt : bankTransactions){
            if(month.equals(bt.getLd().getMonth()))
                selected.add(bt);
        }

        return selected;
    }

    public double calculateTotalForCategory(String category){
        double total = 0;
        for(BankTransaction bt : bankTransactions){
            if(category.equals(bt.getDescription()))
                total += bt.getAmount();
        }
        return total;
    }
}
