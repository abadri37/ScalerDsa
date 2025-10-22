package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.*;

public class TopView {
    public static void main(String[] args) {
        // Build a sample tree from TreeBuilder (assume it gives a root node)
        TreeNode node = TreeBuilder.buildTree();

        // List to store top view nodes
        List<Integer> list = new ArrayList<>();
        topView(node, list);

        // Print the top view as an array for easy visualization
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Computes the Top View of a Binary Tree.
     *
     * ➤ The Top View of a binary tree is the set of nodes visible when the tree
     *   is viewed from the top.
     *
     * ➤ It essentially includes the **first node encountered at each horizontal distance (HD)**
     *   when traversing the tree level-wise (BFS). Nodes that are "below" others in the same
     *   vertical line are not visible from the top.
     *
     * ➤ The **leftmost node** (smallest HD) becomes the **first element** in the final result list,
     *   and the **rightmost node** (largest HD) becomes the **last element**.
     *
     * For example, for the following tree:
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
     *  -1 → 30
     *   0 → 50
     *  +1 → 70
     *  +2 → 90
     *
     * Hence, Top View: [10, 20, 30, 50, 70, 90]
     *   → 10 (leftmost visible node)
     *   → 90 (rightmost visible node)
     *
     * @param node Root of the binary tree
     * @param list List to store nodes forming the top view
     */
    public static void topView(TreeNode node, List<Integer> list) {
        if (node == null) return; // edge case: empty tree

        // Queue for BFS traversal.
        // Each entry contains a node and its corresponding horizontal distance (HD) from root.
        Queue<Pair> queue = new LinkedList<>();

        // TreeMap is used to store the first node seen at each HD.
        // Keys (HD) are automatically sorted, ensuring left-to-right order in output.
        Map<Integer, TreeNode> map = new TreeMap<>();

        // Start BFS from the root with HD = 0.
        queue.add(new Pair(node, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode currentNode = pair.node;
            int hd = pair.hd;

            // If this HD is not yet present in the map, record it.
            // This ensures that only the *first node* encountered at each HD
            // (topmost node from this viewpoint) is stored.
            if (!map.containsKey(hd)) {
                map.put(hd, currentNode);
            }

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
        // and collect node values to form the final top view list.
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