package binarysearchtree;

import java.util.*;
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    // Constructors
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class ValidateBST {
    // ---------- Approach 1: Using Min/Max Ranges (Top-Down) ----------

    /**
     * Validates BST using min/max range approach.
     * Ensures all node values lie within allowed min-max range.
     */
    public boolean isValidBST(TreeNode root) {
        return checkValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Recursive function to check if subtree rooted at `root`
     * is a valid BST within [min, max) bounds.
     */
    public boolean checkValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return checkValidBST(root.left, min, root.val) &&
                checkValidBST(root.right, root.val, max);
    }

    // ---------- Approach 2: In-order Traversal (Left -> Root -> Right) ----------

    private long prev = Long.MIN_VALUE; // Tracks the previous node's value
    private boolean res = true;

    /**
     * Validates BST using in-order traversal.
     * BST in-order traversal must yield strictly increasing values.
     */
    public boolean isValidBSTInOrder(TreeNode root) {
        prev = Long.MIN_VALUE; // Reset before use
        res = true;
        inOrder(root);
        return res;
    }

    /**
     * Recursive in-order traversal.
     * If current node's value is not greater than previous, it's invalid.
     */
    private void inOrder(TreeNode node) {
        if (node == null || !res) return;

        inOrder(node.left);

        if (node.val <= prev) {
            res = false;
            return;
        }

        prev = node.val;
        inOrder(node.right);
    }

    // ---------- Main Method to Test Both Approaches ----------
    public static void main(String[] args) {
        ValidateBST solution = new ValidateBST();

        // Creating a valid BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        System.out.println("Using min/max approach:");
        System.out.println("Is valid BST? " + solution.isValidBST(root)); // Expected: true

        System.out.println("Using in-order traversal:");
        System.out.println("Is valid BST? " + solution.isValidBSTInOrder(root)); // Expected: true

        // Creating an invalid BST
        TreeNode invalidRoot = new TreeNode(10);
        invalidRoot.left = new TreeNode(5);
        invalidRoot.right = new TreeNode(8); // Invalid: 8 < 10 but on the right

        System.out.println("Using min/max approach:");
        System.out.println("Is valid BST? " + solution.isValidBST(invalidRoot)); // Expected: false

        System.out.println("Using in-order traversal:");
        System.out.println("Is valid BST? " + solution.isValidBSTInOrder(invalidRoot)); // Expected: false
    }
}
