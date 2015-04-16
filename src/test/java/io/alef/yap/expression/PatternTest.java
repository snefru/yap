package io.alef.yap.expression;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PatternTest {

    @Test
    public void getValue() {
        final String value = "[a-zA-Z][a-zA-Z0-9]";
        final Pattern pattern = new Pattern(value);
        assertEquals(pattern.getValue(), value);
    }

    @Test
    public void matches() {
        final Pattern pattern = new Pattern("\\d\\d\\d([,\\s])?\\d\\d\\d\\d");
        assertTrue(pattern.matches("123 3323"));
    }

}
