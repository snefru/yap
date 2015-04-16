package io.alef.yap;

import io.alef.yap.core.DefaultGrammar;
import io.alef.yap.core.DefaultItem;
import io.alef.yap.core.DefaultRule;
import io.alef.yap.expression.ForwardReference;
import io.alef.yap.expression.Pattern;
import io.alef.yap.expression.Sequence;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

import static io.alef.yap.expression.Expressions.*;

public class RandomUtil {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MAX_SEQUENCE_LENGTH = 10;

    private final String[] names;
    private final Pattern[] patterns;
    private final Sequence[] sequences;

    public RandomUtil() {
        this(100, 200, 600);
    }

    public RandomUtil(int patternCount, int symbolCount, int sequenceCount) {
        this.names = anyNames(symbolCount);
        this.patterns = anyPatterns(patternCount);
        this.sequences = anySequences(sequenceCount);
    }

    private Pattern[] anyPatterns(final int count) {
        final Set<Pattern> patterns = new HashSet<>();
        while (patterns.size() < count) {
            patterns.add(new Pattern(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(1, MAX_NAME_LENGTH))));
        }
        return patterns.toArray(new Pattern[count]);
    }

    private String[] anyNames(int count) {
        final Set<String> names = new HashSet<>();
        while (names.size() < count) {
            names.add(StringUtils.capitalize(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(1, MAX_NAME_LENGTH)).toLowerCase()));
        }
        return names.toArray(new String[count]);
    }

    private Sequence[] anySequences(int count) {
        final Set<Sequence> sequences = new HashSet<>();
        while (sequences.size() < count) {
            final int type = RandomUtils.nextInt(0, 3);
            switch (type) {
                case 0:
                    sequences.add(new Sequence());
                    break;
                case 1:
                    final int length = RandomUtils.nextInt(0, MAX_SEQUENCE_LENGTH);
                    ForwardReference[] references = new ForwardReference[length];
                    for (int i = 0; i < length; i++) {
                        references[i] = new ForwardReference(aName());
                    }
                    sequences.add(new Sequence(references));
                    break;
                case 2:
                    sequences.add(new Sequence(aPattern()));
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        return sequences.toArray(new Sequence[count]);
    }


    public Grammar anyGrammar() {
        final DefaultGrammar.Builder builder = new DefaultGrammar.Builder();
        return builder.startSymbol("exp")
                .define("exp",
                        sequence(reference("exp"), pattern("*"), reference("exp"))
                        , sequence(reference("exp"), pattern("+"), reference("exp"))
                        , sequence(pattern("int"))
                        , sequence(pattern("real")))
                .build();
    }

    public Pattern aPattern() {
        return patterns[RandomUtils.nextInt(0, patterns.length - 1)];
    }

    public String aName() {
        return names[RandomUtils.nextInt(0, names.length - 1)];
    }

    public Sequence aSequence() {
        return sequences[RandomUtils.nextInt(0, sequences.length - 1)];
    }

    public Rule aRule() {
        return new DefaultRule(aName(), aSequence());
    }

    public Item anItem() {
        final Rule rule = aRule();
        return new DefaultItem(rule, RandomUtils.nextInt(0, rule.getRhs().size()));
    }

}
