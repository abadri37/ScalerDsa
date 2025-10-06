package Tuf.Day24.Graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 🧠 Problem: Find the number of Strongly Connected Components (SCCs)
 *
 * You are given a directed graph with V vertices (0 to V−1) and adjacency list Adj,
 * where Adj[i] contains all vertices j such that there is a directed edge from i → j.
 *
 * Task: Return the total number of SCCs in the graph using Kosaraju’s Algorithm.
 *
 * -------------------------------------------------------------------
 * ⚙️ Algorithm Used: Kosaraju’s Algorithm (O(V + E))
 *
 * 🧩 What is a Strongly Connected Component (SCC)?
 * → A group of vertices where every vertex is reachable from every other vertex in that group.
 *
 * Example:
 *     1 → 0 → 2 → 1 forms one SCC
 *     3 → 4 are individual SCCs
 *
 * -------------------------------------------------------------------
 * 💡 Steps in Kosaraju’s Algorithm:
 *
 * Step 1️⃣ : Perform a DFS on the original graph and push nodes into a stack
 *            according to their finishing time (when all neighbors are visited).
 *
 * Step 2️⃣ : Reverse (transpose) the graph.
 *            i.e., if u → v exists, make it v → u.
 *
 * Step 3️⃣ : Pop nodes from the stack one by one.
 *            Perform DFS on the reversed graph.
 *            Each DFS traversal marks one Strongly Connected Component.
 *
 * -------------------------------------------------------------------
 * 🧮 Example Graph:
 *
 *     Vertices = {0, 1, 2, 3, 4}
 *     Edges:
 *        1 → 0
 *        0 → 2
 *        2 → 1
 *        0 → 3
 *        3 → 4
 *
 *     SCCs found:
 *        {0, 1, 2}, {3}, {4}
 *     Total SCCs = 3
 *
 * -------------------------------------------------------------------
 * ⏱️ Time Complexity:  O(V + E)
 * 💾 Space Complexity: O(V + E)
 */

public class KosarajuAlgorithm {

    public static void main(String[] args) {
        int V = 5;

        // Creating adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Original graph edges
        adj.get(1).add(0);
        adj.get(0).add(2);
        adj.get(2).add(1);
        adj.get(0).add(3);
        adj.get(3).add(4);

        System.out.println("The total number of Strongly Connected Components in the Graph is: "
                + solveKosaraju(adj, V));
    }

    // Main Kosaraju solver method
    public static int solveKosaraju(ArrayList<ArrayList<Integer>> adj, int v) {
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();

        // Step 1️⃣ : Perform DFS and push nodes in stack according to finishing time
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(adj, v, visited, i, stack);
            }
        }

        // Step 2️⃣ : Create Transpose Graph (reverse all edges)
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            revAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            for (int nbr : adj.get(i)) {
                revAdj.get(nbr).add(i); // reverse edge
            }
        }

        // Step 3️⃣ : Pop from stack and do DFS on reversed graph
        Arrays.fill(visited, false);
        int sccCount = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                sccCount++;
                revDfs(revAdj, v, visited, node);
            }
        }
        return sccCount;
    }

    // DFS for Step 1: Filling the stack
    public static void dfs(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, int node, Stack<Integer> stack) {
        visited[node] = true;
        for (int val : adj.get(node)) {
            if (!visited[val]) { // corrected condition (previously checked wrong index)
                dfs(adj, v, visited, val, stack);
            }
        }
        // Once DFS is done for this node, push it to stack
        stack.push(node);
    }

    // DFS for Step 3: Traversing the reversed graph
    public static void revDfs(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, int node) {
        visited[node] = true;
        for (int val : adj.get(node)) {
            if (!visited[val]) { // corrected condition (previously checked wrong index)
                revDfs(adj, v, visited, val);
            }
        }
    }
}

/**
 * -------------------------------------------------------------------
 * 🧠 INTUITION BEHIND THE ALGORITHM:
 *
 * 1️⃣ First DFS finds the finishing order — which node “finishes” last.
 * 2️⃣ Reversing the graph flips connectivity direction.
 * 3️⃣ When you process nodes in reverse finish order,
 *     you ensure that you visit exactly one SCC in each DFS call.
 *
 * -------------------------------------------------------------------
 * 🧩 DRY RUN (for better clarity)
 *
 * Original Graph:
 *     1 → 0 → 2 → 1
 *     0 → 3 → 4
 *
 * Step 1 (DFS Order):
 *     Stack (Top→Bottom): [4, 3, 0, 1, 2]
 *
 * Step 2 (Reversed Graph):
 *     0 → 1, 2 → 0, 1 → 2, 3 → 0, 4 → 3
 *
 * Step 3 (Process Stack):
 *     Pop 2 → Visit {2,0,1} → SCC1
 *     Pop 3 → Visit {3}     → SCC2
 *     Pop 4 → Visit {4}     → SCC3
 *
 * ✅ Total SCCs = 3
 * -------------------------------------------------------------------
 *
 * 🔥 INTERVIEW-STYLE QUESTIONS
 *
 * 1️⃣ What is the difference between a connected component and a strongly connected component?
 * 2️⃣ Why do we need to reverse the graph in Kosaraju’s algorithm?
 * 3️⃣ What is the role of the stack in Kosaraju’s algorithm?
 * 4️⃣ Can we find SCCs without reversing the graph? (Tarjan’s Algorithm)
 * 5️⃣ What is the time complexity of Kosaraju’s algorithm and why?
 * 6️⃣ Can Kosaraju’s algorithm be applied to undirected graphs?
 * 7️⃣ How is Kosaraju’s algorithm different from Tarjan’s algorithm?
 * 8️⃣ In which real-world problems are SCCs useful? (Hint: Dependency graphs, deadlock detection)
 * 9️⃣ How would the algorithm change if we used a queue instead of a stack?
 * 🔟 Could you write the algorithm iteratively (without recursion)?
 * -------------------------------------------------------------------
 */