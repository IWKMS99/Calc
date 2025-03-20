package org.example;

import java.math.BigDecimal;

public record ParsedExpression(BigDecimal number1, BigDecimal number2, char operation) { }