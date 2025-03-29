package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

public class Addition implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    @Override
    public String getSymbol() { return "+"; }

    @Override
    public String getDescription() {
        return "Addition: Adds two numbers together. Example: 5 + 3 = 8";
    }
}
