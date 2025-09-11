package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

public class ZigZagTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree2();

        List<List<Integer>> result = new ArrayList<>();
        solve(node, result);

        // Print ZigZag traversal result
        System.out.println("ZigZag Traversal: " + Arrays.toString(result.toArray()));
    }

    public static void solve(TreeNode node, List<List<Integer>> result) {
        if (node == null) {
            return; // Base case: empty tree
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node); // Start BFS with root node

        boolean isLeftToRight = true; // Flag to control direction

        // Standard BFS traversal
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at current level
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // If direction is left-to-right → add normally
                if (isLeftToRight) {
                    list.add(current.val);
                }
                // If direction is right-to-left → add at the front
                else {
                    list.add(0, current.val);
                }

                // Push children into queue for next level
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            // Add the current level’s nodes to result
            result.add(list);

            // Flip direction for the next level
            isLeftToRight = !isLeftToRight;
        }
    }
}