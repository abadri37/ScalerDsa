package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class MorrisInOrderTraversal {
    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        List<Integer> list = new ArrayList<>();
        inOrderTraversal(node, list);

        // Print result in array format for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static void inOrderTraversal(TreeNode node, List<Integer> list) {
        TreeNode current = node;

        // Traverse until all nodes are processed
        while (current != null) {

            // Case 1: If there is no left child
            // Directly visit this node and go to the right child
            if (current.left == null) {
                list.add(current.val);      // "Visit" current node
                current = current.right;    // Move to right subtree
            } else {
                // Case 2: If left child exists, find the inorder predecessor
                // (rightmost node in the left subtree)
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                // Subcase A: If predecessor's right is NULL,
                // make a temporary link (thread) back to current node
                if (pred.right == null) {
                    pred.right = current;     // Create thread
                    current = current.left;   // Move left to explore left subtree
                }
                // Subcase B: If predecessor's right is already pointing to current
                // -> means left subtree is already processed
                else {
                    pred.right = null;        // Remove the thread
                    list.add(current.val);    // "Visit" current node
                    current = current.right;  // Move to right subtree
                }
            }
        }
    }
}