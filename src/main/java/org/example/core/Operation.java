package org.example.core;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal apply(BigDecimal number1, BigDecimal number2);
    String getSymbol();
    default String getDescription() {
        return "Operation" + getSymbol();
    };
}
