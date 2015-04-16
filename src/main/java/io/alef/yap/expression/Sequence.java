package io.alef.yap.expression;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Sequence implements Expression {

    private final List<Expression> elements;

    public Sequence() {
        this(Collections.<Expression>emptyList());
    }

    public Sequence(final Expression expression) {
        this(Collections.singletonList(expression));
    }

    public Sequence(final Expression... expressions) {
        this(Arrays.asList(expressions));
    }

    public Sequence(List<Expression> elements) {
        checkNotNull(elements);
        this.elements = elements;
    }

    public List<Expression> getElements() {
        return elements;
    }

    public int size() {
        return elements.size();
    }

    public Expression get(int index) {
        return elements.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sequence sequence = (Sequence) o;
        return java.util.Objects.equals(elements, sequence.elements);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(elements);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Expression apply(Evaluator evaluator) {
        return evaluator.apply(this);
    }


    @Override
    public String toString() {
        return elements.toString();
    }
}
