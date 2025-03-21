package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.example.config.Config.ROUNDING_MODE;
import static org.example.config.Config.SCALE;
import static org.junit.jupiter.api.Assertions.*;

class PercentTest {
    private final Percent percent = new Percent();

    @Test
    void basicPercentageCalculation_ShouldBeCorrect() {
        BigDecimal result = percent.apply(new BigDecimal("50"), new BigDecimal("200"));
        BigDecimal expected = new BigDecimal("100").setScale(SCALE, ROUNDING_MODE);
        assertEquals(expected, result, "50% от 200 = 100.0000000000");
    }

    @Test
    void decimalPercentage_ShouldRoundCorrectly() {
        BigDecimal result = percent.apply(new BigDecimal("25.5"), new BigDecimal("200"));
        BigDecimal expected = new BigDecimal("51.0000000000");
        assertEquals(expected, result, "25.5% от 200 = 51.0000000000");
    }

    @Test
    void negativePercentage_ShouldHandleSigns() {
        BigDecimal result = percent.apply(new BigDecimal("-10"), new BigDecimal("50"));
        BigDecimal expected = new BigDecimal("-5.0000000000");
        assertEquals(expected, result, "-10% от 50 = -5.0000000000");
    }

    @Test
    void zeroValuePercentage_ShouldReturnZero() {
        BigDecimal result = percent.apply(BigDecimal.ZERO, new BigDecimal("100"));
        assertEquals(BigDecimal.ZERO.setScale(SCALE), result, "0% от 100 = 0");
    }

    @Test
    void percentageOfZero_ShouldReturnZero() {
        BigDecimal result = percent.apply(new BigDecimal("30"), BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO.setScale(SCALE), result, "30% от 0 = 0");
    }

    @Test
    void complexRoundingScenario_CheckPrecision() {
        BigDecimal result = percent.apply(new BigDecimal("33.333333"), new BigDecimal("99"));
        BigDecimal expected = new BigDecimal("32.9999996700"); // 99 * 33.333333% = 33
        assertEquals(expected, result, "33.333333% от 99 = 32.9999996700");
    }

    @Test
    void getSymbol_ShouldReturnPercentSign() {
        assertEquals('%', percent.getSymbol(), "Символ операции должен быть '%'");
    }
}