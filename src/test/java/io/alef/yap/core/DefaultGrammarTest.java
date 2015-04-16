package io.alef.yap.core;

import io.alef.yap.RandomUtil;
import org.testng.annotations.Test;

public class DefaultGrammarTest {

    @Test
    public void getSymbols() {
        new RandomUtil(10, 10, 10).anyGrammar();
    }
}
