package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

public class MorrisInOrderTraversal {
    public static void main(String[] args) {
        // Build a sample binary tree from TreeBuilder (assume it returns a root node)
        TreeNode node = TreeBuilder.buildTree();

        // List to store inorder traversal result
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(node, list);

        // Print the inorder traversal result
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Performs **Morris Inorder Traversal** on a binary tree.
     *
     * ➤ Time Complexity: O(n)
     * ➤ Space Complexity: O(1)
     *
     * ✅ **Core Idea (Threaded Binary Tree Concept):**
     * Instead of using recursion or stack (which consume O(h) space),
     * we establish *temporary threads* from the rightmost node of a node’s
     * left subtree (inorder predecessor) back to that node.
     *
     * ✅ **Inorder Traversal Order:**
     *     → Left Subtree → Root → Right Subtree
     *
     * ✅ **Why Morris Traversal?**
     * - Completely eliminates recursion and extra space
     * - Tree structure is temporarily modified but restored back
     * - Great for memory-efficient traversals on large trees
     *
     * ✅ **Example Binary Tree:**
     *
     *                50
     *             /      \
     *           30        70
     *         /   \     /    \
     *       20    40   60     90
     *
     * Inorder Traversal (Left → Root → Right):
     * [20, 30, 40, 50, 60, 70, 90]
     *
     * @param node Root node of the binary tree
     * @param list List that stores the inorder traversal sequence
     */
    public static void inOrderTraversal(TreeNode node, List<Integer> list) {
        TreeNode current = node;

        // Continue traversal until all nodes are processed
        while (current != null) {

            /**
             * CASE 1: If the current node has NO LEFT CHILD
             * - This means there is no left subtree to process
             * - So, "visit" the node and move to its right child
             */
            if (current.left == null) {
                list.add(current.val);      // Visit the node
                current = current.right;    // Move to right subtree
            }

            /**
             * CASE 2: If the current node HAS a LEFT CHILD
             * - Find its inorder predecessor (rightmost node in its left subtree)
             * - Use that predecessor to create or remove a temporary "thread"
             */
            else {
                // Step 1: Find the inorder predecessor of the current node
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                /**
                 * SUBCASE A: First time visiting this subtree
                 * - The predecessor's right is NULL
                 * - Create a temporary thread back to 'current'
                 * - Move left to continue exploring left subtree
                 */
                if (pred.right == null) {
                    pred.right = current;     // Create temporary thread
                    current = current.left;   // Move to left subtree
                }

                /**
                 * SUBCASE B: Second time visiting the same node
                 * - Thread already exists (pred.right == current)
                 * - This means left subtree is processed
                 * - Remove the temporary thread to restore tree
                 * - Visit the current node (Inorder: after left)
                 * - Move to right subtree
                 */
                else {
                    pred.right = null;        // Remove the thread
                    list.add(current.val);    // Visit current node
                    current = current.right;  // Move to right subtree
                }
            }
        }
    }
}