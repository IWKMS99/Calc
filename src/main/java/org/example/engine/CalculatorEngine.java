package org.example.engine;

import org.example.dto.Token;
import org.example.parser.*;

import java.math.BigDecimal;
import java.util.List;

public class CalculatorEngine {
    private final Tokenizer tokenizer = new Tokenizer();
    private final ShuntingYardConverter converter = new ShuntingYardConverter();
    private final PostfixEvaluator evaluator = new PostfixEvaluator();

    public BigDecimal evaluate(String expr) {
        List<Token> tokens = tokenizer.tokenize(expr);
        List<Token> postfix = converter.convertToPostfix(tokens);
        return evaluator.evaluate(postfix);
    }
}