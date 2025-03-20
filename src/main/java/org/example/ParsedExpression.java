package org.example;

public class ParsedExpression {
    private double number1;
    private double number2;
    private char operation;

    public ParsedExpression(double number1, double number2, char operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public char getOperation() {
        return operation;
    }
}