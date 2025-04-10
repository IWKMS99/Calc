package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Division implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        try {
            return number1.divide(number2, SCALE, ROUNDING_MODE);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error during division: " + e.getMessage());
        }
    }

    @Override
    public String getSymbol() { return "/"; }

    @Override
    public String getDescription() {
        return "Division: Divides the first number by the second. Returns null for division by zero. Example: 10 / 2 = 5";
    }
}
