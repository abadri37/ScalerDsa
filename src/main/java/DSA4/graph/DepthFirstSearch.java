package DSA4.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * DepthFirstSearch class demonstrates a simple DFS traversal
 * on a directed graph using an adjacency list.
 */
public class DepthFirstSearch {

    /**
     * Builds the graph and initiates the DFS traversal to check
     * if a path exists from the source to the destination.
     *
     * @param u           array representing the source nodes of edges
     * @param v           array representing the destination nodes of edges
     * @param n           number of nodes in the graph
     * @param e           number of edges in the graph
     * @param source      starting node for the DFS
     * @param destination target node to reach
     */
    public static void process(int[] u, int[] v, int n, int e, int source, int destination) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();

        // Initialize the adjacency list for each node (1-based indexing)
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Populate the adjacency list with directed edges from u[i] -> v[i]
        for (int i = 0; i < e; i++) {
            adjacencyList.get(u[i]).add(v[i]);
        }

        // Visited array to track visited nodes during DFS
        boolean[] visited = new boolean[n + 1];

        // Perform DFS from source
        dfs(adjacencyList, source, visited);

        // Output result based on whether destination was visited
        if (visited[destination]) {
            System.out.println("There exists a path between " + source + " -> " + destination);
        } else {
            System.out.println("No path exists between " + source + " -> " + destination);
        }
    }

    /**
     * Recursive DFS traversal to visit all reachable nodes from source.
     *
     * @param adjacencyList graph represented as adjacency list
     * @param current       current node in DFS
     * @param visited       boolean array to track visited nodes
     */
    public static void dfs(List<List<Integer>> adjacencyList, int current, boolean[] visited) {
        if (visited[current]) {
            return;
        }

        visited[current] = true;

        // Visit all neighbors recursively
        for (Integer neighbor : adjacencyList.get(current)) {
            dfs(adjacencyList, neighbor, visited);
        }
    }

    /**
     * Main method to test the DFS implementation with sample input.
     */
    public static void main(String[] args) {
        System.out.println("Depth First Search - Directed Graph Example");

        // Example graph input: directed edges
        int[] u = {1, 1, 2, 2, 3, 4, 5, 6, 7};
        int[] v = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = 10;              // Total number of nodes
        int e = u.length;        // Number of edges
        int source = 1;          // Starting node
        int destination = 10;    // Target node

        // Call the process function
        process(u, v, n, e, source, destination);
    }
}
