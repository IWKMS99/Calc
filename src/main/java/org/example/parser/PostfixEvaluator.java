package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import org.example.core.Operation;
import static org.example.core.OperationFactory.getOperation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class PostfixEvaluator {
    public BigDecimal evaluate(List<Token> postfixTokens) {
        Stack<BigDecimal> operandStack = new Stack<>();
        for (Token token : postfixTokens) {
            if (token.type() == Type.NUMBER) {
                operandStack.push(new BigDecimal(token.value().replace(',', '.')));
            } else if (token.type() == Type.OPERATOR) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Incorrect postfix expression: there are not enough operands for the operator " + token.value());
                }
                BigDecimal operand2 = operandStack.pop();
                BigDecimal operand1 = operandStack.pop();
                try {
                    Operation operation = getOperation(token.value());
                    BigDecimal result = operation.apply(operand1, operand2); // Используем метод apply напрямую
                    operandStack.push(result);
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Error when performing the operation " + token.value() + ": " + e.getMessage());
                }
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Incorrect postfix expression: there is more than one value left in the stack.");
        }

        return operandStack.pop();
    }
}