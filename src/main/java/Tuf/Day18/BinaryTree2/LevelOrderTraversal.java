package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

/**
 * This program performs a **Level Order Traversal** of a binary tree.
 *
 * ✅ Level Order Traversal (also known as Breadth-First Search - BFS)
 * means visiting nodes level by level from top to bottom and left to right.
 *
 * Example:
 *          1
 *         / \
 *        2   3
 *       / \   \
 *      4   5   6
 *
 * Output: [ [1], [2, 3], [4, 5, 6] ]
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using helper class TreeBuilder
        TreeNode node = TreeBuilder.buildTree2();

        // Perform level order traversal
        List<List<Integer>> list = levelOrder(node);

        // Print the traversal result in 2D form (each level as a separate list)
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Function to perform level order traversal (BFS) of a binary tree.
     *
     * @param root Root node of the binary tree
     * @return A list of lists, where each inner list represents one level of the tree
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // List to store the final result (each level in a separate list)
        List<List<Integer>> result = new ArrayList<>();

        // ✅ Base case: if tree is empty, return empty result
        if (root == null) {
            return result;
        }

        // Queue is used for Breadth-First Search (BFS)
        // It helps process nodes level by level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // Start BFS with root node

        // 🔁 Traverse until the queue becomes empty
        while (!queue.isEmpty()) {

            // Number of nodes present at the current level
            int size = queue.size();

            // Temporary list to store all node values of the current level
            List<Integer> list = new ArrayList<>();

            // Process all nodes of the current level one by one
            for (int i = 0; i < size; i++) {

                // Remove one node from the queue (FIFO order)
                TreeNode node = queue.poll();

                // Add the node’s value to the current level list
                if (node != null) {
                    list.add(node.val);
                }

                // Add child nodes (if they exist) for next level processing
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // After processing the current level, add it to the final result
            result.add(list);
        }

        // Return all levels collected during BFS traversal
        return result;
    }
}