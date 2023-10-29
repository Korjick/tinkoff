package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    @Test
    void convertToRomanTest() {
        int value = 2;
        String result = Task4.convertToRoman(value);
        assertThat(result).isEqualTo("II");

        value = 12;
        result = Task4.convertToRoman(value);
        assertThat(result).isEqualTo("XII");

        value = 16;
        result = Task4.convertToRoman(value);
        assertThat(result).isEqualTo("XVI");
    }
}
