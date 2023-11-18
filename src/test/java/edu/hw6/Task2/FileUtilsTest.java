package edu.hw6.Task2;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void cloneFile() {
        String fileName = "test";
        String extension = ".txt";
        Path path = Paths.get("./%s%s".formatted(fileName, extension));
        assertDoesNotThrow(() -> {
            assertTrue(
                FileUtils.cloneFile(path)
                    && path.getParent().resolve(
                    fileName
                        + extension).toFile().exists());
            assertTrue(
                FileUtils.cloneFile(path)
                    && path.getParent().resolve(
                    fileName
                        + FileUtils.CP_FILE_APPEND
                        + extension).toFile().exists());
            assertTrue(
                FileUtils.cloneFile(path)
                    && path.getParent().resolve(
                    fileName
                        + FileUtils.CP_FILE_APPEND
                        + FileUtils.CP_NUM_FILE_APPEND.formatted(1)
                        + extension).toFile().exists());
        });
    }
}
