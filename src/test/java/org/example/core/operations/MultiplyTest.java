package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {
    private final Multiply multiply = new Multiply();

    @Test
    void multiplyPositiveNumbers_ShouldReturnCorrectResult() {
        BigDecimal result = multiply.apply(new BigDecimal("5"), new BigDecimal("3"));
        assertEquals(new BigDecimal("15"), result, "5 * 3 = 15");
    }

    @Test
    void multiplyNegativeNumbers_ShouldReturnPositive() {
        BigDecimal result = multiply.apply(new BigDecimal("-4"), new BigDecimal("-2"));
        assertEquals(new BigDecimal("8"), result, "-4 * -2 = 8");
    }

    @Test
    void multiplyByZero_ShouldReturnZero() {
        BigDecimal result = multiply.apply(new BigDecimal("7"), BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, result, "7 * 0 = 0");
    }

    @Test
    void multiplyMixedSigns_ShouldReturnNegative() {
        BigDecimal result = multiply.apply(new BigDecimal("9"), new BigDecimal("-1"));
        assertEquals(new BigDecimal("-9"), result, "9 * -1 = -9");
    }

    @Test
    void getSymbol_ShouldReturnAsterisk() {
        assertEquals('*', multiply.getSymbol(), "Символ операции должен быть '*'");
    }

    @Test
    void multiplyWithDecimalScales_CheckPrecision() {
        BigDecimal number1 = new BigDecimal("2.50");
        BigDecimal number2 = new BigDecimal("3.000");
        BigDecimal expected = new BigDecimal("7.50000"); // 2.50 * 3.000 = 7.50000 (масштаб 5)
        assertEquals(expected, multiply.apply(number1, number2), "Проверка масштаба и точности");
    }
}