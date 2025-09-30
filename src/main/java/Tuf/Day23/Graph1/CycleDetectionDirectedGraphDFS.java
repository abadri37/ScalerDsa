package Tuf.Day23.Graph1;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionDirectedGraphDFS {
    public static void main(String[] args) {
        // ----------------- Example 1: Graph WITH cycles -----------------
        int N = 10; // number of vertices
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges (directed)
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1); // cycle: 1 -> 2 -> 3 -> 1

        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(7).add(8);
        adj.get(8).add(6); // cycle: 6 -> 7 -> 8 -> 6

        adj.get(9).add(4); // connects node 9 to the second component

        // Print adjacency list (optional)
        System.out.println("Graph 1 adjacency list:");
        for (int i = 0; i < N; i++) {
            System.out.println("Node " + i + ": " + adj.get(i));
        }

        System.out.println("Graph 1 has cycle: " + isCycle(N, adj)); // should print true

        // ----------------- Example 2: Graph WITHOUT cycles -----------------
        int M = 5; // number of vertices
        List<List<Integer>> adj2 = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            adj2.add(new ArrayList<>());
        }

        // Add edges (directed)
        adj2.get(0).add(1);
        adj2.get(1).add(2);
        adj2.get(2).add(3);
        adj2.get(3).add(4);
        // No cycles here

        // Print adjacency list
        System.out.println("\nGraph 2 adjacency list:");
        for (int i = 0; i < M; i++) {
            System.out.println("Node " + i + ": " + adj2.get(i));
        }

        System.out.println("Graph 2 has cycle: " + isCycle(M, adj2)); // should print false
    }

    // Function to check if the directed graph contains a cycle
    public static boolean isCycle(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];   // Marks nodes that have been visited
        boolean[] recStack = new boolean[v];  // Marks nodes in the current DFS path (recursion stack)

        // Iterate all nodes in case the graph is disconnected
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (cycleDetection(i, adj, visited, recStack)) {
                    return true; // cycle detected
                }
            }
        }
        return false; // no cycles found
    }

    // Recursive DFS function to detect a cycle
    public static boolean cycleDetection(int start, List<List<Integer>> adj, boolean[] visited, boolean[] recStack) {
        visited[start] = true;      // Mark current node as visited
        recStack[start] = true;     // Add current node to recursion stack

        List<Integer> neighbors = adj.get(start);
        for (int i = 0; i < neighbors.size(); i++) {
            int val = neighbors.get(i);
            if (!visited[val]) {
                // Recursive call to DFS
                if (cycleDetection(val, adj, visited, recStack)) {
                    return true;
                }
            } else if (recStack[val]) {
                // If neighbor is in recursion stack → cycle detected
                return true;
            }
        }

        recStack[start] = false; // Backtrack: remove node from recursion stack
        return false;
    }
}