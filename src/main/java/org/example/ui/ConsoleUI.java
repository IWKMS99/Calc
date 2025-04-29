package org.example.ui;

import org.example.core.Operation;
import org.example.core.OperationFactory;
import org.example.engine.CalculatorEngine;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private final Scanner scanner;
    private final OperationFactory operationFactory;
    private final CalculatorEngine engine; // Add CalculatorEngine field

    public ConsoleUI(Scanner scanner, OperationFactory operationFactory, CalculatorEngine engine) {
        this.scanner = scanner;
        this.operationFactory = operationFactory;
        this.engine = engine;
    }

    public static ConsoleUI createDefault() {
        return new ConsoleUI(
                new Scanner(System.in),
                new OperationFactory(),
                new CalculatorEngine()
        );
    }


    public void run() {
        System.out.print("""
                         ------------------------------------
                                Welcome to Calculator!
                         ------------------------------------
                         Type 'help' to see available commands
                         """);
        while (true) {
            System.out.println("\nEnter expression or 'q' to quit:");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("q")) break;
            if (input.equals("help")) {
                showHelp();
                continue;
            }
            if (input.isBlank()) {
                continue;
            }
            processInput(input);
        }
        scanner.close();
        System.out.println("Calculator shutting down. Goodbye!");
    }

    private void showHelp() {
        System.out.print("""
                         -------- Calculator Help --------
                               Available operations:
                         """);

        Set<String> operators = operationFactory.getSupportedOperators();
        for (String symbol : operators) {
            Operation operation = operationFactory.getOperation(symbol);
            System.out.println(" " + symbol + " - " + operation.getDescription());
        }
        System.out.println("\nUsage: Enter a mathematical expression (e.g., 2 + 3 * 4, (5 - 2) / 3)\n");
        System.out.println("Example with root: 27 s 3 (calculates cube root of 27)");
        System.out.println("Example with power: 2 ^ 3 (calculates 2 to the power of 3)");
        System.out.println("Example with percent: 200 % 10 (calculates 10% of 200)");
        System.out.println("Example with comparison: 5 > 3 (returns 1 if true, 0 if false)");
        System.out.println("---------------------------------");
    }


    private void processInput(String input) {
        try {
            BigDecimal result = engine.evaluate(input);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid input -> " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Error: Calculation error -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred -> " + e.getMessage());
        }
    }
}