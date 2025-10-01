package Tuf.Day27.Trie;

/**
 * Problem:
 * Given a string s, count the number of **distinct substrings** including the empty substring.
 *
 * Example:
 * s = "abc"
 * Substrings: "", "a", "b", "c", "ab", "bc", "abc"
 * Answer: 7
 *
 * Key Idea:
 * - Use a Trie to efficiently store all substrings.
 * - Each **new Trie node represents a new distinct substring**.
 * - Common prefixes are shared, so duplicates are automatically ignored.
 */

public class DistinctSubString {

    public static TrieNode root;

    public static void main(String[] args) {
        root = new TrieNode();

        // Test inputs
        String[] testStrings = {"abc", "aaa", "abcd", "ababa"};

        for (String st : testStrings) {
            int count = countDistinctSubstrings(st);
            System.out.println("Distinct substrings in \"" + st + "\": " + count);
        }
    }

    /**
     * Count the number of distinct substrings in a string using Trie.
     *
     * @param st input string
     * @return number of distinct substrings including empty substring
     */
    public static int countDistinctSubstrings(String st) {
        root = new TrieNode(); // reset Trie for each string
        int count = 0;         // count of distinct substrings (excluding empty string)

        // Iterate over all possible starting indices of substrings
        for (int i = 0; i < st.length(); i++) {
            TrieNode node = root; // start from root for each new substring
            // Extend the substring from position i to end
            for (int j = i; j < st.length(); j++) {
                int index = st.charAt(j) - 'a'; // map character 'a'-'z' to 0-25
                // If the character path does not exist, create a new node
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                    count++; // new node = new distinct substring
                }
                node = node.children[index]; // move to next character
            }
        }

        return count + 1; // add 1 for the empty substring
    }

    // TrieNode structure
    static class TrieNode {
        TrieNode[] children; // 26 lowercase letters
        boolean isEnd;       // not necessary for counting substrings

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false; // not used here
        }
    }
}