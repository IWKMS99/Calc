package org.example.core.operations;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest {

    private final Addition addition = new Addition();

    @Test
    void testApplyPositiveNumbers() {
        BigDecimal num1 = new BigDecimal("5");
        BigDecimal num2 = new BigDecimal("3");
        BigDecimal result = addition.apply(num1, num2);
        assertEquals(new BigDecimal("8"), result);
    }

    @Test
    void testApplyNegativeNumbers() {
        BigDecimal num1 = new BigDecimal("-5");
        BigDecimal num2 = new BigDecimal("-3");
        BigDecimal result = addition.apply(num1, num2);
        assertEquals(new BigDecimal("-8"), result);
    }

    @Test
    void testApplyMixedNumbers() {
        BigDecimal num1 = new BigDecimal("5");
        BigDecimal num2 = new BigDecimal("-3");
        BigDecimal result = addition.apply(num1, num2);
        assertEquals(new BigDecimal("2"), result);
    }

    @Test
    void testGetSymbol() {
        assertEquals('+', addition.getSymbol());
    }
}