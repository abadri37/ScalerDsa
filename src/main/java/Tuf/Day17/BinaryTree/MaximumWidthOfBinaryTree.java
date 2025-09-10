package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        // Build the binary tree
        TreeNode node = TreeBuilder.buildTree();

        // Calculate and print the maximum width
        System.out.println("Maximum Width of binary tree is " + widthOfBinaryTree(node));
    }

    /**
     * Function to calculate the maximum width of a binary tree.
     * Width is defined as the length between the leftmost and rightmost
     * nodes at each level, counting nulls in between.
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0; // stores the maximum width
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0)); // root is at index 0

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at current level
            int start = 0, end = 0; // to track first and last index at this level
            int minIndex = queue.peek().index; // normalize indices to avoid integer overflow

            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                int currentIndex = current.index - minIndex; // normalized index

                // First node at this level
                if (i == 0) start = currentIndex;

                // Last node at this level
                if (i == size - 1) end = currentIndex;

                // Add left child with corresponding index
                if (current.node.left != null) {
                    queue.add(new Pair(current.node.left, currentIndex * 2));
                }

                // Add right child with corresponding index
                if (current.node.right != null) {
                    queue.add(new Pair(current.node.right, currentIndex * 2 + 1));
                }
            }

            // Width of this level = last index - first index + 1
            maxWidth = Math.max(maxWidth, end - start + 1);
        }

        return maxWidth;
    }

    // Helper class to store node and its corresponding index in a "virtual" complete tree
    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}