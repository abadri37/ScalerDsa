package Tuf.Day2.Arrays2;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("The Given Matrix is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        rotate(matrix); // Rotate the matrix 90° clockwise

        System.out.println("The Rotated Matrix is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Rotates the input matrix 90 degrees clockwise in-place.
     * Approach: Transpose the matrix, then reverse each row.
     *
     * Step 1: Transpose - matrix[i][j] becomes matrix[j][i]
     * Step 2: Reverse each row to get the 90° rotated matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        // Swap matrix[i][j] with matrix[j][i] only for i < j to avoid double swap
        for (int i = 0; i < n; i++) {
            // Start from i+1 to avoid re-swapping lower triangle
            for (int j = i + 1; j < n; j++) {
                // Swap the elements at positions (i, j) and (j, i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            // Swap elements from left to right in each row
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
