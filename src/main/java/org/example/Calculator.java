package org.example;

public class Calculator {
    public static double calculate(double number1, double number2, char operation) {
        return switch (operation) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> {
                if (number2 == 0) {
                    System.out.println("Division by zero is not allowed!");
                    yield Double.NaN;
                }
                yield number1 / number2;
            }
            case '^' -> Math.pow(number1, number2);
            case '%' -> (number1 * number2) / 100;
            case 's' -> {
                if (number1 < 0 && number2 % 2 != 0) {
                    yield -Math.pow(-number1, 1.0 / number2);
                } else if (number1 < 0 && number2 % 2 == 0) {
                    System.out.println("It is impossible to calculate the root of an even power from a negative number!");
                    yield Double.NaN;
                } else {
                    yield Math.pow(number1, 1.0 / number2);
                }
            }
            default -> {
                System.out.println("Invalid operation. Type '?' for help.");
                yield Double.NaN;
            }
        };
    }
}