package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;
import static org.junit.jupiter.api.Assertions.*;

class PowerTest {
    private final Power power = new Power();

    @Test
    void positiveExponent_ShouldReturnCorrectValue() {
        BigDecimal result = power.apply(new BigDecimal("2"), new BigDecimal("3"));
        BigDecimal expected = new BigDecimal("8").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "2^3 = 8");
    }

    @Test
    void fractionalExponent_CheckRounding() {
        BigDecimal result = power.apply(new BigDecimal("4"), new BigDecimal("0.5"));
        BigDecimal expected = new BigDecimal("2.0000000000");
        assertEquals(expected, result, "4^0.5 = 2.0000000000");
    }

    @Test
    void negativeBaseWithOddExponent_ShouldReturnNegative() {
        BigDecimal result = power.apply(new BigDecimal("-2"), new BigDecimal("3"));
        BigDecimal expected = new BigDecimal("-8").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "(-2)^3 = -8");
    }

    @Test
    void negativeBaseWithEvenExponent_ShouldReturnPositive() {
        BigDecimal result = power.apply(new BigDecimal("-3"), new BigDecimal("2"));
        BigDecimal expected = new BigDecimal("9").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "(-3)^2 = 9");
    }

    @Test
    void zeroExponent_ShouldReturnOne() {
        BigDecimal result = power.apply(new BigDecimal("5"), BigDecimal.ZERO);
        assertEquals(BigDecimal.ONE.setScale(SCALE), result, "5^0 = 1");
    }

    @Test
    void zeroToPositivePower_ShouldReturnZero() {
        BigDecimal result = power.apply(BigDecimal.ZERO, new BigDecimal("5"));
        assertEquals(BigDecimal.ZERO.setScale(SCALE), result, "0^5 = 0");
    }

    @Test
    void zeroToNegativePower_ShouldReturnNull() {
        BigDecimal result = power.apply(BigDecimal.ZERO, new BigDecimal("-1"));
        assertNull(result, "0^-1 → NaN → null");
    }

    @Test
    void negativeBaseWithFractionalExponent_ShouldReturnNull() {
        BigDecimal result = power.apply(new BigDecimal("-2"), new BigDecimal("0.5"));
        assertNull(result, "(-2)^0.5 → NaN → null");
    }

    @Test
    void getSymbol_ShouldReturnCaret() {
        assertEquals('^', power.getSymbol(), "Символ операции должен быть '^'");
    }

    @Test
    void largeExponent_CheckHandling() {
        BigDecimal result = power.apply(new BigDecimal("10"), new BigDecimal("100"));
        assertNotNull(result, "10^100 должно быть корректным числом");
    }

    @Test
    void decimalPrecision_CheckScale() {
        BigDecimal result = power.apply(new BigDecimal("2.5"), new BigDecimal("3"));
        assertEquals(SCALE, result.scale(), "Масштаб должен соответствовать Config.SCALE");
    }
}