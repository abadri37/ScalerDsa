package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class PostOrderTraversal {

    public static void main(String[] args) {
        TreeBuilder.TreeNode node = TreeBuilder.buildTree();

        // Iterative Postorder Traversal
        List<Integer> result = postOrderIteration(node);
        System.out.println(Arrays.toString(result.toArray()));

        // Recursive Postorder Traversal
        List<Integer> list = new ArrayList<>();
        postOrderRecursion(TreeBuilder.buildTree(), list);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Recursive Postorder Traversal (Left → Right → Root)
     * Intuition:
     *   - Visit left subtree
     *   - Visit right subtree
     *   - Finally, visit the root node
     */
    public static void postOrderRecursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return; // base case
        }
        postOrderRecursion(node.left, list);  // 1. Left
        postOrderRecursion(node.right, list); // 2. Right
        list.add(node.val);                   // 3. Root
    }

    /**
     * Iterative Postorder Traversal using TWO stacks
     * Algorithm:
     *   - Push root to stack1
     *   - Pop from stack1 → push into stack2
     *   - Push left & right children of popped node into stack1
     *   - At the end, stack2 will have nodes in Root → Right → Left order
     *   - Pop all from stack2 → gives Left → Right → Root (Postorder)
     */
    public static List<Integer> postOrderIteration(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list; // empty tree
        }

        Stack<TreeNode> stack1 = new Stack<>(); // processing stack
        Stack<TreeNode> stack2 = new Stack<>(); // result stack

        // Step 1: push root into stack1
        stack1.push(node);

        // Step 2: process until stack1 is empty
        while (!stack1.isEmpty()) {
            TreeNode root = stack1.pop(); // take out a node
            stack2.push(root);            // push it into stack2

            // Push children to stack1
            if (root.left != null) {
                stack1.push(root.left);
            }
            if (root.right != null) {
                stack1.push(root.right);
            }
        }

        // Step 3: pop everything from stack2 to get Postorder
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }
}