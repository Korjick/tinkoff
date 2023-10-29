package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void freqDictTest() {
        List<?> value = List.of("a", "bb", "a", "bb");
        Map<?, Integer> result = Task3.freqDict(value);
        assertThat(result).isEqualTo(Map.of("bb", 2, "a", 2));

        value = List.of("this", "and", "that", "and");
        result = Task3.freqDict(value);
        assertThat(result).isEqualTo(Map.of("that", 1, "and", 2, "this", 1));

        value = List.of("код", "код", "код", "bug");
        result = Task3.freqDict(value);
        assertThat(result).isEqualTo(Map.of("код", 3, "bug", 1));

        value = List.of(1, 1, 2, 2);
        result = Task3.freqDict(value);
        assertThat(result).isEqualTo(Map.of(1, 2, 2, 2));
    }
}
