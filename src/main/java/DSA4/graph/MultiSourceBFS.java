package DSA4.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MultiSourceBFS {

    /**
     * Performs multi-source BFS on an undirected graph.
     * @param u     Array representing source nodes of edges
     * @param v     Array representing destination nodes of edges
     * @param n     Total number of nodes in the graph
     * @param e     Total number of edges
     * @param s1    First source node
     * @param s2    Second source node
     * @param s3    Third source node
     */
    public static void process(int[] u, int[] v, int n, int e, int s1, int s2, int s3) {

        // Adjacency list representation of the graph
        List<List<Integer>> input = new ArrayList<>();

        // Initialize the adjacency list for nodes 0 to n (1-based indexing)
        for (int i = 0; i <= n; i++) {
            input.add(new ArrayList<>());
        }

        // Populate adjacency list with undirected edges
        for (int i = 0; i < e; i++) {
            input.get(u[i]).add(v[i]);
            input.get(v[i]).add(u[i]);
        }

        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // Track visited nodes to avoid revisiting
        boolean[] visitedArray = new boolean[n + 1];

        // Array to track the shortest distance (in levels) from the nearest source
        int[] level = new int[n + 1];

        // Enqueue the three source nodes and mark them visited
        queue.add(s1);
        queue.add(s2);
        queue.add(s3);
        visitedArray[s1] = true;
        visitedArray[s2] = true;
        visitedArray[s3] = true;

        // BFS traversal
        while (!queue.isEmpty()) {
            int val = queue.poll();  // Dequeue the current node
            List<Integer> neighbors = input.get(val);  // Get all connected neighbors

            // Visit all unvisited neighbors
            for (Integer neighbor : neighbors) {
                if (!visitedArray[neighbor]) {
                    visitedArray[neighbor] = true;
                    queue.add(neighbor);
                    // Distance from source is parent's level + 1
                    level[neighbor] = level[val] + 1;
                }
            }
        }

        // Print the shortest distance from any source to each node
        for (int i = 1; i <= n; i++) {
            System.out.println("Shortest Length from source to node " + i + " is " + level[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("MultiSourceBFS:");

        // Input edge list for an undirected graph
        int[] u = {1, 1, 2, 3, 4, 5, 5, 7, 8, 9};
        int[] v = {2, 4, 3, 6, 5, 7, 6, 8, 9, 10};

        int n = 10;          // Number of nodes
        int e = u.length;    // Number of edges
        int s1 = 1;          // Source node 1
        int s2 = 5;          // Source node 2
        int s3 = 10;         // Source node 3

        // Perform multi-source BFS
        process(u, v, n, e, s1, s2, s3);
    }
}
