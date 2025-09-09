package Tuf.Day17.BinaryTree;

import java.util.*;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class LeftView {

    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        List<Integer> list = new ArrayList<>();
        leftView(node, list);

        // Print the result as an array for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static void leftView(TreeNode node, List<Integer> list) {
        if (node == null) return; // edge case: empty tree

        // Queue for level-order traversal (BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at the current level

            // Traverse all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // For left view, pick the first node of this level
                if (i == 0) {
                    list.add(current.val);
                }

                // Add left and right children to the queue for next level
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
    }
}