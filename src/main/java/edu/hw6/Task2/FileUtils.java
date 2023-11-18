package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileUtils {

    public static final String CP_FILE_APPEND = " — копия";
    public static final String CP_NUM_FILE_APPEND = " (%d)";
    private static final String EMPTY_STRING = "";
    private static final String FILE_EXTENSION = ".";
    private static final int SPLIT_SIZE = 2;

    private FileUtils() {

    }

    public static boolean cloneFile(Path path) throws IOException {

        if (!Files.exists(path)) {
            return path.toFile().createNewFile();
        }

        String pathFullName = path.getFileName().toString();
        int extensionIdx = pathFullName.lastIndexOf(FILE_EXTENSION);
        String fileName = pathFullName.substring(0, extensionIdx);
        String fileExtension = pathFullName.substring(extensionIdx);

        try (Stream<Path> stream = Files.list(path.toAbsolutePath().getParent())) {
            long count = stream
                .filter(el -> !el.toFile().isDirectory())
                .filter(el -> el.getFileName().toString().contains(fileName))
                .filter(el -> {

                    var splitted = el.getFileName().toString().split(CP_FILE_APPEND);
                    if (splitted.length != SPLIT_SIZE) {
                        return false;
                    }

                    return splitted[0].strip().equals(fileName);
                }).count();

            String format;
            if (count == 0) {
                format = EMPTY_STRING;
            } else {
                format = String.format(CP_NUM_FILE_APPEND, count);
            }
            return path.toAbsolutePath()
                .getParent()
                .resolve(fileName + CP_FILE_APPEND + format + fileExtension)
                .toFile()
                .createNewFile();
        }
    }
}
