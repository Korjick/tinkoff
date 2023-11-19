package edu.project3.builder;

public record OptionData(
    String optionName,
    String longOptionName,
    boolean hasAttributes,
    String description
) {
}
