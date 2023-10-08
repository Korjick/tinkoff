package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {

    @Test
    @DisplayName("Проверка заданных значений")
    void testCountK() {
        int value = 8;
        int shift = 1;
        int result = Task7.rotateRight(value, shift);
        assertThat(result).isEqualTo(4);

        value = 16;
        shift = 1;
        result = Task7.rotateLeft(value, shift);
        assertThat(result).isEqualTo(1);

        value = 17;
        shift = 2;
        result = Task7.rotateLeft(value, shift);
        assertThat(result).isEqualTo(6);
    }
}
