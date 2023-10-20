package edu.project1;

import edu.project1.game.GameRuleUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HangmanGameTest {

    @Test
    @Order(1)
    @DisplayName("Тестирование ответа с опечаткой")
    public void mistakeTest() {

        final InputStream defaultIS = System.in;
        final PrintStream defaultOS = System.out;

        byte[] inputToByteArray = "тест".getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(inputToByteArray));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HangmanGame.main(new String[]{});

        assertThat(outContent.toString()).contains("A typo has been spotted");

        System.setIn(defaultIS);
        System.setOut(defaultOS);
    }

    @Test
    @Order(2)
    @DisplayName("Тестирование неправильного слова")
    public void incorrectWordTest() {
        String incorrect = "Ия";
        boolean isCorrect = GameRuleUtils.isGeneratedWordCorrect(incorrect);
        assertThat(isCorrect).isEqualTo(false);
    }
}
