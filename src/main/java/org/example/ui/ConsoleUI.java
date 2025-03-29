package org.example.ui;

import org.example.core.OperationFactory;
import org.example.core.Operation;
import org.example.dto.Token;
import org.example.parser.PostfixEvaluator;
import org.example.parser.ShuntingYardConverter;
import org.example.parser.Tokenizer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private final Scanner scanner;
    private final Tokenizer tokenizer;
    private final ShuntingYardConverter converter;
    private final PostfixEvaluator evaluator;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.tokenizer = new Tokenizer();
        this.converter = new ShuntingYardConverter();
        this.evaluator = new PostfixEvaluator();
    }

    public void run() {
        System.out.print("""
                         ------------------------------------
                                Welcome to Calculator!
                         ------------------------------------
                         Type 'help' to see available commands
                         """);
        while (true) {
            System.out.println("Enter expression or 'q' to quit:");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("q")) break;
            if (input.equals("help")) {
                showHelp();
                continue;
            }
            processInput(input);
        }
    }

    private void showHelp() {
        System.out.print("""
                         -------- Calculator Help --------
                               Available operations:
                         """);

        Set<String> operators = OperationFactory.getSupportedOperators();
        for (String symbol : operators) {
            String description = getOperationDescription(symbol);
            System.out.println(" " + symbol + " - " + description);
        }
        System.out.println("\nUsage: Enter a mathematical expression (e.g., 2 + 3 * 4, (5 - 2) / 3)\n");
    }

    private String getOperationDescription(String symbol) {
        Operation operation = OperationFactory.getOperation(symbol);
        if (operation != null) {
            return operation.getDescription();
        }
        return "Operation " + symbol;
    }

    private void processInput(String input) {
        try {
            List<Token> tokens = tokenizer.tokenize(input);
            List<Token> postfixTokens = converter.convertToPostfix(tokens);
            BigDecimal result = evaluator.evaluate(postfixTokens);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}