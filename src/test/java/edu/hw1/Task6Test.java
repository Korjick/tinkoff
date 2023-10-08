package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {

    @Test
    @DisplayName("Проверка заданных значений")
    void testCountK() {
        int value = 6621;
        int result = Task6.countK(value, 0);
        assertThat(result).isEqualTo(5);

        value = 6554;
        result = Task6.countK(value, 0);
        assertThat(result).isEqualTo(4);

        value = 1234;
        result = Task6.countK(value, 0);
        assertThat(result).isEqualTo(3);
    }
}
