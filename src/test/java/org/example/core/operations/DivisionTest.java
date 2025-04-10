package org.example.core.operations;

import org.example.config.Config;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    private final Division division = new Division();

    @Test
    void testApply_ExactDivision() {
        BigDecimal expected = new BigDecimal("2.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, division.apply(new BigDecimal("10"), new BigDecimal("5")));
    }

    @Test
    void testApply_WithRounding() {
        BigDecimal expected = new BigDecimal("3.3333333333");
        if (expected.scale() != Config.SCALE) {
            expected = new BigDecimal("10.0").divide(new BigDecimal("3"), Config.SCALE, Config.ROUNDING_MODE);
        }
        assertEquals(expected, division.apply(new BigDecimal("10"), new BigDecimal("3")));
    }

    @Test
    void testApply_NegativeNumbers() {
        BigDecimal expected = new BigDecimal("-2.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, division.apply(new BigDecimal("-10"), new BigDecimal("5")));
        assertEquals(expected, division.apply(new BigDecimal("10"), new BigDecimal("-5")));
    }

    @Test
    void testApply_TwoNegatives() {
        BigDecimal expected = new BigDecimal("2.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, division.apply(new BigDecimal("-10"), new BigDecimal("-5")));
    }


    @Test
    void testApply_DivisionByZero_ThrowsArithmeticException() {
        BigDecimal number1 = new BigDecimal("10");
        BigDecimal number2 = BigDecimal.ZERO;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> division.apply(number1, number2));
        assertEquals("Division by zero is not allowed!", exception.getMessage());
    }
}