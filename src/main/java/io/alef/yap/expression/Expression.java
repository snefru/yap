package io.alef.yap.expression;


import java.io.Serializable;

public interface Expression extends Serializable {

    Expression apply(Evaluator evaluator);
}
