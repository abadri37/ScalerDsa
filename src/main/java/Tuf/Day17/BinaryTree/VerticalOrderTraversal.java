package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        List<List<Integer>> list = new ArrayList<>();
        verticalOrderTraversal(node, list);

        // Print result for visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Performs vertical order traversal of a binary tree.
     * Groups nodes by their "horizontal distance" (hd) from root.
     *
     * @param node Root node of the tree
     * @param list Final list of lists storing vertical order nodes
     */
    public static void verticalOrderTraversal(TreeNode node, List<List<Integer>> list) {
        if (node == null) {
            return;
        }

        // Queue for BFS traversal: stores nodes along with their horizontal distance (hd)
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(node, 0));

        // Map to store hd -> list of nodes
        // Using TreeMap so keys (hd) are automatically sorted (leftmost to rightmost)
        Map<Integer, List<TreeNode>> map = new TreeMap<>();

        // BFS traversal
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            // Add current node into its hd group
            map.putIfAbsent(pair.hd, new ArrayList<>());
            map.get(pair.hd).add(pair.node);

            // Left child → hd - 1
            if (pair.node.left != null) {
                queue.add(new Pair(pair.node.left, pair.hd - 1));
            }

            // Right child → hd + 1
            if (pair.node.right != null) {
                queue.add(new Pair(pair.node.right, pair.hd + 1));
            }
        }

        // Convert TreeNode lists into lists of integer values for final output
        for (Map.Entry<Integer, List<TreeNode>> entry : map.entrySet()) {
            List<Integer> nodeKeys = new ArrayList<>();
            for (TreeNode nodes : entry.getValue()) {
                nodeKeys.add(nodes.val);
            }
            list.add(nodeKeys);
        }
    }

    // Helper class to store a node along with its horizontal distance
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}