package io.alef.yap;

import io.alef.yap.expression.Sequence;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface Symbol extends Serializable {

    String getName();

    List<Sequence> getAlternates();

    Set<Rule> getRules();

    Set<Item> getItems();
}
