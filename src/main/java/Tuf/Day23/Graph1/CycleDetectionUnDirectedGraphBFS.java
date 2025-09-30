package Tuf.Day23.Graph1;

import java.util.*;

public class CycleDetectionUnDirectedGraphBFS {
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

        System.out.println("Graph 1 has cycle: " + isCycle(V1, adj1)); // true

        // Example 2: Graph without a cycle
        int V2 = 5;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());
        adj2.get(0).addAll(Arrays.asList(1, 2));
        adj2.get(1).addAll(Arrays.asList(0, 3));
        adj2.get(2).addAll(Arrays.asList(0, 4));
        adj2.get(3).addAll(Arrays.asList(1));
        adj2.get(4).addAll(Arrays.asList(2));

        System.out.println("Graph 2 has cycle: " + isCycle(V2, adj2)); // false
    }

    // Method to detect cycles in any component of the graph
    public static boolean isCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && cycleDetection(i, adj, visited)) {
                return true; // Cycle found
            }
        }
        return false; // No cycles in any component
    }

    // BFS-based cycle detection for undirected graph
    public static boolean cycleDetection(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start, -1)); // Start node has no parent
        visited[start] = true;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node;
            int parent = pair.parent;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new Pair(neighbor, node));
                } else if (neighbor != parent) {
                    // Visited neighbor that is not parent → cycle
                    return true;
                }
            }
        }
        return false; // No cycle found in this component
    }

    // Pair class to store current node and its parent
    static class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}