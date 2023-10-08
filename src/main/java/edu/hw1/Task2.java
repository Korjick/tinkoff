package edu.hw1;

public class Task2 {

    private static final int NUM_COUNT_DIVIDER = 10;

    private Task2() {
    }

    @SuppressWarnings("ParameterAssignment")
    public static int countDigits(int value) {
        int numCount = 0;

        do {
            value /= NUM_COUNT_DIVIDER;
            numCount++;
        }
        while (value != 0);

        return numCount;
    }
}
