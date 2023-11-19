package edu.project3.builder;

import org.apache.commons.cli.CommandLine;

public class ApacheCLIParser implements CLILogParserBuilder.Parser {

    private final CommandLine commandLine;

    public ApacheCLIParser(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public String getOptionValue(String optionName) {
        return commandLine.getOptionValue(optionName);
    }
}
