package io.alef.yap.expression;

import io.alef.yap.Symbol;

public class Reference implements Expression {

    private final Symbol symbol;

    public Reference(Symbol symbol) {
        checkNotNull(symbol);
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public Expression apply(Evaluator evaluator) {
        return evaluator.apply(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference reference = (Reference) o;
        return java.util.Objects.equals(symbol, reference.symbol);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return '<' + symbol.getName() + '>';
    }
}
