package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class TreeIdenticalOrNot {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node1 = TreeBuilder.buildTree2();
        TreeNode node2 = TreeBuilder.buildTree2();

        // Both trees are built using buildTree2(), so they should be identical
        System.out.println("Tree is Identical or not : " + solve(node1, node2)); // true

        // Build two different trees
        TreeNode node3 = TreeBuilder.buildTree2(); // larger tree
        TreeNode node4 = TreeBuilder.buildTree();  // smaller/different tree

        // These trees should not be identical
        System.out.println("Tree is Identical or not : " + solve(node3, node4)); // false
    }

    /**
     * Function to check if two binary trees are identical.
     * Two trees are identical if:
     * 1. Both are empty (null)
     * 2. Root values are the same
     * 3. Left subtrees are identical
     * 4. Right subtrees are identical
     */
    public static boolean solve(TreeNode node1, TreeNode node2) {
        // Case 1: Both nodes are null → identical
        if (node1 == null && node2 == null) {
            return true;
        }

        // Case 2: One is null and the other is not → not identical
        if (node1 == null || node2 == null) {
            return false;
        }

        // Case 3: If values of the current nodes don’t match → not identical
        if (node1.val != node2.val) {
            return false;
        }

        // Case 4: Recursively check both left and right subtrees
        return solve(node1.left, node2.left) && solve(node1.right, node2.right);
    }
}