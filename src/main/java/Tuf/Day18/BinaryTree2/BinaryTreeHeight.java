package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class BinaryTreeHeight {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree();

        // Compute and print the maximum depth (height) of the binary tree
        System.out.println("Maximum Depth of Binary Tree: " + maxDepth(node));
    }

    /**
     * Recursive function to compute the maximum depth (height) of a binary tree
     * @param node root of the current subtree
     * @return maximum depth of the subtree rooted at this node
     */
    public static int maxDepth(TreeNode node) {
        // Base case: If the node is null, height = 0
        if (node == null) {
            return 0;
        }

        // Recursively compute height of left and right subtrees
        int leftHeight = maxDepth(node.left);
        int rightHeight = maxDepth(node.right);

        // Height of current node = 1 + max(leftHeight, rightHeight)
        return 1 + Math.max(leftHeight, rightHeight);
    }
}