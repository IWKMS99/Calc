package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Percent implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2)
                .divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
    }

    @Override
    public char getSymbol() { return '%'; }

    @Override
    public String getDescription() {
        return "Percent: Calculates the specified percentage of a number. Example: 200 % 10 = 20 (10% of 200)";
    }
}
