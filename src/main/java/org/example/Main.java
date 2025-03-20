package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

                String[] parts = parseExpression(input);
                if (parts == null || parts.length == 0) {
                    System.out.println("Invalid format. Type '?' for help.");
                    continue;
                }

                double number1 = parseNumber(parts[1]);
                char operation = parts[0].charAt(0);
                double number2 = parseNumber(parts[2]);

                double result = Calculator.calculate(number1, number2, operation);
                System.out.println("Result: " + result);

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid expression. Type '?' for help.");
            }
        }
        scanner.close();
    }

    private static double parseNumber(String input) {
        String normalized = input.replace(',', '.');
        return Double.parseDouble(normalized);
    }

    private static String[] parseExpression(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ("+-*/^%s".indexOf(c) != -1) {
                if (i == 0 && (c == '-' || c == '+')) {
                    continue;
                }
                String num1 = input.substring(0, i).trim();
                String op = String.valueOf(c);
                String num2 = input.substring(i + 1).trim();
                if (num1.isEmpty() || num2.isEmpty()) {
                    return null;
                }
                for (int j = 0; j < num2.length(); j++) {
                    char ch = num2.charAt(j);
                    if (j == 0 && (ch == '-' || ch == '+')) {
                        continue;
                    }
                    if ("+-*/^%s".indexOf(ch) != -1) {
                        return null;
                    }
                }
                return new String[]{op, num1, num2};
            }
        }
        return null;
    }
} 