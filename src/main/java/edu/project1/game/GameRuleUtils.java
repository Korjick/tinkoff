package edu.project1.game;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameRuleUtils {

    public static final Pattern WRONG_WORD_PATTERN = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]|\\d|\\s");
    public static final int MIN_WORD_LEN = 3;
    public static final int ATTEMPT_COUNT = 5;
    public static final Pattern ANY_CHARACTER_PATTERN = Pattern.compile(".");
    private final static Logger LOGGER = LogManager.getLogger();

    private GameRuleUtils() {

    }

    public static boolean isGeneratedWordCorrect(String word) {
        return word != null && word.length() >= MIN_WORD_LEN && !WRONG_WORD_PATTERN.matcher(word).find();
    }

    public static boolean isGuessLetterCorrect(String letter) {
        return letter != null && letter.length() == 1;
    }

    public static String processInput(String string) {
        return string.toLowerCase();
    }

    public static List<Integer> findAllLetterIndexesInWord(String word, String letter) {
        return IntStream
            .iterate(
                word.indexOf(letter),
                index -> index >= 0,
                index -> word.indexOf(letter, index + 1))
            .boxed()
            .collect(Collectors.toList());
    }

    public static void printLog(String message, Object... params) {
        LOGGER.printf(Level.INFO, message, params);
    }
}
