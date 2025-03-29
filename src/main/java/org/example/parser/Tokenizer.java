package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import static org.example.core.OperationFactory.isOperationSupported;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize (String input) {
        List<Token> tokens = new ArrayList<Token>();
        int start = 0;
        while (start < input.length()) {
            char currentChar = input.charAt(start);

            if (Character.isWhitespace(currentChar)) {
                start++;
                continue;
            }

            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder numberBuilder = new StringBuilder();
                boolean hasDecimalPoint = false;
                while (start < input.length() && (Character.isDigit(currentChar) || currentChar == '.')) {
                    if (currentChar == '.') {
                        if (hasDecimalPoint) {
                            throw new IllegalArgumentException("Multiple decimal points in number");
                        }
                        hasDecimalPoint = true;
                    }
                    numberBuilder.append(currentChar);
                    start++;
                    if (start < input.length()) currentChar = input.charAt(start);
                }
                tokens.add(new Token(Type.NUMBER, numberBuilder.toString()));
                continue;
            }

            if (isOperationSupported(String.valueOf(currentChar))) {
                tokens.add(new Token(Type.OPERATOR, String.valueOf(currentChar)));
                start++;
                continue;
            }

            if (currentChar == '(') {
                tokens.add(new Token(Type.LEFT_BRACKET, "("));
                start++;
                continue;
            }

            if (currentChar == ')') {
                tokens.add(new Token(Type.RIGHT_BRACKET, ")"));
                start++;
                continue;
            }

            throw new IllegalArgumentException("Unknown symbol: " + currentChar);
        }
        return tokens;
    }
}
