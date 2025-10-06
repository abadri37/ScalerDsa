package Tuf.Day24.Graph2;

import java.util.*;

public class Dijkstra {

    /**
     * Dijkstra's Algorithm (Single Source Shortest Path)
     *
     * Intuition:
     * - This algorithm finds the shortest distance from a source node to all other nodes
     *   in a weighted graph with non-negative edges.
     * - It is a greedy algorithm: always pick the node with the smallest current distance.
     * - Once a node is processed, its shortest distance is finalized.
     * - Uses a priority queue (min-heap) to efficiently select the next node to process.
     *
     * Difference from Bellman-Ford:
     * - Dijkstra does NOT work with negative weight edges; Bellman-Ford does.
     * - Dijkstra is faster: O((V + E) log V) vs Bellman-Ford O(V * E).
     * - Dijkstra finalizes distances greedily, Bellman-Ford relaxes all edges V-1 times.
     * - Bellman-Ford can detect negative cycles; Dijkstra cannot.
     */
    public static void process(int[][] edges, int sourceNode, int n) {
        // Step 1: Create adjacency list for undirected weighted graph
        List<List<int[]>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Step 2: Build the graph from edge list
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            int weight = edges[i][2];
            // Add both directions since the graph is undirected
            adjacencyList.get(source).add(new int[]{destination, weight});
            adjacencyList.get(destination).add(new int[]{source, weight});
        }

        // Step 3: Initialize a min-heap (priority queue) to pick node with smallest distance
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Map.Entry.comparingByKey());

        // Step 4: Initialize distance array; time[i] = shortest distance from source to i
        int[] time = new int[n + 1];
        Arrays.fill(time, Integer.MAX_VALUE); // Initially all distances are "infinity"
        time[sourceNode] = 0; // Distance to source is zero

        // Step 5: Track visited nodes to finalize their shortest distance
        boolean[] visited = new boolean[n + 1];

        // Step 6: Add source node to min-heap
        minHeap.offer(new AbstractMap.SimpleEntry<>(0, sourceNode));

        // Step 7: Process nodes until all shortest distances are found
        while (!minHeap.isEmpty()) {
            int timeValue = minHeap.peek().getKey(); // Current shortest distance
            int nodeValue = minHeap.peek().getValue(); // Node being processed
            minHeap.remove();

            // Skip if node is already finalized
            if (visited[nodeValue]) {
                continue;
            }

            // Step 8: Relax all neighbors of the current node
            List<int[]> listValue = adjacencyList.get(nodeValue);
            for (int i = 0; i < listValue.size(); i++) {
                int[] arr = listValue.get(i);
                int destNode = arr[0];
                int weight = arr[1];
                int arrivalTime = timeValue + weight; // Distance via current node

                // Relaxation: update if shorter path is found and node is not visited
                if (arrivalTime < time[destNode] && !visited[destNode]) {
                    time[destNode] = arrivalTime;
                    minHeap.offer(new AbstractMap.SimpleEntry<>(arrivalTime, destNode));
                }
            }

            // Step 9: Mark node as visited
            visited[nodeValue] = true;
        }

        // Step 10: Print final shortest distances from source node
        for (int i = 1; i < time.length; i++) {
            System.out.println("Shortest distance from node " + sourceNode + " to node " + i + " is " + time[i]);
        }
    }

    // Sample main method to test Dijkstra's implementation
    public static void main(String[] args) {
        System.out.println("Dijkstra Algorithm Example");

        // Each edge: {source, destination, weight}
        int[][] edges = {
                {1, 2, 4}, {1, 3, 1}, {2, 4, 3}, {2, 5, 2},
                {3, 6, 7}, {4, 7, 2}, {5, 6, 3}, {6, 7, 4},
                {5, 8, 5}, {8, 9, 1}, {9, 10, 6}, {7, 10, 2}
        };

        int sourceNode = 1;
        process(edges, sourceNode, 10);
    }
}