package org.example.core.operations;

import org.example.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RootTest {

    private final Root root = new Root();

    @ParameterizedTest
    @CsvSource({
            "8, 3, 2",
            "16, 2, 4",
            "81, 4, 3",
            "1, 5, 1",
            "-27, 3, -3",
            "0, 3, 0"
    })
    void testApply_IntegerRootDegrees(String baseStr, String degreeStr, String expectedStr) {
        BigDecimal base = new BigDecimal(baseStr);
        BigDecimal degree = new BigDecimal(degreeStr);
        BigDecimal expected = new BigDecimal(expectedStr).setScale(Config.SCALE, Config.ROUNDING_MODE);
        assertEquals(expected, root.apply(base, degree));
    }

    @Test
    void testApply_RootDegreeZero_ThrowsException() {
        BigDecimal base = new BigDecimal("10");
        BigDecimal degree = BigDecimal.ZERO;
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> root.apply(base, degree));
        assertEquals("Root of degree zero is not allowed!", exception.getMessage());
    }

    @Test
    void testApply_EvenRootOfNegative_ThrowsException() {
        BigDecimal base = new BigDecimal("-16");
        BigDecimal degree = new BigDecimal("2");
        BigDecimal finalDegree = degree;
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> root.apply(base, finalDegree));
        assertEquals("Even root of a negative number is not allowed!", exception.getMessage());

        degree = new BigDecimal("4");
        BigDecimal finalDegree1 = degree;
        exception = assertThrows(ArithmeticException.class, () -> root.apply(base, finalDegree1));
        assertEquals("Even root of a negative number is not allowed!", exception.getMessage());
    }

    @Test
    void testApply_FractionalRootDegree_PossibleInaccuracy() {
        BigDecimal base = new BigDecimal("16");
        BigDecimal degree = new BigDecimal("0.5");
        BigDecimal expected = new BigDecimal("256.0").setScale(Config.SCALE, Config.ROUNDING_MODE);

        assertEquals(expected, root.apply(base, degree));
    }
}