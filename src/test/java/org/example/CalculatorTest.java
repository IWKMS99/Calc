package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorTest {
    private static final int SCALE = 10;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private BigDecimal bd(double value) {
        return BigDecimal.valueOf(value).setScale(SCALE, ROUNDING_MODE);
    }

    private void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
        assertEquals(expected.setScale(SCALE, ROUNDING_MODE), actual.setScale(SCALE, ROUNDING_MODE));
    }

    @Test
    public void testAddition() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(3.0), '+');
        assertBigDecimalEquals(bd(8.0), result);
    }

    @Test
    public void testAdditionWithDecimalNumbers() {
        BigDecimal result = Calculator.calculate(bd(5.5), bd(3.3), '+');
        assertBigDecimalEquals(bd(8.8), result);
    }

    @Test
    public void testAdditionWithNegativeNumbers() {
        BigDecimal result = Calculator.calculate(bd(-5.0), bd(-3.0), '+');
        assertBigDecimalEquals(bd(-8.0), result);
    }

    @Test
    public void testSubtraction() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(3.0), '-');
        assertBigDecimalEquals(bd(2.0), result);
    }

    @Test
    public void testSubtractionWithDecimalNumbers() {
        BigDecimal result = Calculator.calculate(bd(5.5), bd(3.3), '-');
        assertBigDecimalEquals(bd(2.2), result);
    }

    @Test
    public void testSubtractionWithNegativeResult() {
        BigDecimal result = Calculator.calculate(bd(3.0), bd(5.0), '-');
        assertBigDecimalEquals(bd(-2.0), result);
    }

    @Test
    public void testMultiplication() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(3.0), '*');
        assertBigDecimalEquals(bd(15.0), result);
    }

    @Test
    public void testMultiplicationWithDecimalNumbers() {
        BigDecimal result = Calculator.calculate(bd(2.5), bd(4.0), '*');
        assertBigDecimalEquals(bd(10.0), result);
    }

    @Test
    public void testMultiplicationWithNegativeNumbers() {
        BigDecimal result = Calculator.calculate(bd(-5.0), bd(3.0), '*');
        assertBigDecimalEquals(bd(-15.0), result);

        result = Calculator.calculate(bd(-5.0), bd(-3.0), '*');
        assertBigDecimalEquals(bd(15.0), result);
    }

    @Test
    public void testDivision() {
        BigDecimal result = Calculator.calculate(bd(6.0), bd(2.0), '/');
        assertBigDecimalEquals(bd(3.0), result);
    }

    @Test
    public void testDivisionWithDecimalResult() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(2.0), '/');
        assertBigDecimalEquals(bd(2.5), result);
    }

    @Test
    public void testDivisionWithNegativeNumbers() {
        BigDecimal result = Calculator.calculate(bd(-6.0), bd(2.0), '/');
        assertBigDecimalEquals(bd(-3.0), result);

        result = Calculator.calculate(bd(6.0), bd(-2.0), '/');
        assertBigDecimalEquals(bd(-3.0), result);

        result = Calculator.calculate(bd(-6.0), bd(-2.0), '/');
        assertBigDecimalEquals(bd(3.0), result);
    }

    @Test
    public void testDivisionByZero() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(0.0), '/');
        assertNull(result);
    }

    @Test
    public void testPower() {
        BigDecimal result = Calculator.calculate(bd(2.0), bd(3.0), '^');
        assertBigDecimalEquals(bd(8.0), result);
    }

    @Test
    public void testPowerWithDecimalNumbers() {
        BigDecimal result = Calculator.calculate(bd(2.0), bd(0.5), '^');
        assertBigDecimalEquals(bd(Math.sqrt(2.0)), result);
    }

    @Test
    public void testPowerWithNegativeBase() {
        BigDecimal result = Calculator.calculate(bd(-2.0), bd(3.0), '^');
        assertBigDecimalEquals(bd(-8.0), result);

        result = Calculator.calculate(bd(-2.0), bd(2.0), '^');
        assertBigDecimalEquals(bd(4.0), result);
    }

    @Test
    public void testNegativeExponent() {
        BigDecimal result = Calculator.calculate(bd(2.0), bd(-2.0), '^');
        assertBigDecimalEquals(bd(0.25), result);
    }

    @Test
    public void testZeroExponent() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(0.0), '^');
        assertBigDecimalEquals(bd(1.0), result);
    }

    @Test
    public void testPercentage() {
        BigDecimal result = Calculator.calculate(bd(50.0), bd(20.0), '%');
        assertBigDecimalEquals(bd(10.0), result);
    }

    @Test
    public void testPercentageWithDecimalNumbers() {
        BigDecimal result = Calculator.calculate(bd(75.5), bd(10.0), '%');
        assertBigDecimalEquals(bd(7.55), result);
    }

    @Test
    public void testPercentageWithNegativeNumbers() {
        BigDecimal result = Calculator.calculate(bd(-100.0), bd(10.0), '%');
        assertBigDecimalEquals(bd(-10.0), result);
    }

    @Test
    public void testSquareRoot() {
        BigDecimal result = Calculator.calculate(bd(16.0), bd(2.0), 's');
        assertBigDecimalEquals(bd(4.0), result);
    }

    @Test
    public void testCubeRoot() {
        BigDecimal result = Calculator.calculate(bd(8.0), bd(3.0), 's');
        assertBigDecimalEquals(bd(2.0), result);
    }

    @Test
    public void testNegativeRootOddPower() {
        BigDecimal result = Calculator.calculate(bd(-8.0), bd(3.0), 's');
        assertBigDecimalEquals(bd(-2.0), result);
    }

    @Test
    public void testNegativeRootEvenPower() {
        BigDecimal result = Calculator.calculate(bd(-16.0), bd(2.0), 's');
        assertNull(result);
    }

    @Test
    public void testInvalidOperation() {
        BigDecimal result = Calculator.calculate(bd(5.0), bd(3.0), 'f');
        assertNull(result);
    }

    @Test
    public void testPercentageOfZero() {
        BigDecimal result = Calculator.calculate(bd(0.0), bd(20.0), '%');
        assertBigDecimalEquals(bd(0.0), result);
    }

    @Test
    public void testPercentageOfOne() {
        BigDecimal result = Calculator.calculate(bd(1.0), bd(50.0), '%');
        assertBigDecimalEquals(bd(0.5), result);
    }

    @Test
    public void testNthRoot() {
        BigDecimal result = Calculator.calculate(bd(16.0), bd(4.0), 's');
        assertBigDecimalEquals(bd(2.0), result);
    }

    @Test
    public void testRootWithDecimalNumber() {
        BigDecimal result = Calculator.calculate(bd(4.84), bd(2.0), 's');
        assertBigDecimalEquals(bd(2.2), result);
    }

    @Test
    public void testRootOfOne() {
        BigDecimal result = Calculator.calculate(bd(1.0), bd(2.0), 's');
        assertBigDecimalEquals(bd(1.0), result);

        result = Calculator.calculate(bd(1.0), bd(3.0), 's');
        assertBigDecimalEquals(bd(1.0), result);
    }

    @Test
    public void testRootOfZero() {
        BigDecimal result = Calculator.calculate(bd(0.0), bd(2.0), 's');
        assertBigDecimalEquals(bd(0.0), result);
    }

    @Test
    public void testLargeRoot() {
        BigDecimal result = Calculator.calculate(bd(1_000_000.0), bd(2.0), 's');
        assertBigDecimalEquals(bd(1000.0), result);

        result = Calculator.calculate(bd(1_000_000_000.0), bd(3.0), 's');
        assertBigDecimalEquals(bd(1000.0), result);
    }

    @Test
    public void testSmallRoot() {
        BigDecimal result = Calculator.calculate(bd(0.000001), bd(2.0), 's');
        assertBigDecimalEquals(bd(0.001), result);
    }

    @Test
    public void testRootOfTinyNumber() {
        BigDecimal result = Calculator.calculate(bd(1e-10), bd(2.0), 's');
        assertBigDecimalEquals(bd(1e-5), result);
    }

    @Test
    public void testPerfectRoot() {
        BigDecimal result = Calculator.calculate(bd(81.0), bd(4.0), 's');
        assertBigDecimalEquals(bd(3.0), result);
    }

    @Test
    public void testDivisionByTinyNumber() {
        BigDecimal result = Calculator.calculate(bd(1.0), bd(1e-10), '/');
        assertBigDecimalEquals(bd(1e10), result);
    }

    @Test
    public void testVeryLargeNumbers() {
        BigDecimal result = Calculator.calculate(bd(1e10), bd(1e10), '+');
        assertBigDecimalEquals(bd(2e10), result);
    }

    @Test
    public void testVerySmallNumbers() {
        BigDecimal result = Calculator.calculate(bd(1e-10), bd(2e-10), '+');
        assertBigDecimalEquals(bd(3e-10), result);
    }
}