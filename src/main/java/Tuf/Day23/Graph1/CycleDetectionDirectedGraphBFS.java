package Tuf.Day23.Graph1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionDirectedGraphBFS {

    public static void main(String[] args) {
        int V = 10; // number of vertices
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // ---------------------------
        // Example 1: Graph with cycles
        // ---------------------------

        // First cycle: 1 -> 2 -> 3 -> 1
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1);

        // Second cycle: 6 -> 7 -> 8 -> 6
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(7).add(8);
        adj.get(8).add(6);

        adj.get(9).add(4); // connects 9 to the second component

        // Print adjacency list
        System.out.println("Adjacency list (Graph with cycles):");
        for (int i = 0; i < V; i++) {
            System.out.println(i + ": " + adj.get(i));
        }

        // Run BFS cycle detection
        boolean hasCycle = isCyclicBFS(V, adj);
        System.out.println("Graph 1 has cycle: " + hasCycle);
        System.out.println();

        // ---------------------------
        // Example 2: Graph without cycles
        // ---------------------------
        int V2 = 6;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) {
            adj2.add(new ArrayList<>());
        }

        // Edges (Directed Acyclic Graph - DAG)
        adj2.get(0).add(1);
        adj2.get(0).add(2);
        adj2.get(1).add(3);
        adj2.get(2).add(4);
        adj2.get(3).add(5);

        // Print adjacency list
        System.out.println("Adjacency list (Graph without cycles):");
        for (int i = 0; i < V2; i++) {
            System.out.println(i + ": " + adj2.get(i));
        }

        // Run BFS cycle detection
        boolean hasCycle2 = isCyclicBFS(V2, adj2);
        System.out.println("Graph 2 has cycle: " + hasCycle2);
    }

    /**
     * BFS-based cycle detection using Kahn's Algorithm (Topological Sort).
     * If all nodes can be processed -> no cycle.
     * If some nodes remain unprocessed -> cycle exists.
     */
    public static boolean isCyclicBFS(int V, List<List<Integer>> adj) {
        // Step 1: Calculate in-degree (number of incoming edges) for each node
        int[] inDegree = new int[V];
        for (int i = 0; i < adj.size(); i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Step 2: Push all nodes with in-degree 0 into the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 3: Process nodes in BFS order
        int processedNodes = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedNodes++;

            // Decrease in-degree of all its neighbors
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                // If in-degree becomes 0 -> add to queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 4: If processed nodes < total nodes, cycle exists
        return processedNodes != V;
    }
}