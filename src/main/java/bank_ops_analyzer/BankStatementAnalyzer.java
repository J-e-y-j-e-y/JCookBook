package bank_ops_analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(String filename, final BankStatementParser
                               bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES + filename);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private void collectSummary(BankStatementProcessor bankStatementProcessor){

        System.out.println("Total for all transactions = " + bankStatementProcessor.summarizeTransactions(((accumulator, bt) -> accumulator + bt.getAmount())));

        System.out.println("Transactions in January " + bankStatementProcessor.findTransactions(bt -> bt.getLd().getMonth() == Month.FEBRUARY));

        System.out.println("Total salary " + bankStatementProcessor.calculateTotalForCategory("Salary"));

        BankTransactionFilter filter = new BankTransactionIsInFebruaryAndExpensive();
        List<BankTransaction> transactionsFebAndTotalGE1000 = bankStatementProcessor.findTransactions(filter);
        System.out.println("Transactions made in February and total >= 1000 " + transactionsFebAndTotalGE1000);


        List<BankTransaction> transactionsMarchAndCinema = bankStatementProcessor.findTransactions(bt ->
                            bt.getLd().getMonth() == Month.MARCH
                            && bt.getDescription().equals("Cinema"));
        System.out.println("Transactions made in March and category = 'Cinema' " + transactionsMarchAndCinema);
    }
}
