package io.alef.yap.expression;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Pattern implements Expression {


    private final String value;
    private final java.util.regex.Pattern pattern;

    public Pattern(final String value) {
        checkNotNull(value);
        checkArgument(!value.isEmpty());
        this.value = value;
        this.pattern = java.util.regex.Pattern.compile(value);
    }

    public String getValue() {
        return value;
    }

    public boolean matches(final String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public Expression apply(Evaluator evaluator) {
        return evaluator.apply(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return java.util.Objects.equals(value, pattern.value);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
