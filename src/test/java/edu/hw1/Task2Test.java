package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    @Test
    @DisplayName("Тестирование натурального числа")
    void testNaturalNumber() {
        int digit = 4666;
        int result = Task2.countDigits(digit);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Тестирование нуля")
    void testZero() {
        int digit = 0;
        int result = Task2.countDigits(digit);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Тестирование отрицательного числа")
    void testNegativeNumber() {
        int digit = -333;
        int result = Task2.countDigits(digit);

        assertThat(result).isEqualTo(3);
    }
}
