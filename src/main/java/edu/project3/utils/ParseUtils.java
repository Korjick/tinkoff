package edu.project3.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("MultipleStringLiterals")
public final class ParseUtils {

    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    private static final Map<String, String> SUPPORT_OUTPUTS_FORMAT = new HashMap<>();
    private static final String DEFAULT_OUTPUT_FORMAT = ".md";
    private static final String DEFAULT_OUTPUT_DIRECTORY = "./output";

    static {
        SUPPORT_OUTPUTS_FORMAT.put("markdown", ".md");
        SUPPORT_OUTPUTS_FORMAT.put("adoc", ".adoc");
    }

    private ParseUtils() {

    }

    public static LocalDate parseDateTime(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        return LocalDate.parse(dateTime, DEFAULT_TIME_FORMATTER);
    }

    public static LocalDate parseDateTime(String dateTime, DateTimeFormatter formatter) {
        if (dateTime == null) {
            return null;
        }
        return LocalDate.parse(dateTime, formatter);
    }

    public static String getFileFormat(String format) {
        if (format == null) {
            return DEFAULT_OUTPUT_FORMAT;
        }
        return SUPPORT_OUTPUTS_FORMAT.getOrDefault(format.strip().toLowerCase(), DEFAULT_OUTPUT_FORMAT);
    }

    public static Map.Entry<Boolean, List<URI>> getInputURI(String inputPath) throws IOException {
        try {
            return new AbstractMap.SimpleEntry<>(true, Collections.singletonList(new URL(inputPath).toURI()));
        } catch (Exception e) {
            int splashIdx = inputPath.indexOf("/");
            return new AbstractMap.SimpleEntry<>(false, findFiles(inputPath.substring(0, splashIdx),
                inputPath.substring(splashIdx + 1)));
        }
    }

    private static List<URI> findFiles(String directory, String filePattern) throws IOException {
        Path dirPath = Paths.get(".").resolve(directory);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + filePattern);

        try (Stream<Path> pathStream = Files.walk(dirPath)) {
            return pathStream
                .filter(Files::isRegularFile)
                .filter(path -> pathMatcher.matches(path.getFileName()))
                .map(Path::toUri)
                .collect(Collectors.toList());
        }
    }

    @SuppressWarnings("ParameterAssignment")
    public static Path getOutputPath(String outputPath, URI inputURI, boolean isURL, String fileFormat)
        throws IOException {
        if (outputPath == null) {
            outputPath = DEFAULT_OUTPUT_DIRECTORY;
        }
        Path path = Paths.get(outputPath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        if (!Files.isDirectory(path)) {
            throw new NotDirectoryException("Output path should be a directory");
        }
        path = path.resolve(
            isURL
            ? Paths.get(inputURI.getPath()).getFileName() + fileFormat
            : Paths.get(inputURI).getFileName() + fileFormat);
        return path;
    }
}
