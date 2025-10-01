package Tuf.Day27.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PowerSet - Generate all unique subsets of an integer array.
 *
 * Problem:
 * - Given an array of integers (may contain duplicates), generate all possible subsets (the power set)
 *   but remove duplicate subsets.
 *
 * Approach:
 * 1. Generate all possible subsets using recursion/backtracking.
 * 2. Insert each subset into a Trie.
 *    - The Trie is used here to automatically merge subsets that have the same sequence of numbers.
 *    - Each path from the root to a node marked as isEnd=true represents a unique subset.
 *    - This avoids manually checking duplicates with a HashSet.
 * 3. Traverse the Trie to collect all unique subsets.
 */
public class PowerSet {
    public static TrieNode root;

    public static void main(String[] args) {
        // Input array (may contain duplicates)
        int[] nums = new int[]{1, 2, 3, 3};

        // Step 1: Generate all possible subsets (including duplicates)
        List<List<Integer>> results = new ArrayList<>();
        generateSubSet(nums, 0, new ArrayList<>(), results);
        System.out.println("All subsets (with duplicates): " + results);

        // Step 2: Insert subsets into a Trie to remove duplicates
        root = new TrieNode();
        for (List<Integer> list : results) {
            insert(list);  // Each subset is inserted into the Trie
        }

        // Step 3: Collect unique subsets from the Trie
        List<List<Integer>> finalList = new ArrayList<>();
        collectSubSet(root, new ArrayList<>(), finalList);
        System.out.println("Unique subsets (from Trie): " + finalList);
    }

    /**
     * Insert a subset into the Trie.
     *
     * Each node of the Trie represents an integer from the subset.
     * A path from root to a node marked isEnd=true represents a unique subset.
     * Using a Trie ensures duplicates (subsets with same elements) are merged automatically.
     */
    public static void insert(List<Integer> list) {
        TrieNode node = root;
        for (Integer in : list) {
            // Check if child node for the current number exists
            TrieNode child = node.children.get(in);
            if (child == null) {
                // If it does not exist, create a new TrieNode and put it in the map
                child = new TrieNode();
                node.children.put(in, child);
            }
            // Move to the child node
            node = child;
        }
        // Mark the end of a subset
        node.isEnd = true;
    }

    /**
     * Collect all unique subsets from the Trie.
     *
     * - DFS traversal of Trie.
     * - Each node with isEnd=true corresponds to a valid subset.
     * - The path from root to this node gives the subset elements.
     */
    public static void collectSubSet(TrieNode node, List<Integer> list, List<List<Integer>> results) {
        if (node.isEnd) {
            results.add(new ArrayList<>(list));  // Add a copy of current path as a subset
        }
        for (int num : node.children.keySet()) {
            list.add(num);  // Include this number in current path
            collectSubSet(node.children.get(num), list, results);  // Recurse
            list.remove(list.size() - 1);  // Backtrack
        }
    }

    /**
     * Generate all subsets of the input array using recursion/backtracking.
     *
     * - For each index, we have two choices: include nums[index] or skip it.
     * - Base case: if index == nums.length, add the current subset to results.
     * - This generates all possible subsets, including duplicates if input has repeated numbers.
     */
    public static void generateSubSet(int[] nums, int index, List<Integer> list, List<List<Integer>> results) {
        if (index == nums.length) {
            results.add(new ArrayList<>(list));
            return;
        }
        // Include current number
        list.add(nums[index]);
        generateSubSet(nums, index + 1, list, results);
        list.remove(list.size() - 1);  // Backtrack
        // Exclude current number
        generateSubSet(nums, index + 1, list, results);
    }

    /**
     * TrieNode class for storing subsets.
     *
     * - children: Map<Integer, TrieNode> to dynamically store subset elements (not fixed to 26 letters).
     * - isEnd: true if this node represents the end of a subset.
     */
    static class TrieNode {
        boolean isEnd;  // marks the end of a subset
        Map<Integer, TrieNode> children;

        public TrieNode() {
            isEnd = false;
            children = new HashMap<>();
        }
    }
}