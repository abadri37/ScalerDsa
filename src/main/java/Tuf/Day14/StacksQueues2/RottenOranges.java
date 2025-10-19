/**
 * ✅ Problem: Rotten Oranges (LeetCode #994)
 *
 * 🔹 Question:
 * You are given a grid representing a box of oranges.
 * Each cell in the grid can have one of three values:
 *   0 → empty cell
 *   1 → fresh orange
 *   2 → rotten orange
 *
 * Every minute, any fresh orange that is 4-directionally adjacent
 * (up, down, left, right) to a rotten orange becomes rotten.
 *
 * Return the **minimum number of minutes** that must elapse
 * until **no cell has a fresh orange**.
 *
 * If it is impossible to rot all oranges, return **-1**.
 *
 * ⚙️ Constraints:
 *  - 1 <= grid.length, grid[0].length <= 10
 *  - grid[i][j] is 0, 1, or 2
 *
 * Example 1:
 * Input:
 *   [[2,1,1],
 *    [1,1,0],
 *    [0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input:
 *   [[2,1,1],
 *    [0,1,1],
 *    [1,0,1]]
 * Output: -1  (since bottom-left fresh orange never rots)
 *
 * 💡 Approach:
 * Use **BFS (Breadth-First Search)** traversal starting from all rotten oranges.
 * - Push all initially rotten oranges (with time = 0) into a queue.
 * - For each rotten orange, rot its adjacent fresh oranges and enqueue them with `time + 1`.
 * - Keep track of the maximum time taken.
 * - If fresh oranges remain after BFS, return -1.
 *
 * 🕒 Time Complexity: O(N × M)
 * 🧮 Space Complexity: O(N × M)
 */

package Tuf.Day14.StacksQueues2;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    /**
     * Function to find the minimum time to rot all oranges.
     *
     * @param grid a 2D array representing oranges:
     *             0 → empty, 1 → fresh, 2 → rotten
     * @return minimum minutes to rot all oranges, or -1 if impossible
     */
    public int orangesRotting(int[][] grid) {
        int maxTime = 0;                    // Track the maximum time taken
        Queue<int[]> queue = new LinkedList<>(); // Queue for BFS: [row, col, time]
        int fresh = 0;                      // Count of fresh oranges

        // 🔹 Step 1: Initialize queue with all initially rotten oranges
        // and count the total number of fresh oranges
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    // Rotten orange found → enqueue with time = 0
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    fresh++; // Count fresh oranges
                }
            }
        }

        // 🔹 Step 2: BFS traversal
        while (!queue.isEmpty()) {
            int[] in = queue.poll();
            int row = in[0];
            int col = in[1];
            int time = in[2];

            // Update maximum time so far
            maxTime = Math.max(maxTime, time);

            // Check all 4 adjacent cells (up, down, left, right)
            // If a fresh orange is found → rot it and enqueue it with time + 1
            if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                grid[row - 1][col] = 2; // Make it rotten
                queue.offer(new int[]{row - 1, col, time + 1});
                fresh--; // One less fresh orange
            }
            if (row + 1 < grid.length && grid[row + 1][col] == 1) {
                grid[row + 1][col] = 2;
                queue.offer(new int[]{row + 1, col, time + 1});
                fresh--;
            }
            if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                grid[row][col - 1] = 2;
                queue.offer(new int[]{row, col - 1, time + 1});
                fresh--;
            }
            if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {
                grid[row][col + 1] = 2;
                queue.offer(new int[]{row, col + 1, time + 1});
                fresh--;
            }
        }

        // 🔹 Step 3: If fresh oranges remain, return -1; otherwise, return time taken
        return fresh > 0 ? -1 : maxTime;
    }

    /**
     * 🧪 Main method for testing orangesRotting() with different inputs
     */
    public static void main(String[] args) {
        RottenOranges solver = new RottenOranges();

        // 🧩 Test Case 1
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Test Case 1 Output: " + solver.orangesRotting(grid1));
        // Expected Output: 4
        // Explanation: It takes 4 minutes for all fresh oranges to rot.

        // 🧩 Test Case 2
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println("Test Case 2 Output: " + solver.orangesRotting(grid2));
        // Expected Output: -1
        // Explanation: The orange at bottom-left never rots (isolated).

        // 🧩 Test Case 3
        int[][] grid3 = {
                {0, 2}
        };
        System.out.println("Test Case 3 Output: " + solver.orangesRotting(grid3));
        // Expected Output: 0
        // Explanation: No fresh oranges to rot.

        // 🧩 Test Case 4
        int[][] grid4 = {
                {1}
        };
        System.out.println("Test Case 4 Output: " + solver.orangesRotting(grid4));
        // Expected Output: -1
        // Explanation: Single fresh orange cannot rot (no rotten neighbor).

        // 🧩 Test Case 5
        int[][] grid5 = {
                {2, 2, 0, 1}
        };
        System.out.println("Test Case 5 Output: " + solver.orangesRotting(grid5));
        // Expected Output: -1
        // Explanation: The fresh orange at last cell is isolated.
    }
}