package DSA4.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

    /**
     * Performs a topological sort on a Directed Acyclic Graph (DAG).
     *
     * @param edges list of directed edges where each edge is {source, destination}
     * @param n     the maximum node number (nodes are numbered 1 to n)
     */
    public static void process(int[][] edges, int n) {
        // incomingNodes[i] will hold all nodes that have edges pointing to i
        List<List<Integer>> incomingNodes = new ArrayList<>();
        // outgoingNodes[i] will hold all nodes that i points to
        List<List<Integer>> outgoingNodes = new ArrayList<>();

        // Initialize adjacency lists for nodes 0 through n (we ignore index 0)
        for (int i = 0; i <= n; i++) {
            incomingNodes.add(new ArrayList<>());
            outgoingNodes.add(new ArrayList<>());
        }

        // Build the adjacency lists from the edge array
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            // Add destination as outgoing from source
            outgoingNodes.get(source).add(destination);
            // Add source as incoming to destination
            incomingNodes.get(destination).add(source);
        }

        // count[i] = number of incoming edges to node i
        int[] count = new int[n + 1];
        // Queue to hold nodes with zero incoming edges
        Queue<Integer> queue = new LinkedList<>();
        // List to record the topological order
        List<Integer> output = new ArrayList<>();

        // Initialize count[] and queue with nodes that have no incoming edges
        for (int i = 1; i < count.length; i++) {
            count[i] = incomingNodes.get(i).size();
            if (count[i] == 0) {
                // Node i has no prerequisites, so add it to the queue
                queue.add(i);
            }
        }

        // Process nodes in queue
        while (!queue.isEmpty()) {
            int value = queue.poll();
            // Append this node to the topological ordering
            output.add(value);

            // For each node that 'value' points to, decrement its incoming count
            List<Integer> outgoingList = outgoingNodes.get(value);
            for (int x : outgoingList) {
                count[x] -= 1;
                // If x now has no remaining incoming edges, add x to the queue
                if (count[x] == 0) {
                    queue.add(x);
                }
            }
        }

        // After processing, check for any node that still has incoming edges
        boolean flag = true;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                // Found a cycle or unreachable node
                flag = false;
                break;
            }
        }
        if (!flag) {
            System.out.println("Cycle detected or not all nodes reachable - -1");
            return;
        }

        // Print the topological order
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));
            if (i != output.size() - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Topological Sort Algorithm");

        // Define edges of the DAG (each pair {u, v} means u → v)
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 4},
                {3, 4},
                {2, 5},
                {5, 6},
                {4, 7},
                {6, 7},
                {7, 8},
                {8, 9},
                {9, 10}
        };
        int n = 10; // Number of nodes, labeled 1 through 10

        // Run topological sort
        process(edges, n);
    }
}
