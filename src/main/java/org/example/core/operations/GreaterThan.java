package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

public class GreaterThan implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        return number1.compareTo(number2) > 0 ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    @Override
    public char getSymbol() {
        return '>';
    }

    @Override
    public String getDescription() {
        return "Greater Than: Checks if the first number is greater than the second. Returns 1 if true, 0 if false. Example: 5 > 3 = 1";
    }
}