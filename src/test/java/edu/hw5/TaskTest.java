package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    @DisplayName("Нужно посчитать, сколько времени в среднем посетители проводят времени за один сеанс")
    void calculateAverageDuration() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        Duration expected = Duration.ofHours(3).plusMinutes(40);
        Duration result = Task.calculateAverageDuration(sessions);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ищет все пятницы, выпадающие на 13-е число в заданном году")
    void findFridayThe13() {
        int year = 1925;
        List<LocalDate> result = Task.findFridayThe13(year);
        List<LocalDate> expected =
            List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)
            );

        assertEquals(expected, result);

        year = 2024;
        result = Task.findFridayThe13(year);
        expected =
            List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            );

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Для заданной даты ищет следующую ближайшую пятницу 13")
    void findNextFridayThe13() {
        LocalDate currentDate = LocalDate.of(2024, 9, 13);
        LocalDate result = Task.findNextFridayThe13(currentDate);
        LocalDate expected = LocalDate.of(2024, 12, 13);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Распознает строку в одном из форматов")
    void parseDate() {
        String[] dates = {
            "2020-10-10",
            "2020-12-2",
            "1/3/1976",
            "1/3/20",
            "tomorrow",
            "today",
            "yesterday",
            "1 day ago",
            "2234 days ago",
            "illegal"};
        Optional[] expected = {
            Optional.of(LocalDate.of(2020, 10, 10)),
            Optional.of(LocalDate.of(2020, 12, 2)),
            Optional.of(LocalDate.of(1976, 3, 1)),
            Optional.of(LocalDate.of(2020, 3, 1)),
            Optional.of(LocalDate.now().plusDays(1)),
            Optional.of(LocalDate.now()),
            Optional.of(LocalDate.now().minusDays(1)),
            Optional.of(LocalDate.now().minusDays(1)),
            Optional.of(LocalDate.now().minusDays(2234)),
            Optional.empty()
        };

        for (int i = 0; i < dates.length; i++) {
            Optional<LocalDate> result = Task.parseDate(dates[i]);
            assertEquals(expected[i], result);
        }
    }

    @Test
    @DisplayName("все пароли содержали хотя бы один из следующих символов: ~ ! @ # $ % ^ & * |")
    void passwordCorrect() {
        String password = "Test1";
        assertFalse(Task.passwordCorrect(password));
        password = "Test!";
        assertTrue(Task.passwordCorrect(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177"})
    @DisplayName("Регулярное выражение для валидации российских номерных знаков")
    void isValidLicensePlateValid(String input) {
        assertTrue(Task.isValidLicensePlate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777"})
    @DisplayName("Регулярное выражение для валидации российских номерных знаков")
    void isValidLicensePlateInvalid(String input) {
        assertFalse(Task.isValidLicensePlate(input));
    }

    @Test
    @DisplayName("Заданная строка S является подпоследовательностью другой строки T")
    void isSubsequence() {
        String t = "achfdbaabgabcaabg";
        String s = "abc";
        assertTrue(Task.isSubsequence(s, t));
        s = "abd";
        assertFalse(Task.isSubsequence(s, t));
    }

    @Test
    void containsAtLeastThreeCharsWithThirdZero() {
        String input = "100";
        assertTrue(Task.containsAtLeastThreeCharsWithThirdZero(input));
        input = "101";
        assertFalse(Task.containsAtLeastThreeCharsWithThirdZero(input));
        input = "10";
        assertFalse(Task.containsAtLeastThreeCharsWithThirdZero(input));
    }

    @Test
    void startsAndEndsWithSameCharacter() {
        String input = "101010010101001000011111";
        assertTrue(Task.startsAndEndsWithSameCharacter(input));
        input = "101010010101001000011110";
        assertFalse(Task.startsAndEndsWithSameCharacter(input));
    }

    @Test
    void hasLengthBetweenOneAndThree() {
        String input = "100";
        assertTrue(Task.hasLengthBetweenOneAndThree(input));
        input = "1";
        assertTrue(Task.hasLengthBetweenOneAndThree(input));
        input = "";
        assertFalse(Task.hasLengthBetweenOneAndThree(input));
        input = "1011";
        assertFalse(Task.hasLengthBetweenOneAndThree(input));
    }

    @Test
    void hasNoConsistenceOnes() {
        String input = "100";
        assertTrue(Task.hasNoConsistenceOnes(input));
        input = "11";
        assertFalse(Task.hasNoConsistenceOnes(input));
    }

    @Test
    void hasOddLengthString() {
        String input = "100";
        assertTrue(Task.hasOddLengthString(input));
        input = "1111";
        assertFalse(Task.hasOddLengthString(input));
    }

    @Test
    void hasQuantityOfZeroModThree() {
        String input = "10001";
        assertTrue(Task.hasQuantityOfZeroModThree(input));
        input = "100010";
        assertFalse(Task.hasQuantityOfZeroModThree(input));
    }

    @Test
    void hasAnyExceptDoubleOrTripleOnes() {
        String input = "10001";
        assertTrue(Task.hasAnyExceptDoubleOrTripleOnes(input));
        input = "11";
        assertFalse(Task.hasAnyExceptDoubleOrTripleOnes(input));
        input = "111";
        assertFalse(Task.hasAnyExceptDoubleOrTripleOnes(input));
    }
}
