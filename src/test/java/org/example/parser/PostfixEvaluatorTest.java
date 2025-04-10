package org.example.parser;

import org.example.config.Config;
import org.example.dto.Token;
import org.example.dto.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PostfixEvaluatorTest {

    private final PostfixEvaluator evaluator = new PostfixEvaluator();
    private final Tokenizer tokenizer = new Tokenizer();
    private final ShuntingYardConverter converter = new ShuntingYardConverter();

    private List<Token> getPostfix(String infix) {
        return converter.convertToPostfix(tokenizer.tokenize(infix));
    }

    private BigDecimal expectedDecimal(String value) {
        try {
            return new BigDecimal(value).setScale(Config.SCALE, Config.ROUNDING_MODE);
        } catch (NumberFormatException e) {
            return new BigDecimal(value);
        }
    }


    static Stream<Arguments> evaluationProvider() {
        return Stream.of(
                Arguments.of("2 + 3", "5"),
                Arguments.of("2 + 3 * 4", "14"),
                Arguments.of("( 2 + 3 ) * 4", "20"),
                Arguments.of("10 / 4", "2.5"),
                Arguments.of("8 / 4 * 2", "4"),
                Arguments.of("2^3", "8.0"),
                Arguments.of("27 s 3", "3.0"),
                Arguments.of("200 % 10", "20.0"),
                Arguments.of("5 > 3", "1"),
                Arguments.of("3 > 5", "0"),
                Arguments.of("3 < 5", "1"),
                Arguments.of("5 < 3", "0"),
                Arguments.of("1 + 2 * 3 - 4 / 2", "5")
        );
    }

    @ParameterizedTest
    @MethodSource("evaluationProvider")
    void testEvaluate_ValidExpressions(String infixExpression, String expectedResultStr) {
        List<Token> postfixTokens = getPostfix(infixExpression);
        BigDecimal expectedResult = expectedDecimal(expectedResultStr);
        BigDecimal actualResult = evaluator.evaluate(postfixTokens);
        if (expectedResult.scale() == actualResult.scale() || expectedResult.compareTo(BigDecimal.ZERO) == 0 || expectedResult.compareTo(BigDecimal.ONE) == 0) {
            assertEquals(expectedResult, actualResult);
        } else {
            assertEquals(0, expectedResult.compareTo(actualResult),
                    "Expected: " + expectedResult + " but got: " + actualResult);
        }
    }


    @Test
    void testEvaluate_DivisionByZero_ThrowsArithmeticException() {
        List<Token> postfix = getPostfix("10 / 0");
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> evaluator.evaluate(postfix));
        assertEquals("Division by zero is not allowed!", exception.getMessage());
    }

    @Test
    void testEvaluate_InsufficientOperands_ThrowsIllegalArgumentException() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "5"),
                new Token(Type.OPERATOR, "*")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(postfix));
        assertTrue(exception.getMessage().contains("not enough operands for operator '*'"));
    }

    @Test
    void testEvaluate_TooManyValuesLeft_ThrowsIllegalArgumentException() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "5"),
                new Token(Type.NUMBER, "3")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(postfix));
        assertTrue(exception.getMessage().contains("values left on stack instead of 1"));
    }

    @Test
    void testEvaluate_InvalidNumberToken_ThrowsIllegalArgumentException() {
        List<Token> postfix = List.of(new Token(Type.NUMBER, "invalid"));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(postfix));
        assertTrue(exception.getMessage().contains("Invalid number format in token"));
    }

    @Test
    void testEvaluate_EvenRootOfNegative_ThrowsArithmeticException() {
        List<Token> postfix = getPostfix("-16 s 2");
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> evaluator.evaluate(postfix));
        assertEquals("Even root of a negative number is not allowed!", exception.getMessage());
    }
}