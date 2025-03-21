package org.example.core;

import org.example.core.operations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class OperationFactory {
    private static final Map<Character, Supplier<Operation>> OPERATIONS = new HashMap<>();

    static {
        register('+', Addition::new);
        register('-', Subtract::new);
        register('*', Multiply::new);
        register('/', Division::new);
        register('^', Power::new);
        register('%', Percent::new);
        register('s', Root::new);
        register('>', GreaterThan::new);
        register('<', LessThan::new);
    }

    private static void register(char symbol, Supplier<Operation> operationSupplier) {
        OPERATIONS.put(symbol, operationSupplier);
    }

    public static Operation getOperation(char symbol) {
        Supplier<Operation> supplier = OPERATIONS.get(symbol);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown operation: " + symbol);
        }
        return supplier.get();
    }

    public static Set<Character> getSupportedOperators() {
        return Collections.unmodifiableSet(OPERATIONS.keySet());
    }

    public static boolean isOperationSupported(char symbol) {
        return OPERATIONS.containsKey(symbol);
    }
}