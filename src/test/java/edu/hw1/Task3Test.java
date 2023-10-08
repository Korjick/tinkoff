package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class Task3Test {

    @Test
    @DisplayName("Проверка массивов с обычными и краевыми значениями")
    void testSimpleNestable() {
        int[] a = new int[] {1, 2, 3, 4};
        int[] b = new int[] {0, 6};
        boolean result = Task3.isNestable(a, b);
        assertThat(result).isEqualTo(true);

        a = new int[] {3, 1};
        b = new int[] {4, 0};
        result = Task3.isNestable(a, b);
        assertThat(result).isEqualTo(true);

        a = new int[] {9, 9, 8};
        b = new int[] {8, 9};
        result = Task3.isNestable(a, b);
        assertThat(result).isEqualTo(false);

        a = new int[] {1, 2, 3, 4};
        b = new int[] {2, 3};
        result = Task3.isNestable(a, b);
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка некорретных входных данных массива")
    void testThrowableNestable() {
        assertThatThrownBy(() -> {
            int[] a = new int[] {};
            int[] b = new int[] {0, 6};
            Task3.isNestable(a, b);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            int[] a = new int[] {5, 3};
            int[] b = new int[] {};
            Task3.isNestable(a, b);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            int[] a = null;
            int[] b = new int[] {};
            Task3.isNestable(a, b);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            int[] a = new int[] {1, 4};
            int[] b = null;
            Task3.isNestable(a, b);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
