package bank_ops_analyzer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(filename, bankStatementParser);
    }
}
