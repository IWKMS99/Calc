package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    private static final int SCALE = 10;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal calculate(BigDecimal number1, BigDecimal number2, char operation) {
        return switch (operation) {
            case '+' -> add(number1, number2);
            case '-' -> subtract(number1, number2);
            case '*' -> multiply(number1, number2);
            case '/' -> divide(number1, number2);
            case '^' -> power(number1, number2);
            case '%' -> percent(number1, number2);
            case 's' -> root(number1, number2);
            default -> {
                System.out.println("Invalid operation. Type '?' for help.");
                yield null;
            }
        };
    }

    private static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    private static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    private static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    private static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Division by zero is not allowed!");
            return null;
        }
        return a.divide(b, SCALE, ROUNDING_MODE);
    }

    private static BigDecimal power(BigDecimal base, BigDecimal exponent) {
        try {
            double baseDouble = base.doubleValue();
            double exponentDouble = exponent.doubleValue();
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

    private static BigDecimal percent(BigDecimal number, BigDecimal percentage) {
        return number.multiply(percentage)
                .divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
    }

    private static BigDecimal root(BigDecimal number, BigDecimal rootDegree) {
        if (rootDegree.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Root of degree zero is not allowed!");
            return null;
        }

        try {
            double numberDouble = number.doubleValue();
            double rootDegreeDouble = rootDegree.doubleValue();

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
}