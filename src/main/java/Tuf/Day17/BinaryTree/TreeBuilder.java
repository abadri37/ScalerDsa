package Tuf.Day17.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeBuilder {

    /**
     * Constructs the following binary tree:
     * <p>
     * 50
     * /       \
     * 30        70
     * /   \     /    \
     * 20    40   60     90
     * /  \        / \
     * 10   25     55   65
     * / \
     * 52   58
     * <p>
     * This is a Binary Search Tree (BST)-like structure with
     * both left and right subtrees expanded at multiple levels.
     */
    public static TreeNode buildTree() {
        TreeNode root = new TreeNode(50);

        root.left = new TreeNode(30);
        root.right = new TreeNode(70);

        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);

        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(90);

        root.left.left.left = new TreeNode(10);
        root.left.left.right = new TreeNode(25);

        root.right.left.left = new TreeNode(55);
        root.right.left.right = new TreeNode(65);

        root.right.left.left.left = new TreeNode(52);
        root.right.left.left.right = new TreeNode(58);

        return root;
    }

    /**
     * Builds a bigger tree with sequential values:
     * 1
     * /   \
     * 2     3
     * /  \   / \
     * 4    5 6   7
     * / \  /
     * 8  9 10
     */
    public static TreeNode buildTree2() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);

        return root;
    }

    /**
     * Prints the binary tree upright with connecting lines.
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }

        int maxLevel = getHeight(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    // Recursive printing logic
    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || allElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(edgeLines * 2 + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i * 2 - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(edgeLines * 2 - i);
            }
            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private static boolean allElementsNull(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null)
                return false;
        }
        return true;
    }


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }
}
