package Tuf.Day23.Graph1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDFS {

    public static void main(String[] args) {
        // -------------------------------
        // What is Topological Sort?
        // -------------------------------
        // Topological Sort is a linear ordering of nodes in a Directed Acyclic Graph (DAG)
        // such that for every directed edge u -> v, node u comes before v in the ordering.
        //
        // Example: If course A must be taken before course B, then A should appear before B.
        //
        // NOTE: Topological sort is ONLY valid for DAGs (no cycles).
        //
        // There can be multiple valid topological orders depending on the graph.

        // -------------------------------
        // Input Graph (edges of DAG)
        // -------------------------------
        int[][] edges = {
                {1, 2}, {1, 3}, {2, 4}, {3, 4},
                {2, 5}, {5, 6}, {4, 7}, {6, 7},
                {7, 8}, {8, 9}, {9, 10}
        };
        int n = 10; // Number of nodes (nodes are 1 to 10)

        // -------------------------------
        // Step 1: Build Adjacency List
        // -------------------------------
        // adjacencyList[i] contains all nodes that i points to
        // Example: if we have edge 1 -> 2, then adjacencyList[1] contains 2
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // use n+1 because nodes start at 1
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] in : edges) {
            int source = in[0];
            int destination = in[1];
            adjacencyList.get(source).add(destination);
        }

        // -------------------------------
        // Step 2: Initialize Helpers
        // -------------------------------
        boolean[] visited = new boolean[n + 1]; // to mark visited nodes
        Stack<Integer> stack = new Stack<>();   // stack will hold topological order

        // -------------------------------
        // Step 3: DFS for all unvisited nodes
        // -------------------------------
        // We loop over ALL nodes because the graph might be disconnected
        // (multiple components). DFS ensures all nodes are covered.
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, adjacencyList, stack, visited);
            }
        }

        // -------------------------------
        // Step 4: Pop from stack to get order
        // -------------------------------
        // Why stack?
        // Because in DFS, we add a node to stack AFTER visiting all its children.
        // This guarantees that dependencies come before the node itself.
        System.out.println("Topological Sort (DFS):");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    // -------------------------------
    // DFS Helper Function
    // -------------------------------
    // Visits all neighbors recursively.
    // Once all neighbors are visited, push the current node onto the stack.
    //
    // This "post-order" behavior ensures prerequisites (children) are placed
    // in the stack before the current node.
    public static void dfs(int node, List<List<Integer>> adjacencyList, Stack<Integer> stack, boolean[] visited) {
        visited[node] = true; // mark current node as visited

        // Visit all neighbors (outgoing edges)
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjacencyList, stack, visited);
            }
        }

        // Push node to stack AFTER visiting all neighbors
        // (ensures correct dependency ordering)
        stack.push(node);
    }
}