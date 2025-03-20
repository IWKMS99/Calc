package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser parser = new ExpressionParser();

        final String helpMessage = """
                                   Available operations: +, -, *, /, ^, %, s (sqrt)
                                   % calculates x% of y (e.g., 50 % 200 = 100)
                                   Fractional numbers are supported (for example, 2.5 * 3.7 or 2,7 * 3,7)
                                   Example: 5 + 3
                                   """;

        System.out.println("Welcome to Calculator! ");
        System.out.println(helpMessage);

        while (true) {
            try {
                System.out.println("Enter an expression, 'q' to quit, or '?' for help: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                } else if (input.equalsIgnoreCase("?")) {
                    System.out.println(helpMessage);
                    continue;
                }

                ParsedExpression parsedExpression = parser.parse(input);
                if (parsedExpression == null) {
                    System.out.println("Invalid format. Type '?' for help.");
                    continue;
                }

                BigDecimal result = Calculator.calculate(parsedExpression.number1(), parsedExpression.number2(), parsedExpression.operation());
                System.out.println("Result: " + result);

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid expression. Type '?' for help.");
            }
        }
        scanner.close();
    }
}