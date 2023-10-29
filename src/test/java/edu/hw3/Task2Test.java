package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("Тестирование кластеризации скобок")
    void clusterizeTest() {
        String value = "()()()";
        List<String> result = Task2.clusterize(value);
        assertThat(result).isEqualTo(List.of("()", "()", "()"));

        value = "((()))";
        result = Task2.clusterize(value);
        assertThat(result).isEqualTo(List.of("((()))"));

        value = "((()))(())()()(()())";
        result = Task2.clusterize(value);
        assertThat(result).isEqualTo(List.of("((()))", "(())", "()", "()", "(()())"));

        value = "((())())(()(()()))";
        result = Task2.clusterize(value);
        assertThat(result).isEqualTo(List.of("((())())", "(()(()()))"));

        String illegalValue = "Illegal()";
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(illegalValue));
    }
}
