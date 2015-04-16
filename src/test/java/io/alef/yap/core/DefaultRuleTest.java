package io.alef.yap.core;

import io.alef.yap.RandomUtil;
import io.alef.yap.Rule;
import io.alef.yap.expression.Sequence;
import org.apache.commons.lang3.SerializationUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class DefaultRuleTest {

    public static final String LHS = "lhs";
    public static final Sequence RHS = new Sequence();

    @Test(expectedExceptions = NullPointerException.class)
    public void nullException() {
        new DefaultRule(null, null);
    }

    @Test
    public void getLhs() {
        assertEquals(new DefaultRule(LHS, RHS).getLhs(), LHS);
    }

    @Test
    public void getRhs() {
        assertSame(new DefaultRule(LHS, RHS).getRhs(), RHS);
    }


    @Test
    public void equality() {
        final Rule rule = new RandomUtil().aRule();
        assertEquals(SerializationUtils.clone(rule), rule);
    }
}
