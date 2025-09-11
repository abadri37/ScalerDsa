package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BoundaryTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree2();

        List<Integer> result = new ArrayList<>();
        solve(node, result);

        // Print boundary traversal result
        System.out.println("Boundary Traversal: " + Arrays.toString(result.toArray()));
    }

    public static void solve(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Step 1: Add root node (if not a leaf)
        if (!isLeafNode(node)) {
            result.add(node.val);
        }

        // Step 2: Add left boundary (excluding leaf nodes)
        LeftBoundaryNodes(node.left, result);

        // Step 3: Add all leaf nodes (both left & right subtrees)
        leafNodes(node, result);

        // Step 4: Add right boundary (excluding leaf nodes, in reverse order)
        rightBoundaryNodes(node.right, result);
    }

    // Traverse down the left boundary (excluding leaf nodes)
    public static void LeftBoundaryNodes(TreeNode node, List<Integer> list) {
        while (node != null) {
            if (!isLeafNode(node)) {
                list.add(node.val);
            }
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    // DFS to collect all leaf nodes
    public static void leafNodes(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (isLeafNode(node)) {
            list.add(node.val);
            return;
        }
        leafNodes(node.left, list);
        leafNodes(node.right, list);
    }

    // Traverse down the right boundary (excluding leaf nodes)
    // and store them in stack to reverse the order
    public static void rightBoundaryNodes(TreeNode node, List<Integer> list) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            if (!isLeafNode(node)) {
                stack.push(node.val);
            }
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        // Add to result in reverse (bottom-up)
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
    }

    // Utility function to check if a node is a leaf
    public static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}