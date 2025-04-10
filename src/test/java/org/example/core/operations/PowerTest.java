package org.example.core.operations;

import org.example.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class PowerTest {

    private final Power power = new Power();

    @ParameterizedTest
    @CsvSource({
            "2, 3, 8",
            "5, 2, 25",
            "10, 0, 1",
            "4, -1, 0.25",
            "-2, 3, -8",
            "3, 1, 3"
    })
    void testApply_IntegerExponents(String baseStr, String expStr, String expectedStr) {
        BigDecimal base = new BigDecimal(baseStr);
        BigDecimal exponent = new BigDecimal(expStr);
        BigDecimal expected = new BigDecimal(expectedStr).setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, power.apply(base, exponent));
    }

    @Test
    void testApply_FractionalExponent_SquareRoot() {
        BigDecimal base = new BigDecimal("9");
        BigDecimal exponent = new BigDecimal("0.5");
        BigDecimal expected = new BigDecimal("3.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, power.apply(base, exponent));
    }

    @Test
    void testApply_FractionalExponent_CubeRoot() {
        BigDecimal base = new BigDecimal("27");
        BigDecimal exponent = new BigDecimal("1.0").divide(new BigDecimal("3.0"), Config.SCALE + 5, RoundingMode.HALF_UP);
        BigDecimal expected = new BigDecimal("3.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, power.apply(base, exponent));
    }


    @Test
    void testApply_NegativeBaseEvenFractionalExponent_ThrowsException() {
        BigDecimal base = new BigDecimal("-4");
        BigDecimal exponent = new BigDecimal("0.5");
        assertThrows(ArithmeticException.class, () -> power.apply(base, exponent));
    }

    @Test
    void testApply_ZeroToNegativePower_ThrowsException() {
        BigDecimal base = BigDecimal.ZERO;
        BigDecimal exponent = new BigDecimal("-1");
        assertThrows(ArithmeticException.class, () -> power.apply(base, exponent));
    }

    @Test
    void testApply_VeryLargeResult_ThrowsException() {
        BigDecimal base = new BigDecimal("10");
        BigDecimal exponent = new BigDecimal("500");
        assertThrows(ArithmeticException.class, () -> power.apply(base, exponent));
    }
}