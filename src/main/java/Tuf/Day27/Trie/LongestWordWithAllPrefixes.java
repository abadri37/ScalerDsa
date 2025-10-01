package Tuf.Day27.Trie;

import java.util.ArrayList;
import java.util.List;

public class LongestWordWithAllPrefixes {

    // Root of the Trie
    public static TrieNode root;

    public static void main(String[] args) {
        // Sample input: list of words
        List<String> nums = new ArrayList<>();
        nums.add("a");
        nums.add("ap");
        nums.add("app");
        nums.add("appl");
        nums.add("apple");
        nums.add("apply");
        // You can try other test cases as well
        // nums.add("b"); nums.add("ba"); nums.add("bat"); etc.

        // Initialize root node
        root = new TrieNode();

        // Step 1: Insert all words into the Trie
        for (String st : nums) {
            insert(st);
        }

        // Step 2: Find the longest complete word
        String bestWord = "None"; // default if no complete string exists
        int bestLength = 0;

        for (String st : nums) {
            if (isLongestPrefixString(st)) { // check if every prefix exists
                // Update longest length and lexicographical order
                if (st.length() > bestLength) {
                    bestLength = st.length();
                    bestWord = st;
                } else if (st.length() == bestLength) {
                    if (st.compareTo(bestWord) < 0) { // pick lexicographically smaller
                        bestWord = st;
                    }
                }
            }
        }

        // Step 3: Output the answer
        System.out.println(bestWord);
    }

    // Function to check if all prefixes of a word exist in the Trie
    public static boolean isLongestPrefixString(String st) {
        TrieNode node = root;
        for (char c : st.toCharArray()) {
            int index = c - 'a'; // map 'a'->0, 'b'->1, ..., 'z'->25
            TrieNode child = node.children[index];
            // If child node doesn't exist OR prefix is not a complete word → return false
            if (child == null || !child.isEnd) {
                return false;
            }
            node = child; // move to next character
        }
        return true; // all prefixes exist
    }

    // Insert a word into the Trie
    public static void insert(String st) {
        TrieNode node = root;
        for (char c : st.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(); // create new node if needed
            }
            node = node.children[index]; // move to the child
        }
        node.isEnd = true; // mark the end of the word
    }

    // TrieNode structure
    static class TrieNode {
        TrieNode[] children; // 26 lowercase letters
        boolean isEnd;       // true if a word ends here

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
}