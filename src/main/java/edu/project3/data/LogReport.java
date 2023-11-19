package edu.project3.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LogReport {

    private final Map<String, Long> resourceCounts;
    private final Map<Map.Entry<Integer, String>, Long> statusCodeCounts;
    private long totalRequests;
    private long totalBytes;
    private LocalDate from;
    private LocalDate to;

    private LogReport() {
        resourceCounts = new HashMap<>();
        statusCodeCounts = new HashMap<>();
    }

    public static LogReport parse(Stream<LogRecord> logStream, LocalDate from, LocalDate to) {
        LogReport report = new LogReport();
        report.from = from;
        report.to = to;
        Predicate<LogRecord> timePredicate = rec -> (from == null || rec.dateTime().toLocalDate().isAfter(from))
            && (to == null || rec.dateTime().toLocalDate().isBefore(to));

        logStream.filter(timePredicate)
            .forEach(rec -> {
                report.totalRequests++;
                report.totalBytes += rec.bodyBytesSent();
                report.resourceCounts.merge(rec.request(), 1L, Long::sum);
                report.statusCodeCounts.merge(
                    new AbstractMap.SimpleEntry<>(rec.statusCode(), parseStatus(rec.statusCode())),
                    1L,
                    Long::sum
                );
            });
        return report;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public void save(Path output) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("#### Общая информация\n\n");
            writer.write("|        Метрика        |   Значение   |\n");
            writer.write("|:---------------------:|-------------:|\n");
            writer.write("|       Файл(-ы)        | %s |\n".formatted(output.getFileName().toString()));
            writer.write("|    Начальная дата     | " + formatDate(from) + " |\n");
            writer.write("|     Конечная дата     | " + formatDate(to) + " |\n");
            writer.write("|  Количество запросов  | " + totalRequests + " |\n");
            writer.write("| Средний размер ответа | "
                + (totalRequests > 0 ? (totalBytes / totalRequests) + "b" : "0b") + " |\n\n");

            writer.write("#### Запрашиваемые ресурсы\n\n");
            writeTableSingle(writer, resourceCounts, "|     Ресурс      | Количество |");

            writer.write("#### Коды ответа\n\n");
            writeTableEntry(writer, statusCodeCounts, "| Код |          Имя          | Количество |");
        }
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "-";
    }

    @SuppressWarnings("MultipleStringLiterals")
    private <T> void writeTableSingle(BufferedWriter writer, Map<T, Long> counts, String header) throws IOException {
        writer.write(header + "\n");
        for (Map.Entry<T, Long> entry : counts.entrySet()) {
            writer.write("| " + entry.getKey() + " | " + entry.getValue() + " |\n");
        }
        writer.write("\n");
    }

    @SuppressWarnings("MultipleStringLiterals")
    private <T, S> void writeTableEntry(BufferedWriter writer, Map<Map.Entry<T, S>, Long> counts, String header)
        throws IOException {
        writer.write(header + "\n");
        for (Map.Entry<Map.Entry<T, S>, Long> entry : counts.entrySet()) {
            writer.write("| " + entry.getKey().getKey() + " | " + entry.getKey().getValue()
                + " | " + entry.getValue() + " |\n");
        }
        writer.write("\n");
    }

    @SuppressWarnings("MagicNumber")
    private static String parseStatus(int status) {
        return switch (status) {
            case 200 -> "OK";
            case 301 -> "Moved Permanently";
            case 300 -> "Bad Request";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            case 502 -> "Bad Gateway";
            default -> "";
        };
    }
}
