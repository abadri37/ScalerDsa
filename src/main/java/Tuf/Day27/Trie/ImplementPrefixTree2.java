package Tuf.Day27.Trie;

public class ImplementPrefixTree2 {

    public static TrieNode root;

    public ImplementPrefixTree2() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (char c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
            node.prefixCount++; // increment prefix count for each node
        }
        node.isEnd = true;
        node.wordCount++; // word ends here
    }

    // Count how many times a word was inserted
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (char c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return 0;
            }
            node = node.children[index];
        }
        return node.wordCount;
    }

    // Count how many words start with this prefix
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        char[] ch = prefix.toCharArray();
        for (char c : ch) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return 0;
            }
            node = node.children[index];
        }
        return node.prefixCount;
    }

    // Erase one occurrence of a word
    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) {
            return; // word not present
        }
        TrieNode node = root;
        char[] ch = word.toCharArray();
        for (char c : ch) {
            int index = c - 'a';
            TrieNode child = node.children[index];
            if (child == null) {
                return; // nothing to erase
            }
            child.prefixCount--; // reduce prefix count
            if (child.prefixCount == 0) {
                node.children[index] = null; // free memory
                return;
            }
            node = child;
        }
        node.wordCount--; // reduce word count
        if (node.wordCount == 0) {
            node.isEnd = false; // no words end here now
        }
    }

    // TrieNode definition
    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        int wordCount;
        int prefixCount;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
            prefixCount = 0;
            wordCount = 0;
        }
    }

    // ---------------- TESTING ----------------
    public static void main(String[] args) {
        ImplementPrefixTree2 trie = new ImplementPrefixTree2();

        // Insert words
        trie.insert("apple");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");

        // Count words equal to
        System.out.println("Count equal to 'apple': " + trie.countWordsEqualTo("apple")); // 2
        System.out.println("Count equal to 'app': " + trie.countWordsEqualTo("app"));     // 1
        System.out.println("Count equal to 'bat': " + trie.countWordsEqualTo("bat"));     // 1
        System.out.println("Count equal to 'ball': " + trie.countWordsEqualTo("ball"));   // 0

        // Count words starting with
        System.out.println("Count starting with 'app': " + trie.countWordsStartingWith("app")); // 3 ("apple","apple","app")
        System.out.println("Count starting with 'ba': " + trie.countWordsStartingWith("ba"));   // 1 ("bat")

        // Erase one "apple"
        trie.erase("apple");
        System.out.println("Count equal to 'apple' after erase: " + trie.countWordsEqualTo("apple")); // 1
        System.out.println("Count starting with 'app' after erase: " + trie.countWordsStartingWith("app")); // 2

        // Erase remaining "apple"
        trie.erase("apple");
        System.out.println("Count equal to 'apple' after erase again: " + trie.countWordsEqualTo("apple")); // 0
        System.out.println("Count starting with 'app' after erasing both 'apple': " + trie.countWordsStartingWith("app")); // 1 ("app")

        // Erase "app"
        trie.erase("app");
        System.out.println("Count equal to 'app': " + trie.countWordsEqualTo("app")); // 0
        System.out.println("Count starting with 'app': " + trie.countWordsStartingWith("app")); // 0
    }
}