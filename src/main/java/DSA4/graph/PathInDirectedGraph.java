package DSA4.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathInDirectedGraph {

    /**
     * Performs BFS traversal from source to destination and prints path details.
     *
     * @param u     Source node list for each edge
     * @param v     Destination node list for each edge
     * @param n     Number of nodes in the graph
     * @param e     Number of edges in the graph
     * @param s     Source node for the path
     * @param d     Destination node for the path
     */
    public static void process(int[] u, int[] v, int n, int e, int s, int d) {
        // Adjacency list representation of the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();

        // Initialize the adjacency list for each node (1-based index)
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build adjacency list (unidirectional since graph is directed)
        for (int i = 0; i < u.length; i++) {
            adjacencyList.get(u[i]).add(v[i]);
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];    // Tracks visited nodes
        int[] distance = new int[n + 1];           // Stores level/distance from source
        int[] parent = new int[n + 1];             // Stores parent for path reconstruction

        // Initialize source node
        visited[s] = true;
        distance[s] = 0;
        parent[s] = -1;
        queue.add(s);

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            List<Integer> neighbors = adjacencyList.get(currentNode);

            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[currentNode] + 1;
                    parent[neighbor] = currentNode;
                }
            }
        }

        // Output result
        if (visited[d]) {
            System.out.println("There is a path between " + s + " -> " + d);
            System.out.println("Shortest distance from " + s + " to " + d + " is: " + distance[d]);

            // Reconstruct and print path from destination to source
            System.out.print("Path: " + d);
            int current = d;
            while (current != s && parent[current] != -1) {
                current = parent[current];
                System.out.print(" <- " + current);
            }
            System.out.println();
        } else {
            System.out.println("There is no path between " + s + " -> " + d);
        }
    }

    /**
     * Main method: driver code with sample directed graph
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Sample Program");

        // Example 1: Simple directed path
//        int n = 5;
//        int e = 4;
//        int[] u = {1, 2, 3, 4}; // Directed edges: u[i] -> v[i]
//        int[] v = {2, 3, 4, 5};
//        int source = 1;
//        int destination = 5;

        // Example 2: Complex graph
        int n = 10;
        int e = 12;
        int[] u = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 4}; // Directed edges
        int[] v = {2, 3, 4, 5, 6, 6, 7, 8, 10, 8, 9, 5};
        int source = 1;
        int destination = 10;

        // Call process function
        process(u, v, n, e, source, destination);
    }
}
