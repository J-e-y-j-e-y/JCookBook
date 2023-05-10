package bank_ops_analyzer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryStatistics {
    private final double sum;
    private final double max;
    private final double min;
    private final double average;
}
