package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class LCAOfBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder.buildTree()
        TreeNode root1 = TreeBuilder.buildTree();

        System.out.println("LCA in Tree 1 (BST-like):");
        System.out.println("LCA of 10 and 25: " + lca(root1, 10, 25).val); // Expected 20
        System.out.println("LCA of 52 and 58: " + lca(root1, 52, 58).val); // Expected 55
        System.out.println("LCA of 25 and 40: " + lca(root1, 25, 40).val); // Expected 30
        System.out.println("LCA of 55 and 90: " + lca(root1, 55, 90).val); // Expected 70

        // Build another sample binary tree using TreeBuilder.buildTree2()
        TreeNode root2 = TreeBuilder.buildTree2();

        System.out.println("\nLCA in Tree 2 (General Binary Tree):");
        System.out.println("LCA of 8 and 9: " + lca(root2, 8, 9).val);   // Expected 4
        System.out.println("LCA of 8 and 10: " + lca(root2, 8, 10).val); // Expected 2
        System.out.println("LCA of 9 and 6: " + lca(root2, 9, 6).val);   // Expected 1
        System.out.println("LCA of 10 and 7: " + lca(root2, 10, 7).val); // Expected 1
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
     *
     * @param node Current root of the tree (or subtree)
     * @param n1   Value of the first node
     * @param n2   Value of the second node
     * @return The TreeNode that is the LCA of n1 and n2
     */
    public static TreeNode lca(TreeNode node, int n1, int n2) {
        // Base condition:
        // If node is null, or node itself matches n1 or n2, return node
        if (node == null || node.val == n1 || node.val == n2) {
            return node;
        }

        // Search LCA in the left and right subtrees
        TreeNode left = lca(node.left, n1, n2);
        TreeNode right = lca(node.right, n1, n2);

        // If both left and right are non-null,
        // it means n1 is in one subtree and n2 is in the other → current node is the LCA
        if (left != null && right != null) {
            return node;
        }

        // Otherwise, return whichever side is non-null
        return left != null ? left : right;
    }
}