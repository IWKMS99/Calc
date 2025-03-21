package org.example.core;

import org.example.core.operations.Addition;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {

    @Test
    void testCalculateAddition() {
        Operation addition = new Addition();
        Calculator calculator = new Calculator();
        BigDecimal num1 = new BigDecimal("5");
        BigDecimal num2 = new BigDecimal("3");
        BigDecimal result = calculator.calculate(num1, num2, addition);
        assertEquals(new BigDecimal("8"), result);
    }

    @Test
    void testCalculateWithMockOperation() {
        Operation mockOperation = mock(Operation.class);
        when(mockOperation.apply(new BigDecimal("10"), new BigDecimal("20"))).thenReturn(new BigDecimal("30"));
        Calculator calculator = new Calculator();
        BigDecimal result = calculator.calculate(new BigDecimal("10"), new BigDecimal("20"), mockOperation);
        assertEquals(new BigDecimal("30"), result);
        verify(mockOperation).apply(new BigDecimal("10"), new BigDecimal("20"));
    }
}