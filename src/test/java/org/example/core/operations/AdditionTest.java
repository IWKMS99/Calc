package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {
    private final Addition addition = new Addition();

    @Test
    void testApply_PositiveIntegers() {
        assertEquals(new BigDecimal("5"), addition.apply(new BigDecimal("2"), new BigDecimal("3")));
    }

    @Test
    void testApply_PositiveAndNegative() {
        assertEquals(new BigDecimal("-1"), addition.apply(new BigDecimal("2"), new BigDecimal("-3")));
    }

    @Test
    void testApply_DecimalNumbers() {
        assertEquals(new BigDecimal("3.8"), addition.apply(new BigDecimal("1.5"), new BigDecimal("2.3")));
    }

    @Test
    void testApply_Zero() {
        assertEquals(new BigDecimal("5"), addition.apply(new BigDecimal("5"), BigDecimal.ZERO));
        assertEquals(new BigDecimal("5"), addition.apply(BigDecimal.ZERO, new BigDecimal("5")));
    }
}