package org.example.parser;

import org.example.dto.Token;
import org.example.dto.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    private final Tokenizer tokenizer = new Tokenizer();

    @Test
    void testTokenize_SimpleExpression() {
        List<Token> expected = List.of(
                new Token(Type.NUMBER, "2"),
                new Token(Type.OPERATOR, "+"),
                new Token(Type.NUMBER, "3")
        );
        assertEquals(expected, tokenizer.tokenize("2 + 3"));
        assertEquals(expected, tokenizer.tokenize("2+3"));
    }

    @Test
    void testTokenize_WithBracketsAndDecimals() {
        List<Token> expected = List.of(
                new Token(Type.LEFT_BRACKET, "("),
                new Token(Type.NUMBER, "5.5"),
                new Token(Type.OPERATOR, "-"),
                new Token(Type.NUMBER, "1.2"),
                new Token(Type.RIGHT_BRACKET, ")"),
                new Token(Type.OPERATOR, "*"),
                new Token(Type.NUMBER, "10")
        );
        assertEquals(expected, tokenizer.tokenize(" ( 5.5 - 1.2 ) * 10 "));
    }

    @Test
    void testTokenize_AllOperators() {
        String input = "1+2-3*4/5^6%7s8>9<0";
        List<Token> expected = List.of(
                new Token(Type.NUMBER, "1"), new Token(Type.OPERATOR, "+"),
                new Token(Type.NUMBER, "2"), new Token(Type.OPERATOR, "-"),
                new Token(Type.NUMBER, "3"), new Token(Type.OPERATOR, "*"),
                new Token(Type.NUMBER, "4"), new Token(Type.OPERATOR, "/"),
                new Token(Type.NUMBER, "5"), new Token(Type.OPERATOR, "^"),
                new Token(Type.NUMBER, "6"), new Token(Type.OPERATOR, "%"),
                new Token(Type.NUMBER, "7"), new Token(Type.OPERATOR, "s"),
                new Token(Type.NUMBER, "8"), new Token(Type.OPERATOR, ">"),
                new Token(Type.NUMBER, "9"), new Token(Type.OPERATOR, "<"),
                new Token(Type.NUMBER, "0")
        );
        assertEquals(expected, tokenizer.tokenize(input));
    }


    @Test
    void testTokenize_NumberStartingWithDecimalPoint() {
        List<Token> expected = List.of(new Token(Type.NUMBER, ".5"));
        assertEquals(expected, tokenizer.tokenize(".5"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"&", "#", "@"})
    void testTokenize_InvalidCharacter_ThrowsIllegalArgumentException(String invalidChar) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("2 + " + invalidChar + " 3");
        });
        assertTrue(exception.getMessage().contains("Unknown symbol: '" + invalidChar + "'"));
    }

    @Test
    void testTokenize_MultipleDecimalPoints_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("1.2.3 + 4");
        });
        assertTrue(exception.getMessage().contains("Multiple decimal points"));
    }

    @Test
    void testTokenize_NumberEndsWithDecimalPoint_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("12.");
        });
        assertTrue(exception.getMessage().contains("Number cannot end with a decimal point"));
    }

    @Test
    void testTokenize_OnlyDecimalPoint_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(" . ");
        });
        assertTrue(exception.getMessage().contains("Invalid number format: '.'"));
    }

    @Test
    void testTokenize_InvalidNumberFormatMixed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("1..2");
        });
        assertTrue(exception.getMessage().contains("Multiple decimal points"));
    }
}