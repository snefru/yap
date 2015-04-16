package io.alef.yap.core;

import com.google.common.collect.ImmutableSet;
import io.alef.yap.Item;
import io.alef.yap.RandomUtil;
import io.alef.yap.State;
import io.alef.yap.expression.Expression;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class DefaultStateTest {

    private static final int ITEMS_COUNT = 10;
    private Set<Item> items;
    private DefaultState state;

    @BeforeMethod
    public void setUp() {
        final RandomUtil helper = new RandomUtil(10, 10, 50);
        final ImmutableSet.Builder<Item> builder = new ImmutableSet.Builder<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            builder.add(helper.anItem());
        }
        items = builder.build();
        state = new DefaultState(items);
    }

    @Test
    public void items() {
        assertEquals(state.items(), items);
    }

    @Test
    public void next() {
        final ImmutableSet.Builder<Expression> builder = new ImmutableSet.Builder<>();
        for (Item item : items) {
            if (item.hasNext())
                builder.add(item.next());
        }
        assertEquals(state.next(), builder.build());
    }

    @Test(invocationCount = 100)
    public void successor() {
        final Set<Expression> expressions = state.next();
        final Expression[] candidates = expressions.toArray(new Expression[expressions.size()]);
        final Expression expression = candidates[RandomUtils.nextInt(0, candidates.length)];
        final ImmutableSet.Builder<Item> builder = new ImmutableSet.Builder<>();
        for (final Item item : items) {
            if (item.hasNext() && expression.equals(item.next())) {
                builder.add(item.nextItem());
            }
        }
        final ImmutableSet<Item> items = builder.build();
        final State successor = state.successor(expression);
        assertEquals(successor, new DefaultState(items));
    }
}
