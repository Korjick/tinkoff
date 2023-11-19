package edu.project3.builder;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class ApacheCLIParserBuilder implements CLILogParserBuilder {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(ApacheCLIParserBuilder.class);

    private final CommandLineParser parser;
    private final HelpFormatter formatter;
    private final Options options;

    public ApacheCLIParserBuilder(OptionData... requiredOptions) {
        parser = new DefaultParser();
        formatter = new HelpFormatter();
        options = new Options();
        for (OptionData option : requiredOptions) {
            options.addOption(convertFromOptionData(option, true));
        }
    }

    @Override
    public CLILogParserBuilder addOption(OptionData optionData) {
        options.addOption(convertFromOptionData(optionData, false));
        return this;
    }

    @Override
    public Parser build(String[] args) {
        try {
            return new ApacheCLIParser(parser.parse(options, args));
        } catch (ParseException e) {
            LOGGER.error(e);
            formatter.printHelp(ApacheCLIParserBuilder.class.getName(), options);
            return null;
        }
    }

    private Option convertFromOptionData(OptionData optionData, boolean required) {
        Option option = new Option(
            optionData.optionName(),
            optionData.longOptionName(),
            optionData.hasAttributes(),
            optionData.description()
        );
        option.setRequired(required);
        return option;
    }
}
