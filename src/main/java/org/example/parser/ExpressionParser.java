package org.example.parser;

import org.example.core.OperationFactory;
import org.example.model.ParsedExpression;

import java.math.BigDecimal;
import java.util.Set;

public class ExpressionParser {

    public ParsedExpression parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String trimmedInput = input.trim();
        int operatorIndex = findOperatorIndex(trimmedInput);

        if (operatorIndex == -1) {
            throw new IllegalArgumentException("The operator was not found in the expression: " + trimmedInput);
        }

        String number1Str = trimmedInput.substring(0, operatorIndex).trim();
        String operationStr = String.valueOf(trimmedInput.charAt(operatorIndex));
        String number2Str = trimmedInput.substring(operatorIndex + 1).trim();

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            throw new IllegalArgumentException("One of the operands is missing from the expression: " + trimmedInput);
        }

        if (containsOperatorInOperand(number2Str)) {
            throw new IllegalArgumentException("More than one operator is detected in the expression: " + trimmedInput);
        }

        BigDecimal number1 = parseNumber(number1Str);
        BigDecimal number2 = parseNumber(number2Str);
        char operation = operationStr.charAt(0);

        return new ParsedExpression(number1, number2, operation);
    }

    private int findOperatorIndex(String input) {
        Set<Character> supportedOperators = OperationFactory.getSupportedOperators();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (supportedOperators.contains(c) && !(i == 0 && (c == '-' || c == '+'))) {
                return i;
            }
        }
        return -1;
    }

    private boolean containsOperatorInOperand(String input) {
        Set<Character> supportedOperators = OperationFactory.getSupportedOperators();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (supportedOperators.contains(c) && !(i == 0 && (c == '-' || c == '+'))) {
                return true;
            }
        }
        return false;
    }

    private BigDecimal parseNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String normalized = input.replace(',', '.').replaceAll("\\s+", "");

        try {
            return new BigDecimal(normalized);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + input, e);
        }
    }
}