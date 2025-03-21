package org.example.ui;

import org.example.core.Calculator;
import org.example.core.OperationFactory;
import org.example.core.Operation;
import org.example.model.ParsedExpression;
import org.example.parser.ExpressionParser;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private final Calculator calculator;
    private final ExpressionParser parser;
    private final Scanner scanner;

    public ConsoleUI() {
        this.calculator = new Calculator();
        this.parser = new ExpressionParser();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.print("""
                         ------------------------------------
                                Welcome to Calculator!
                         ------------------------------------
                         """);
        while (true) {
            System.out.println("Enter expression or 'q' to quit:");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("q")) break;
            processInput(input);
        }
    }

    private void processInput(String input) {
        try {
            ParsedExpression expr = parser.parse(input);
            Operation op = OperationFactory.getOperation(expr.operation());
            BigDecimal result = calculator.calculate(expr.number1(), expr.number2(), op);
            System.out.println(expr.number1() + " " + op.getSymbol() + " " + expr.number2() + " = " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}