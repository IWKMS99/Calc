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
                try {
                    operandStack.push(new BigDecimal(token.value()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in token: " + token.value(), e);
                }
            } else if (token.type() == Type.OPERATOR) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Incorrect postfix expression: not enough operands for operator '" + token.value() + "'");
                }

                BigDecimal operand2 = operandStack.pop();
                BigDecimal operand1 = operandStack.pop();

                Operation operation = getOperation(token.value());
                BigDecimal result = operation.apply(operand1, operand2);

                if (result == null) {
                    throw new IllegalStateException("Operation " + token.value() + " unexpectedly returned null.");
                }
                operandStack.push(result);

            } else {
                throw new IllegalArgumentException("Unexpected token type in postfix expression: " + token.type());
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Incorrect postfix expression: " + operandStack.size() + " values left on stack instead of 1.");
        }

        return operandStack.pop();
    }
}