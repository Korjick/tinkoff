package edu.project3;

import edu.project3.builder.ApacheCLIParserBuilder;
import edu.project3.builder.CLILogParserBuilder;
import edu.project3.builder.OptionData;
import edu.project3.data.LogParser;
import edu.project3.data.LogRecord;
import edu.project3.data.LogReport;
import edu.project3.utils.ParseUtils;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public final class NginxLogAnalyzer {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(NginxLogAnalyzer.class);

    private NginxLogAnalyzer() {

    }

    @SuppressWarnings(value = {"UncommentedMain", "MultipleStringLiterals"})
    public static void main(String[] args) {
        CLILogParserBuilder builder =
            new ApacheCLIParserBuilder(
                new OptionData(
                    "p",
                    "path",
                    true,
                    "Path to log files"
                ));

        CLILogParserBuilder.Parser parser = builder
            .addOption(
                new OptionData(
                    "f",
                    "from",
                    true,
                    "Start date (inclusive) in ISO8601 format"
                ))
            .addOption(
                new OptionData(
                    "t",
                    "to",
                    true,
                    "End date (inclusive) in ISO8601 format"
                ))
            .addOption(
                new OptionData(
                    "fmt",
                    "format",
                    true,
                    "Output format (markdown or adoc)"
                ))
            .addOption(
                new OptionData(
                    "out",
                    "output",
                    true,
                    "Output path"
                ))
            .addOption(
                new OptionData(
                    "jar",
                    "jar",
                    true,
                    "Execution JAR file"
                ))
            .build(args);

        if (parser != null) {
            try {
                Map.Entry<Boolean, List<URI>> input = ParseUtils.getInputURI(parser.getOptionValue("path"));
                LocalDate from = ParseUtils.parseDateTime(parser.getOptionValue("from"));
                LocalDate to = ParseUtils.parseDateTime(parser.getOptionValue("to"));

                for (URI uri : input.getValue()) {
                    Stream<LogRecord> logEntries = LogParser.parse(uri);
                    LogReport report = LogReport.parse(logEntries, from, to);
                    Path output = ParseUtils.getOutputPath(
                        parser.getOptionValue("output"),
                        uri,
                        input.getKey(),
                        ParseUtils.getFileFormat(parser.getOptionValue("format"))
                    );
                    report.save(output);
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }
}
