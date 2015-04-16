package io.alef.yap;

import io.alef.yap.expression.Expression;

import java.io.Serializable;

public interface Item extends Serializable {

    Rule getRule();

    Item nextItem();

    boolean hasNext();

    Expression next();

    boolean atStart();
}
