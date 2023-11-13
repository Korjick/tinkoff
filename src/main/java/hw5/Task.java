package hw5;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {

    private static final DateTimeFormatter AVERAGE_DURATION_DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private static final String AVERAGE_DURATION_SEPARATOR = " - ";

    private static final String PARSE_DATA_SEPARATOR = " ";
    private static final DateTimeFormatter[] PARSE_DATA_DATE_FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("yyyy-MM-d"),
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("d/M/yy")
    };
    private static final String PARSE_DATA_TOMORROW = "tomorrow";
    private static final String PARSE_DATA_TODAY = "today";
    private static final String PARSE_DATA_YESTERDAY = "yesterday";
    private static final String PARSE_DATA_DAY_AGO = "day ago";
    private static final String PARSE_DATA_DAYS_AGO = "days ago";

    private static final Pattern PASSWORD_CORRECT_PATTERN = Pattern.compile("[~!@#$%^&*|]");

    private static final Pattern LICENSE_PLATE_CORRECT_PATTERN = Pattern.compile("^[А-Я]{1}\\d{3}[А-Я]{2}\\d{3}$");

    private static final Pattern A01_THIRD_SYMB_PATTERN = Pattern.compile(".{2}0.*");
    private static final Pattern A01_START_END_PATTERN = Pattern.compile("^(0|1)([01]*)(\\1)$");
    private static final Pattern A01_LEN_PATTERN = Pattern.compile("^[01]{1,3}$");

    private static final Pattern A01_CONSISTENCE_PATTERN = Pattern.compile("^(?!.*11)[01]+$");
    private static final Pattern A01_ODD_PATTERN = Pattern.compile("^(?:[01]{2})*[01]$");

    private Task() {

    }

    public static Duration calculateAverageDuration(List<String> sessions) {
        Duration totalDuration = Duration.ZERO;

        for (String session : sessions) {
            String[] parts = session.split(AVERAGE_DURATION_SEPARATOR);
            LocalDateTime startDateTime = LocalDateTime.parse(parts[0], AVERAGE_DURATION_DATE_FORMATTER);
            LocalDateTime endDateTime = LocalDateTime.parse(parts[1], AVERAGE_DURATION_DATE_FORMATTER);
            Duration sessionDuration = Duration.between(startDateTime, endDateTime);
            totalDuration = totalDuration.plus(sessionDuration);
        }

        return Duration.of(totalDuration.toMinutes() / sessions.size(), ChronoUnit.MINUTES);
    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> findFridayThe13(int year) {
        List<LocalDate> fridayThe13ths = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayThe13ths.add(date);
            }
        }

        return fridayThe13ths;
    }

    public static LocalDate findNextFridayThe13(LocalDate date) {
        LocalDate nextFriday13 = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday13.getDayOfMonth() != 13) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return nextFriday13;
    }

    @SuppressWarnings("ReturnCount")
    public static Optional<LocalDate> parseDate(String string) {
        for (DateTimeFormatter formatter : PARSE_DATA_DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(string, formatter);
                return Optional.of(date);
            } catch (Exception ignored) {

            }
        }

        if (string.equalsIgnoreCase(PARSE_DATA_TOMORROW)) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else if (string.equalsIgnoreCase(PARSE_DATA_TODAY)) {
            return Optional.of(LocalDate.now());
        } else if (string.equalsIgnoreCase(PARSE_DATA_YESTERDAY)) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (string.toLowerCase().endsWith(PARSE_DATA_DAY_AGO)
            || string.toLowerCase().endsWith(PARSE_DATA_DAYS_AGO)) {
            try {
                int daysAgo = Integer.parseInt(string.split(PARSE_DATA_SEPARATOR)[0]);
                return Optional.of(LocalDate.now().minusDays(daysAgo));
            } catch (NumberFormatException ignored) {

            }
        }

        return Optional.empty();
    }

    public static boolean passwordCorrect(String password) {
        return PASSWORD_CORRECT_PATTERN.matcher(password).find();
    }

    public static boolean isValidLicensePlate(String licensePlate) {
        return LICENSE_PLATE_CORRECT_PATTERN.matcher(licensePlate).find();
    }

    public static boolean isSubsequence(String s, String t) {
        String regex = ".*" + s + ".*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(t);
        return matcher.matches();
    }

    public static boolean containsAtLeastThreeCharsWithThirdZero(String input) {
        return A01_THIRD_SYMB_PATTERN.matcher(input).find();
    }

    public static boolean startsAndEndsWithSameCharacter(String input) {
        return A01_START_END_PATTERN.matcher(input).find();
    }

    public static boolean hasLengthBetweenOneAndThree(String input) {
        return A01_LEN_PATTERN.matcher(input).find();
    }
}
