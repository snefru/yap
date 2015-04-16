package io.alef.yap.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.alef.yap.Item;
import io.alef.yap.Rule;
import io.alef.yap.Symbol;
import io.alef.yap.expression.Sequence;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultSymbol implements Symbol {

    private final String name;
    private List<Sequence> alternates;
    private Set<Item> items;
    private Set<Rule> rules;

    public DefaultSymbol(final String name, final List<Sequence> alternates) {
        checkNotNull(name);
        checkArgument(!name.isEmpty());
        checkNotNull(alternates);
        checkArgument(!alternates.isEmpty());
        this.name = name;
        setAlternates(alternates);
    }

    public DefaultSymbol(final String name, Sequence... alternates) {
        this(name, Arrays.asList(alternates));
    }

    private static Set<Rule> createRules(final String name, final List<Sequence> alternates) {
        final ImmutableSet.Builder<Rule> builder = new ImmutableSet.Builder<>();
        for (final Sequence alternate : alternates) {
            builder.add(new DefaultRule(name, alternate));
        }
        return builder.build();
    }

    private static Set<Item> createItems(final Set<Rule> rules) {
        final ImmutableSet.Builder<Item> builder = new ImmutableSet.Builder<>();
        for (final Rule rule : rules) {
            builder.add(new DefaultItem(rule));
        }
        return builder.build();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Sequence> getAlternates() {
        return alternates;
    }

    void setAlternates(final List<Sequence> alternates) {
        this.alternates = alternates;
        this.rules = createRules(name, alternates);
        this.items = createItems(rules);
    }

    @Override
    public Set<Rule> getRules() {
        return rules;
    }

    @Override
    public Set<Item> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultSymbol that = (DefaultSymbol) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
