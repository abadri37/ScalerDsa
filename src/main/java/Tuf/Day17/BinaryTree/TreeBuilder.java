package Tuf.Day17.BinaryTree;

public class TreeBuilder {

    /**
     * Constructs the following binary tree:
     * <p>
     * 50
     * /      \
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
