package io.alef.yap.expression;

import org.testng.annotations.Test;

import static org.testng.Assert.assertSame;

public class ForwardReferenceTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void nullReference() {
        new ForwardReference(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void emptyReference() {
        new ForwardReference("");
    }

    @Test
    public void getValue() {
        assertSame(new ForwardReference("reference").getValue(), "reference");
    }
}
