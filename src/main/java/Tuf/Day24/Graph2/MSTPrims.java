package Tuf.Day24.Graph2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Problem:
 * You are given a connected, weighted, undirected graph with V vertices numbered 0 to V-1.
 * The graph is given in the form of an adjacency list where adj[u] contains a list of pairs [v, w]
 * representing an edge between vertex u and vertex v with weight w.
 *
 * Task:
 * Find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph.
 *
 * Approach: Prim's Algorithm
 * - Start from any vertex (here, vertex 0)
 * - Use a priority queue to always pick the smallest edge connecting MST -> Non-MST vertex
 * - Keep track of visited vertices to avoid cycles
 * - Add edge weights to MST sum
 */

public class MSTPrims {

    public static void main(String[] args) {
        // Number of vertices
        int V = 4;

        // Initialize adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Build graph (vertex, weight)
        adj.get(0).add(new int[]{1, 10});
        adj.get(0).add(new int[]{2, 6});
        adj.get(0).add(new int[]{3, 5});
        adj.get(1).add(new int[]{0, 10});
        adj.get(1).add(new int[]{3, 15});
        adj.get(2).add(new int[]{0, 6});
        adj.get(3).add(new int[]{0, 5});
        adj.get(3).add(new int[]{1, 15});

        // Compute MST weight
        System.out.println("MST Weight = " + spanningTree(V, adj));

        // --- Additional example for practice ---
        int V2 = 5;
        List<List<int[]>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());

        // Build a new graph
        adj2.get(0).add(new int[]{1, 2});
        adj2.get(0).add(new int[]{3, 6});
        adj2.get(1).add(new int[]{0, 2});
        adj2.get(1).add(new int[]{2, 3});
        adj2.get(1).add(new int[]{3, 8});
        adj2.get(1).add(new int[]{4, 5});
        adj2.get(2).add(new int[]{1, 3});
        adj2.get(2).add(new int[]{4, 7});
        adj2.get(3).add(new int[]{0, 6});
        adj2.get(3).add(new int[]{1, 8});
        adj2.get(4).add(new int[]{1, 5});
        adj2.get(4).add(new int[]{2, 7});

        System.out.println("MST Weight of second graph = " + spanningTree(V2, adj2));
    }

    /**
     * Function to compute MST weight using Prim's Algorithm
     * @param v number of vertices
     * @param adj adjacency list representation of graph
     * @return sum of weights of MST edges
     */
    public static int spanningTree(int v, List<List<int[]>> adj) {
        // Priority queue (min-heap) to pick the smallest edge at each step
        PriorityQueue<Pair> heap = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));

        int mstWeight = 0;             // Total weight of MST
        boolean[] visited = new boolean[v]; // Track visited vertices

        // Start from vertex 0, weight 0
        heap.add(new Pair(0, 0));

        while (!heap.isEmpty()) {
            // Pick edge with smallest weight
            Pair current = heap.poll();
            int vertex = current.vertex;
            int weight = current.weight;

            // Skip if vertex already in MST
            if (visited[vertex]) {
                continue;
            }

            // Include this vertex in MST
            visited[vertex] = true;
            mstWeight += weight;

            // Add all edges from this vertex to PQ (only to unvisited vertices)
            for (int[] neighbor : adj.get(vertex)) {
                int vertx = neighbor[0];
                int w = neighbor[1];
                if (!visited[vertx]) {
                    heap.add(new Pair(vertx, w));
                }
            }
        }
        return mstWeight;
    }

    // Helper class to store vertex and weight for priority queue
    static class Pair {
        int vertex, weight;

        public Pair(int vertex, int weight) {
            this.weight = weight;
            this.vertex = vertex;
        }
    }
}