package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;
import java.util.*;

/**
 * This class performs a Vertical Order Traversal of a binary tree.
 *
 * In Vertical Order Traversal, nodes are grouped based on their
 * horizontal distance (hd) from the root:
 *
 *        hd = 0 at root
 *        hd - 1 for left child
 *        hd + 1 for right child
 *
 * Nodes with the same hd are printed in the same vertical line.
 * For example:
 *
 *                50
 *            /       \
 *          30         70
 *        /   \       /   \
 *      20    40   60     90
 *     / \         / \
 *   10  25      55  65
 *                / \
 *              52  58
 *
 *  Vertical Order Output:
 *  [
 *    [10],
 *    [20, 52],
 *    [30, 25, 55],
 *    [50, 40, 60, 58],
 *    [70, 65],
 *    [90]
 *  ]
 */
public class VerticalOrderTraversal {

    public static void main(String[] args) {

        // Build a sample binary tree from TreeBuilder
        TreeNode root = TreeBuilder.buildTree();

        // This list will store the final result
        // Each element represents one vertical column
        List<List<Integer>> list = new ArrayList<>();

        // Perform vertical order traversal
        verticalOrderTraversal(root, list);

        // Print result as list of lists for clarity
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Performs vertical order traversal of the given binary tree.
     * The result is filled into the provided list parameter.
     *
     * @param node Root node of the binary tree
     * @param list Output parameter where final vertical order nodes are added
     */
    public static void verticalOrderTraversal(TreeNode node, List<List<Integer>> list) {
        if (node == null) {
            return;
        }

        // Queue for BFS traversal
        // Each element in the queue holds a node and its horizontal distance (hd)
        Queue<Pair> queue = new LinkedList<>();

        // Start with root node having hd = 0
        queue.add(new Pair(node, 0));

        // TreeMap is used instead of HashMap to automatically
        // keep horizontal distances sorted from leftmost to rightmost
        Map<Integer, List<TreeNode>> map = new TreeMap<>();

        // Perform a level-order (BFS) traversal of the tree
        while (!queue.isEmpty()) {

            // Remove the current node from the queue
            Pair pair = queue.poll();

            // If this hd is not in the map, create a new list
            map.putIfAbsent(pair.hd, new ArrayList<>());

            // Add the current node to its corresponding hd list
            map.get(pair.hd).add(pair.node);

            // For the left child, horizontal distance decreases by 1
            if (pair.node.left != null) {
                queue.add(new Pair(pair.node.left, pair.hd - 1));
            }

            // For the right child, horizontal distance increases by 1
            if (pair.node.right != null) {
                queue.add(new Pair(pair.node.right, pair.hd + 1));
            }
        }

        // After BFS traversal completes, we now have all nodes
        // grouped by their horizontal distance (sorted order)
        // Convert the TreeNode values into integer lists for final output
        for (Map.Entry<Integer, List<TreeNode>> entry : map.entrySet()) {

            // For each column (hd), create a list of integer values
            List<Integer> nodeValues = new ArrayList<>();

            // Add all node values from this hd
            for (TreeNode nodes : entry.getValue()) {
                nodeValues.add(nodes.val);
            }

            // Add this column list to the final result
            list.add(nodeValues);
        }
    }

    /**
     * Helper class to store a TreeNode along with its horizontal distance (hd)
     * from the root. Used to track each node’s vertical position during BFS.
     */
    static class Pair {
        TreeNode node;  // The current node reference
        int hd;         // Horizontal distance of this node from root

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}