package io.alef.yap.core;

import io.alef.yap.Rule;
import io.alef.yap.expression.Sequence;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultRule implements Rule {

    private final String lhs;
    private final Sequence rhs;


    public DefaultRule(final String lhs, final Sequence rhs) {
        checkNotNull(lhs);
        checkNotNull(rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getLhs() {
        return lhs;
    }

    public Sequence getRhs() {
        return rhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultRule that = (DefaultRule) o;
        return java.util.Objects.equals(lhs, that.lhs) &&
                java.util.Objects.equals(rhs, that.rhs);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(lhs, rhs);
    }

    @Override
    public String toString() {
        return lhs + "::=" + rhs;
    }
}
