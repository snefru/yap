package io.alef.yap.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.alef.yap.Item;
import io.alef.yap.Rule;
import io.alef.yap.expression.Sequence;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static io.alef.yap.expression.Expressions.reference;
import static io.alef.yap.expression.Expressions.sequence;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefaultSymbolTest {

    public static final String NAME = "A";
    public static final Sequence[] SEQUENCES = new Sequence[]{sequence(reference("A"), reference("B")), sequence(reference("B"))};
    public static final List<Sequence> SEQUENCE_LIST = Lists.newArrayList(SEQUENCES);

    @Test
    public void getName() {
        assertEquals(new DefaultSymbol(NAME, SEQUENCES).getName(), "A");
    }

    @Test
    public void getAlternates() {
        assertEquals(new DefaultSymbol("A", SEQUENCE_LIST).getAlternates(), SEQUENCE_LIST);
    }

    @Test
    public void getRules() {
        final Set<Sequence> sequences = Sets.newHashSet(SEQUENCE_LIST);
        final Set<Rule> rules = new DefaultSymbol(NAME, SEQUENCE_LIST).getRules();
        for (final Rule rule : rules) {
            assertTrue(sequences.contains(rule.getRhs()));
            assertEquals(rule.getLhs(), NAME);
        }
    }

    @Test
    public void getItems() {
        final Set<Sequence> sequences = Sets.newHashSet(SEQUENCE_LIST);
        final Set<Item> items = new DefaultSymbol(NAME, SEQUENCE_LIST).getItems();
        for (final Item item : items) {
            assertTrue(item.atStart());
            final Rule rule = item.getRule();
            assertEquals(rule.getLhs(), NAME);
            assertTrue(sequences.contains(rule.getRhs()));
        }
    }


}
