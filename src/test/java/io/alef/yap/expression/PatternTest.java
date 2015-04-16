package io.alef.yap.expression;

import org.testng.annotations.Test;

import static org.testng.Assert.assertSame;

public class PatternTest {

    @Test
    public void equalityInvariant() {
        final String value = "[a-zA-Z][a-zA-Z0-9";
        final Pattern pattern = new Pattern(value);
        assertSame(value, pattern.getValue());
    }


}
