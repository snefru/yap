package io.alef.yap;

import io.alef.yap.expression.Sequence;

import java.io.Serializable;

public interface Rule extends Serializable {

    String getLhs();

    Sequence getRhs();
}
