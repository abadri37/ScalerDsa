package Tuf.Day17.BinaryTree;

import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RootToNodePath {

    public static void main(String[] args) {
        TreeNode node = TreeBuilder.buildTree();

        // Stores all root-to-leaf paths
        List<List<Integer>> list = new ArrayList<>();

        // Find all root-to-leaf paths
        rootToNode(node, new ArrayList<>(), list);

        // Print all paths
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Recursive function to find all root-to-leaf paths.
     *
     * @param node    Current tree node
     * @param list    Current path from root to this node
     * @param results Stores all complete root-to-leaf paths
     */
    public static void rootToNode(TreeNode node, List<Integer> list, List<List<Integer>> results) {
        if (node == null) {
            return; // Base case: null node → nothing to do
        }

        // 1️⃣ Add current node to path
        list.add(node.val);

        // 2️⃣ If this is a leaf node, add the path to results
        if (node.left == null && node.right == null) {
            results.add(new ArrayList<>(list)); // copy the path
        } else {
            // 3️⃣ Recurse for left and right children
            rootToNode(node.left, list, results);
            rootToNode(node.right, list, results);
        }

        // 4️⃣ Backtrack → remove the last added node
        // (so path remains correct for other branches)
        list.remove(list.size() - 1);
    }
}