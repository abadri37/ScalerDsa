package Tuf.Day23.Graph1;

import java.util.*;

public class CycleDetectionUnDirectedGraphDFS {

    public static void main(String[] args) {
        // Example 1: Graph with a cycle
        int V1 = 6;
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        adj1.get(0).addAll(Arrays.asList(1, 3));
        adj1.get(1).addAll(Arrays.asList(0, 2, 4));
        adj1.get(2).addAll(Arrays.asList(1, 5));
        adj1.get(3).addAll(Arrays.asList(0, 4));
        adj1.get(4).addAll(Arrays.asList(1, 3, 5));
        adj1.get(5).addAll(Arrays.asList(2, 4));

        System.out.println("Graph 1 has cycle: " + isCycleDFS(V1, adj1)); // true

        // Example 2: Graph without a cycle
        int V2 = 5;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());
        adj2.get(0).addAll(Arrays.asList(1, 2));
        adj2.get(1).addAll(Arrays.asList(0, 3));
        adj2.get(2).addAll(Arrays.asList(0, 4));
        adj2.get(3).addAll(Arrays.asList(1));
        adj2.get(4).addAll(Arrays.asList(2));

        System.out.println("Graph 2 has cycle: " + isCycleDFS(V2, adj2)); // false
    }

    // Iterate through all vertices to check for cycles in all components
    public static boolean isCycleDFS(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && cycleDetection(i, adj, visited)) {
                return true; // Cycle found
            }
        }
        return false; // No cycle in any component
    }

    // DFS iterative cycle detection using a stack
    public static boolean cycleDetection(int start, List<List<Integer>> adj, boolean[] visited) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(start, -1)); // Start node has no parent
        visited[start] = true;

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int node = pair.node;
            int parent = pair.parent;

            // Explore all neighbors
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(new Pair(neighbor, node)); // Current node becomes parent
                } else if (neighbor != parent) {
                    // Visited neighbor that is not parent → cycle
                    return true;
                }
                // If neighbor is parent, ignore it (not a cycle)
            }
        }
        return false; // No cycle found in this component
    }

    // Pair class to store current node and its parent in DFS
    static class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}