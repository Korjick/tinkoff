package edu.hw6.Task3;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.regex.Pattern;

public final class FileFilters {

    private static final String EXTENSION = ".";

    private FileFilters() {

    }

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;
    private static final int MAGIC_BYTE = 0xFF;

    public static AbstractFilter largerThan(long sizeInBytes) {
        return path -> {
            try {
                return Files.size(path) > sizeInBytes;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String ext) {
        String glob = ext.substring(ext.lastIndexOf(EXTENSION));
        return path -> path.toString().endsWith(glob);
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).find();
    }

    public static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            try (InputStream is = Files.newInputStream(path)) {
                for (byte b : bytes) {
                    if (is.read() != (b & MAGIC_BYTE)) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }
}
