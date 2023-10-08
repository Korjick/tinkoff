package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        return rotate(n, shift, false);
    }

    public static int rotateRight(int n, int shift) {
        return rotate(n, shift, true);
    }

    private static int rotate(int n, int shift, boolean isRight) {
        char[] charN = Integer.toBinaryString(n).toCharArray();
        char[] newCharN = new char[charN.length];

        for (int i = 0; i < charN.length; i++) {
            int offset = isRight ? (charN.length - i + shift) % charN.length : (i + shift) % charN.length;
            newCharN[i] = charN[offset];
        }

        return Integer.parseInt(new String(newCharN), 2);
    }
}
