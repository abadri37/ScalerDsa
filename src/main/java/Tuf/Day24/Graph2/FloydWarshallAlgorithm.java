package Tuf.Day24.Graph2;

import java.util.Arrays;

public class FloydWarshallAlgorithm {

    // INF represents "no edge" or "infinite distance"
    final static int INF = 99999;

    public static void main(String[] args) {

        // -----------------------------
        // Example 1: Simple 4-vertex graph
        // -----------------------------
        int V1 = 4; // number of vertices
        int[][] graph1 = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };
        System.out.println("Example 1:");
        floydWarshall(graph1, V1);

        // -----------------------------
        // Example 2: Graph with a negative weight edge
        // -----------------------------
        int V2 = 3;
        int[][] graph2 = {
                {0, -2, INF},
                {INF, 0, 3},
                {4, INF, 0}
        };
        System.out.println("\nExample 2 (with negative edge, but no negative cycle):");
        floydWarshall(graph2, V2);

        // -----------------------------
        // Example 3: Graph with a negative weight cycle
        // -----------------------------
        int V3 = 3;
        int[][] graph3 = {
                {0, 1, INF},
                {INF, 0, -1},
                {-1, INF, 0}
        };
        System.out.println("\nExample 3 (negative cycle exists):");
        floydWarshall(graph3, V3);
    }

    /**
     * Floyd-Warshall Algorithm
     * Computes shortest paths between all pairs of vertices
     * Time Complexity: O(V^3)
     *
     * @param graph adjacency matrix representing the graph
     * @param v     number of vertices
     */
    public static void floydWarshall(int[][] graph, int v) {
        // Step 1: Initialize distance matrix
        // Copy the original graph distances
        int[][] dist = new int[v][v];
        for (int i = 0; i < v; i++) {
            dist[i] = Arrays.copyOf(graph[i], v);
        }

        // -----------------------------
        // Step 2: Main triple loop
        // -----------------------------
        // Idea: Consider each vertex k as an "intermediate" vertex
        // Check if a path i -> k -> j is shorter than current i -> j
        for (int k = 0; k < v; k++) {       // intermediate vertex
            for (int i = 0; i < v; i++) {   // source vertex
                for (int j = 0; j < v; j++) { // destination vertex
                    // Only update if paths i->k and k->j exist
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][j] > dist[i][k] + dist[k][j]) {
                        // Update distance to the shorter path through k
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // -----------------------------
        // Step 3: Check for negative weight cycle
        // -----------------------------
        // If dist[i][i] < 0 for any vertex i, a negative cycle exists
        boolean hasNegativeCycle = false;
        for (int i = 0; i < v; i++) {
            if (dist[i][i] < 0) {
                hasNegativeCycle = true;
                break;
            }
        }

        // Step 4: Print result
        if (hasNegativeCycle) {
            System.out.println("⚠️ Graph contains a negative weight cycle!");
        } else {
            printSolution(dist, v);
        }
    }

    /**
     * Prints the distance matrix
     *
     * @param dist shortest distance matrix
     * @param V    number of vertices
     */
    public static void printSolution(int[][] dist, int V) {
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF "); // No path exists
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}