package edu.hw3;

import java.util.Comparator;

public class Task7 {

    private Task7() {

    }

    public static <T extends Comparable<T>> Comparator<T> getNullFriendlyComparator() {
        return (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        };
    }
}
