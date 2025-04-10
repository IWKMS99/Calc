package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class GreaterThanTest {
    private final GreaterThan greaterThan = new GreaterThan();

    @Test
    void testApply_FirstIsGreater() {
        assertEquals(BigDecimal.ONE, greaterThan.apply(new BigDecimal("5"), new BigDecimal("3")));
        assertEquals(BigDecimal.ONE, greaterThan.apply(new BigDecimal("3.1"), new BigDecimal("3.0")));
        assertEquals(BigDecimal.ONE, greaterThan.apply(new BigDecimal("0"), new BigDecimal("-1")));
    }

    @Test
    void testApply_FirstIsNotGreater() {
        assertEquals(BigDecimal.ZERO, greaterThan.apply(new BigDecimal("3"), new BigDecimal("5")));
        assertEquals(BigDecimal.ZERO, greaterThan.apply(new BigDecimal("3.0"), new BigDecimal("3.1")));
        assertEquals(BigDecimal.ZERO, greaterThan.apply(new BigDecimal("-1"), new BigDecimal("0")));
    }

    @Test
    void testApply_NumbersAreEqual() {
        assertEquals(BigDecimal.ZERO, greaterThan.apply(new BigDecimal("5"), new BigDecimal("5")));
        assertEquals(BigDecimal.ZERO, greaterThan.apply(new BigDecimal("5.0"), new BigDecimal("5")));
    }
}