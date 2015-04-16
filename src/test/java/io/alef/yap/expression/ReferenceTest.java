package io.alef.yap.expression;

import org.testng.annotations.Test;

public class ReferenceTest {


    @Test(expectedExceptions = NullPointerException.class)
    public void nullReference() {
        new Reference(null);
    }
}
