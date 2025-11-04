package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

/**
 * This class checks whether a given binary tree is height-balanced.
 * A binary tree is balanced if for every node:
 * the height difference between its left and right subtrees is at most 1.
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder helper class
        // (TreeBuilder.buildTree2() should return a root node)
        TreeNode node = TreeBuilder.buildTree2();

        // Check if the tree is balanced and print the result
        // solve(node) returns an object containing both height and balanced info
        System.out.println("The given Tree is balanced or not: " + solve(node).balanced);

        TreeNode node2 = TreeBuilder.buildTree();

        // Check if the tree is balanced and print the result
        // solve(node) returns an object containing both height and balanced info
        System.out.println("The given Tree is balanced or not: " + solve(node2).balanced);
    }

    /**
     * Recursive function to check if a given binary tree (subtree) is balanced.
     *
     * The function returns a TreeInfo object containing:
     *  - height of the subtree rooted at 'node'
     *  - whether that subtree is balanced or not
     *
     * @param node root of the current subtree
     * @return TreeInfo(height, balanced)
     */
    public static TreeInfo solve(TreeNode node) {
        // ✅ Base case:
        // If the node is null, it's an empty tree — considered balanced
        // Height = 0 for null node
        if (node == null) {
            return new TreeInfo(0, true);
        }

        // 🔁 Recursively get information from left and right subtrees
        TreeInfo left = solve(node.left);
        TreeInfo right = solve(node.right);

        // 📏 Compute the height of the current node:
        // Height = 1 + maximum of (left subtree height, right subtree height)
        int height = 1 + Math.max(left.height, right.height);

        // ⚖️ Determine if the current subtree is balanced:
        // 1. Left subtree must be balanced
        // 2. Right subtree must be balanced
        // 3. The height difference between left and right subtrees ≤ 1
        boolean balanced = left.balanced && right.balanced &&
                (Math.abs(left.height - right.height) <= 1);

        // 🏁 Return the computed height and balanced status for this node
        return new TreeInfo(height, balanced);
    }

    /**
     * Helper class that stores information about a subtree:
     *  - height: the height of the subtree
     *  - balanced: whether this subtree is height-balanced
     */
    static class TreeInfo {
        int height;       // Height of the current subtree
        boolean balanced; // Whether the current subtree is balanced

        // Constructor to initialize height and balanced fields
        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }
}