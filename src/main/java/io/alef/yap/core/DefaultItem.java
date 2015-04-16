package io.alef.yap.core;

import io.alef.yap.Item;
import io.alef.yap.Rule;
import io.alef.yap.expression.Expression;
import io.alef.yap.expression.Sequence;

public class DefaultItem implements Item {

    private final Rule rule;
    private final int position;
    private final Sequence rhs;
    private final Item next;

    public DefaultItem(final Rule rule) {
        this(rule, 0);
    }

    public DefaultItem(final Rule rule, final int position) {
        this.rule = rule;
        this.position = position;
        this.rhs = rule.getRhs();
        this.next = (position < this.rhs.size()) ? new DefaultItem(rule, position + 1) : null;
    }

    @Override
    public Rule getRule() {
        return rule;
    }

    @Override
    public Expression next() {
        return rhs.get(position);
    }

    @Override
    public Item nextItem() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return position < rhs.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultItem that = (DefaultItem) o;
        return java.util.Objects.equals(position, that.position) &&
                java.util.Objects.equals(rule, that.rule);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(rule, position);
    }

    @Override
    public boolean atStart() {
        return position == 0;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(rule.getLhs());
        builder.append(" ::= ");
        final Sequence rhs = rule.getRhs();
        for (int i = 0; i < position; i++) {
            builder.append(rhs.get(i));
        }
        builder.append("^");
        for (int i = position; i < rhs.size(); i++) {
            builder.append(rhs.get(i));
        }
        return builder.toString();
    }
}
