package org.example.parser;

import org.example.model.ParsedExpression;

import java.math.BigDecimal;

public class ExpressionParser {
    public ParsedExpression parse(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ("+-*/^%s".indexOf(c) != -1) {
                if (i == 0 && (c == '-' || c == '+')) continue;

                String number1Str = input.substring(0, i).trim();
                String operationStr = String.valueOf(c);
                String number2Str = input.substring(i + 1).trim();

                if (number1Str.isEmpty() || number2Str.isEmpty()) return null;

                for (int j = 0; j < number2Str.length(); j++) {
                    char ch = number2Str.charAt(j);
                    if (j == 0 && (ch == '-' || ch == '+')) continue;
                    if ("+-*/^%s".indexOf(ch) != -1) return null;
                }

                BigDecimal number1 = parseNumber(number1Str);
                BigDecimal number2 = parseNumber(number2Str);

                char operation = operationStr.charAt(0);
                return new ParsedExpression(number1, number2, operation);
            }
        }
        return null;
    }

    private BigDecimal parseNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String normalized = input.replace(',', '.')
                                 .replaceAll("\\s+", "");

        try {
            return new BigDecimal(normalized);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + input, e);
        }
    }
}