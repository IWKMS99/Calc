package org.example.parser;

import org.example.model.ParsedExpression;

import java.math.BigDecimal;

public class ExpressionParser {

    public ParsedExpression parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String trimmedInput = input.trim();
        int operatorIndex = findOperatorIndex(trimmedInput);

        if (operatorIndex == -1) {
            return null;
        }

        String number1Str = trimmedInput.substring(0, operatorIndex).trim();
        String operationStr = String.valueOf(trimmedInput.charAt(operatorIndex));
        String number2Str = trimmedInput.substring(operatorIndex + 1).trim();

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            return null;
        }

        if (containsOperator(number2Str)) {
            return null;
        }

        BigDecimal number1 = parseNumber(number1Str);
        BigDecimal number2 = parseNumber(number2Str);
        char operation = operationStr.charAt(0);

        return new ParsedExpression(number1, number2, operation);
    }

    private int findOperatorIndex(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isOperator(c) && !(i == 0 && (c == '-' || c == '+'))) {
                return i;
            }
        }
        return -1;
    }

    private boolean isOperator(char c) {
        return "+-*/^%s".indexOf(c) != -1;
    }

    private boolean containsOperator(String input) {
        for (char c : input.toCharArray()) {
            if (isOperator(c) && !(c == '-' || c == '+')) {
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