package DSA4.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class demonstrates how to find whether a path exists between two nodes in
 * an undirected graph using Breadth-First Search (BFS).
 *
 * It also prints the shortest path from source to destination and the level (distance).
 */
public class PathInUnDirectedGraph {

    /**
     * Main method to process the input graph and find the path from source to destination.
     *
     * @param u - array representing one end of each edge
     * @param v - array representing the other end of each edge
     * @param n - number of nodes in the graph
     * @param e - number of edges in the graph
     * @param s - source node
     * @param d - destination node
     */
    public static void process(int[] u, int[] v, int n, int e, int s, int d) {
        // Adjacency list representation of the graph
        List<List<Integer>> input = new ArrayList<>();

        // Initialize the adjacency list for each node
        for (int i = 0; i <= n; i++) {
            input.add(new ArrayList<>());
        }

        // Populate adjacency list (bidirectional because graph is undirected)
        for (int i = 0; i < u.length; i++) {
            input.get(u[i]).add(v[i]);
            input.get(v[i]).add(u[i]);
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedArray = new boolean[n + 1]; // Track visited nodes
        int[] level = new int[n + 1];                // Track level/depth of each node from source
        int[] parent = new int[n + 1];               // Track parent to reconstruct path

        // Initialize source node
        visitedArray[s] = true;
        level[s] = 0;
        parent[s] = -1;
        queue.add(s);

        // BFS traversal
        while (!queue.isEmpty()) {
            int currentNode = queue.poll(); // Dequeue the front element
            List<Integer> neighbors = input.get(currentNode);

            for (Integer neighbor : neighbors) {
                if (!visitedArray[neighbor]) {
                    queue.add(neighbor);              // Enqueue unvisited neighbor
                    visitedArray[neighbor] = true;    // Mark neighbor as visited
                    level[neighbor] = level[currentNode] + 1;  // Update level
                    parent[neighbor] = currentNode;   // Set parent for path tracking
                }
            }
        }

        // Output whether a path exists
        System.out.println(visitedArray[d] ? "There is a path exists between " + s + " -> " + d
                : "There is no path exists between " + s + " -> " + d);

        // Output shortest level from source to destination
        System.out.println("The shortest level from " + s + " -> " + d + " is " + level[d]);

        // Reconstruct and print the path from destination to source using the parent array
        System.out.print("Path: ");
        int value = d;
        System.out.print(value);

        while (value != s && parent[value] != -1) {
            value = parent[value];
            System.out.print(" <- " + value);
        }
        System.out.println(); // Newline
    }

    /**
     * Driver code with sample undirected graph
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Sample Program");

        // Sample graph with 6 nodes and 7 edges
        int n = 6;
        int e = 7;
        int[] u = {1, 1, 2, 2, 3, 5, 4}; // Edges: u[i] <-> v[i]
        int[] v = {2, 4, 4, 3, 5, 6, 5};

        int source = 2;
        int destination = 6;

        // Call to process graph
        process(u, v, n, e, source, destination);
    }
}
