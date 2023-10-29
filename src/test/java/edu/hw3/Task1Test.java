package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void atbashTest() {
        String value = "Hello world!";
        String result = Task1.atbash(value);
        assertThat(result).isEqualTo("Svool dliow!");

        value = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        result = Task1.atbash(value);
        assertThat(result).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
}
