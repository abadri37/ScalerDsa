package DSA4.graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphConnectedComponents {

    /**
     * Finds the total number of connected components in an undirected graph.
     *
     * @param u Array representing start vertices of undirected edges.
     * @param v Array representing end vertices of undirected edges.
     * @param n Total number of nodes (1-based index).
     * @return Number of connected components in the graph.
     */
    public static int process(int[] u, int[] v, int n) {
        // Counter for connected components
        int c = 0;

        // Create an adjacency list to represent the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();

        // Initialize the adjacency list for each node (1-based indexing)
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Populate the adjacency list with undirected edges u[i] <-> v[i]
        for (int i = 0; i < u.length; i++) {
            adjacencyList.get(u[i]).add(v[i]);
            adjacencyList.get(v[i]).add(u[i]);
        }

        // Visited array to track which nodes have been visited during DFS
        boolean[] visited = new boolean[n + 1];

        // Loop through all nodes to perform DFS from unvisited nodes
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, visited, i); // Visit all reachable nodes
                c += 1; // Increment connected component count
            }
        }

        return c;
    }

    /**
     * Standard DFS traversal marking all reachable nodes from the source.
     *
     * @param adjacencyList Adjacency list of the graph.
     * @param visited       Array tracking visited nodes.
     * @param source        Starting node for DFS.
     */
    public static void dfs(List<List<Integer>> adjacencyList, boolean[] visited, int source) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;

        // Visit all neighbors of current node
        for (Integer neighbor : adjacencyList.get(source)) {
            dfs(adjacencyList, visited, neighbor);
        }
    }

    public static void main(String[] args) {
        System.out.println("Depth First Search - Undirected Graph Example");

        // Graph Input:
        // Component 1: 1 - 2 - 3 - 4
        // Component 2: 5 - 6 - 7 - 8
        // Nodes 9 and 10 are isolated (not connected to any edge)

        int[] u = {1, 2, 3, 5, 6, 7};
        int[] v = {2, 3, 4, 6, 7, 8};
        int n = 10; // Total nodes in the graph (1 to 10)

        // Output: 4 connected components: {1-2-3-4}, {5-6-7-8}, {9}, {10}
        System.out.println("Number of connected components: " + process(u, v, n));
    }
}
