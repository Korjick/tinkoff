package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Task2 {

    private static final Pattern BRACKET_PATTERN = Pattern.compile("[^()]");

    private Task2() {

    }

    public static List<String> clusterize(String input) {

        if (BRACKET_PATTERN.matcher(input).find()) {
            throw new IllegalArgumentException();
        }

        List<String> clusters = new ArrayList<>();
        int balance = 0;
        int startIndex = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }

            if (balance == 0) {
                clusters.add(input.substring(startIndex, i + 1));
                startIndex = i + 1;
            }
        }

        return clusters;
    }
}
