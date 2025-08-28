package Tuf.Day10.RecursionBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatInMaze {
    public static void main(String[] args) {
        // 1 means open path, 0 means blocked
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        // visited[][] helps to avoid revisiting same cell in current path
        boolean[][] visited = new boolean[maze.length][maze.length];

        // stores the final shortest path found
        List<int[]> shortestPath = new ArrayList<>();

        // backtracking call starting from (0,0)
        solveRatInMaze(maze, 0, 0, visited, new ArrayList<>(), shortestPath);

        // print result
        if (!shortestPath.isEmpty()) {
            System.out.println("Shortest path to reach destination:");
            for (int[] cell : shortestPath) {
                System.out.print(Arrays.toString(cell) + " ");
            }
            System.out.println("\nLength of shortest path: " + shortestPath.size());
        } else {
            System.out.println("No path found!");
        }
    }

    /**
     * Recursive backtracking function to explore all possible paths.
     * @param maze The maze grid (1=open, 0=blocked)
     * @param row Current row
     * @param col Current column
     * @param visited Tracks visited cells to prevent cycles
     * @param path Stores current path being explored
     * @param shortestPath Stores the overall shortest path found so far
     */
    public static void solveRatInMaze(int[][] maze, int row, int col, boolean[][] visited, List<int[]> path, List<int[]> shortestPath) {
        int n = maze.length - 1;

        // ✅ Base case: reached destination (bottom-right corner)
        if (row == n && col == n) {
            path.add(new int[]{row, col}); // include destination
            // update shortest path if this one is shorter
            if (shortestPath.isEmpty() || path.size() < shortestPath.size()) {
                shortestPath.clear();
                shortestPath.addAll(new ArrayList<>(path));
            }
            path.remove(path.size() - 1); // backtrack
            return;
        }

        // mark current cell as visited
        visited[row][col] = true;
        path.add(new int[]{row, col}); // include current cell in path

        // possible 4 directions: Down, Right, Up, Left
        int[] dRow = new int[]{1, 0, -1, 0};
        int[] dCol = new int[]{0, 1, 0, -1};

        // try all directions
        for (int k = 0; k < 4; k++) {
            int nextRow = row + dRow[k];
            int nextCol = col + dCol[k];

            // check if the move is valid
            if (isSafe(maze, nextRow, nextCol, visited)) {
                solveRatInMaze(maze, nextRow, nextCol, visited, path, shortestPath);
            }
        }

        // backtrack: unmark cell and remove from path
        visited[row][col] = false;
        path.remove(path.size() - 1);
    }

    /**
     * Checks whether the rat can move to (row,col).
     */
    public static boolean isSafe(int[][] maze, int row, int col, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze.length
                && maze[row][col] == 1 && !visited[row][col];
    }
}