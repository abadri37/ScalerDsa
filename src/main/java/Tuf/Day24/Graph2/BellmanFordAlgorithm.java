package Tuf.Day24.Graph2;

import java.util.Arrays;

public class BellmanFordAlgorithm {

    /*
     🧠 INTUITION BEHIND BELLMAN–FORD ALGORITHM
     -------------------------------------------------------
     👉 Goal:
        Find the shortest distance from a single source vertex to
        all other vertices in a weighted graph (which may have negative weights).

     🧩 Key Idea:
        Bellman–Ford is based on *edge relaxation*.
        It tries to improve the shortest distance to every vertex
        by repeatedly checking all edges.

     💡 GREEDY INTUITION:
        - In a graph with V vertices, the longest possible simple path
          between any two vertices has (V - 1) edges.
        - So, if we relax all edges (V - 1) times,
          every vertex’s shortest distance will be correctly computed.
        - If even after (V - 1) relaxations we can still improve a distance,
          then a *negative weight cycle* exists.

     🧱 BASIC STEPS:
        1️⃣ Initialize distances:
            dist[source] = 0, all others = ∞
        2️⃣ Relax all edges (V - 1) times:
            For each edge (u → v) with weight w:
                if dist[u] + w < dist[v], then update dist[v]
        3️⃣ Check for negative cycle:
            If any edge can still be relaxed → negative cycle detected.
        4️⃣ Print final distances.

     ⚠️ Note:
        Unlike Dijkstra’s algorithm, Bellman–Ford works fine even when edges have
        negative weights — as long as there’s no *negative cycle* reachable from the source.
    */

    public static void main(String[] args) {
        int V = 5; // number of vertices
        int E = 10; // number of edges

        // Step 1️⃣: Create all edges (directed)
        Edge[] edges = new Edge[E];
        edges[0] = new Edge(0, 1, 6);
        edges[1] = new Edge(0, 2, 7);
        edges[2] = new Edge(1, 2, 8);
        edges[3] = new Edge(1, 3, 5);
        edges[4] = new Edge(1, 4, -4);
        edges[5] = new Edge(2, 3, -3);
        edges[6] = new Edge(2, 4, 9);
        edges[7] = new Edge(3, 1, -2);
        edges[8] = new Edge(4, 0, 2);
        edges[9] = new Edge(4, 3, 7);

        bellmanFord(edges, V, 0);
    }

    public static void bellmanFord(Edge[] edges, int v, int start) {
        // Step 2️⃣: Initialize distances to all vertices
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; // distance to source = 0

        // Step 3️⃣: Relax all edges |V| - 1 times
        for (int i = 1; i < v; i++) {
            for (Edge e : edges) {
                int src = e.src;
                int dest = e.dest;
                int weight = e.weight;

                // Relaxation condition:
                if (dist[src] != Integer.MAX_VALUE && dist[src] + weight < dist[dest]) {
                    dist[dest] = dist[src] + weight;
                }
            }
        }

        // Step 4️⃣: Check for negative weight cycle
        boolean hasNegativeCycle = false;
        for (Edge e : edges) {
            int src = e.src;
            int dest = e.dest;
            int weight = e.weight;

            if (dist[src] != Integer.MAX_VALUE && dist[src] + weight < dist[dest]) {
                hasNegativeCycle = true;
                break;
            }
        }

        // Step 5️⃣: Print result
        if (hasNegativeCycle) {
            System.out.println("⚠️ Graph contains a negative weight cycle!");
        } else {
            System.out.println("✅ Shortest distances from source " + start + ":");
            for (int i = 0; i < v; i++) {
                System.out.println("Vertex " + i + " → Distance = " + dist[i]);
            }
        }
    }

    // Helper class to represent an edge in the graph
    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}

/*
🧠 EXAMPLE EXPLANATION (STEP-BY-STEP for the above graph):

Graph:
  0 → 1 (6)
  0 → 2 (7)
  1 → 2 (8)
  1 → 3 (5)
  1 → 4 (-4)
  2 → 3 (-3)
  2 → 4 (9)
  3 → 1 (-2)
  4 → 0 (2)
  4 → 3 (7)

Initial distances:
  dist[0] = 0, dist[1..4] = ∞

After 1st iteration:
  0→1 = 6, 0→2 = 7
  dist[1]=6, dist[2]=7

After 2nd iteration:
  Using (1→4, -4) → dist[4]=2
  Using (1→3, 5)  → dist[3]=11
  Using (3→1, -2) → dist[1]=9 (but 6 < 9, so no change)
  Using (4→0, 2) → dist[0]=0 (no change)

After all 4 passes → final shortest paths:
  Vertex 0 → 0
  Vertex 1 → 2
  Vertex 2 → 7
  Vertex 3 → 4
  Vertex 4 → -2

No negative cycle → algorithm ends successfully.

-------------------------------------------------------------
💥 ADDITIONAL TEST CASES
-------------------------------------------------------------

1️⃣ Test Case 1 (Basic Positive Weights)
   Vertices = 4
   Edges = [
     (0,1,5), (0,2,4), (1,3,3), (2,1,6), (3,2,2)
   ]
   Source = 0
   Expected Output:
     0 → 0
     1 → 5
     2 → 4
     3 → 8

2️⃣ Test Case 2 (Contains a Negative Cycle)
   Vertices = 3
   Edges = [
     (0,1,1), (1,2,-1), (2,0,-1)
   ]
   Source = 0
   Expected Output:
     ⚠️ Graph contains a negative weight cycle!

3️⃣ Test Case 3 (Disconnected Graph)
   Vertices = 4
   Edges = [
     (0,1,2), (1,2,3)
   ]
   Source = 0
   Expected Output:
     0 → 0
     1 → 2
     2 → 5
     3 → ∞ (unreachable)

-------------------------------------------------------------

🧩 WHY USE BELLMAN–FORD:
 - Can handle negative weights (unlike Dijkstra).
 - Useful for financial arbitrage or time-travel problems where
   cost/profit can be negative.

⚠️ LIMITATION:
 - Time complexity = O(V * E), slower than Dijkstra (O(E log V)).
 - If a negative cycle is reachable from the source, shortest paths
   become undefined (they keep decreasing infinitely).
*/