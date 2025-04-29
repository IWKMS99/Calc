package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import static org.example.core.OperationFactory.isOperationSupported;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        int currentPosition = 0;
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            if (Character.isWhitespace(currentChar)) {
                currentPosition++;
                continue;
            }

            if (Character.isDigit(currentChar) || currentChar == '.') {
                TokenizationResult result = readNumber(input, currentPosition);
                tokens.add(new Token(Type.NUMBER, result.value()));
                currentPosition = result.newPosition();
                continue;
            }

            String potentialOperator = String.valueOf(currentChar);
            if (isOperationSupported(potentialOperator)) {
                tokens.add(new Token(Type.OPERATOR, potentialOperator));
                currentPosition++;
                continue;
            }

            if (currentChar == '(') {
                tokens.add(new Token(Type.LEFT_BRACKET, "("));
                currentPosition++;
                continue;
            }

            if (currentChar == ')') {
                tokens.add(new Token(Type.RIGHT_BRACKET, ")"));
                currentPosition++;
                continue;
            }

            throw new IllegalArgumentException("Unknown symbol: '" + currentChar + "' at index " + currentPosition);
        }
        return tokens;
    }

    private TokenizationResult readNumber(String input, int startPosition) {
        StringBuilder numberBuilder = new StringBuilder();
        boolean hasDecimalPoint = false;
        int currentPosition = startPosition;

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);
            if (Character.isDigit(currentChar)) {
                numberBuilder.append(currentChar);
            } else if (currentChar == '.') {
                if (hasDecimalPoint) {
                    throw new IllegalArgumentException("Multiple decimal points in number starting at index " + startPosition);
                }
                numberBuilder.append('.');
                hasDecimalPoint = true;
            } else {
                break;
            }
            currentPosition++;
        }

        String numberStr = getString(numberBuilder, startPosition);
        return new TokenizationResult(numberStr, currentPosition);
    }

    private static String getString(StringBuilder numberBuilder, int numberStart) {
        String numberStr = numberBuilder.toString();
        if (numberStr.equals(".")) {
            throw new IllegalArgumentException("Invalid number format: '.' at index " + numberStart);
        }
        if (numberStr.endsWith(".")) {
            throw new IllegalArgumentException("Number cannot end with a decimal point: " + numberStr + " at index " + numberStart);
        }
        if (numberStr.startsWith(".") && numberStr.length() == 1) {
            throw new IllegalArgumentException("Invalid number format: '.' at index " + numberStart);
        }
        return numberStr;
    }

    /**
     * Represents the result of tokenizing a single element from the input
     * @param value The extracted token value
     * @param newPosition The new position in the input string after processing the token
     */
    private record TokenizationResult(String value, int newPosition) {}
}