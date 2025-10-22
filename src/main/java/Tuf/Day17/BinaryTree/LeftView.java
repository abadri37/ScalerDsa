package Tuf.Day17.BinaryTree;

import java.util.*;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class LeftView {

    public static void main(String[] args) {
        // Build a sample binary tree
        TreeNode node = TreeBuilder.buildTree();

        // List to store nodes that form the Left View of the binary tree
        List<Integer> list = new ArrayList<>();
        leftView(node, list);

        // Print the Left View as an array for better readability
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Computes the Left View of a binary tree.
     *
     * ➤ The Left View of a binary tree contains all the nodes that are visible
     *   when the tree is viewed from the **left side**.
     *
     * ➤ In simpler terms:
     *   - For each level (depth) of the tree, the **first node** encountered
     *     from the left is part of the Left View.
     *
     * ➤ Example:
     *                50
     *             /      \
     *           30        70
     *         /   \     /    \
     *       20    40   60     90
     *      /  \        / \
     *    10   25     55   65
     *              / \
     *            52   58
     *
     * Left View: [50, 30, 20, 10, 52]
     *
     * Explanation:
     *  - Level 1: 50  → first node visible from left
     *  - Level 2: 30  → first node at this level
     *  - Level 3: 20  → first node at this level
     *  - Level 4: 10  → first node at this level
     *  - Level 5: 52  → first node at deepest visible branch
     *
     * @param node Root of the binary tree
     * @param list List that stores nodes forming the Left View
     */
    public static void leftView(TreeNode node, List<Integer> list) {
        if (node == null) return; // edge case: handle empty tree

        // Queue is used for level-order traversal (BFS)
        // We process nodes level by level from top to bottom.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        // Iterate through all levels of the tree
        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at the current level

            // Process all nodes of the current level
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // The first node (i == 0) at each level is the one visible from the left
                if (i == 0) {
                    list.add(current.val);
                }

                // Add the left child first to maintain left-to-right traversal order
                if (current.left != null) {
                    queue.add(current.left);
                }

                // Add the right child next
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
    }
}