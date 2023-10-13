package edu.project1.game;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class GameRuleUtils {

    public static final Pattern wrongWordPattern = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]|\\d|\\s");
    public static final int minWordLen = 3;
    public static final Pattern anyCharacterPattern = Pattern.compile(".");

    public static boolean isGeneratedWordCorrect(String word) {
        return word != null && word.length() >= minWordLen && !wrongWordPattern.matcher(word).find();
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
}
