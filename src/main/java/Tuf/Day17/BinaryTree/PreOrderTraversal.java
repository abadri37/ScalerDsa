package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class PreOrderTraversal {
    public static void main(String[] args) {
        // Build a sample tree
        TreeNode node = TreeBuilder.buildTree();

        // Iterative Preorder Traversal
        List<Integer> result = preOrderIteration(node);
        System.out.println(Arrays.toString(result.toArray()));

        // Recursive Preorder Traversal
        List<Integer> list = new ArrayList<>();
        preOrderRecursion(TreeBuilder.buildTree(), list);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Recursive Preorder Traversal (Root → Left → Right)
     * Intuition: Visit root first, then recursively process left subtree,
     * then right subtree.
     */
    public static void preOrderRecursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return; // base case: reached leaf’s child
        }
        list.add(node.val);               // 1. Visit root
        preOrderRecursion(node.left, list);  // 2. Traverse left
        preOrderRecursion(node.right, list); // 3. Traverse right
    }

    /**
     * Iterative Preorder Traversal using Stack
     * Algorithm:
     *   - Use a stack to simulate recursion
     *   - Push root first
     *   - Pop node, add to result
     *   - Push right child first, then left child (so left is processed first)
     */
    public static List<Integer> preOrderIteration(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list; // empty tree
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node); // start with root

        while (!stack.isEmpty()) {
            TreeNode root = stack.pop(); // take out current node
            list.add(root.val);          // visit root

            // Push right first so that left gets processed first
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return list;
    }
}