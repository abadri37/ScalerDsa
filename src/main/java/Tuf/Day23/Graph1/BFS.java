package Tuf.Day23.Graph1;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        int V = 5;  // Number of vertices

        // Adjacency list representation (0-based indexing)
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(Arrays.asList(2, 3, 1)); // Node 0 -> neighbors 2, 3, 1
        adj.add(Arrays.asList(0));       // Node 1 -> neighbor 0
        adj.add(Arrays.asList(0, 4));    // Node 2 -> neighbors 0, 4
        adj.add(Arrays.asList(0));       // Node 3 -> neighbor 0
        adj.add(Arrays.asList(2));       // Node 4 -> neighbor 2

        // Call bfs() and print the traversal order
        System.out.println(Arrays.toString(bfs(adj, V).toArray()));
    }

    /**
     * BFS traversal of a graph using a queue.
     * @param adj adjacency list representing the graph
     * @param V   number of vertices
     * @return list of nodes in BFS traversal order
     */
    public static List<Integer> bfs(List<List<Integer>> adj, int V) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // start BFS from node 0

        List<Integer> list = new ArrayList<>(); // store traversal order
        list.add(0);

        boolean[] visited = new boolean[V]; // track visited nodes
        visited[0] = true; // mark starting node as visited

        // Continue until the queue is empty
        while (!queue.isEmpty()) {
            Integer node = queue.poll(); // dequeue a node

            // Visit all neighbors of the current node
            List<Integer> neighbors = adj.get(node);
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);    // enqueue unvisited neighbor
                    list.add(neighbor);     // add neighbor to traversal list
                    visited[neighbor] = true; // mark as visited
                }
            }
        }

        return list;
    }
}
