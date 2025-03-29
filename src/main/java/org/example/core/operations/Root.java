package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Root implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Root of degree zero is not allowed!");
            return null;
        }

        try {
            double numberDouble = number1.doubleValue();
            double rootDegreeDouble = number2.doubleValue();

            if (numberDouble < 0) {
                if (rootDegreeDouble % 2 == 0) {
                    System.out.println("Even root of negative number is not allowed!");
                    return null;
                }
                double result = -Math.pow(-numberDouble, 1.0 / rootDegreeDouble);
                return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE);
            }

            double result = Math.pow(numberDouble, 1.0 / rootDegreeDouble);
            return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input for root operation");
            return null;
        }
    }

    @Override
    public String getSymbol() { return "s"; }

    @Override
    public String getDescription() {
        return "Root: Calculates the nth root of a number. The first number is the base, the second is the root degree. Example: 27 s 3 = 3 (cube root of 27)";
    }
}
