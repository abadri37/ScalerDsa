package DSA1.Array.Day3;

public class Search2DMatrix {

    public static void main(String[] args) {
        /*
         * Problem:
         * You are given an m x n integer matrix with the following properties:
         *  - Each row is sorted in non-decreasing order.
         *  - The first integer of each row is greater than the last integer of the previous row.
         * Given a target integer, return true if target exists in the matrix, else return false.
         * The solution must have O(log(m * n)) time complexity.
         */

        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target = 16;

        // Approach 1: Brute force-like Row Identification + Linear Search (O(m + n))
        boolean foundBrute = false;
        int row = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] <= target) {
                row = i;  // Keep updating row while target >= first element
            } else {
                break;
            }
        }
        if (row != -1) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[row][i] == target) {
                    System.out.println("[Brute] Target " + target + " found at position [" + row + "][" + i + "]");
                    foundBrute = true;
                    break;
                }
            }
        }
        if (!foundBrute) {
            System.out.println("[Brute] Target " + target + " does not exist in the matrix");
        }

        // Approach 2: Binary Search (O(log(m * n)))
        boolean foundBinary = false;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Map 1D index to 2D coordinates
            int rowIdx = mid / n;
            int colIdx = mid % n;
            int value = matrix[rowIdx][colIdx];

            if (value == target) {
                System.out.println("[Binary] Target " + target + " found at position [" + rowIdx + "][" + colIdx + "]");
                foundBinary = true;
                break;
            } else if (target > value) {
                left = mid + 1;  // search right half
            } else {
                right = mid - 1; // search left half
            }
        }

        if (!foundBinary) {
            System.out.println("[Binary] Target " + target + " does not exist in the matrix");
        }
    }
}
