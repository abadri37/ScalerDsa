package Tuf.Day23.Graph1;

public class BipartiteDFS {
    public static void main(String[] args) {
        // Example 1: Bipartite graph
        // Graph: 0--1, 0--3, 2--1, 2--3
        // This is a "square" cycle (0-1-2-3-0), which has an even number of nodes.
        // Even cycles are always bipartite.
        int[][] graph1 = {
                {1, 3},  // Node 0 connected to 1,3
                {0, 2},  // Node 1 connected to 0,2
                {1, 3},  // Node 2 connected to 1,3
                {0, 2}   // Node 3 connected to 0,2
        };

        // Example 2: Not bipartite
        // Odd cycle: 0-1-2-0
        // Odd cycles CANNOT be bipartite since they force two adjacent nodes to have the same color.
        int[][] graph2 = {
                {1, 2},  // Node 0 connected to 1,2
                {0, 2},  // Node 1 connected to 0,2
                {0, 1}   // Node 2 connected to 0,1
        };

        // Example 3: Disconnected graph (still bipartite)
        // Two separate bipartite components: (0--1), (2--3), and node 4 is isolated.
        // Disconnected graphs can still be bipartite if each component is bipartite.
        int[][] graph3 = {
                {1},     // Node 0 connected to 1
                {0},     // Node 1 connected to 0
                {3},     // Node 2 connected to 3
                {2},     // Node 3 connected to 2
                {}       // Node 4 (isolated, no edges)
        };

        // Example 4: Mixed graph (some bipartite parts, some not)
        // Contains an odd cycle (3-4-5-3), so the entire graph is NOT bipartite.
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
     * Checks if a graph is bipartite using DFS
     * @param graphs adjacency list of graph
     * @return true if bipartite, false otherwise
     */
    public static boolean isBipartite(int[][] graphs) {
        int n = graphs.length;
        int[] color = new int[n];
        // color[i] = 0 → not visited yet
        // color[i] = 1 → one set
        // color[i] = -1 → other set

        // We need to check ALL nodes (graph may be disconnected)
        for (int i = 0; i < graphs.length; i++) {
            if (color[i] == 0) { // not visited
                if (!isBipartiteDFS(graphs, color, i, 1)) {
                    return false; // If any component fails → not bipartite
                }
            }
        }
        return true;
    }

    /**
     * DFS helper to assign colors and check for conflicts
     * @param graphs adjacency list
     * @param color color array
     * @param start current node
     * @param colorValue current color (1 or -1)
     * @return true if bipartite so far, false if conflict found
     */
    public static boolean isBipartiteDFS(int[][] graphs, int[] color, int start, int colorValue) {
        // Assign color to current node
        color[start] = colorValue;

        // Visit all neighbors
        for (int neighbor : graphs[start]) {
            if (color[neighbor] == 0) {
                // If neighbor not colored, give opposite color (-colorValue)
                if (!isBipartiteDFS(graphs, color, neighbor, -colorValue)) {
                    return false; // Conflict deeper in recursion
                }
            } else if (color[neighbor] == colorValue) {
                // Neighbor already has SAME color → conflict → not bipartite
                return false;
            }
        }
        return true;
    }
}