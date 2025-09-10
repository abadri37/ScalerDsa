package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class BalancedBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree2();

        // Check if the tree is balanced and print the result
        System.out.println("The given Tree is balanced or not: " + solve(node).balanced);
    }

    /**
     * Recursive function to determine if a binary tree is balanced
     * @param node root of the current subtree
     * @return TreeInfo containing height and balanced status of the subtree
     */
    public static TreeInfo solve(TreeNode node) {
        // Base case: null node has height = 0 and is balanced
        if (node == null) {
            return new TreeInfo(0, true);
        }

        // Recursively check left and right subtrees
        TreeInfo left = solve(node.left);
        TreeInfo right = solve(node.right);

        // Height of current node = 1 + max(leftHeight, rightHeight)
        int height = 1 + Math.max(left.height, right.height);

        // Tree is balanced if left and right subtrees are balanced
        // and the difference of heights is at most 1
        boolean balanced = left.balanced && right.balanced &&
                (Math.abs(left.height - right.height) <= 1);

        // Return the height and balanced status of the current subtree
        return new TreeInfo(height, balanced);
    }

    // Helper class to store height and balanced status of a subtree
    static class TreeInfo {
        int height;       // Height of the subtree
        boolean balanced; // Whether the subtree is balanced

        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }
}