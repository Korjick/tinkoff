package edu.hw1;

public class Task5 {

    private static final int MIN_NUMBER_LEN = 2;

    private Task5() {
    }

    @SuppressWarnings("ParameterAssignment")
    public static boolean isPalindromeDescendant(int value) throws IllegalArgumentException {
        if (String.valueOf(value).length() < MIN_NUMBER_LEN) {
            throw new IllegalArgumentException();
        }

        int child = -1;
        boolean isPalindrom = isPalindrom(value);

        while (!isPalindrom) {
            value = toChildren(value);
            if (child == value) {
                return false;
            }

            child = value;
            isPalindrom = isPalindrom(value);
        }

        return isPalindrom;
    }

    private static boolean isPalindrom(int value) {
        String valueString = String.valueOf(value);
        int halfIndex = valueString.length() / 2;

        StringBuilder checkSecond = new StringBuilder();
        checkSecond.append(valueString.substring(valueString.length() % 2 == 0 ? halfIndex : halfIndex + 1));

        return valueString.substring(0, halfIndex).contentEquals(checkSecond.reverse());
    }

    private static int toChildren(int parentValue) {
        StringBuilder stringBuilder = new StringBuilder();
        String parentValueString = String.valueOf(parentValue);

        for (int i = 1; i < parentValueString.length(); i += 2) {
            stringBuilder.append(
                Integer.parseInt(String.valueOf(parentValueString.charAt(i)))
                    + Integer.parseInt(String.valueOf(parentValueString.charAt(i - 1))));
        }

        if (parentValueString.length() % 2 != 0) {
            stringBuilder.append(parentValueString.charAt(parentValueString.length() - 1));
        }

        String preParsed = stringBuilder.toString();

        if (preParsed.length() < MIN_NUMBER_LEN) {
            return parentValue;
        }

        return Integer.parseInt(preParsed);
    }
}
