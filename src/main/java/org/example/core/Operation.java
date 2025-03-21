package org.example.core;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal apply(BigDecimal number1, BigDecimal number2);
    char getSymbol();
}
