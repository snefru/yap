package io.alef.yap.core;

import io.alef.yap.Item;
import io.alef.yap.RandomUtil;
import io.alef.yap.Rule;
import io.alef.yap.expression.Expression;
import io.alef.yap.expression.ForwardReference;
import org.apache.commons.lang3.SerializationUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.alef.yap.expression.Expressions.*;
import static org.testng.Assert.*;

public class DefaultItemTest {

    private Item atStart;
    private Item atEndItem;
    private Item emptyItem;
    private Rule rule;
    private Expression b;

    @BeforeTest
    public void setUp() {
        Expression a = reference("A");
        b = reference("B");
        rule = new DefaultRule("A", sequence(a, b));
        atStart = new DefaultItem(rule);
        atEndItem = new DefaultItem(rule, 2);
        emptyItem = new DefaultItem(new DefaultRule("A", empty()), 0);
    }

    @Test
    public void hasNextAtStart() {
        assertTrue(atStart.hasNext());
    }

    @Test
    public void hasNextAtEnd() {
        assertFalse(atEndItem.hasNext());
    }

    @Test
    public void hasNextEmptyItem() {
        assertFalse(emptyItem.hasNext());
    }

    @Test
    public void next() {
        final Expression next = atStart.next();
        assertTrue(next instanceof ForwardReference);
        assertSame(((ForwardReference) next).getValue(), "A");
    }

    @Test
    public void getRule() {
        assertSame(rule, atStart.getRule());
    }

    @Test
    public void nextItemAtStart() {
        final Item item = atStart.nextItem();
        assertSame(item.getRule(), rule);
        assertSame(item.next(), b);
    }

    @Test
    public void equality() throws IOException, ClassNotFoundException {
        final Item item = new RandomUtil().anItem();
        assertEquals(SerializationUtils.clone(item), item);
    }

    @Test
    public void nextItemAtEnd() {
        assertNull(atEndItem.nextItem());
    }

    @Test
    public void nextItemEmptyItem() {
        assertNull(atEndItem.nextItem());
    }
}
