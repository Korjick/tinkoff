package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {

    @Test
    @DisplayName("Проверка значений на палиндром")
    void testIsPalindromeDescendant() {
        int value = 631;
        boolean result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(false);

        value = 2122;
        result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(false);

        value = 11211230;
        result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(true);

        value = 13001120;
        result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(true);

        value = 23336014;
        result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(true);

        value = 11;
        result = Task5.isPalindromeDescendant(value);
        assertThat(result).isEqualTo(true);
    }
}
