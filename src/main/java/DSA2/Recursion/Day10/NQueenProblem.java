package DSA2.Recursion.Day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueenProblem {
    public static void main(String[] args) {
        int n = 4; // Input: size of the chessboard (n x n)

        // Step 1: Create an empty chessboard and fill with '.'
        char[][] board = new char[n][n];
        for (char[] ch : board) {
            Arrays.fill(ch, '.');
        }

        List<List<String>> results = new ArrayList<>();

        // Step 2: Start solving the N-Queens problem from row 0
        solveNQueen(n, board, 0, results);

        // Step 3: Print all the solutions
        System.out.println("The Resultant Board is");
        for (List<String> list : results) {
            for (String st : list) {
                System.out.println(st + " ");
            }
            System.out.println();
        }
    }

    /**
     * Recursive function to place queens row by row
     *
     * @param n        size of board
     * @param board    current state of board
     * @param row      current row we are trying to place a queen
     * @param results  stores all possible solutions
     */
    public static void solveNQueen(int n, char[][] board, int row, List<List<String>> results) {
        // Base case: if we have placed queens in all rows, save the board
        if (row == board.length) {
            constructResults(board, results);
            return;
        }

        // Try placing queen in each column of the current row
        for (int i = 0; i < board.length; i++) {
            // QUESTION: Why do we check if the current cell is empty ('.') before placing?
            if (board[row][i] == '.') {
                // QUESTION: Why do we need to check safety before placing a queen?
                if (isSafe(board, row, i)) {
                    board[row][i] = 'Q'; // Place queen

                    // Recurse for the next row
                    solveNQueen(n, board, row + 1, results);

                    // Backtrack: remove queen and try next position
                    board[row][i] = '.';
                }
            }
        }
    }

    /**
     * Check whether it's safe to place a queen at board[row][col]
     */
    public static boolean isSafe(char[][] board, int row, int col) {
        // QUESTION: Why do we check the column above?
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // QUESTION: Why do we check the upper left diagonal?
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        // QUESTION: Why do we check the upper right diagonal?
        i = row;
        j = col;
        while (i >= 0 && j < board.length) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        return true; // Safe to place
    }

    /**
     * Convert the board into a list of strings (final solution format)
     */
    public static void constructResults(char[][] board, List<List<String>> results) {
        List<String> list = new ArrayList<>();
        for (char[] ch : board) {
            list.add(new String(ch));
        }
        results.add(list);
    }
}