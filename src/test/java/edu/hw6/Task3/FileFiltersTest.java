package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.FileFilters.globMatches;
import static edu.hw6.Task3.FileFilters.largerThan;
import static edu.hw6.Task3.FileFilters.magicNumber;
import static edu.hw6.Task3.FileFilters.READABLE;
import static edu.hw6.Task3.FileFilters.regexContains;
import static edu.hw6.Task3.FileFilters.REGULAR_FILE;
import static edu.hw6.Task3.FileFilters.WRITABLE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileFiltersTest {
    @Test
    void findImageInDirectory() {
        Path dir = Paths.get(".");

        AbstractFilter filter = REGULAR_FILE
            .and(READABLE)
            .and(WRITABLE)
            .and(largerThan(100_000))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'));

        assertDoesNotThrow(() -> {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
                assertTrue(entries.iterator().hasNext());
            }
        });
    }
}
