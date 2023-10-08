package edu.hw1;

import java.util.Arrays;
import java.util.OptionalInt;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] a, int[] b) throws IllegalArgumentException {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }

        OptionalInt aMax = Arrays.stream(a).max();
        OptionalInt aMin = Arrays.stream(a).min();

        OptionalInt bMax = Arrays.stream(b).max();
        OptionalInt bMin = Arrays.stream(b).min();

        if (aMax.isEmpty() || bMax.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return aMin.getAsInt() > bMin.getAsInt() && aMax.getAsInt() < bMax.getAsInt();
    }
}
