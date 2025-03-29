package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

public class LessThan implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        return number1.compareTo(number2) < 0 ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    @Override
    public String getSymbol() {
        return "<";
    }

    @Override
    public String getDescription() {
        return "Less Than: Checks if the first number is less than the second. Returns 1 if true, 0 if false. Example: 3 < 5 = 1";
    }
}