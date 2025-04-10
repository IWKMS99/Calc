package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;


class PostfixEvaluatorTest {

    private final PostfixEvaluator evaluator = new PostfixEvaluator();

    @Test
    void testEvaluate_SimpleAddition() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "2"),
                new Token(Type.NUMBER, "3"),
                new Token(Type.OPERATOR, "+")
        );
        assertEquals(new BigDecimal("5"), evaluator.evaluate(postfix));
    }

    @Test
    void testEvaluate_ComplexExpression() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "5"),
                new Token(Type.NUMBER, "10"),
                new Token(Type.NUMBER, "3"),
                new Token(Type.OPERATOR, "+"),
                new Token(Type.OPERATOR, "*")
        );
        assertEquals(new BigDecimal("65"), evaluator.evaluate(postfix));
    }

    @Test
    void testEvaluate_DivisionByZero_ThrowsArithmeticException() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "10"),
                new Token(Type.NUMBER, "0"),
                new Token(Type.OPERATOR, "/")
        );
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            evaluator.evaluate(postfix);
        });
        assertEquals("Division by zero is not allowed!", exception.getMessage());
    }

    @Test
    void testEvaluate_InsufficientOperands_ThrowsIllegalArgumentException() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "10"),
                new Token(Type.OPERATOR, "+")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluate(postfix);
        });
        assertTrue(exception.getMessage().contains("not enough operands for operator '+'"));
    }

    @Test
    void testEvaluate_TooManyValuesLeft_ThrowsIllegalArgumentException() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "10"),
                new Token(Type.NUMBER, "5")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluate(postfix);
        });
        assertTrue(exception.getMessage().contains("values left on stack instead of 1"));
    }

    @Test
    void testEvaluate_PowerOperation() {
        List<Token> postfix = List.of(
                new Token(Type.NUMBER, "2"),
                new Token(Type.NUMBER, "3"),
                new Token(Type.OPERATOR, "^")
        );
        BigDecimal expected = new BigDecimal("8.0").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, evaluator.evaluate(postfix));
    }
}