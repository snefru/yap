package io.alef.yap.core;

import io.alef.yap.expression.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReferenceProcessor implements Evaluator {

    private final Map<String, DefaultSymbol> symbols;

    public ReferenceProcessor(Map<String, DefaultSymbol> symbols) {
        this.symbols = symbols;
    }

    @Override
    public Expression apply(ForwardReference forwardReference) {
        return Expressions.reference(symbols.get(forwardReference.getValue()));
    }

    @Override
    public Expression apply(Reference reference) {
        return reference;
    }

    @Override
    public Expression apply(Pattern pattern) {
        return pattern;
    }

    @Override
    public Expression apply(Sequence sequence) {
        final List<Expression> elements = sequence.getElements();
        final List<Expression> normalisedElements = new ArrayList<>();
        for (Expression element : elements) {
            normalisedElements.add(element.apply(this));
        }
        return Expressions.sequence(normalisedElements);
    }
}
