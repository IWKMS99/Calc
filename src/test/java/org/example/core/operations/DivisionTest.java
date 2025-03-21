package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;
import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {
    private final Division division = new Division();

    @Test
    void divideByZero_ShouldReturnNull() {
        BigDecimal result = division.apply(BigDecimal.TEN, BigDecimal.ZERO);
        assertNull(result, "Деление на ноль должно возвращать null");
    }

    @Test
    void validDivision_ShouldReturnCorrectResult() {
        BigDecimal result = division.apply(new BigDecimal("10"), new BigDecimal("2"));
        BigDecimal expected = new BigDecimal("5").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "10 / 2 должно быть 5.0000000000");
    }

    @Test
    void divisionRequiresRounding_ShouldRoundCorrectly() {
        BigDecimal result = division.apply(new BigDecimal("10"), new BigDecimal("3"));
        BigDecimal expected = new BigDecimal("3.3333333333").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "10 / 3 должно округлиться до 3.3333333333");
    }

    @Test
    void negativeDivision_ShouldHandleSigns() {
        BigDecimal result = division.apply(new BigDecimal("-15"), new BigDecimal("4"));
        BigDecimal expected = new BigDecimal("-3.75").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "-15 / 4 = -3.7500000000");
    }

    @Test
    void getSymbol_ShouldReturnSlash() {
        assertEquals('/', division.getSymbol(), "Символ операции должен быть '/'");
    }

    @Test
    void divisionWithFullScalePrecision_CheckScale() {
        BigDecimal result = division.apply(BigDecimal.ONE, new BigDecimal("7"));
        assertEquals(SCALE, result.scale(), "Масштаб результата должен быть равен 10");
    }
}