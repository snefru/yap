package io.alef.yap.expression;

public class Pattern implements Expression {


    private final String value;

    public Pattern(String value) {
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
