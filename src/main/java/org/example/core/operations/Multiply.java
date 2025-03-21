package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

public class Multiply implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2);
    }

    @Override
    public char getSymbol() { return '*'; }
}
