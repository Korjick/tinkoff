package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String incorrect) {
        StringBuilder stringBuilder = new StringBuilder();

        char[] incorrectChars = incorrect.toCharArray();
        for (int i = 1; i < incorrectChars.length; i += 2) {
            stringBuilder.append(incorrectChars[i]);
            stringBuilder.append(incorrectChars[i - 1]);
        }

        if (incorrectChars.length % 2 != 0) {
            stringBuilder.append(incorrectChars[incorrectChars.length - 1]);
        }

        return stringBuilder.toString();
    }
}
