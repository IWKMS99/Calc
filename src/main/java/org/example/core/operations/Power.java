package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Power implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        try {
            double baseDouble = number1.doubleValue();
            double exponentDouble = number2.doubleValue();
            double result = Math.pow(baseDouble, exponentDouble);

            if (Double.isNaN(result) || Double.isInfinite(result)) {
                System.out.println("Invalid power operation");
                return null;
            }

            return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for power operation");
            return null;
        }
    }

    @Override
    public String getSymbol() { return "^"; }

    @Override
    public String getDescription() {
        return "Power: Raises the first number to the power of the second number. Example: 2 ^ 3 = 8";
    }
}
