package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MorrisPreOrderTraversal {

    public static void main(String[] args) {
        // Build a sample tree using TreeBuilder
        TreeNode node = TreeBuilder.buildTree();

        // List to store preorder traversal result
        List<Integer> list = new ArrayList<>();
        preOrderTraversal(node, list);

        // Print the Preorder traversal result
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Performs **Morris Preorder Traversal** of a binary tree.
     *
     * ➤ Time Complexity: O(n)
     * ➤ Space Complexity: O(1)
     *
     * ✅ **Key Idea (Threaded Binary Tree Concept):**
     * Morris traversal eliminates recursion and stack usage by temporarily modifying
     * the tree's right pointers (creating “threads”) to revisit nodes after
     * traversing their left subtrees.
     *
     * ✅ **Preorder Traversal Order:**
     *    → Visit Root → Traverse Left Subtree → Traverse Right Subtree
     *
     * ✅ **Why Morris?**
     *  - Normally, recursive and stack-based traversals use O(h) space (where h = height).
     *  - Morris traversal uses *no extra space*, modifying the tree temporarily.
     *  - The tree is fully restored to its original structure afterward.
     *
     * ✅ **Example Tree:**
     *
     *                50
     *             /      \
     *           30        70
     *         /   \     /    \
     *       20    40   60     90
     *
     * Preorder Traversal (Root → Left → Right):
     * [50, 30, 20, 40, 70, 60, 90]
     *
     * @param node Root of the binary tree
     * @param list List that will store preorder traversal nodes
     */
    public static void preOrderTraversal(TreeNode node, List<Integer> list) {
        TreeNode current = node;

        // Continue traversal until all nodes are processed
        while (current != null) {

            /**
             * CASE 1: If the current node does NOT have a left child
             * → Visit the node (Preorder = process before children)
             * → Move to the right subtree
             */
            if (current.left == null) {
                list.add(current.val); // Visit the current node
                current = current.right; // Move right
            }

            /**
             * CASE 2: If the current node has a LEFT child
             * → Find its inorder predecessor (rightmost node in the left subtree)
             * → Use this predecessor to create or remove temporary threads
             */
            else {
                // Step 1: Find the inorder predecessor of the current node
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                /**
                 * CASE 2a: First time visiting this node (no thread yet)
                 * - Visit current node (because it's preorder: process before going left)
                 * - Create a temporary thread from predecessor → current
                 * - Move to the left child
                 */
                if (pred.right == null) {
                    list.add(current.val); // Visit current node
                    pred.right = current; // Create thread
                    current = current.left; // Move to left subtree
                }

                /**
                 * CASE 2b: Second time visiting (thread already exists)
                 * - This means the left subtree is already processed.
                 * - Remove the thread to restore tree structure.
                 * - Move to the right child.
                 */
                else {
                    pred.right = null; // Remove thread
                    current = current.right; // Move to right subtree
                }
            }
        }
    }
}