package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Тестирование компаратора с проверкой null значения в TreeMap")
    void treeMapNullComparatorTest(){
        Map<String, String> tree = new TreeMap<>(Task7.getNullFriendlyComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
