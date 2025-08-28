package Tuf.Day10.RecursionBackTracking;

public class SudokuSolver {
    public static void main(String[] args) {
        // Input Sudoku board (0 = empty cell)
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Solve the Sudoku
        solveSudoku(board);

        // Print the solved board
        for (int[] in : board) {
            for (int i : in) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * Recursive backtracking function to solve the Sudoku.
     *
     * @param board 9x9 Sudoku grid
     * @return true if solved, false otherwise
     */
    public static boolean solveSudoku(int[][] board) {
        // Traverse the board cell by cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // If current cell is empty
                if (board[i][j] == 0) {
                    // Try numbers 1 to 9
                    for (int k = 1; k <= 9; k++) {
                        // QUESTION: Why must we check safety before placing k?
                        if (isSafe(board, i, j, k)) {
                            board[i][j] = k; // Place number

                            // Recursively solve for remaining cells
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack: reset the cell
                            board[i][j] = 0;
                        }
                    }
                    // If no number can be placed, backtrack (return false)
                    return false;
                }
            }
        }
        // If no empty cell is found, Sudoku is solved
        return true;
    }

    /**
     * Check if placing 'num' at board[row][col] is valid.
     *
     * @param board Sudoku grid
     * @param row   current row
     * @param col   current column
     * @param num   number to place (1-9)
     * @return true if safe, false otherwise
     */
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true; // Safe to place
    }
}