package edu.project1;

import edu.project1.dao.IWordsDao;
import edu.project1.dao.ListWordsDao;
import edu.project1.game.Game;
import edu.project1.game.GameRuleUtils;
import java.io.IOException;

public class HangmanGame {
    public static void main(String[] args) {
        try {
            IWordsDao wordsDao = new ListWordsDao();
            String word = wordsDao.getRandomWord();

            if (!GameRuleUtils.isGeneratedWordCorrect(word)) {
                throw new IllegalArgumentException();
            }

            Game game = new Game(System.in, 5, word);
            game.start();
        } catch (IOException e) {
            System.out.println("Unsupported input system");
        } catch (IllegalArgumentException e) {
            System.out.printf(
                "Incorrect word spotted. " +
                    "Word must contains at least %d character & not contain %s pattern. " +
                    "Game stopped!", GameRuleUtils.minWordLen, GameRuleUtils.wrongWordPattern.pattern());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
