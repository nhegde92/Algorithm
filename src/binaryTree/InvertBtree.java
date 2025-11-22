package binaryTree;

/*

226. Invert Binary Tree
Solved
Easy
Topics
Companies
Given the root of a binary tree, invert the tree, and return its root.



Example 1:


Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
Example 2:


Input: root = [2,1,3]
Output: [2,3,1]
Example 3:

Input: root = []
Output: []

 */
public class InvertBtree {
    public TreeNode invertTree(TreeNode root) {
        if (root != null)
            invert(root);

        return root;

    }
    private void invert(TreeNode node) {
        if(node == null)
            return;
        TreeNode intermediate = node.left;
        node.left = node.right;
        node.right = intermediate;
        invert(node.left);
        invert(node.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(new int[]{1, 2, 3 , 4, 5, 6, 7, 8});
        tree.printLevelOrder();
        InvertBtree invert = new InvertBtree();
        invert.invertTree(tree.head);
        tree.printLevelOrder();

    }
}
