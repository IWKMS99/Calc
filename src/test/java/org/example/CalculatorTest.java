package org.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testAddition() {
        double result = Calculator.calculate(5.0, 3.0, '+');
        assertEquals(8.0, result, DELTA); // Expected 5 + 3 = 8
    }

    @Test
    public void testAdditionWithDecimalNumbers() {
        double result = Calculator.calculate(5.5, 3.3, '+');
        assertEquals(8.8, result, DELTA); // Expected 5.5 + 3.3 = 8.8
    }

    @Test
    public void testAdditionWithNegativeNumbers() {
        double result = Calculator.calculate(-5.0, -3.0, '+');
        assertEquals(-8.0, result, DELTA); // Expected -5 + -3 = -8
    }

    @Test
    public void testSubtraction() {
        double result = Calculator.calculate(5.0, 3.0, '-');
        assertEquals(2.0, result, DELTA); // Expected 5 - 3 = 2
    }

    @Test
    public void testSubtractionWithDecimalNumbers() {
        double result = Calculator.calculate(5.5, 3.3, '-');
        assertEquals(2.2, result, DELTA); // Expected 5.5 - 3.3 = 2.2
    }

    @Test
    public void testSubtractionWithNegativeResult() {
        double result = Calculator.calculate(3.0, 5.0, '-');
        assertEquals(-2.0, result, DELTA); // Expected 3 - 5 = -2
    }

    @Test
    public void testMultiplication() {
        double result = Calculator.calculate(5.0, 3.0, '*');
        assertEquals(15.0, result, DELTA); // Expected 5 * 3 = 15
    }

    @Test
    public void testMultiplicationWithDecimalNumbers() {
        double result = Calculator.calculate(2.5, 4.0, '*');
        assertEquals(10.0, result, DELTA); // Expected 2.5 * 4 = 10
    }

    @Test
    public void testMultiplicationWithNegativeNumbers() {
        double result = Calculator.calculate(-5.0, 3.0, '*');
        assertEquals(-15.0, result, DELTA); // Expected -5 * 3 = -15

        result = Calculator.calculate(-5.0, -3.0, '*');
        assertEquals(15.0, result, DELTA); // Expected -5 * -3 = 15
    }

    @Test
    public void testDivision() {
        double result = Calculator.calculate(6.0, 2.0, '/');
        assertEquals(3.0, result, DELTA); // Expected 6 / 2 = 3
    }

    @Test
    public void testDivisionWithDecimalResult() {
        double result = Calculator.calculate(5.0, 2.0, '/');
        assertEquals(2.5, result, DELTA); // Expected 5 / 2 = 2.5
    }

    @Test
    public void testDivisionWithNegativeNumbers() {
        double result = Calculator.calculate(-6.0, 2.0, '/');
        assertEquals(-3.0, result, DELTA); // Expected -6 / 2 = -3

        result = Calculator.calculate(6.0, -2.0, '/');
        assertEquals(-3.0, result, DELTA); // Expected 6 / -2 = -3

        result = Calculator.calculate(-6.0, -2.0, '/');
        assertEquals(3.0, result, DELTA); // Expected -6 / -2 = 3
    }

    @Test
    public void testDivisionByZero() {
        double result = Calculator.calculate(5.0, 0.0, '/');
        assertTrue(Double.isNaN(result)); // Expected NaN when dividing by 0
    }

    @Test
    public void testDivisionByTinyNumber() {
        double result = Calculator.calculate(1.0, 1e-10, '/');
        assertEquals(1e10, result, 1e5); // Expected 1 / 10^-10 = 10^10
    }

    @Test
    public void testPower() {
        double result = Calculator.calculate(2.0, 3.0, '^');
        assertEquals(8.0, result, DELTA); // Expected 2^3 = 8
    }

    @Test
    public void testPowerWithDecimalNumbers() {
        double result = Calculator.calculate(2.0, 0.5, '^');
        assertEquals(Math.sqrt(2.0), result, DELTA); // Expected 2^0.5 = √2
    }

    @Test
    public void testPowerWithNegativeBase() {
        double result = Calculator.calculate(-2.0, 3.0, '^');
        assertEquals(-8.0, result, DELTA); // Expected (-2)^3 = -8

        result = Calculator.calculate(-2.0, 2.0, '^');
        assertEquals(4.0, result, DELTA); // Expected (-2)^2 = 4
    }

    @Test
    public void testNegativeExponent() {
        double result = Calculator.calculate(2.0, -2.0, '^');
        assertEquals(0.25, result, DELTA); // Expected 2^-2 = 0.25
    }

    @Test
    public void testZeroExponent() {
        double result = Calculator.calculate(5.0, 0.0, '^');
        assertEquals(1.0, result, DELTA); // Expected 5^0 = 1
    }

    @Test
    public void testPercentage() {
        double result = Calculator.calculate(50.0, 20.0, '%');
        assertEquals(10.0, result, DELTA); // Expected 50 * 20 / 100 = 10
    }

    @Test
    public void testPercentageWithDecimalNumbers() {
        double result = Calculator.calculate(75.5, 10.0, '%');
        assertEquals(7.55, result, DELTA); // Expected 75.5 * 10 / 100 = 7.55
    }

    @Test
    public void testPercentageWithNegativeNumbers() {
        double result = Calculator.calculate(-100.0, 10.0, '%');
        assertEquals(-10.0, result, DELTA); // Expected -100 * 10 / 100 = -10
    }

    @Test
    public void testPercentageOfZero() {
        double result = Calculator.calculate(0.0, 20.0, '%');
        assertEquals(0.0, result, DELTA); // Expected 0 * 20 / 100 = 0
    }

    @Test
    public void testPercentageOfOne() {
        double result = Calculator.calculate(1.0, 50.0, '%');
        assertEquals(0.5, result, DELTA); // Expected 1 * 50 / 100 = 0.5
    }

    @Test
    public void testSquareRoot() {
        double result = Calculator.calculate(16.0, 2.0, 's');
        assertEquals(4.0, result, DELTA); // Expected √16 = 4
    }

    @Test
    public void testCubeRoot() {
        double result = Calculator.calculate(8.0, 3.0, 's');
        assertEquals(2.0, result, DELTA); // Expected ∛8 = 2
    }

    @Test
    public void testNthRoot() {
        double result = Calculator.calculate(16.0, 4.0, 's');
        assertEquals(2.0, result, DELTA); // Expected ∜16 = 2
    }

    @Test
    public void testRootWithDecimalNumber() {
        double result = Calculator.calculate(4.84, 2.0, 's');
        assertEquals(2.2, result, DELTA); // Expected √4.84 ≈ 2.2
    }

    @Test
    public void testNegativeRootOddPower() {
        double result = Calculator.calculate(-8.0, 3.0, 's');
        assertEquals(-2.0, result, DELTA); // Expected ∛(-8) = -2

        result = Calculator.calculate(-27.0, 3.0, 's');
        assertEquals(-3.0, result, DELTA); // Expected ∛(-27) = -3
    }

    @Test
    public void testNegativeRootEvenPower() {
        double result = Calculator.calculate(-16.0, 2.0, 's');
        assertTrue(Double.isNaN(result)); // Expected NaN for even root of negative number

        result = Calculator.calculate(-9.0, 2.0, 's');
        assertTrue(Double.isNaN(result)); // Expected NaN for even root of negative number
    }

    @Test
    public void testVeryLargeNumbers() {
        double result = Calculator.calculate(1e10, 1e10, '+');
        assertEquals(2e10, result, 1e5); // Expected 10^10 + 10^10 = 2*10^10 with larger delta
    }

    @Test
    public void testVerySmallNumbers() {
        double result = Calculator.calculate(1e-10, 2e-10, '+');
        assertEquals(3e-10, result, 1e-15); // Expected 10^-10 + 2*10^-10 = 3*10^-10
    }

    @Test
    public void testInvalidOperation() {
        double result = Calculator.calculate(5.0, 3.0, 'f');
        assertTrue(Double.isNaN(result)); // Expected NaN for invalid operation
    }

    @Test
    public void testRootOfOne() {
        double result = Calculator.calculate(1.0, 2.0, 's');
        assertEquals(1.0, result, DELTA); // Expected √1 = 1

        result = Calculator.calculate(1.0, 3.0, 's');
        assertEquals(1.0, result, DELTA); // Expected ∛1 = 1

        result = Calculator.calculate(1.0, 4.0, 's');
        assertEquals(1.0, result, DELTA); // Expected ∜1 = 1
    }

    @Test
    public void testRootOfZero() {
        double result = Calculator.calculate(0.0, 2.0, 's');
        assertEquals(0.0, result, DELTA); // Expected √0 = 0

        result = Calculator.calculate(0.0, 3.0, 's');
        assertEquals(0.0, result, DELTA); // Expected ∛0 = 0

        result = Calculator.calculate(0.0, 4.0, 's');
        assertEquals(0.0, result, DELTA); // Expected ∜0 = 0
    }

    @Test
    public void testLargeRoot() {
        double result = Calculator.calculate(1e6, 2.0, 's');
        assertEquals(1000.0, result, DELTA); // Expected √10^6 = 1000

        result = Calculator.calculate(1e9, 3.0, 's');
        assertEquals(1000.0, result, DELTA); // Expected ∛10^9 = 1000
    }

    @Test
    public void testSmallRoot() {
        double result = Calculator.calculate(1e-6, 2.0, 's');
        assertEquals(1e-3, result, DELTA); // Expected √10^-6 = 10^-3

        result = Calculator.calculate(1e-9, 3.0, 's');
        assertEquals(1e-3, result, DELTA); // Expected ∛10^-9 = 10^-3
    }

    @Test
    public void testRootOfTinyNumber() {
        double result = Calculator.calculate(1e-10, 2.0, 's');
        assertEquals(1e-5, result, DELTA); // Expected √10^-10 = 10^-5
    }

    @Test
    public void testPerfectRoot() {
        double result = Calculator.calculate(81.0, 4.0, 's');
        assertEquals(3.0, result, DELTA); // Expected ∜81 = 3

        result = Calculator.calculate(256.0, 8.0, 's');
        assertEquals(2.0, result, DELTA); // Expected ⁸√256 = 2
    }

    @Test
    public void testMaxValueOperations() {
        double max = Double.MAX_VALUE;
        double result = Calculator.calculate(max, 1.0, '+');
        assertEquals(max, result, DELTA); // Expected MAX_VALUE + 1 ≈ MAX_VALUE

        result = Calculator.calculate(max, 1.0, '-');
        assertEquals(max - 1.0, result, DELTA); // Expected MAX_VALUE - 1

        result = Calculator.calculate(max, 0.5, '*');
        assertEquals(max * 0.5, result, 1e10); // Expected MAX_VALUE * 0.5

        result = Calculator.calculate(max, 2.0, '/');
        assertEquals(max / 2.0, result, 1e10); // Expected MAX_VALUE / 2

        result = Calculator.calculate(max, 2.0, '^');
        assertTrue(Double.isInfinite(result)); // Expected MAX_VALUE^2 = Infinity
    }

    @Test
    public void testMinValueOperations() {
        double min = Double.MIN_VALUE;
        double result = Calculator.calculate(min, 1.0, '+');
        assertEquals(1.0, result, 1.0 - min); // Expected MIN_VALUE + 1 ≈ 1

        result = Calculator.calculate(min, 1.0, '-');
        assertEquals(-1.0, result, 1.0 + min); // Expected MIN_VALUE - 1 ≈ -1

        result = Calculator.calculate(min, 2.0, '*');
        assertEquals(min * 2.0, result, DELTA); // Expected MIN_VALUE * 2

        result = Calculator.calculate(min, 2.0, '/');
        assertEquals(min / 2.0, result, DELTA); // Expected MIN_VALUE / 2

        result = Calculator.calculate(min, 2.0, '^');
        assertEquals(0.0, result, DELTA); // Expected MIN_VALUE^2 ≈ 0
    }

}