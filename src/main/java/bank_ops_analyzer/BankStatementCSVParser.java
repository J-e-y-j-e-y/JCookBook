package bank_ops_analyzer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DT_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(final String line){
        String[] columns = line.split(",");

        LocalDate ld = LocalDate.parse(columns[0], DT_FORMATTER);
        double amount = Double.parseDouble(columns[1]);
        String description = columns[2];

        return new BankTransaction(ld, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines){
        List<BankTransaction> bankTransactions = new ArrayList<>();

        for(String line : lines){
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }
}
