package io.alef.yap.core;

import io.alef.yap.Symbol;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;

import static io.alef.yap.expression.Expressions.*;
import static org.testng.Assert.assertEquals;

public class DefaultGrammarTest {

    public static final String START_SYMBOL = "A";
    private DefaultGrammar grammar;

    @BeforeTest
    public void setUp() {
        final DefaultGrammar.Builder builder = new DefaultGrammar.Builder();
        grammar = builder.startSymbol(START_SYMBOL)
                .define(START_SYMBOL, sequence(reference(START_SYMBOL), reference("B")), sequence(reference("B")))
                .define("B", sequence(pattern("b"))).build();
    }

    @Test
    public void getSymbols() {
        final Collection<Symbol> symbols = grammar.getSymbols();
        assertEquals(symbols.size(), 2);
    }

    @Test
    public void startSymbol() {
        assertEquals(grammar.getStartSymbol().getName(), START_SYMBOL);
    }

    @Test
    public void alternates() {
        assertEquals(grammar.getAlternates(START_SYMBOL).size(), 2);
    }


}
