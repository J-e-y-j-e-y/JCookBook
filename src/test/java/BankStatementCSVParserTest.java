import bank_ops_analyzer.BankStatementCSVParser;
import bank_ops_analyzer.BankStatementParser;
import bank_ops_analyzer.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest {
  private final BankStatementParser bankStatementParser
          = new BankStatementCSVParser();
  private static final double DELTA = 1e-15;

  @Test
  public void parseOneLineCorrectly(){
      final String line = "01-03-2023,1873,Royalties";
      final BankTransaction expectedResult = new BankTransaction(
              LocalDate.of(2023, Month.MARCH, 1),
              1873,
              "Royalties"
      );

      final BankTransaction result = bankStatementParser.parseFrom(line);
      final double zeroTotal = 0;

      Assert.assertEquals(result, expectedResult);
      Assert.assertEquals(result.getLd(), expectedResult.getLd());
      Assert.assertEquals(result.getAmount(), zeroTotal, DELTA);
      Assert.assertEquals(result.getDescription(), expectedResult.getDescription());
  }
}
