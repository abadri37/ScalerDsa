package Tuf.Day19.BinaryTree3;

import Tuf.Day17.BinaryTree.TreeBuilder;

// LeetCode 124
public class MaxPathSum {

    // Global variable to store the maximum path sum found so far
    public static int MAX_SUM = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeBuilder.TreeNode root1 = TreeBuilder.buildTree();

        // Call the recursive function to calculate max path sum
        maxSum(root1);

        // Print the final result (global max path sum)
        System.out.println("Maximum Path Sum: " + MAX_SUM);
    }

    // Recursive DFS function to compute max path sum for each subtree
    public static int maxSum(TreeBuilder.TreeNode node) {
        // Base case: if the node is null, return 0 (no contribution)
        if (node == null) {
            return 0;
        }

        // Recursively find the maximum path sum on the left subtree
        // If the value is negative, ignore it by taking max(..., 0)
        int leftNodeValue = Math.max(maxSum(node.left), 0);

        // Recursively find the maximum path sum on the right subtree
        // Again, ignore negative paths since they reduce the sum
        int rightNodeValue = Math.max(maxSum(node.right), 0);

        // At this node, the best path that passes *through* it
        // includes node.val + leftNodeValue + rightNodeValue
        // Update global MAX_SUM if this total is higher than the previous maximum
        MAX_SUM = Math.max(MAX_SUM, node.val + leftNodeValue + rightNodeValue);

        // Return to parent: node.val + max gain from one side (either left or right)
        // because the path cannot branch upward — it continues only one way
        return node.val + Math.max(leftNodeValue, rightNodeValue);
    }
}