package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MorrisPreOrderTraversal {
    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        List<Integer> list = new ArrayList<>();
        preOrderTraversal(node, list);

        // Print result in array format for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    // Morris Preorder Traversal: O(n) time, O(1) extra space
    public static void preOrderTraversal(TreeNode node, List<Integer> list) {
        TreeNode current = node;

        // Loop until we finish traversing the tree
        while (current != null) {

            // Case 1: No left child
            if (current.left == null) {
                // Visit the node (Preorder: root first)
                list.add(current.val);

                // Move right
                current = current.right;
            }
            else {
                // Case 2: Node has a left child
                // Find the inorder predecessor of current (rightmost node in left subtree)
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                // If predecessor's right is null → first visit
                if (pred.right == null) {
                    // Visit current node before going left (Preorder)
                    list.add(current.val);

                    // Create a temporary thread from predecessor back to current
                    pred.right = current;

                    // Move to left child
                    current = current.left;
                }
                // If predecessor’s right already points to current → second visit
                else {
                    // Remove the temporary thread (restore tree structure)
                    pred.right = null;

                    // Move to right child
                    current = current.right;
                }
            }
        }
    }
}