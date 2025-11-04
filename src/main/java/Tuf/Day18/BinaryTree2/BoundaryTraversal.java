package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * This program performs a **Boundary Traversal** of a binary tree.
 *
 * Boundary traversal means:
 * - Visit nodes in the following order:
 *   1️⃣ Left boundary (top to bottom, excluding leaves)
 *   2️⃣ Leaf nodes (left to right)
 *   3️⃣ Right boundary (bottom to top, excluding leaves)
 *
 * Example:
 *          1
 *         / \
 *        2   3
 *       / \   \
 *      4   5   6
 *         / \
 *        7   8
 *
 * Boundary traversal: [1, 2, 4, 7, 8, 6, 3]
 */
public class BoundaryTraversal {

    public static void main(String[] args) {
        // Build a sample binary tree using TreeBuilder helper class
        TreeNode node = TreeBuilder.buildTree2();

        // List to store boundary nodes in traversal order
        List<Integer> result = new ArrayList<>();

        // Perform boundary traversal
        solve(node, result);

        // Print boundary traversal result
        System.out.println("Boundary Traversal: " + Arrays.toString(result.toArray()));
    }

    /**
     * Performs boundary traversal of the binary tree.
     * @param node Root node of the tree
     * @param result List to collect the traversal order
     */
    public static void solve(TreeNode node, List<Integer> result) {
        if (node == null) {
            return; // Empty tree — nothing to traverse
        }

        // Step 1️⃣: Add root node (only if it's not a leaf)
        if (!isLeafNode(node)) {
            result.add(node.val);
        }

        // Step 2️⃣: Add left boundary nodes (excluding leaf nodes)
        LeftBoundaryNodes(node.left, result);

        // Step 3️⃣: Add all leaf nodes from left and right subtrees
        leafNodes(node, result);

        // Step 4️⃣: Add right boundary nodes (excluding leaves, in reverse)
        rightBoundaryNodes(node.right, result);
    }

    /**
     * Traverse the left boundary (excluding leaf nodes).
     * Always prefer going left; if left doesn't exist, go right.
     */
    public static void LeftBoundaryNodes(TreeNode node, List<Integer> list) {
        while (node != null) {
            if (!isLeafNode(node)) {
                list.add(node.val); // Add boundary node
            }
            // Move down: prefer left child, otherwise right
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    /**
     * Perform DFS (Depth-First Search) to collect all leaf nodes.
     * A leaf node is a node without left or right children.
     */
    public static void leafNodes(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        // If it's a leaf, add to list and stop exploring further
        if (isLeafNode(node)) {
            list.add(node.val);
            return;
        }

        // Recursively visit left and right children
        leafNodes(node.left, list);
        leafNodes(node.right, list);
    }

    /**
     * Traverse the right boundary (excluding leaf nodes).
     * We push nodes to a stack first to reverse the order
     * because right boundary should be printed bottom-up.
     */
    public static void rightBoundaryNodes(TreeNode node, List<Integer> list) {
        Stack<Integer> stack = new Stack<>(); // To reverse order

        // Traverse the right boundary
        while (node != null) {
            if (!isLeafNode(node)) {
                stack.push(node.val); // Temporarily store node
            }

            // Move down: prefer right child, otherwise left
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        // Pop nodes to get bottom-up order
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
    }

    /**
     * Utility function to check whether a node is a leaf node.
     * @param node The node to check
     * @return true if both left and right children are null
     */
    public static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
