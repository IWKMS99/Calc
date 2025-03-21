package org.example.core;

import java.math.BigDecimal;

public class Calculator {
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2, Operation operation) {
        return operation.apply(number1, number2);
    }
}