package DSA4.graph;

/**
 * This class implements the solution to the "Number of Islands" problem.
 * It counts the total number of connected groups of 1s (islands) in a given 2D matrix.
 */
public class NumberofIslands {

    /**
     * Counts the total number of islands (connected components of 1s) in the matrix.
     *
     * @param mat 2D matrix of 0s and 1s representing water and land respectively
     * @return total number of islands
     */
    public static int process(int[][] mat) {
        int count = 0;

        // Iterate through the matrix
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                // If we find an unvisited land cell (1), start DFS
                if (mat[i][j] == 1) {
                    dfs(mat, i, j);
                    count++; // Increase island count after full traversal
                }
            }
        }
        return count;
    }

    /**
     * Depth-First Search to mark all connected land cells as visited.
     *
     * @param mat 2D matrix
     * @param i current row index
     * @param j current column index
     */
    public static void dfs(int[][] mat, int i, int j) {
        // Boundary and visited checks
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || mat[i][j] == 0) {
            return;
        }

        // Mark the cell as visited
        mat[i][j] = 0;

        // Visit all four adjacent cells (up, down, left, right)
        dfs(mat, i - 1, j); // Up
        dfs(mat, i + 1, j); // Down
        dfs(mat, i, j - 1); // Left
        dfs(mat, i, j + 1); // Right
    }

    /**
     * Main method to test the number of islands algorithm.
     */
    public static void main(String[] args) {
        System.out.println("Number of Islands:");

        int[][] mat = {
                {1, 1, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0}
        };

        // Output the number of islands
        System.out.println(process(mat));  // Expected Output: 5
    }
}
