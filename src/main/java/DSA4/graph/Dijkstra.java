package DSA4.graph;

import java.util.*;

public class Dijkstra {

    // Main logic to process shortest paths using Dijkstra's algorithm
    public static void process(int[][] edges, int sourceNode, int n) {
        // Creating an adjacency list to represent the undirected weighted graph
        List<List<int[]>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Building the graph from edge list (undirected)
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            int weight = edges[i][2];
            // Add edge both ways since the graph is undirected
            adjacencyList.get(source).add(new int[]{destination, weight});
            adjacencyList.get(destination).add(new int[]{source, weight});
        }

        // Min-heap based on the current shortest time to reach a node
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Map.Entry.comparingByKey());

        // Initialize shortest times to all nodes as infinity (MAX_VALUE)
        int[] time = new int[n + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[sourceNode] = 0; // Distance to source is zero

        // Track visited nodes to avoid reprocessing
        boolean[] visited = new boolean[n + 1];

        // Add the source node to the priority queue with distance 0
        minHeap.offer(new AbstractMap.SimpleEntry<>(0, sourceNode));

        // Process the queue until all shortest paths are found
        while (!minHeap.isEmpty()) {
            int timeValue = minHeap.peek().getKey(); // Current known shortest time
            int nodeValue = minHeap.peek().getValue(); // Current node being processed
            minHeap.remove();

            // Skip this node if it's already been visited
            if (visited[nodeValue]) {
                continue;
            }

            // Traverse all adjacent nodes
            List<int[]> listValue = adjacencyList.get(nodeValue);
            for (int i = 0; i < listValue.size(); i++) {
                int[] arr = listValue.get(i);
                int destNode = arr[0];
                int weight = arr[1];
                int arrivalTime = timeValue + weight; // Calculate new arrival time
                int availableTime = time[destNode];

                // Relaxation step: Update time if a shorter path is found and node is unvisited
                if (arrivalTime < availableTime && !visited[destNode]) {
                    time[destNode] = arrivalTime;
                    minHeap.offer(new AbstractMap.SimpleEntry<>(arrivalTime, destNode));
                }
            }

            // Mark the current node as visited
            visited[nodeValue] = true;
        }

        // Print the final shortest times from the source node to all other nodes
        for (int i = 1; i < time.length; i++) {
            System.out.println("Time to reach the node " + i + " from the source node " + sourceNode + " is " + time[i]);
        }
    }

    // Sample main method to test the Dijkstra's implementation
    public static void main(String[] args) {
        System.out.println("Dijkstra Algorithm");

        // Each edge is represented as {source, destination, weight}
        int[][] edges = {
                {1, 2, 4},
                {1, 3, 1},
                {2, 4, 3},
                {2, 5, 2},
                {3, 6, 7},
                {4, 7, 2},
                {5, 6, 3},
                {6, 7, 4},
                {5, 8, 5},
                {8, 9, 1},
                {9, 10, 6},
                {7, 10, 2}
        };

        int sourceNode = 1;
        process(edges, sourceNode, 10);
    }
}
