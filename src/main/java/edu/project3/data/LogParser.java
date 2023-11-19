package edu.project3.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings(value = {"OperatorWrap", "MultipleStringLiterals"})
public final class LogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^([\\d.]+)\\s" + // Remote address
            "(\\S+)\\s" +     // Remote user
            "(\\S+)\\s" +     // User logname (not used, but parsed)
            "\\[([\\w:/]+\\s[+\\-]\\d{4})\\]\\s" + // Date and time
            "\"(.+?)\"\\s" +  // Request
            "(\\d{3})\\s" +   // Status code
            "(\\d+)\\s" +     // Body bytes sent
            "\"(.*?)\"\\s" +  // Referer
            "\"(.*?)\"$"      // User agent
    );

    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private LogParser() {

    }

    public static Stream<LogRecord> parse(URI uri) throws IOException {
        Stream.Builder<LogRecord> recordStream = Stream.builder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            uri.toURL().openStream(),
            StandardCharsets.UTF_8
        ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    recordStream.add(parse(line));
                } catch (Exception ignored) {
                }
            }
        }
        return recordStream.build();
    }

    @SuppressWarnings("MagicNumber")
    public static LogRecord parse(String logRecord) throws IllegalArgumentException {
        Matcher matcher = LOG_PATTERN.matcher(logRecord);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid log entry format");
        }

        LocalDateTime dateTime = LocalDateTime.parse(matcher.group(4), DATE_FORMATTER);
        return new LogRecord(
            matcher.group(1),
            matcher.group(2),
            dateTime,
            matcher.group(5),
            Integer.parseInt(matcher.group(6)),
            Integer.parseInt(matcher.group(7)),
            matcher.group(8),
            matcher.group(9)
        );
    }
}
