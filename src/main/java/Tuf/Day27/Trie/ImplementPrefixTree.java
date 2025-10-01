package Tuf.Day27.Trie;

public class ImplementPrefixTree {

    public static void main(String[] args) {
        // ✅ Simulate LeetCode operations
        // Operations: ["Trie","insert","search","search","startsWith","insert","search"]
        // Arguments:  [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]

        ImplementPrefixTree trie = new ImplementPrefixTree(); // "Trie" operation

        // "insert","apple"
        insert("apple");

        // "search","apple" → true
        System.out.println("search(\"apple\") = " + search("apple")); // ✅ true

        // "search","app" → false (not inserted yet)
        System.out.println("search(\"app\") = " + search("app")); // ❌ false

        // "startsWith","app" → true
        System.out.println("startsWith(\"app\") = " + startsWith("app")); // ✅ true

        // "insert","app"
        insert("app");

        // "search","app" → true (now inserted)
        System.out.println("search(\"app\") = " + search("app")); // ✅ true
    }

    public static TrieNode root;

    public ImplementPrefixTree() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public static void insert(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (Character c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    // Search full word in the Trie
    public static boolean search(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (Character c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }

    // Check if prefix exists in the Trie
    public static boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] ch = prefix.toCharArray();
        for (Character c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index]; // ✅ FIX: Move node to child
        }
        return true;
    }

    // TrieNode definition
    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }
}