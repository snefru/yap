package io.alef.yap.expression;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class SequenceTest {

    public static final Expression A = new ForwardReference("A");
    public static final Expression B = new ForwardReference("B");
    public static final Expression C = new ForwardReference("C");

    public static final Expression[] EXPRESSIONS = new Expression[]{A, B, C};

    @Test
    public void get() {
        final Sequence sequence = Expressions.sequence(A, B, C);
        assertEquals(sequence.get(0), A);
        assertEquals(sequence.get(1), B);
        assertEquals(sequence.get(2), C);
    }

    @Test
    public void isEmptyTrue() {
        assertTrue(new Sequence().isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        assertFalse(new Sequence(new Pattern("a")).isEmpty());
    }

    @Test
    public void getElementsFull() {
        final List<Expression> elements = Arrays.asList(EXPRESSIONS);
        final Sequence sequence = new Sequence(elements);
        assertEquals(sequence.getElements(), elements);
    }

    @Test
    public void sizeEmpty() {
        assertEquals(new Sequence().size(), 0);
    }

    @Test
    public void sizeFull() {
        assertEquals(new Sequence(EXPRESSIONS).size(), EXPRESSIONS.length);
    }
}
