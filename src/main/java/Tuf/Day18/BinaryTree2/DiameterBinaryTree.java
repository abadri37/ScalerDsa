package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class DiameterBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree2();

        // Compute the diameter of the binary tree
        TreeInfo info = solveDiameter(node);

        // Print the maximum diameter
        System.out.println("The Max Diameter of the TreeNode is " + info.diameter);
    }

    /**
     * Recursive function to calculate the diameter of a binary tree
     * @param node root of the current subtree
     * @return TreeInfo containing height and diameter of the subtree
     */
    public static TreeInfo solveDiameter(TreeNode node) {
        // Base case: empty node has height = 0 and diameter = 0
        if (node == null) {
            return new TreeInfo(0, 0);
        }

        // Recursively get height and diameter of left and right subtrees
        TreeInfo left = solveDiameter(node.left);
        TreeInfo right = solveDiameter(node.right);

        // Height of current node = 1 + max(leftHeight, rightHeight)
        int height = 1 + Math.max(left.height, right.height);

        // Diameter passing through the current node = leftHeight + rightHeight
        int diameterThroughNode = left.height + right.height;

        // Maximum diameter from children
        int maxDiameter = Math.max(left.diameter, right.diameter);

        // Overall diameter at current node = max(diameterThroughNode, maxDiameter)
        int diameter = Math.max(diameterThroughNode, maxDiameter);

        return new TreeInfo(height, diameter);
    }

    // Helper class to store height and diameter of a subtree
    static class TreeInfo {
        int diameter; // Maximum diameter of the subtree
        int height;   // Height of the subtree

        public TreeInfo(int height, int diameter) {
            this.diameter = diameter;
            this.height = height;
        }
    }
}