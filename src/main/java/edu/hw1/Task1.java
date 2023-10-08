package edu.hw1;

public class Task1 {

    private static final int MINUTES_DIVIDE_TO_SECONDS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String minutesAndSecconds) throws NumberFormatException {
        String[] timeSplit = minutesAndSecconds.split(":");
        int minutes = Integer.parseInt(timeSplit[0]);
        int seconds = Integer.parseInt(timeSplit[1]);

        if (seconds >= MINUTES_DIVIDE_TO_SECONDS) {
            return -1;
        }

        return minutes * MINUTES_DIVIDE_TO_SECONDS + seconds;
    }
}
