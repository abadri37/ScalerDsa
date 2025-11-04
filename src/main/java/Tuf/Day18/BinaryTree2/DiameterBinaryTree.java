package Tuf.Day18.BinaryTree2;

import Tuf.Day17.BinaryTree.TreeBuilder;
import Tuf.Day17.BinaryTree.TreeBuilder.TreeNode;

/**
 * This class finds the **diameter** of a binary tree.
 *
 * The diameter (or width) of a binary tree is the length of the **longest path**
 * between any two nodes in the tree.
 *
 * This path may or may not pass through the root.
 *
 * Formula:
 *     diameter(node) = max(
 *          left.height + right.height,   // Path passes through the node
 *          left.diameter,                // Max diameter in left subtree
 *          right.diameter                // Max diameter in right subtree
 *     )
 */
public class DiameterBinaryTree {

    public static void main(String[] args) {
        // Build a sample binary tree using the TreeBuilder helper class
        TreeNode node = TreeBuilder.buildTree2();

        // Compute height and diameter information using recursion
        TreeInfo info = solveDiameter(node);

        // Print the computed maximum diameter of the tree
        System.out.println("The Max Diameter of the Tree is " + info.diameter);
    }

    /**
     * Recursive function to calculate the **diameter** of a binary tree.
     *
     * @param node root of the current subtree
     * @return TreeInfo object containing height and diameter for the subtree
     */
    public static TreeInfo solveDiameter(TreeNode node) {

        // ✅ Base Case:
        // If the node is null (empty subtree),
        // height = 0, diameter = 0
        if (node == null) {
            return new TreeInfo(0, 0);
        }

        // 🔁 Recursively compute diameter and height for left and right subtrees
        TreeInfo left = solveDiameter(node.left);
        TreeInfo right = solveDiameter(node.right);

        // 📏 Height of the current node = 1 + maximum of left and right heights
        int height = 1 + Math.max(left.height, right.height);

        // 📈 Diameter passing through the current node =
        // left subtree height + right subtree height
        int diameterThroughNode = left.height + right.height;

        // 🧮 Max diameter found in child subtrees
        int maxDiameter = Math.max(left.diameter, right.diameter);

        // 🏁 Final diameter for current node =
        // max(diameterThroughNode, maxDiameter)
        int diameter = Math.max(diameterThroughNode, maxDiameter);

        // Return TreeInfo with computed height and diameter
        return new TreeInfo(height, diameter);
    }

    /**
     * Helper class that stores both:
     *  - height of the subtree
     *  - diameter of the subtree
     *
     * This avoids recalculating heights repeatedly, improving performance.
     */
    static class TreeInfo {
        int diameter; // Maximum diameter for this subtree
        int height;   // Height of this subtree

        public TreeInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }
}
