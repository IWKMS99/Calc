package org.example.core.operations;

import org.example.config.Config;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class PercentTest {
    private final Percent percent = new Percent();

    @Test
    void testApply_SimplePercentage() {
        BigDecimal expected = new BigDecimal("20.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(new BigDecimal("200"), new BigDecimal("10")));
    }

    @Test
    void testApply_DecimalBaseAndPercent() {
        BigDecimal expected = new BigDecimal("7.75").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(new BigDecimal("15.5"), new BigDecimal("50")));
    }

    @Test
    void testApply_ZeroPercent() {
        BigDecimal expected = BigDecimal.ZERO.setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(new BigDecimal("200"), BigDecimal.ZERO));
    }

    @Test
    void testApply_ZeroBase() {
        BigDecimal expected = BigDecimal.ZERO.setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(BigDecimal.ZERO, new BigDecimal("10")));
    }

    @Test
    void testApply_NegativeBase() {
        BigDecimal expected = new BigDecimal("-20.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(new BigDecimal("-200"), new BigDecimal("10")));
    }

    @Test
    void testApply_NegativePercent() {
        BigDecimal expected = new BigDecimal("-20.0").setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, percent.apply(new BigDecimal("200"), new BigDecimal("-10")));
    }
}