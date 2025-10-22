package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

public class BottomView {

    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        // List to store bottom view nodes
        List<Integer> list = new ArrayList<>();
        bottomView(node, list);

        // Print the bottom view as an array for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Computes the Bottom View of a Binary Tree.
     *
     * ➤ The Bottom View of a binary tree is the set of nodes visible when the tree
     *   is viewed from the **bottom**.
     *
     * ➤ It consists of the **last (deepest) node** at each horizontal distance (HD)
     *   from the root when traversed level by level (BFS).
     *
     * ➤ Conceptually, this is similar to the Top View, except that:
     *   - Top View → takes the **first node** encountered at each HD.
     *   - Bottom View → takes the **last node** encountered at each HD.
     *
     * ➤ The **leftmost node** (smallest HD) becomes the **first element** in the final result list,
     *   and the **rightmost node** (largest HD) becomes the **last element**.
     *
     * Example tree:
     *
     *                50
     *             /      \
     *           30        70
     *         /   \     /    \
     *       20    40   60     90
     *      /  \        / \
     *    10   25     55   65
     *              / \
     *            52   58
     *
     * Horizontal Distances (HD):
     *  -3 → 10
     *  -2 → 20
     *  -1 → 25   (overwrites 30 as it’s deeper)
     *   0 → 55   (overwrites 50 and 60)
     *  +1 → 65
     *  +2 → 90
     *
     * Hence, Bottom View: [10, 20, 25, 55, 65, 90]
     *   → 10 (leftmost visible node)
     *   → 90 (rightmost visible node)
     *
     * @param node Root of the binary tree
     * @param list List to store nodes forming the bottom view
     */
    public static void bottomView(TreeNode node, List<Integer> list) {
        if (node == null) return; // edge case: empty tree

        // Queue for BFS traversal.
        // Each entry contains a node and its corresponding horizontal distance (HD) from root.
        Queue<Pair> queue = new LinkedList<>();

        // TreeMap is used to store nodes at each HD.
        // Keys (HD) are automatically sorted, ensuring left-to-right order in output.
        // For Bottom View, we keep *overwriting* the value at each HD.
        Map<Integer, TreeNode> map = new TreeMap<>();

        // Start BFS traversal from the root with HD = 0.
        queue.add(new Pair(node, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode currentNode = pair.node;
            int hd = pair.hd;

            // For Bottom View, overwrite the entry at this HD.
            // This ensures that the *last node encountered* (deepest one in BFS order)
            // will remain mapped to this HD.
            map.put(hd, currentNode);

            // Add left child with HD - 1
            if (currentNode.left != null) {
                queue.add(new Pair(currentNode.left, hd - 1));
            }

            // Add right child with HD + 1
            if (currentNode.right != null) {
                queue.add(new Pair(currentNode.right, hd + 1));
            }
        }

        // Traverse the TreeMap in sorted HD order (from leftmost to rightmost)
        // and collect node values to form the final bottom view list.
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            list.add(entry.getValue().val);
        }
    }

    /**
     * Helper class to store a node along with its horizontal distance (HD).
     * HD is calculated relative to the root:
     *   - Left child → HD - 1
     *   - Right child → HD + 1
     */
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}