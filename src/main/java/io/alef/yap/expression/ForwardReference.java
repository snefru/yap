package io.alef.yap.expression;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ForwardReference implements Expression {


    private final String value;

    public ForwardReference(final String value) {
        checkNotNull(value);
        checkArgument(!value.isEmpty());
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Expression apply(Evaluator evaluator) {
        return evaluator.apply(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForwardReference that = (ForwardReference) o;
        return java.util.Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(value);
    }

    @Override
    public String toString() {
        return "[" + value + "]";

    }
}
