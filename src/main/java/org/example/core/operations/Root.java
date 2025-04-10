package org.example.core.operations;

import java.math.BigDecimal;
import org.example.core.Operation;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;

public class Root implements Operation {
    @Override
    public BigDecimal apply(BigDecimal number1, BigDecimal number2) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Root of degree zero is not allowed!");
        }

        double numberDouble = number1.doubleValue();
        double rootDegreeDouble = getRootDegreeDouble(number2, numberDouble);

        double result;
        if (numberDouble < 0) {
            result = -Math.pow(-numberDouble, 1.0 / rootDegreeDouble);
        } else {
            result = Math.pow(numberDouble, 1.0 / rootDegreeDouble);
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException("Invalid root operation result (NaN or Infinity) for " + number1 + " s " + number2);
        }

        return new BigDecimal(result).setScale(SCALE, ROUNDING_MODE);
    }

    private static double getRootDegreeDouble(BigDecimal number2, double numberDouble) {
        double rootDegreeDouble;
        try {
            rootDegreeDouble = number2.doubleValue();
            if (rootDegreeDouble == 0) {
                throw new ArithmeticException("Root of degree zero is not allowed!");
            }
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Root degree too large or not exact: " + number2);
        } catch (NumberFormatException e) {
            throw new ArithmeticException("Invalid input for root operation");
        }


        if (numberDouble < 0 && rootDegreeDouble % 2 == 0) {
            throw new ArithmeticException("Even root of a negative number is not allowed!");
        }
        return rootDegreeDouble;
    }

    @Override
    public String getSymbol() { return "s"; }

    @Override
    public String getDescription() {
        return "Root: Calculates the nth root of a number. The first number is the base, the second is the root degree. Example: 27 s 3 = 3 (cube root of 27)";
    }
}
