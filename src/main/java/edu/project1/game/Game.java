package edu.project1.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class Game {

    private static final String replaceCharacter = "*";

    private final BufferedReader bufferedSystemIn;
    private final int guessCount;
    private final String guessWord;
    private final StringBuilder censoredWord;

    private GameState gameState;
    private int guessAttempts;

    public Game(InputStream inputStream, int guessCount, String guessWord) {

        bufferedSystemIn = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        this.guessCount = guessCount;
        this.guessWord = guessWord;

        censoredWord = new StringBuilder(
            guessWord.replaceAll(GameRuleUtils.anyCharacterPattern.pattern(), replaceCharacter));
        gameState = GameState.IN_GAME;
        guessAttempts = 1;
    }

    public void start() throws IOException {
        String line;

        System.out.printf("Game started. Censored word: %s. Guess a letter:\n", censoredWord);
        String guessWordProcess = GameRuleUtils.processInput(guessWord);

        while ((line = bufferedSystemIn.readLine()) != null) {

            if (!GameRuleUtils.isGuessLetterCorrect(line)) {
                System.out.print("A typo has been spotted. Please re-enter letter\n");
                continue;
            }

            String letter = GameRuleUtils.processInput(line);
            List<Integer> foundIndexes = GameRuleUtils.findAllLetterIndexesInWord(guessWordProcess, letter);

            if (!foundIndexes.isEmpty()) {
                System.out.print("Hit!\n");
                foundIndexes.forEach(i -> censoredWord.setCharAt(i, guessWord.charAt(i)));

                if (censoredWord.toString().equals(guessWord)) {
                    gameState = GameState.WON;
                }
            } else {
                System.out.printf("Missed, mistake %d out of %d.\n".formatted(guessAttempts, guessCount));
                guessAttempts++;

                if (guessAttempts - 1 == guessCount) {
                    gameState = GameState.LOST;
                }
            }

            System.out.printf("The word: %s\n", censoredWord.toString());

            if (gameState.equals(GameState.IN_GAME)) {
                System.out.print("Guess a letter:\n");
            } else {
                break;
            }
        }

        System.out.printf(gameState.equals(GameState.WON) ? "You won!" : "You lost! The word was: %s", guessWord);
    }
}
