package DSA4.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Description:
 *
 * You are given N towns labeled from 1 to N. All towns are connected via a unique directed path,
 * as defined by the input array A.
 *
 * You are to determine whether you can reach town B from town C without revisiting any edge.
 *
 * Input:
 * - A: Integer array of size N, where for every 1 <= i < N, there is a directed edge from A[i] to (i + 1).
 *   Note that A is 0-indexed, and A[0] = 1 should be ignored.
 * - B: Target town to reach.
 * - C: Starting town.
 *
 * Constraints:
 * - 1 <= N <= 100000
 * - 1 <= B, C <= N
 * - For all 1 <= i < N, A[i] <= i
 *
 * Example:
 * A = [1, 1, 2]
 * B = 1
 * C = 2
 *
 * Output:
 * "There is no path between 2 -> 1"
 *
 * Explanation:
 * Edges: 1 -> 2, 2 -> 3
 * There is no path going back from 2 to 1.
 */

public class FirstDepthFirstSearchGraph {

    public static void solve(int[] A, final int B, final int C) {
        // Adjacency list representation of the graph (1-indexed)
        List<List<Integer>> adjacencyList = new ArrayList<>();

        // Initialize the list for each town from 0 to N
        for (int i = 0; i <= A.length; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the graph: For each i, add edge from A[i] to i+1
        for (int i = 1; i < A.length; i++) {
            adjacencyList.get(A[i]).add(i + 1);
        }

        // Use BFS to check if C can reach B
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[A.length + 1];

        queue.add(C);
        visited[C] = true;

        while (!queue.isEmpty()) {
            int currentTown = queue.poll();
            for (int neighbor : adjacencyList.get(currentTown)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        // Print result
        if (visited[B]) {
            System.out.println("There is a path between " + C + " -> " + B);
        } else {
            System.out.println("There is no path between " + C + " -> " + B);
        }
    }

    public static void main(String[] args) {
        System.out.println("Directed Path Existence Check");

        int[] A = {1, 1, 2};  // A[0] is ignored
        int B = 1;
        int C = 2;

        solve(A, B, C);
    }
}
