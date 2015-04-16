package io.alef.yap;

import io.alef.yap.expression.Sequence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Grammar extends Serializable {

    Symbol getStartSymbol();

    Collection<Symbol> getSymbols();

    Symbol getSymbol(String name);

    List<Sequence> getAlternates(final String symbol);

    State getInitialState();
}
