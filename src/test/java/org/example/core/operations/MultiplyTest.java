package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {
    private final Multiply multiply = new Multiply();

    @Test
    void testApply_PositiveIntegers() {
        assertEquals(new BigDecimal("15"), multiply.apply(new BigDecimal("5"), new BigDecimal("3")));
    }

    @Test
    void testApply_PositiveAndNegative() {
        assertEquals(new BigDecimal("-15"), multiply.apply(new BigDecimal("5"), new BigDecimal("-3")));
    }

    @Test
    void testApply_TwoNegatives() {
        assertEquals(new BigDecimal("15"), multiply.apply(new BigDecimal("-5"), new BigDecimal("-3")));
    }


    @Test
    void testApply_DecimalNumbers() {
        assertEquals(new BigDecimal("3.75"), multiply.apply(new BigDecimal("1.5"), new BigDecimal("2.5")));
    }

    @Test
    void testApply_MultiplyByZero() {
        assertEquals(BigDecimal.ZERO, multiply.apply(new BigDecimal("5"), BigDecimal.ZERO));
        assertEquals(BigDecimal.ZERO, multiply.apply(BigDecimal.ZERO, new BigDecimal("5")));
        assertEquals(BigDecimal.ZERO, multiply.apply(BigDecimal.ZERO, BigDecimal.ZERO));
    }
}