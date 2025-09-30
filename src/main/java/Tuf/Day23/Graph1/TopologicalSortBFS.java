package Tuf.Day23.Graph1;

import java.util.*;

public class TopologicalSortBFS {

    public static void main(String[] args) {
        System.out.println("Topological Sort Algorithm using BFS (Kahn's Algorithm)");

        // -----------------------------------------
        // Input graph: Directed Acyclic Graph (DAG)
        // -----------------------------------------
        // Each edge {u, v} means a directed edge from u -> v
        // i.e., u must come before v in the topological ordering
        int[][] edges = {
                {1, 2}, {1, 3}, {2, 4}, {3, 4},
                {2, 5}, {5, 6}, {4, 7}, {6, 7},
                {7, 8}, {8, 9}, {9, 10}
        };
        int n = 10; // Number of nodes (1 through 10)

        // Run topological sort and print the result
        List<Integer> topoOrder = process(edges, n);
        if (topoOrder != null) {
            System.out.println("Topological Sort Order: " + topoOrder);
        }
    }

    // ------------------------------------------------
    // BFS-based Topological Sort (Kahn's Algorithm)
    // ------------------------------------------------
    public static List<Integer> process(int[][] edges, int n) {
        // incomingNodes[i] = list of nodes pointing to i (in-degree connections)
        List<List<Integer>> incomingNodes = new ArrayList<>();
        // outgoingNodes[i] = list of nodes that i points to (out-degree connections)
        List<List<Integer>> outgoingNodes = new ArrayList<>();

        // Step 1: Initialize adjacency lists for all nodes
        // (Index 0 is unused, as nodes are labeled from 1..n)
        for (int i = 0; i <= n; i++) {
            incomingNodes.add(new ArrayList<>());
            outgoingNodes.add(new ArrayList<>());
        }

        // Step 2: Build adjacency lists from edges
        for (int[] in : edges) {
            int source = in[0];
            int destination = in[1];
            // Add outgoing edge for source
            outgoingNodes.get(source).add(destination);
            // Add incoming edge for destination
            incomingNodes.get(destination).add(source);
        }

        // Step 3: Calculate in-degrees for each node
        // count[i] = number of incoming edges to node i
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count[i] = incomingNodes.get(i).size();
        }

        // Step 4: Initialize queue with all nodes that have in-degree = 0
        // (These nodes have no prerequisites and can be processed immediately)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }

        // Step 5: BFS traversal
        // Pick nodes with in-degree = 0, remove their outgoing edges,
        // and update in-degrees of their neighbors.
        List<Integer> output = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Get node with in-degree = 0
            output.add(node);        // Add it to topological order

            // Reduce in-degree of all its neighbors
            for (int neighbor : outgoingNodes.get(node)) {
                count[neighbor] -= 1; // Remove edge node -> neighbor
                if (count[neighbor] == 0) {
                    // If neighbor now has in-degree 0, add it to queue
                    queue.add(neighbor);
                }
            }
        }

        // Step 6: Check for cycle
        // If there is a cycle, not all nodes will be processed
        for (int i = 1; i <= n; i++) {
            if (count[i] > 0) {
                // At least one node still has in-degree > 0
                // => cycle exists OR graph is not fully reachable
                System.out.println("Cycle detected or graph is not a valid DAG");
                return null;
            }
        }

        // Return the valid topological order
        return output;
    }
}