package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrePostInOrderTraversal {

    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        // Lists to store the three different traversals
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        // Perform all traversals in a single recursion
        traversals(node, preorder, inorder, postorder);

        // Print results
        System.out.println("PreOrder List is " + Arrays.toString(preorder.toArray()));
        System.out.println("InOrder List is " + Arrays.toString(inorder.toArray()));
        System.out.println("PostOrder List is " + Arrays.toString(postorder.toArray()));
    }

    /**
     * Perform Preorder, Inorder, and Postorder traversals in one recursion.
     *
     * @param node current node of the binary tree
     * @param preorder list to collect preorder traversal (Root -> Left -> Right)
     * @param inorder list to collect inorder traversal (Left -> Root -> Right)
     * @param postorder list to collect postorder traversal (Left -> Right -> Root)
     */
    public static void traversals(TreeNode node, List<Integer> preorder, List<Integer> inorder, List<Integer> postorder) {
        // Base case: if node is null, just return
        if (node == null) {
            return;
        }

        // ---- PREORDER STEP ----
        // Visit the node before its children
        preorder.add(node.val);

        // Recurse on left child
        traversals(node.left, preorder, inorder, postorder);

        // ---- INORDER STEP ----
        // Visit the node after left child but before right child
        inorder.add(node.val);

        // Recurse on right child
        traversals(node.right, preorder, inorder, postorder);

        // ---- POSTORDER STEP ----
        // Visit the node after both children
        postorder.add(node.val);
    }
}