package org.example.core;

import org.example.core.operations.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationFactoryTest {

    @Test
    void testGetAdditionOperation() {
        Operation operation = OperationFactory.getOperation('+');
        assertInstanceOf(Addition.class, operation);
    }

    @Test
    void testGetSubtractionOperation() {
        Operation operation = OperationFactory.getOperation('-');
        assertInstanceOf(Subtract.class, operation);
    }

    @Test
    void testGetMultiplicationOperation() {
        Operation operation = OperationFactory.getOperation('*');
        assertInstanceOf(Multiply.class, operation);
    }

    @Test
    void testGetDivisionOperation() {
        Operation operation = OperationFactory.getOperation('/');
        assertInstanceOf(Division.class, operation);
    }

    @Test
    void testGetPowerOperation() {
        Operation operation = OperationFactory.getOperation('^');
        assertInstanceOf(Power.class, operation);
    }

    @Test
    void testGetPercentOperation() {
        Operation operation = OperationFactory.getOperation('%');
        assertInstanceOf(Percent.class, operation);
    }

    @Test
    void testGetRootOperation() {
        Operation operation = OperationFactory.getOperation('s');
        assertInstanceOf(Root.class, operation);
    }

    @Test
    void testGetUnknownOperation() {
        assertThrows(IllegalArgumentException.class, () -> OperationFactory.getOperation('$'));
    }
}