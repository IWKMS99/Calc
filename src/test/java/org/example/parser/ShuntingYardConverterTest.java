package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ShuntingYardConverterTest {

    private final ShuntingYardConverter converter = new ShuntingYardConverter();
    private final Tokenizer tokenizer = new Tokenizer();

    private List<Token> tokenize(String expression) {
        return tokenizer.tokenize(expression);
    }

    static Stream<Arguments> expressionProvider() {
        return Stream.of(
                Arguments.of("2 + 3", List.of(
                        new Token(Type.NUMBER, "2"), new Token(Type.NUMBER, "3"), new Token(Type.OPERATOR, "+")
                )),
                Arguments.of("2 + 3 * 4", List.of(
                        new Token(Type.NUMBER, "2"), new Token(Type.NUMBER, "3"), new Token(Type.NUMBER, "4"),
                        new Token(Type.OPERATOR, "*"), new Token(Type.OPERATOR, "+")
                )),
                Arguments.of("( 2 + 3 ) * 4", List.of(
                        new Token(Type.NUMBER, "2"), new Token(Type.NUMBER, "3"), new Token(Type.OPERATOR, "+"),
                        new Token(Type.NUMBER, "4"), new Token(Type.OPERATOR, "*")
                )),
                Arguments.of("3 + 4 * 2 / ( 1 - 5 ) ^ 2", List.of(
                        new Token(Type.NUMBER, "3"), new Token(Type.NUMBER, "4"), new Token(Type.NUMBER, "2"),
                        new Token(Type.OPERATOR, "*"), new Token(Type.NUMBER, "1"), new Token(Type.NUMBER, "5"),
                        new Token(Type.OPERATOR, "-"), new Token(Type.NUMBER, "2"), new Token(Type.OPERATOR, "^"),
                        new Token(Type.OPERATOR, "/"), new Token(Type.OPERATOR, "+")
                )),
                Arguments.of("8 / 4 * 2", List.of(
                        new Token(Type.NUMBER, "8"), new Token(Type.NUMBER, "4"), new Token(Type.OPERATOR, "/"),
                        new Token(Type.NUMBER, "2"), new Token(Type.OPERATOR, "*")
                )),
                Arguments.of("5 > 3 + 1", List.of(
                        new Token(Type.NUMBER, "5"), new Token(Type.NUMBER, "3"), new Token(Type.NUMBER, "1"),
                        new Token(Type.OPERATOR, "+"), new Token(Type.OPERATOR, ">")
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("expressionProvider")
    void testConvertToPostfix_ValidExpressions(String infixExpression, List<Token> expectedPostfix) {
        List<Token> infixTokens = tokenize(infixExpression);
        List<Token> actualPostfix = converter.convertToPostfix(infixTokens);
        assertEquals(expectedPostfix, actualPostfix);
    }


    @Test
    void testConvertToPostfix_MismatchedBrackets_MissingClosing() {
        List<Token> infixTokens = tokenize("( 2 + 3");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToPostfix(infixTokens));
        assertEquals("Mismatched brackets", exception.getMessage());
    }

    @Test
    void testConvertToPostfix_MismatchedBrackets_MissingOpening() {
        List<Token> infixTokens = tokenize("2 + 3 )");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToPostfix(infixTokens));
        assertEquals("Mismatched brackets", exception.getMessage());
    }

    @Test
    void testConvertToPostfix_MismatchedBrackets_ExtraOpening() {
        List<Token> infixTokens = tokenize("( ( 2 + 3 )");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToPostfix(infixTokens));
        assertEquals("Mismatched brackets", exception.getMessage());
    }

    @Test
    void testConvertToPostfix_MismatchedBrackets_ExtraClosing() {
        List<Token> infixTokens = tokenize("( 2 + 3 ) )");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToPostfix(infixTokens));
        assertEquals("Mismatched brackets", exception.getMessage());
    }
}