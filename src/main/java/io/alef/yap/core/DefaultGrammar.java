package io.alef.yap.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import io.alef.yap.Grammar;
import io.alef.yap.State;
import io.alef.yap.Symbol;
import io.alef.yap.expression.Expression;
import io.alef.yap.expression.Sequence;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultGrammar implements Grammar {

    private final Symbol startSymbol;
    private final Map<String, Symbol> symbols;

    public DefaultGrammar(String startSymbol, Map<String, Symbol> symbols) {
        checkNotNull(startSymbol, "undefined start symbol");
        checkArgument(!symbols.isEmpty(), "undefined symbols");
        checkArgument(symbols.containsKey(startSymbol), "undefined start symbol");
        this.startSymbol = symbols.get(startSymbol);
        this.symbols = symbols;
    }

    @Override
    public Symbol getStartSymbol() {
        return startSymbol;
    }

    @Override
    public Collection<Symbol> getSymbols() {
        return symbols.values();
    }

    @Override
    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    @Override
    public List<Sequence> getAlternates(String symbol) {
        return symbols.get(symbol).getAlternates();
    }

    public static class Builder {

        private final Map<String, DefaultSymbol> symbols = new HashMap<>();
        private String startSymbol;

        public Builder startSymbol(final String value) {
            checkNotNull(value);
            checkArgument(!value.isEmpty());
            this.startSymbol = value;
            return this;
        }

        public Builder define(final String name, final Sequence... choices) {
            symbols.put(name, new DefaultSymbol(name, choices));
            return this;
        }

        public DefaultGrammar build() {
            final ReferenceProcessor processor = new ReferenceProcessor(symbols);
            for (final DefaultSymbol symbol : symbols.values()) {
                final List<Sequence> alternates = symbol.getAlternates();
                final ImmutableList.Builder<Sequence> builder = new ImmutableList.Builder<>();
                for (final Sequence alternate : alternates) {
                    builder.add((Sequence) alternate.apply(processor));
                }
                symbol.setAlternates(builder.build());
            }
            final Map<String, Symbol> symbols = ImmutableMap.<String, Symbol>builder().putAll(this.symbols).build();
            final Symbol symbol = symbols.get(startSymbol);
            final State initialState = new DefaultState(symbol.getItems());
            final Queue<State> outstanding = new LinkedList<>();
            outstanding.add(initialState);
            final Set<State> processed = Sets.newHashSet();
            while (!outstanding.isEmpty()) {
                final State state = outstanding.remove();
                if (processed.contains(state)) {
                    continue;
                }
                final Set<Expression> next = state.next();
                for (Expression expression : next) {
                    final State successor = state.successor(expression);
                    if (successor == null)
                        continue;
                    outstanding.add(successor);
                }
                processed.add(state);
            }
            return new DefaultGrammar(startSymbol, symbols);
        }
    }
}
