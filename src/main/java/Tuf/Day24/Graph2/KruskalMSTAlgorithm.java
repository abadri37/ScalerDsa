package Tuf.Day24.Graph2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 🧠 INTUITION BEHIND KRUSKAL’S ALGORITHM (MST)

 👉 Goal:
    We need to connect all vertices with the minimum possible total edge weight,
    without forming any cycles — that’s what a Minimum Spanning Tree (MST) is.

 ⚙️ Kruskal’s Algorithm Strategy:
    Kruskal’s algorithm follows a greedy approach —
    it always picks the smallest available edge that doesn’t form a cycle.

 🪜 Step-by-Step Intuition:

    1️⃣ Represent all edges as a list of (u, v, weight).
        - Since we need to compare edges by weight, a simple list is best.
        - We’re not doing DFS or BFS; instead, we think in terms of edges.

    2️⃣ Sort all edges in ascending order of their weights.
        - We want to pick the smallest edge first (greedy choice).
        - This ensures that when we connect components, it’s always the cheapest way.

    3️⃣ Use a Disjoint Set Union (Union-Find) structure.
        - It helps us check whether adding an edge would create a cycle.
        - Each vertex starts in its own set (disconnected component).
        - If two vertices are already in the same set, adding an edge between them would form a cycle.

    4️⃣ Iterate over all edges (from smallest to largest):
        - If the two vertices belong to *different sets*,
          we safely add this edge to the MST and merge the two sets (union).
        - If they belong to the *same set*, skip this edge (it forms a cycle).

    5️⃣ Continue until we have used (V - 1) edges in the MST.
        - A connected graph with V vertices always has (V - 1) edges in its MST.

 🔄 Union-Find Intuition:
    - FIND(x): returns the "representative" (root) of the component containing x.
      -> Think of it as: “Which group does this node currently belong to?”
    - UNION(x, y): merges the groups containing x and y.
      -> Used when we connect two different components with an edge.
    - Path Compression (in find): Makes future finds faster by directly connecting nodes to their root.
    - Union by Rank: Ensures we attach the smaller tree under the larger one to keep trees shallow.

 💡 Why Kruskal Works (Greedy Proof Idea):
    - Each step picks the smallest possible edge that doesn’t break the MST property.
    - The greedy choice is always safe because:
      Adding the smallest non-cyclic edge can never prevent forming a valid MST later.

 🧩 Example (Simple Understanding):
    Given edges:
        (0,1,10), (0,2,6), (0,3,5), (1,3,15), (2,3,4)
    After sorting by weight:
        (2,3,4), (0,3,5), (0,2,6), (0,1,10), (1,3,15)
    - Pick (2,3): connects 2–3
    - Pick (0,3): connects 0–3
    - Skip (0,2): would form a cycle
    - Pick (0,1): connects 1
    ✅ MST edges = {(2,3), (0,3), (0,1)}, total weight = 19

 🚀 Summary:
    Kruskal’s Algorithm = Sort edges + Pick smallest non-cyclic edges using Union-Find.
*/

public class KruskalMSTAlgorithm {
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                {0, 1, 10},
                {0, 2, 6},
                {0, 3, 5},
                {1, 3, 15},
                {2, 3, 4}
        };

        System.out.println("Weight of MST is: " + spanningTree(V, edges));

        // Additional test cases
        testCases();
    }

    /**
     * Computes the total weight of the Minimum Spanning Tree using Kruskal's Algorithm.
     */
    public static int spanningTree(int V, int[][] edgesInput) {
        List<Edge> edges = new ArrayList<>();

        // Step 1: Convert input edges into Edge objects
        for (int[] e : edgesInput) {
            edges.add(new Edge(e[0], e[1], e[2]));
        }

        // Step 2: Sort all edges based on their weights
        Collections.sort(edges);

        int mstWeight = 0;
        int edgesUsed = 0;

        // Step 3: Initialize Disjoint Set for cycle detection
        DisjointSet ds = new DisjointSet(V);

        // Step 4: Pick smallest edges and check if they form a cycle
        for (Edge edge : edges) {
            int uParent = DisjointSet.find(edge.src);
            int vParent = DisjointSet.find(edge.dest);

            // If they belong to different sets, include the edge in MST
            if (uParent != vParent) {
                mstWeight += edge.weight;
                DisjointSet.union(uParent, vParent);
                edgesUsed++;
            }

            // Optimization: Stop when MST has V-1 edges
            if (edgesUsed == V - 1)
                break;
        }

        return mstWeight;
    }

    /**
     * Helper class to represent an edge with a comparable weight.
     */
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight; // Sort ascending by weight
        }
    }

    /**
     * Disjoint Set Union (Union-Find) structure for detecting cycles efficiently.
     */
    static class DisjointSet {
        static int[] parent, rank;

        public DisjointSet(int v) {
            parent = new int[v];
            rank = new int[v];
            for (int i = 0; i < v; i++) {
                parent[i] = i; // each node is initially its own parent
                rank[i] = 0;   // initial rank = 0
            }
        }

        /**
         * Find operation with path compression:
         * Makes each node in the path point directly to the root.
         */
        public static int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // Path compression
            return parent[x];
        }

        /**
         * Union by Rank:
         * Attach smaller tree under the root of the deeper tree.
         */
        public static void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    /**
     * Some additional test cases to validate your understanding.
     */
    public static void testCases() {
        int[][] edges1 = {
                {0, 1, 1},
                {1, 2, 2},
                {0, 2, 3}
        };
        System.out.println("MST (Test 1) = " + spanningTree(3, edges1)); // Expected 3

        int[][] edges2 = {
                {0, 1, 10},
                {1, 2, 5},
                {2, 3, 4},
                {0, 3, 7},
                {1, 3, 9}
        };
        System.out.println("MST (Test 2) = " + spanningTree(4, edges2)); // Expected 16

        int[][] edges3 = {
                {0, 1, 4},
                {0, 2, 3},
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 4},
                {3, 4, 2},
                {4, 5, 6}
        };
        System.out.println("MST (Test 3) = " + spanningTree(6, edges3)); // Expected 14
    }
}