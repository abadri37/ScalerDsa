package Tuf.Day27.Trie;

import java.util.Arrays;

public class MaximumXorWithElementFromArray {
    TrieNode root = new TrieNode();

    /*
     * Problem:
     * For each query (x, m), we want to find the maximum XOR of x with any number
     * from nums[] such that the chosen number <= m.
     * If no such number exists, return -1.
     *
     * Approach:
     * 1. Sort nums[] so that we can insert numbers into the Trie in increasing order.
     * 2. Sort queries[] by m (limit), and process them in that order.
     * 3. For each query:
     *    - Insert all numbers <= m into the Trie.
     *    - Query the Trie to find the maximum XOR with x.
     * 4. Return answers in the original query order.
     */

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums); // Step 1: Sort numbers
        int n = queries.length;
        int[][] queriesWithIndex = new int[n][3];
        int[] ans = new int[n];

        // Step 2: Attach index to queries so we can return results in original order
        for (int i = 0; i < n; i++) {
            queriesWithIndex[i][0] = queries[i][0]; // x
            queriesWithIndex[i][1] = queries[i][1]; // m
            queriesWithIndex[i][2] = i;             // original index
        }

        // Sort queries by m (limit)
        Arrays.sort(queriesWithIndex, (a, b) -> a[1] - b[1]);

        int idx = 0; // pointer for nums[]
        for (int[] q : queriesWithIndex) {
            int x = q[0], m = q[1], originalIdx = q[2];

            // Insert all numbers from nums[] that are <= m into the Trie
            while (idx < nums.length && nums[idx] <= m) {
                insert(nums[idx]);
                idx++;
            }

            // If no number inserted, result = -1
            if (idx == 0) {
                ans[originalIdx] = -1;
            } else {
                ans[originalIdx] = getMaxXor(x);
            }
        }
        return ans;
    }

    // Function to find maximum XOR of num with elements in Trie
    public int getMaxXor(int num) {
        TrieNode node = root;
        int xor = 0;
        for (int i = 31; i >= 0; i--) { // check 32-bit representation
            int bit = (num >> i) & 1;
            int opposedBit = bit ^ 1; // flip the bit to maximize XOR
            if (node.children[opposedBit] != null) {
                xor |= (1 << i); // add power of 2 for this bit
                node = node.children[opposedBit];
            } else {
                node = node.children[bit];
            }
        }
        return xor;
    }

    // Function to insert number into the Trie
    public void insert(int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    // Trie Node (binary tree with 2 children)
    class TrieNode {
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[2]; // children[0] and children[1]
        }
    }

    // ------------------------------
    // Example run
    // ------------------------------
    public static void main(String[] args) {
        MaximumXorWithElementFromArray solver = new MaximumXorWithElementFromArray();

        int[] nums = {0, 1, 2, 3, 4}; // input array
        int[][] queries = {
                {3, 1}, // x=3, m=1 → numbers allowed: {0,1}
                {1, 3}, // x=1, m=3 → numbers allowed: {0,1,2,3}
                {5, 6}  // x=5, m=6 → numbers allowed: {0,1,2,3,4}
        };

        int[] result = solver.maximizeXor(nums, queries);

        System.out.println("Results:");
        for (int r : result) {
            System.out.println(r);
        }

        /*
         * Dry run:
         * Query(3,1): numbers={0,1}
         *   Max XOR = max(3^0=3, 3^1=2) = 3
         * Query(1,3): numbers={0,1,2,3}
         *   Max XOR = max(1^0=1, 1^1=0, 1^2=3, 1^3=2) = 3
         * Query(5,6): numbers={0,1,2,3,4}
         *   Max XOR = max(5^0=5, 5^1=4, 5^2=7, 5^3=6, 5^4=1) = 7
         *
         * Output: [3,3,7]
         */
    }
}