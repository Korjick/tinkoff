package edu.project3.builder;

public interface CLILogParserBuilder {
    CLILogParserBuilder addOption(OptionData optionData);

    Parser build(String[] args);

    interface Parser {
        String getOptionValue(String optionName);
    }
}
