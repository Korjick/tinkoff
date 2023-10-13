package edu.project1;

import edu.project1.dao.IWordsDao;
import edu.project1.dao.ListWordsDao;
import edu.project1.game.Game;
import edu.project1.game.GameRuleUtils;
import java.io.IOException;

public class HangmanGame {

    private HangmanGame() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        try {
            IWordsDao wordsDao = new ListWordsDao();
            String word = wordsDao.getRandomWord();

            if (!GameRuleUtils.isGeneratedWordCorrect(word)) {
                throw new IllegalArgumentException();
            }

            Game game = new Game(System.in, GameRuleUtils.ATTEMPT_COUNT, word);
            game.start();
        } catch (IOException e) {
            GameRuleUtils.printLog("Unsupported input system");
        } catch (IllegalArgumentException e) {
            GameRuleUtils.printLog(
                "Incorrect word spotted. "
                    + "Word must contains at least %d character & not contain %s pattern. "
                    + "Game stopped!", GameRuleUtils.MIN_WORD_LEN, GameRuleUtils.WRONG_WORD_PATTERN.pattern());
        } catch (Exception e) {
            GameRuleUtils.printLog(e.getMessage());
        }
    }
}
