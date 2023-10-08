package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class Task1Test {
    @Test
    @DisplayName("Тестирование правильного времени")
    void testCorrectMinutesAndSeconds() {
        String minutesAndSeconds = "13:56";
        int result = Task1.minutesToSeconds(minutesAndSeconds);

        assertThat(result).isEqualTo(836);
    }

    @Test
    @DisplayName("Тестирование неправильных секунд")
    void testIncorrectSeconds() {
        String incorrectSeconds = "10:60";
        int result = Task1.minutesToSeconds(incorrectSeconds);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тестирование без минут")
    void testOnlySeconds() {
        String onlySeconds = "00:59";
        int result = Task1.minutesToSeconds(onlySeconds);

        assertThat(result).isEqualTo(59);
    }

    @Test
    @DisplayName("Тестирование выброса ошибки при неправильном формате")
    void testIncorrectFormat() {
        String incorrect = "incorrect";

        assertThatThrownBy(() -> {
            Task1.minutesToSeconds(incorrect);
        }).isInstanceOf(NumberFormatException.class);
    }
}
