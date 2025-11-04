package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

/**
 * This class finds the **Lowest Common Ancestor (LCA)** of two nodes in a binary tree.
 *
 * ✅ The Lowest Common Ancestor of two nodes n1 and n2 in a binary tree
 * is the **lowest (deepest)** node that has **both n1 and n2 as descendants**.
 *
 * For example:
 *          1
 *         / \
 *        2   3
 *       / \
 *      4   5
 *
 * LCA(4,5) = 2
 * LCA(4,3) = 1
 * LCA(2,4) = 2
 */
public class LCAOfBinaryTree {

    public static void main(String[] args) {
        // 🌳 Build a sample Binary Search Tree (BST-like)
        TreeNode root1 = TreeBuilder.buildTree();

        System.out.println("LCA in Tree 1 (BST-like):");

        // Test various LCA cases in the BST-like tree
        System.out.println("LCA of 10 and 25: " + lca(root1, 10, 25).val); // Expected 20
        System.out.println("LCA of 52 and 58: " + lca(root1, 52, 58).val); // Expected 55
        System.out.println("LCA of 25 and 40: " + lca(root1, 25, 40).val); // Expected 30
        System.out.println("LCA of 55 and 90: " + lca(root1, 55, 90).val); // Expected 70

        // 🌳 Build another general (non-BST) binary tree
        TreeNode root2 = TreeBuilder.buildTree2();

        System.out.println("\nLCA in Tree 2 (General Binary Tree):");

        // Test LCA for general binary tree
        System.out.println("LCA of 8 and 9: " + lca(root2, 8, 9).val);   // Expected 4
        System.out.println("LCA of 8 and 10: " + lca(root2, 8, 10).val); // Expected 2
        System.out.println("LCA of 9 and 6: " + lca(root2, 9, 6).val);   // Expected 1
        System.out.println("LCA of 10 and 7: " + lca(root2, 10, 7).val); // Expected 1
    }

    /**
     * Recursive function to find the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
     *
     * This function works for both:
     *  - Binary Search Trees (BSTs)
     *  - General Binary Trees
     *
     * @param node Current root node of the (sub)tree being processed
     * @param n1   Value of the first node
     * @param n2   Value of the second node
     * @return TreeNode that is the Lowest Common Ancestor (LCA) of n1 and n2
     */
    public static TreeNode lca(TreeNode node, int n1, int n2) {

        // 🧱 Base condition:
        // If the current node is null, return null (no node found here)
        // OR if current node matches either n1 or n2, return this node
        // because the current node could be one of the nodes we are looking for.
        if (node == null || node.val == n1 || node.val == n2) {
            return node;
        }

        // 🔁 Recursively search for LCA in left and right subtrees
        TreeNode left = lca(node.left, n1, n2);
        TreeNode right = lca(node.right, n1, n2);

        // ⚖️ Case 1: If both left and right recursive calls return non-null,
        // it means:
        //   → one of the nodes (n1 or n2) is in the left subtree
        //   → and the other one is in the right subtree.
        // Hence, the current node is the **Lowest Common Ancestor**.
        if (left != null && right != null) {
            return node;
        }

        // ⚙️ Case 2: If only one side (left or right) returns non-null,
        // it means both nodes are in that same subtree.
        // So we bubble up the non-null result to the parent.
        return left != null ? left : right;
    }
}