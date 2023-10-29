package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("Тестирование сортировки списка контактов")
    void parseContactsTest() {
        List<String> value = List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");
        List<Task5.Contact> result = Task5.parseContacts(value, Task5.ASC_PARAMETER);
        assertThat(result).isEqualTo(List.of(
            new Task5.Contact("Thomas Aquinas"),
            new Task5.Contact("Rene Descartes"),
            new Task5.Contact("David Hume"),
            new Task5.Contact("John Locke")));

        value = List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss");
        result = Task5.parseContacts(value, Task5.DESC_PARAMETER);
        assertThat(result).isEqualTo(List.of(
            new Task5.Contact("Carl Gauss"),
            new Task5.Contact("Leonhard Euler"),
            new Task5.Contact("Paul Erdos")));

        value = List.of();
        result = Task5.parseContacts(value, Task5.DESC_PARAMETER);
        assertThat(result).isEqualTo(List.of());

        value = null;
        result = Task5.parseContacts(value, Task5.DESC_PARAMETER);
        assertThat(result).isEqualTo(List.of());
    }
}
