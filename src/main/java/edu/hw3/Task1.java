package edu.hw3;

public class Task1 {

    private static final int ENG_ALP_LEN = 26;

    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char baseA = Character.isUpperCase(c) ? 'A' : 'a';
                char baseZ = (char) (baseA + ENG_ALP_LEN - 1);
                char mirroredChar = (char) (baseA + (baseZ - c));
                result.append(mirroredChar);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
