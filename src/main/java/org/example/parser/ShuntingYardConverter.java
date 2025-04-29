package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;

import java.util.*;

import static org.example.core.OperationFactory.getPrecedence;

public class ShuntingYardConverter {
    public List<Token> convertToPostfix(List<Token> tokens) {
        Queue<Token> outputQueue = new LinkedList<>();
        Deque<Token> operatorStack = new ArrayDeque<>();

        for (Token token : tokens) {
            if (token.type() == Type.NUMBER) {
                outputQueue.add(token);
            } else if (token.type() == Type.OPERATOR) {
                while (!operatorStack.isEmpty() && operatorStack.peek().type() == Type.OPERATOR &&
                getPrecedence(operatorStack.peek().value()) >= getPrecedence(token.value())) {
                    outputQueue.offer(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.type() == Type.LEFT_BRACKET) {
                operatorStack.push(token);
            } else if (token.type() == Type.RIGHT_BRACKET) {
                while (!operatorStack.isEmpty() && operatorStack.peek().type() != Type.LEFT_BRACKET) {
                    outputQueue.offer(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().type() == Type.LEFT_BRACKET) {
                    operatorStack.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched brackets");
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().type() == Type.LEFT_BRACKET) {
                throw new IllegalArgumentException("Mismatched brackets");
            }
            outputQueue.offer(operatorStack.pop());
        }

        return new ArrayList<>(outputQueue);
    }
}