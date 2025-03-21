package org.example.core.operations;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.example.config.Config.SCALE;
import static org.junit.jupiter.api.Assertions.*;

class RootTest {
    private final Root root = new Root();

    @Test
    void squareRootOfPositiveNumber_ShouldCalculate() {
        BigDecimal result = root.apply(new BigDecimal("16"), new BigDecimal("2"));
        BigDecimal expected = new BigDecimal("4.0000000000");
        assertEquals(expected, result, "√16 = 4.0000000000");
    }

    @Test
    void cubeRootOfNegativeNumber_ShouldHandleSign() {
        BigDecimal result = root.apply(new BigDecimal("-27"), new BigDecimal("3"));
        BigDecimal expected = new BigDecimal("-3.0000000000");
        assertEquals(expected, result, "³√-27 = -3.0000000000");
    }

    @Test
    void zeroDegreeRoot_ShouldReturnNull() {
        BigDecimal result = root.apply(new BigDecimal("25"), BigDecimal.ZERO);
        assertNull(result, "Корень нулевой степени недопустим");
    }

    @Test
    void evenRootOfNegativeNumber_ShouldReturnNull() {
        BigDecimal result = root.apply(new BigDecimal("-16"), new BigDecimal("2"));
        assertNull(result, "Чётный корень из отрицательного числа → null");
    }

    @Test
    void rootOfZero_ShouldReturnZero() {
        BigDecimal result = root.apply(BigDecimal.ZERO, new BigDecimal("5"));
        assertEquals(BigDecimal.ZERO.setScale(SCALE), result, "⁵√0 = 0");
    }

    @Test
    void fractionalRoot_CheckPrecision() {
        BigDecimal result = root.apply(new BigDecimal("8"), new BigDecimal("3"));
        assertEquals(SCALE, result.scale(), "Масштаб результата должен быть 10");
    }

    @Test
    void highDegreeRoot_CheckCalculation() {
        BigDecimal result = root.apply(new BigDecimal("256"), new BigDecimal("4"));
        BigDecimal expected = new BigDecimal("4.0000000000");
        assertEquals(expected, result, "⁴√256 = 4.0000000000");
    }

    @Test
    void getSymbol_ShouldReturnS() {
        assertEquals('s', root.getSymbol(), "Символ операции должен быть 's'");
    }

    @Test
    void irrationalRoot_CheckRounding() {
        BigDecimal result = root.apply(new BigDecimal("2"), new BigDecimal("2"));
        BigDecimal expected = new BigDecimal("1.4142135624"); // √2 ≈ 1.41421356237...
        assertEquals(expected, result, "√2 округляется до 1.4142135624");
    }

    @Test
    void verySmallNumberRoot_CheckHandling() {
        BigDecimal result = root.apply(new BigDecimal("0.00000001"), new BigDecimal("2"));
        assertNotNull(result, "Корень из малого числа должен вычисляться");
    }
}