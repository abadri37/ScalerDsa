package DSA1.Array.Day1;

import java.util.Arrays;

public class SetMatrixZeros {

    public static void main(String[] args) {

        // -------------------------------
        // Approach 1: Using Extra Space
        // -------------------------------
        int[][] val = new int[][]{
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        // Step 1: Create two boolean arrays to track rows and columns that need to be zeroed
        boolean[] row = new boolean[val.length];
        boolean[] col = new boolean[val[0].length];

        // Step 2: Identify the rows and columns that have at least one zero
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                if (val[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Step 3: Update the matrix based on marked rows and columns
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                if (row[i] || col[j]) {
                    val[i][j] = 0;
                }
            }
        }

        // Step 4: Print result of extra space method
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                System.out.print(val[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


        // -------------------------------
        // Approach 2: In-Place Modification (O(1) space)
        // -------------------------------
        val = new int[][]{
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Check if the first row has any zero
        for (int i = 0; i < val[0].length; i++) {
            if (val[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 2: Check if the first column has any zero
        for (int i = 0; i < val.length; i++) {
            if (val[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 3: Use first row and first column as markers for zero rows/columns
        for (int i = 1; i < val.length; i++) {
            for (int j = 1; j < val[0].length; j++) {
                if (val[i][j] == 0) {
                    val[0][j] = 0;  // mark column
                    val[i][0] = 0;  // mark row
                }
            }
        }

        // Step 4: Use the markers to set corresponding cells to zero
        for (int i = 1; i < val.length; i++) {
            for (int j = 1; j < val[0].length; j++) {
                if (val[0][j] == 0 || val[i][0] == 0) {
                    val[i][j] = 0;
                }
            }
        }

        // Step 5: Zero the first row if it had a zero
        if (firstRowZero) {
            Arrays.fill(val[0], 0);
        }

        // Step 6: Zero the first column if it had a zero
        if (firstColZero) {
            for (int i = 0; i < val.length; i++) {
                val[i][0] = 0;
            }
        }

        // Step 7: Print result of in-place method
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                System.out.print(val[i][j] + " ");
            }
            System.out.println();
        }
    }
}
