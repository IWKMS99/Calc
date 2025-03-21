package org.example.parser;

import org.example.model.ParsedExpression;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    void testValidAddition() {
        ParsedExpression expr = parser.parse("5 + 3");
        assertNotNull(expr);
        assertEquals(new BigDecimal("5"), expr.number1());
        assertEquals(new BigDecimal("3"), expr.number2());
        assertEquals('+', expr.operation());
    }

    @Test
    void testValidSubtraction() {
        ParsedExpression expr = parser.parse("10 - 4");
        assertNotNull(expr);
        assertEquals(new BigDecimal("10"), expr.number1());
        assertEquals(new BigDecimal("4"), expr.number2());
        assertEquals('-', expr.operation());
    }

    @Test
    void testValidMultiplication() {
        ParsedExpression expr = parser.parse("2 * 6");
        assertNotNull(expr);
        assertEquals(new BigDecimal("2"), expr.number1());
        assertEquals(new BigDecimal("6"), expr.number2());
        assertEquals('*', expr.operation());
    }

    @Test
    void testValidDivision() {
        ParsedExpression expr = parser.parse("15 / 3");
        assertNotNull(expr);
        assertEquals(new BigDecimal("15"), expr.number1());
        assertEquals(new BigDecimal("3"), expr.number2());
        assertEquals('/', expr.operation());
    }

    @Test
    void testValidPower() {
        ParsedExpression expr = parser.parse("2 ^ 3");
        assertNotNull(expr);
        assertEquals(new BigDecimal("2"), expr.number1());
        assertEquals(new BigDecimal("3"), expr.number2());
        assertEquals('^', expr.operation());
    }

    @Test
    void testValidPercent() {
        ParsedExpression expr = parser.parse("50 % 10");
        assertNotNull(expr);
        assertEquals(new BigDecimal("50"), expr.number1());
        assertEquals(new BigDecimal("10"), expr.number2());
        assertEquals('%', expr.operation());
    }

    @Test
    void testValidRoot() {
        ParsedExpression expr = parser.parse("9 s 2");
        assertNotNull(expr);
        assertEquals(new BigDecimal("9"), expr.number1());
        assertEquals(new BigDecimal("2"), expr.number2());
        assertEquals('s', expr.operation());
    }

    @Test
    void testLeadingPlusAndMinus() {
        ParsedExpression exprPlus = parser.parse("+5 + 3");
        assertNotNull(exprPlus);
        assertEquals(new BigDecimal("5"), exprPlus.number1());
        assertEquals(new BigDecimal("3"), exprPlus.number2());
        assertEquals('+', exprPlus.operation());

        ParsedExpression exprMinus = parser.parse("-10 - 4");
        assertNotNull(exprMinus);
        assertEquals(new BigDecimal("-10"), exprMinus.number1());
        assertEquals(new BigDecimal("4"), exprMinus.number2());
        assertEquals('-', exprMinus.operation());
    }

    @Test
    void testInvalidInputNoOperator() {
        assertNull(parser.parse("123"));
    }

    @Test
    void testInvalidInputMultipleOperators() {
        assertNull(parser.parse("5 + 3 * 2"));
    }

    @Test
    void testInvalidInputEmptyNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("");
        }, "Expected IllegalArgumentException to be thrown for empty input");
    }

    @Test
    void testInvalidInputOperatorAtTheBeginningOrEnd() {
        assertNull(parser.parse("+ 5")); // Leading plus is handled, so this should pass
        assertNull(parser.parse("5 +"));
        assertNull(parser.parse("- 3")); // Leading minus is handled, so this should pass
        assertNull(parser.parse("3 -"));
    }

    @Test
    void testInvalidNumberFormat() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("abc + 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("5 + xyz"));
    }

    @Test
    void testCommaAsDecimalSeparator() {
        ParsedExpression expr = parser.parse("5,5 + 2,5");
        assertNotNull(expr);
        assertEquals(new BigDecimal("5.5"), expr.number1());
        assertEquals(new BigDecimal("2.5"), expr.number2());
        assertEquals('+', expr.operation());
    }

    @Test
    void testWhitespaceAroundNumbersAndOperator() {
        ParsedExpression expr = parser.parse("  10   -   5  ");
        assertNotNull(expr);
        assertEquals(new BigDecimal("10"), expr.number1());
        assertEquals(new BigDecimal("5"), expr.number2());
        assertEquals('-', expr.operation());
    }
}