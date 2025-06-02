package DSA4.graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    /**
     * Main processing function to calculate the minimum time required
     * to rot all fresh oranges in the matrix.
     *
     * @param mat 2D grid representing the oranges (0 = empty, 1 = fresh, 2 = rotten)
     * @return minimum time to rot all fresh oranges, or -1 if not all can be rotten
     */
    public static int process(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>(); // Queue for BFS traversal
        int[][] time = new int[mat.length][mat[0].length]; // Time matrix to track when each orange gets rotten

        // Initialize the queue with all initially rotten oranges
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    time[i][j] = 0; // Rotten at time 0
                }
            }
        }

        // Perform BFS to spread the rot
        bfs(queue, mat, time);

        // Check the final state of the grid
        int max = -1;
        boolean allRotten = true;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    // Still a fresh orange left — impossible to rot all
                    allRotten = false;
                }
                max = Math.max(max, time[i][j]); // Track the max time needed
            }
        }

        return allRotten ? max : -1; // Return result
    }

    /**
     * Breadth-First Search to simulate rotting process level by level.
     *
     * @param queue Queue containing initially rotten oranges' positions
     * @param mat 2D grid of oranges
     * @param time 2D matrix storing time when each orange becomes rotten
     */
    public static void bfs(Queue<int[]> queue, int[][] mat, int[][] time) {
        int rows = mat.length;
        int cols = mat[0].length;

        while (!queue.isEmpty()) {
            int[] value = queue.poll(); // Get the current rotten orange's position
            int i = value[0];
            int j = value[1];

            // Try to rot the top neighbor
            if (i - 1 >= 0 && mat[i - 1][j] == 1) {
                queue.add(new int[]{i - 1, j});
                mat[i - 1][j] = 2; // Mark as rotten
                time[i - 1][j] = time[i][j] + 1; // Set time
            }

            // Try to rot the bottom neighbor
            if (i + 1 < rows && mat[i + 1][j] == 1) {
                queue.add(new int[]{i + 1, j});
                mat[i + 1][j] = 2;
                time[i + 1][j] = time[i][j] + 1;
            }

            // Try to rot the left neighbor
            if (j - 1 >= 0 && mat[i][j - 1] == 1) {
                queue.add(new int[]{i, j - 1});
                mat[i][j - 1] = 2;
                time[i][j - 1] = time[i][j] + 1;
            }

            // Try to rot the right neighbor
            if (j + 1 < cols && mat[i][j + 1] == 1) {
                queue.add(new int[]{i, j + 1});
                mat[i][j + 1] = 2;
                time[i][j + 1] = time[i][j] + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Rotten Oranges:");

        // Test input matrix (0 = empty, 1 = fresh, 2 = rotten)
        int[][] mat = {
                {2, 1, 1, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 1},
                {2, 0, 1, 1}
        };

        // Expected output: Minimum time to rot all oranges, or -1 if impossible
        System.out.println(process(mat));
    }
}
