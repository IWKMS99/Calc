package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class SubtractTest {
    private final Subtract subtract = new Subtract();

    @Test
    void subtractPositiveNumbers_ShouldReturnCorrectResult() {
        BigDecimal result = subtract.apply(new BigDecimal("5"), new BigDecimal("3"));
        assertEquals(new BigDecimal("2"), result, "5 - 3 = 2");
    }

    @Test
    void subtractNegativeNumbers_ShouldReturnCorrectResult() {
        BigDecimal result = subtract.apply(new BigDecimal("-4"), new BigDecimal("-2"));
        assertEquals(new BigDecimal("-2"), result, "-4 - (-2) = -2");
    }

    @Test
    void subtractFromZero_ShouldReturnNegative() {
        BigDecimal result = subtract.apply(BigDecimal.ZERO, new BigDecimal("5"));
        assertEquals(new BigDecimal("-5"), result, "0 - 5 = -5");
    }

    @Test
    void subtractZero_ShouldReturnOriginalNumber() {
        BigDecimal result = subtract.apply(new BigDecimal("7"), BigDecimal.ZERO);
        assertEquals(new BigDecimal("7"), result, "7 - 0 = 7");
    }

    @Test
    void subtractMixedSigns_ShouldReturnPositive() {
        BigDecimal result = subtract.apply(new BigDecimal("9"), new BigDecimal("-1"));
        assertEquals(new BigDecimal("10"), result, "9 - (-1) = 10");
    }

    @Test
    void getSymbol_ShouldReturnMinus() {
        assertEquals('-', subtract.getSymbol(), "Символ операции должен быть '-'");
    }

    @Test
    void subtractWithDifferentScales_CheckPrecision() {
        BigDecimal number1 = new BigDecimal("2.50");
        BigDecimal number2 = new BigDecimal("1.3");
        BigDecimal expected = new BigDecimal("1.20"); // 2.50 - 1.3 = 1.20 (масштаб 2)
        assertEquals(expected, subtract.apply(number1, number2), "Проверка точности и масштаба");
    }

    @Test
    void subtractLargeNumbers_CheckHandling() {
        BigDecimal number1 = new BigDecimal("1000000000");
        BigDecimal number2 = new BigDecimal("999999999");
        assertEquals(new BigDecimal("1"), subtract.apply(number1, number2), "10^9 - (10^9 -1) = 1");
    }
}