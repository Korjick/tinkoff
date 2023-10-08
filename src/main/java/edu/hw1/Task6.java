package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int INFINITE_NUMBER = 6174;

    private Task6() {
    }

    @SuppressWarnings("ParameterAssignment")
    public static int countK(int value, int startCounter) {

        if (value == INFINITE_NUMBER) {
            return startCounter;
        }

        char[] charValue = String.valueOf(value).toCharArray();
        Arrays.sort(charValue);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charValue);
        int asc = Integer.parseInt(stringBuilder.toString());
        stringBuilder.reverse();
        int desc = Integer.parseInt(stringBuilder.toString());

        startCounter++;
        return countK(desc - asc, startCounter);
    }
}
