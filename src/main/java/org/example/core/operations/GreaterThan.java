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
}