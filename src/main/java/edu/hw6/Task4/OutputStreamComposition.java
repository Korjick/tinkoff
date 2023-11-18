package edu.hw6.Task4;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class OutputStreamComposition {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(OutputStreamComposition.class);

    private OutputStreamComposition() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        Path filePath = Paths.get("./output.txt");

        try (
            OutputStream fileOut = Files.newOutputStream(filePath);
            CheckedOutputStream checkedOut = new CheckedOutputStream(fileOut, new Adler32());
            BufferedOutputStream bufferedOut = new BufferedOutputStream(checkedOut);
            OutputStreamWriter writer = new OutputStreamWriter(bufferedOut, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(writer)
        ) {
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
