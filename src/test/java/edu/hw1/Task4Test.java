package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @Test
    @DisplayName("Проверка исправленных строк")
    void testFixString() {
        String value = "123456";
        String result = Task4.fixString(value);
        assertThat(result).isEqualTo("214365");

        value = "hTsii  s aimex dpus rtni.g";
        result = Task4.fixString(value);
        assertThat(result).isEqualTo("This is a mixed up string.");

        value = "badce";
        result = Task4.fixString(value);
        assertThat(result).isEqualTo("abcde");
    }
}
