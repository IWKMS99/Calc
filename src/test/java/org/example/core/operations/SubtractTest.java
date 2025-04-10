package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class SubtractTest {
    private final Subtract subtract = new Subtract();

    @Test
    void testApply_PositiveIntegers() {
        assertEquals(new BigDecimal("2"), subtract.apply(new BigDecimal("5"), new BigDecimal("3")));
    }

    @Test
    void testApply_ResultIsNegative() {
        assertEquals(new BigDecimal("-1"), subtract.apply(new BigDecimal("2"), new BigDecimal("3")));
    }

    @Test
    void testApply_SubtractNegative() {
        assertEquals(new BigDecimal("5"), subtract.apply(new BigDecimal("2"), new BigDecimal("-3")));
    }

    @Test
    void testApply_DecimalNumbers() {
        assertEquals(new BigDecimal("0.3"), subtract.apply(new BigDecimal("2.5"), new BigDecimal("2.2")).stripTrailingZeros());
    }

    @Test
    void testApply_Zero() {
        assertEquals(new BigDecimal("5"), subtract.apply(new BigDecimal("5"), BigDecimal.ZERO));
        assertEquals(new BigDecimal("-5"), subtract.apply(BigDecimal.ZERO, new BigDecimal("5")));
    }
}