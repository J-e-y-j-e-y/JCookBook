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

        System.out.println("Total for all transactions = " + bankStatementProcessor.calculateTotalAmount());

        System.out.println("Transactions in January " + bankStatementProcessor.selectInMonth(Month.JANUARY));

        System.out.println("Total salary " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
