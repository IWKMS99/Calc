package org.example.core;

import org.example.core.operations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class OperationFactory {
    private static final Map<String, Supplier<Operation>> OPERATIONS = new HashMap<>();
    private static final Map<String, Integer> OPERATOR_PRECEDENCE = new HashMap<>();

    static {
        register("+", Addition::new, 2);
        register("-", Subtract::new, 2);
        register("*", Multiply::new, 3);
        register("/", Division::new, 3);
        register("^", Power::new, 4);
        register("%", Percent::new, 3);
        register("s", Root::new, 4);
        register(">", GreaterThan::new, 1);
        register("<", LessThan::new, 1);
    }

    private static void register(String symbol, Supplier<Operation> operationSupplier, int precedence) {
        OPERATIONS.put(symbol, operationSupplier);
        OPERATOR_PRECEDENCE.put(symbol, precedence);
    }

    public static Operation getOperation(String symbol) {
        Supplier<Operation> supplier = OPERATIONS.get(symbol);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown operation: " + symbol);
        }
        return supplier.get();
    }

    public static Set<String> getSupportedOperators() {
        return Collections.unmodifiableSet(OPERATIONS.keySet());
    }

    public static boolean isOperationSupported(String symbol) {
        return OPERATIONS.containsKey(symbol);
    }

    public static int getPrecedence(String symbol) {
        return OPERATOR_PRECEDENCE.get(symbol);
    }
}