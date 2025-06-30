package DSA2.Recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public static void main(String[] args) {
        int n = 5; // Total number of elements to permute (1 to 5)
        int k = 2; // Find the k-th permutation in lexicographical order

        // A boolean array to keep track of used numbers (1-based indexing)
        boolean[] bool = new boolean[n + 1];

        // List to store all permutations
        List<List<Integer>> result = new ArrayList<>();

        // Generate all permutations of numbers from 1 to n
        generatePermuationSequence(n, new ArrayList<>(), bool, result);

        // Get the (k-1)th permutation from the result list (0-based indexing)
        List<Integer> list = result.get(k - 1);

        // Convert the list of integers to a string
        StringBuilder sb = new StringBuilder();
        for (int nt : list) {
            sb.append(nt);
        }

        // Print the k-th permutation sequence
        System.out.println("The result is " + sb.toString());
    }

    /**
     * Generates all permutations of numbers from 1 to n using backtracking.
     *
     * @param n      The total number of elements to permute
     * @param path   The current permutation being built
     * @param used   Boolean array to mark which numbers are used
     * @param result List to store all generated permutations
     */
    public static void generatePermuationSequence(int n, List<Integer> path, boolean[] used,
                                                  List<List<Integer>> result) {
        // Base case: if the path has n elements, it's a valid permutation
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Try all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue; // Skip if the number is already used
            }

            // Choose the number
            used[i] = true;
            path.add(i);

            // Recurse to build the rest of the permutation
            generatePermuationSequence(n, path, used, result);

            // Backtrack: remove the last added number and mark it unused
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
