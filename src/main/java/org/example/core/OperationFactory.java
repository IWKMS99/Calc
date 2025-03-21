package org.example.core;

import org.example.core.operations.*;

public class OperationFactory {
    public static Operation getOperation(char symbol) {
        return switch (symbol) {
            case '+' -> new Addition();
            case '-' -> new Subtract();
            case '*' -> new Multiply();
            case '/' -> new Division();
            case '^' -> new Power();
            case '%' -> new Percent();
            case 's' -> new Root();
            case '>' -> new GreaterThan();
            case '<' -> new LessThan();
            default -> throw new IllegalArgumentException("Unknown operation: " + symbol);
        };
    }
}