package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Power implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        double baseDouble = number1.doubleValue();
        double exponentDouble;
        try {
            exponentDouble = number2.doubleValue();
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Exponent too large or not exact for power operation: " + number2);
        } catch (NumberFormatException e) {
            throw new ArithmeticException("Invalid input for power operation");
        }


        double result = Math.pow(baseDouble, exponentDouble);

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException("Invalid power operation result (NaN or Infinity) for " + number1 + " ^ " + number2);
        }

        return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE);
    }


    @Override
    public String getSymbol() { return "^"; }

    @Override
    public String getDescription() {
        return "Power: Raises the first number to the power of the second number. Example: 2 ^ 3 = 8";
    }
}
