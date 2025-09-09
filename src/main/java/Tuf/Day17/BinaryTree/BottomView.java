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

    public static void bottomView(TreeNode node, List<Integer> list) {
        if (node == null) return; // edge case: empty tree

        // Queue for BFS traversal. Each element stores the node and its horizontal distance (HD)
        Queue<TopView.Pair> queue = new LinkedList<>();

        // TreeMap to store the last node at each HD in sorted order (HD -> TreeNode)
        Map<Integer, TreeNode> map = new TreeMap<>();

        // Start BFS from root with HD = 0
        queue.add(new TopView.Pair(node, 0));

        while (!queue.isEmpty()) {
            TopView.Pair pair = queue.poll();
            TreeNode currentNode = pair.node;
            int hd = pair.hd;

            // For bottom view, overwrite the value at each HD
            // This ensures the last node encountered at each HD is stored
            map.put(hd, currentNode);

            // Add left child to queue with HD - 1
            if (currentNode.left != null) {
                queue.add(new TopView.Pair(currentNode.left, hd - 1));
            }

            // Add right child to queue with HD + 1
            if (currentNode.right != null) {
                queue.add(new TopView.Pair(currentNode.right, hd + 1));
            }
        }

        // Traverse the TreeMap in order of HD (leftmost to rightmost)
        // and add the node values to the result list
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            list.add(entry.getValue().val);
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