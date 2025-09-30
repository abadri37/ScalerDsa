package Tuf.Day23.Graph1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFS {

    public static void main(String[] args) {
        int V = 5;  // Number of vertices

        // Adjacency list representation (0-based indexing)
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(Arrays.asList(2, 3, 1)); // Node 0 -> neighbors 2, 3, 1
        adj.add(Arrays.asList(0));       // Node 1 -> neighbor 0
        adj.add(Arrays.asList(0, 4));    // Node 2 -> neighbors 0, 4
        adj.add(Arrays.asList(0));       // Node 3 -> neighbor 0
        adj.add(Arrays.asList(2));       // Node 4 -> neighbor 2

        // Call dfs() and print the traversal order
        System.out.println(Arrays.toString(dfs(adj, V).toArray()));
    }

    /**
     * Iterative DFS traversal using a stack.
     * @param adj adjacency list representing the graph
     * @param V   number of vertices in the graph
     * @return list of nodes in DFS traversal order
     */
    public static List<Integer> dfs(List<List<Integer>> adj, int V) {
        List<Integer> list = new ArrayList<>(); // store traversal order

        Stack<Integer> stack = new Stack<>();
        stack.push(0); // start DFS from node 0

        boolean[] visited = new boolean[V]; // track visited nodes

        // Perform DFS until stack is empty
        while (!stack.isEmpty()) {
            Integer node = stack.pop();

            // Process node only if it hasn't been visited
            if (!visited[node]) {
                visited[node] = true;
                list.add(node); // add node to traversal order

                // Push neighbors onto the stack in reverse order
                // so that we visit them in the original adjacency list order
                List<Integer> neighbors = adj.get(node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int val = neighbors.get(i);
                    if (!visited[val]) {
                        stack.push(val);
                    }
                }
            }
        }

        return list;
    }
}