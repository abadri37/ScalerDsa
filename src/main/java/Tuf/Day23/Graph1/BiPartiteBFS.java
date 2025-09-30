package Tuf.Day23.Graph1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BiPartiteBFS {

    public static void main(String[] args) {
        // Example 1: Bipartite graph
        // Graph: 0--1, 0--3, 2--1, 2--3 (square structure, even cycle)
        int[][] graph1 = {
                {1, 3},  // Neighbors of node 0
                {0, 2},  // Neighbors of node 1
                {1, 3},  // Neighbors of node 2
                {0, 2}   // Neighbors of node 3
        };

        // Example 2: Not bipartite
        // Odd cycle: 0-1-2-0
        int[][] graph2 = {
                {1, 2},  // Node 0 connected to 1,2
                {0, 2},  // Node 1 connected to 0,2
                {0, 1}   // Node 2 connected to 0,1
        };

        // Example 3: Disconnected graph (still bipartite)
        // Two components: (0--1), (2--3), and node 4 is isolated
        int[][] graph3 = {
                {1},     // Node 0 connected to 1
                {0},     // Node 1 connected to 0
                {3},     // Node 2 connected to 3
                {2},     // Node 3 connected to 2
                {}       // Node 4 (isolated node)
        };

        // Example 4: Mixed graph (some bipartite parts, some not)
        // Contains an odd cycle (3-4-5-3) -> not bipartite
        int[][] graph4 = {
                {1},        // Node 0 → 1
                {0, 2},     // Node 1 → 0,2
                {1},        // Node 2 → 1
                {4, 5},     // Node 3 → 4,5 (odd cycle starts here)
                {3, 5},     // Node 4 → 3,5
                {3, 4}      // Node 5 → 3,4
        };

        // Run and print results
        System.out.println("Graph 1 (Bipartite): " + isBipartite(graph1)); // true
        System.out.println("Graph 2 (Not Bipartite): " + isBipartite(graph2)); // false
        System.out.println("Graph 3 (Disconnected Bipartite): " + isBipartite(graph3)); // true
        System.out.println("Graph 4 (Odd Cycle Not Bipartite): " + isBipartite(graph4)); // false
    }

    /**
     * Main function to check if graph is bipartite.
     * It handles disconnected graphs by calling BFS from every node.
     *
     * @param graphs adjacency list representation
     * @return true if bipartite, false otherwise
     */
    public static boolean isBipartite(int[][] graphs) {
        int n = graphs.length;
        int[] color = new int[n]; // 0 = uncolored, 1 = color1, -1 = color2

        // For every node, run BFS (important for disconnected graphs)
        for (int i = 0; i < graphs.length; i++) {
            if (color[i] == 0) { // not yet visited
                if (!isBipartiteBFS(graphs, color, i)) {
                    return false; // found odd cycle
                }
            }
        }
        return true;
    }

    /**
     * BFS traversal to check bipartite property.
     * Idea: try coloring nodes with two colors while traversing.
     *
     * @param graphs adjacency list
     * @param color  array holding color assignments
     * @param start  starting node for BFS
     * @return true if bipartite from this component, false otherwise
     */
    public static boolean isBipartiteBFS(int[][] graphs, int[] color, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        // Start coloring this component with 1
        color[start] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graphs[node]) {
                if (color[neighbor] == 0) {
                    // Assign opposite color to neighbor
                    color[neighbor] = -color[node];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    // If neighbor has same color -> odd cycle
                    return false;
                }
            }
        }
        return true; // no conflict
    }
}