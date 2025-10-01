package Tuf.Day27.Trie;

/**
 * Problem: Maximum XOR of two numbers in an array.
 *
 * Idea:
 * - To maximize XOR, for each number we want to find another number such that
 *   their bits differ as much as possible.
 * - Example: if current bit = 1, we prefer to match it with 0 (opposite bit).
 *
 * Approach:
 * - Use a Trie (binary trie) where each path represents the binary form of a number.
 * - Each node has 2 children (0 and 1).
 * - Insert each number bit by bit (from MSB -> LSB).
 * - While inserting a number, also try to find the maximum XOR with numbers already in the Trie.
 *
 * Time Complexity: O(N * 32) = O(N), since each number has 32 bits.
 */
public class MaximumXorTwoNumbers {
    TrieNode root;

    /**
     * Finds the maximum XOR of two numbers in nums.
     */
    public int findMaximumXOR(int[] nums) {
        root = new TrieNode();

        // Insert the first number into the trie
        insert(nums[0]);

        int maxXor = 0;

        // For each number, find maxXor with previously inserted numbers
        for (int i = 1; i < nums.length; i++) {
            int currentXor = getMaxXor(nums[i]); // get best XOR with this number
            maxXor = Math.max(maxXor, currentXor);
            insert(nums[i]); // insert current number for future comparisons
        }
        return maxXor;
    }

    /**
     * Insert a number into the binary Trie.
     */
    public void insert(int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) { // check all 32 bits
            int bit = (num >> i) & 1;  // extract i-th bit (0 or 1)
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode(); // create if absent
            }
            node = node.children[bit]; // move to next bit node
        }
    }

    /**
     * For a given number, compute the maximum XOR
     * it can form with numbers already in the Trie.
     */
    public int getMaxXor(int num) {
        TrieNode node = root;
        int xor = 0;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;       // extract i-th bit
            int opposedBit = bit ^ 1;       // opposite bit (if bit=1, opposedBit=0)

            // Prefer to go to the opposite bit (to maximize XOR)
            if (node.children[opposedBit] != null) {
                xor = xor | (1 << i);       // add this bit to XOR result
                node = node.children[opposedBit];
            } else {
                // Otherwise, go to the same bit (forced choice)
                node = node.children[bit];
            }
        }
        return xor;
    }

    /**
     * TrieNode class for binary Trie
     * - children[0] -> represents bit 0
     * - children[1] -> represents bit 1
     */
    class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[2];
        }
    }

    /**
     * Driver code to test
     */
    public static void main(String[] args) {
        MaximumXorTwoNumbers solver = new MaximumXorTwoNumbers();

        int[] nums1 = {3, 10, 5, 25, 2, 8};
        System.out.println("Max XOR in nums1: " + solver.findMaximumXOR(nums1));
        // Expected: 28 (5 ^ 25 = 28)

        int[] nums2 = {8, 1, 2, 12, 7, 6};
        System.out.println("Max XOR in nums2: " + solver.findMaximumXOR(nums2));
        // Expected: 15 (7 ^ 8 = 15)

        int[] nums3 = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println("Max XOR in nums3: " + solver.findMaximumXOR(nums3));
        // Expected: 127
    }
}