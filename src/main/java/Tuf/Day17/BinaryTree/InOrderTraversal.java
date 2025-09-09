package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class InOrderTraversal {
    public static void main(String[] args) {
        TreeNode node = TreeBuilder.buildTree();

        // Iterative Inorder Traversal
        List<Integer> result = inOrderIteration(node);
        System.out.println(Arrays.toString(result.toArray()));

        // Recursive Inorder Traversal
        List<Integer> list = new ArrayList<>();
        inOrderRecursion(TreeBuilder.buildTree(), list);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Recursive Inorder Traversal (Left → Root → Right)
     * Intuition:
     *   - Go left until you can’t
     *   - Visit the root
     *   - Then process the right subtree
     */
    public static void inOrderRecursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return; // base case
        }
        inOrderRecursion(node.left, list);  // 1. Left
        list.add(node.val);                 // 2. Root
        inOrderRecursion(node.right, list); // 3. Right
    }

    /**
     * Iterative Inorder Traversal using Stack
     * Algorithm:
     *   - Use a stack to simulate the recursive call stack
     *   - Push nodes while going left
     *   - When left is null, pop from stack (visit root)
     *   - Then move to right subtree
     */
    public static List<Integer> inOrderIteration(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list; // empty tree
        }

        TreeNode current = node;
        Stack<TreeNode> stack = new Stack<>();

        // Continue until all nodes are processed
        while (current != null || !stack.isEmpty()) {

            // Step 1: Go as left as possible
            while (current != null) {
                stack.push(current); // save current node
                current = current.left;
            }

            // Step 2: Process the node (backtrack)
            current = stack.pop();
            list.add(current.val); // visit root

            // Step 3: Explore right subtree
            current = current.right;
        }
        return list;
    }
}