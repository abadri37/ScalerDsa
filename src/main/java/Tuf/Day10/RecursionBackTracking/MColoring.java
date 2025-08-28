package Tuf.Day10.RecursionBackTracking;

public class MColoring {
    public static void main(String[] args) {
        // ✅ Positive case (solvable with 3 colors)
        // Graph represented as adjacency matrix
        // graph[i][j] = 1 means vertex i and vertex j are connected (edge exists)
        int[][] graph1 = {
                {0, 1, 1, 0},  // Vertex 0 is connected to Vertex 1 & 2
                {1, 0, 1, 0},  // Vertex 1 is connected to Vertex 0 & 2
                {1, 1, 0, 1},  // Vertex 2 is connected to Vertex 0,1 & 3
                {0, 0, 1, 0}   // Vertex 3 is connected only to Vertex 2
        };
        int m1 = 3; // Number of colors
        runTest(graph1, m1, "Positive Case");

        // ❌ Negative case (triangle graph with only 2 colors)
        int[][] graph2 = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };
        int m2 = 2; // Not enough colors
        runTest(graph2, m2, "Negative Case");
    }

    // Helper method to run each test case
    public static void runTest(int[][] graph, int m, String caseName) {
        int[] colorArray = new int[graph.length];
        System.out.println("\n--- " + caseName + " ---");
        if (solveMColoring(graph, colorArray, 0, m)) {
            System.out.println("✅ Solution exists. Colors assigned:");
            for (int i = 0; i < colorArray.length; i++) {
                System.out.println("Vertex " + i + " → Color " + colorArray[i]);
            }
        } else {
            System.out.println("❌ Solution does not exist for the above problem");
        }
    }

    public static boolean solveMColoring(int[][] graph, int[] colorArray, int vertex, int m) {
        // Base case: if all vertices are assigned a color
        if (vertex == graph.length) {
            return true;
        }

        // Try all possible colors for this vertex
        for (int color = 1; color <= m; color++) {
            if (isSafe(graph, colorArray, color, vertex)) {
                colorArray[vertex] = color; // assign color
                if (solveMColoring(graph, colorArray, vertex + 1, m)) {
                    return true; // valid assignment found
                }
                colorArray[vertex] = 0; // backtrack
            }
        }
        return false; // no valid color found
    }

    // Check if assigning this color is safe for the vertex
    public static boolean isSafe(int[][] graph, int[] colorArray, int color, int vertex) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && colorArray[i] == color) {
                return false; // adjacent vertex has same color
            }
        }
        return true;
    }
}