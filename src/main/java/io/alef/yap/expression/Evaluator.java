package io.alef.yap.expression;

public interface Evaluator {

    Expression apply(ForwardReference forwardReference);

    Expression apply(Pattern pattern);

    Expression apply(Sequence sequence);

    Expression apply(Reference reference);
}
