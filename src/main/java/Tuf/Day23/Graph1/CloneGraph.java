package Tuf.Day23.Graph1;

import java.util.*;

public class CloneGraph {
    public static void main(String[] args) {
        // Create nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Connect neighbors (undirected graph)
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // Call solution
        Node clonedGraph = cloneGraph(node1);

        // Print cloned graph (simple BFS print)
        printGraph(clonedGraph);
    }

    /**
     * Clone an undirected graph using BFS
     * @param node - starting node of the graph
     * @return cloned graph starting from the given node
     */
    public static Node cloneGraph(Node node) {
        if (node == null) return null;  // Edge case: empty graph

        // Queue for BFS traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // Map to store mapping: original node -> cloned node
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val, new ArrayList<>()));

        // BFS traversal
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            // Visit all neighbors of the current node
            for (Node neighbor : curr.neighbors) {
                // If neighbor is not cloned yet, clone and put into map
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor); // add original neighbor for future processing
                }
                // Add cloned neighbor to the current cloned node's neighbor list
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node); // return the clone of the starting node
    }

    /**
     * Print the graph using BFS
     * Prints each node and its neighbors
     */
    private static void printGraph(Node node) {
        if (node == null) return;

        Set<Integer> visited = new HashSet<>(); // track visited nodes to avoid infinite loop
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (visited.contains(curr.val)) continue; // already printed

            visited.add(curr.val);
            System.out.print("Node " + curr.val + " -> [");
            for (Node neighbor : curr.neighbors) {
                System.out.print(neighbor.val + " ");
                queue.add(neighbor);
            }
            System.out.println("]");
        }
    }

    // Definition for graph node
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}