package io.alef.yap;

import io.alef.yap.expression.Expression;

import java.io.Serializable;
import java.util.Set;

public interface State extends Serializable {

    Set<Expression> next();

    Set<Item> items();

    State successor(Expression expression);
}
