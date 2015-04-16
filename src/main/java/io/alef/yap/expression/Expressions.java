package io.alef.yap.expression;

import com.google.common.collect.Maps;
import io.alef.yap.Symbol;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Expressions {

    private static final Map<String, Pattern> patterns = Maps.newHashMap();
    private static final Map<String, ForwardReference> forwardReferences = Maps.newHashMap();
    private static final Map<Symbol, Reference> references = Maps.newHashMap();
    private static final Map<List<Expression>, Sequence> sequences = Maps.newHashMap();

    public static Expression lexical(String pattern) {
        if (patterns.containsKey(pattern))
            return patterns.get(pattern);
        final Pattern value = new Pattern(pattern);
        patterns.put(pattern, value);
        return value;
    }

    public static Expression reference(String reference) {
        if (forwardReferences.containsKey(reference))
            return forwardReferences.get(reference);
        final ForwardReference value = new ForwardReference(reference);
        forwardReferences.put(reference, value);
        return value;
    }

    public static Expression reference(Symbol symbol) {
        if (references.containsKey(symbol))
            return references.get(symbol);
        final Reference value = new Reference(symbol);
        references.put(symbol, value);
        return value;
    }

    public static Sequence empty() {
        return new Sequence();
    }

    public static Sequence sequence(Expression... expressions) {
        return sequence(Arrays.asList(expressions));
    }

    public static Sequence sequence(List<Expression> elements) {
        if (sequences.containsKey(elements))
            return sequences.get(elements);
        final Sequence value = new Sequence(elements);
        sequences.put(elements, value);
        return value;
    }
}
