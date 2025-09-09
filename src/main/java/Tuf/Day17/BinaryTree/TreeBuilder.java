package Tuf.Day17.BinaryTree;

public class TreeBuilder {

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

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }
}
