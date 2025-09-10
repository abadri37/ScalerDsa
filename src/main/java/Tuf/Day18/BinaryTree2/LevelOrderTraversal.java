package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree();

        // Perform level order traversal
        List<List<Integer>> list = levelOrder(node);

        // Print the result as a 2D list for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Function to perform level order traversal (BFS) of a binary tree
     * @param root root node of the tree
     * @return List of lists, where each inner list contains nodes at the same level
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return empty list
        if (root == null) {
            return result;
        }

        // Queue to store nodes for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Iterate while there are nodes in the queue
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at current level
            List<Integer> list = new ArrayList<>(); // List to store current level values

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // Remove node from queue

                if (node != null) {
                    list.add(node.val); // Add node value to current level list
                }

                // Add left and right children to queue if they exist
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // Add current level list to final result
            result.add(list);
        }

        return result;
    }
}