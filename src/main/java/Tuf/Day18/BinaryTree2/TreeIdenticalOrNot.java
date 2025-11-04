package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

/**
 * This class checks whether two binary trees are **identical** or not.
 *
 * ✅ Two binary trees are considered identical if:
 *   1. They have the **same structure**
 *   2. Each corresponding node has the **same value**
 *
 * Approach:
 *   - Use recursion to compare nodes pair by pair
 *   - If both nodes are null → they are identical (base case)
 *   - If only one node is null → not identical
 *   - If values differ → not identical
 *   - Otherwise, recursively check left and right subtrees
 */
public class TreeIdenticalOrNot {

    public static void main(String[] args) {
        // Build first pair of trees — both using buildTree2()
        // These should be structurally and value-wise identical
        TreeNode node1 = TreeBuilder.buildTree2();
        TreeNode node2 = TreeBuilder.buildTree2();

        // Check if both trees are identical → should return true
        System.out.println("Tree is Identical or not : " + solve(node1, node2)); // true

        // Build another pair — using two different tree structures
        TreeNode node3 = TreeBuilder.buildTree2(); // likely a larger tree
        TreeNode node4 = TreeBuilder.buildTree();  // a smaller or different tree

        // These trees should not be identical → should return false
        System.out.println("Tree is Identical or not : " + solve(node3, node4)); // false
    }

    /**
     * Recursive function to determine whether two binary trees are identical.
     *
     * @param node1 root of the first tree
     * @param node2 root of the second tree
     * @return true if trees are identical, false otherwise
     */
    public static boolean solve(TreeNode node1, TreeNode node2) {

        // 🟢 Case 1: Both nodes are null → trees match up to this point
        if (node1 == null && node2 == null) {
            return true;
        }

        // 🔴 Case 2: One node is null while the other is not → mismatch
        if (node1 == null || node2 == null) {
            return false;
        }

        // ⚖️ Case 3: Node values are not equal → trees differ
        if (node1.val != node2.val) {
            return false;
        }

        // 🔁 Case 4: Recursively check left and right subtrees
        // Trees are identical only if both subtrees are identical
        return solve(node1.left, node2.left) && solve(node1.right, node2.right);
    }
}