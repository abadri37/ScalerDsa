package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

/**
 * This program performs a **ZigZag (Spiral) Level Order Traversal** of a binary tree.
 *
 * ✅ ZigZag traversal means:
 *   - Traverse the first level (root) from **left to right**,
 *   - The next level from **right to left**,
 *   - Then alternate directions for each subsequent level.
 *
 * Example:
 *          1
 *         / \
 *        2   3
 *       / \   \
 *      4   5   6
 *
 * ZigZag traversal → [ [1], [3, 2], [4, 5, 6] ]
 */
public class ZigZagTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder helper class
        TreeNode node = TreeBuilder.buildTree2();

        // List to store traversal result as a list of levels
        List<List<Integer>> result = new ArrayList<>();

        // Perform zigzag level-order traversal
        solve(node, result);

        // Print the zigzag traversal result
        System.out.println("ZigZag Traversal: " + Arrays.toString(result.toArray()));
    }

    /**
     * Function to perform ZigZag (spiral) level order traversal of a binary tree.
     * @param node   Root node of the tree
     * @param result List to store the traversal result
     */
    public static void solve(TreeNode node, List<List<Integer>> result) {
        // ✅ Base case: if the tree is empty, there's nothing to traverse
        if (node == null) {
            return;
        }

        // Queue for standard level order (BFS) traversal
        Queue<TreeNode> queue = new LinkedList<>();

        // Start BFS by adding the root node to the queue
        queue.add(node);

        // Flag to control the direction of traversal for each level
        boolean isLeftToRight = true;

        // 🔁 Perform BFS level by level
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level
            List<Integer> list = new ArrayList<>(); // Holds values of current level

            // Process all nodes in the current level
            for (int i = 0; i < size; i++) {
                // Dequeue a node from the front of the queue
                TreeNode current = queue.poll();

                // 🧭 If traversing left-to-right, append to the end of list
                if (isLeftToRight) {
                    list.add(current.val);
                }
                // 🔄 If traversing right-to-left, insert at the beginning
                else {
                    list.add(0, current.val);
                }

                // 🚸 Add child nodes for next level traversal
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            // 📥 Add current level's result to the final list
            result.add(list);

            // 🔁 Flip the direction for the next level
            isLeftToRight = !isLeftToRight;
        }
    }
}