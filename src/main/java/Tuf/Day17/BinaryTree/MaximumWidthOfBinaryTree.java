package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class finds the **maximum width** of a binary tree.
 *
 * The width of a level is defined as the distance (count) between
 * the leftmost and rightmost nodes in that level — including nulls in between.
 *
 * Example:
 * For a tree like:
 *
 *                50
 *             /      \
 *           30        70
 *         /   \     /    \
 *       20    40   60     90
 *      /  \        / \
 *    10   25     55   65
 *               / \
 *             52   58
 *
 * The maximum width occurs at the level with nodes [10, 25, 55, 65]
 * → Width = 6 (because of the index gap in the virtual complete tree representation)
 */
public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using helper class TreeBuilder
        TreeNode node = TreeBuilder.buildTree();

        // Print the tree structure for reference
        TreeBuilder.printTree(node);

        // Compute and display the maximum width of the binary tree
        System.out.println("Maximum Width of binary tree is " + widthOfBinaryTree(node));
    }

    /**
     * Calculates the maximum width of a binary tree.
     *
     * Approach:
     *  - Use Level Order Traversal (BFS)
     *  - Assign each node a "virtual index" as if the tree were a complete binary tree.
     *  - For each level, calculate width = (last_index - first_index + 1)
     *  - Keep track of the maximum width across all levels.
     *
     * Example of virtual indexing:
     *          (0)
     *        /     \
     *      (0*2)   (0*2+1)
     *     i.e. 0   and 1
     *
     * For left → index = parent_index * 2
     * For right → index = parent_index * 2 + 1
     *
     * @param root the root of the binary tree
     * @return maximum width among all levels
     */
    public static int widthOfBinaryTree(TreeNode root) {
        // Base case: if tree is empty
        if (root == null) return 0;

        int maxWidth = 0; // variable to store the maximum width found so far

        // Queue for level order traversal (BFS)
        // Each element in queue holds the node + its virtual index
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0)); // root has index 0

        // Traverse the tree level by level
        while (!queue.isEmpty()) {

            int size = queue.size(); // number of nodes at the current level
            int start = 0, end = 0;  // to track first and last index at this level

            // To prevent integer overflow (especially in deep trees),
            // we normalize the index of the first node in the level
            int minIndex = queue.peek().index;

            // Process all nodes in the current level
            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();

                // Normalize current index
                int currentIndex = current.index - minIndex;

                // For the first node of this level → record as start
                if (i == 0) start = currentIndex;

                // For the last node of this level → record as end
                if (i == size - 1) end = currentIndex;

                // Add left child to queue with computed virtual index
                if (current.node.left != null) {
                    queue.add(new Pair(current.node.left, currentIndex * 2));
                }

                // Add right child to queue with computed virtual index
                if (current.node.right != null) {
                    queue.add(new Pair(current.node.right, currentIndex * 2 + 1));
                }
            }

            // Compute the width of this level
            // Formula: (last_index - first_index + 1)
            int currentWidth = end - start + 1;

            // Update maxWidth if current level's width is larger
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        // Return the maximum width found across all levels
        return maxWidth;
    }

    /**
     * Helper class to store:
     * - The current TreeNode reference
     * - Its "virtual index" in a conceptual complete binary tree
     */
    static class Pair {
        TreeNode node; // actual node reference
        int index;     // virtual index of this node

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}