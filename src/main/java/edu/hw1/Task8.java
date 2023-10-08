package edu.hw1;

public class Task8 {

    private Task8() {
    }

    @SuppressWarnings("NestedForDepth")
    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board[0].length; l++) {
                            if (Math.abs(i - k) == 2 && Math.abs(j - l) == 1) {
                                if (board[k][l] == 1) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
