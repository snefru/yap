package io.alef.yap.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.alef.yap.Item;
import io.alef.yap.State;
import io.alef.yap.expression.Expression;
import io.alef.yap.expression.Reference;

import java.util.*;

public class DefaultState implements State {

    private final Set<Item> items;
    private final Set<Expression> next;
    private Map<Expression, State> transitions = Maps.newHashMap();

    public DefaultState(Set<Item> items) {
        this.items = closure(items);
        final ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<>();
        for (final Item item : this.items) {
            if (item.hasNext())
                builder.add(item.next());
        }
        next = builder.build();
    }

    private static Set<Item> closure(final Set<Item> items) {
        final Queue<Item> unprocessed = new LinkedList<>(items);
        final Set<Item> processed = Sets.newIdentityHashSet();
        while (!unprocessed.isEmpty()) {
            final Item u = unprocessed.remove();
            processed.add(u);
            if (!u.hasNext()) {
                continue;
            }
            final Expression expression = u.next();
            if (expression instanceof Reference) {
                final Reference reference = (Reference) expression;
                final Set<Item> reachable = reference.getSymbol().getItems();
                for (Item v : reachable) {
                    if (!processed.contains(v))
                        unprocessed.add(v);
                }
            }
        }
        return ImmutableSet.copyOf(processed);
    }

    @Override
    public Set<Expression> next() {
        return this.next;
    }

    @Override
    public Set<Item> items() {
        return this.items;
    }

    @Override
    public State successor(final Expression expression) {
        State state = transitions.get(expression);
        if (state != null)
            return state;
        final LinkedList<Expression> unprocessed = new LinkedList<>();
        unprocessed.add(expression);
        final Set<Item> kernel = new HashSet<>();
        while (!unprocessed.isEmpty()) {
            final Expression current = unprocessed.remove();
            for (final Item item : items) {
                if (item.hasNext() && item.next().equals(current)) {
                    kernel.add(item.nextItem());
                }
            }
        }
        state = new DefaultState(kernel);
        transitions.put(expression, state);
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultState that = (DefaultState) o;
        return com.google.common.base.Objects.equal(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final Item item : items) {
            builder.append(item);
            builder.append("\n");
        }
        return builder.toString();
    }
}
