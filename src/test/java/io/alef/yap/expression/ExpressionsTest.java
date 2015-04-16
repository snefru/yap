package io.alef.yap.expression;

import org.testng.annotations.Test;

import static io.alef.yap.expression.Expressions.reference;
import static io.alef.yap.expression.Expressions.sequence;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

public class ExpressionsTest {

    @Test
    public void seq() {
        Expression u = sequence(reference("A"), reference("B"), reference("C"));
        assertNotNull(u);
        Expression v = sequence(reference("A"), reference("B"), reference("C"));
        assertNotNull(v);
        assertSame(u, v);
    }
}
