package binaryTree;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 */

/*

Logic:

The value should be in left subtree or right subTree. Find out where the value is. If the value is in the
left side, return the nearest leftsubtree, and so on.
 */
public class LowestCommonAncestor {


    public TreeNode findLowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || p == root.value || q == root.value)
            return root;

        return findLAC(root, p, q);
    }

    private TreeNode findLAC(TreeNode root, int p, int q) {
        if (root == null || p == root.value || q == root.value)
            return root;
        TreeNode left = findLAC(root.left, p, q);
        TreeNode right = findLAC(root.right, p, q);

        //One value is in left and another in right subtree
        if (left != null && right != null)
            return root;

        //Both p & q are in left subtree
        if (left != null)
            return left;
        //Both p & q are in right subtree
        return right;
    }

    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.insert(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode res = lca.findLowestCommonAncestor(tree.head, 2, 3);
        System.out.println(res.value);
        tree = new BinaryTree();
        tree.insert(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        TreeNode res2 = lca.findLowestCommonAncestor(tree.head, 2, 4);
        System.out.println(res2.value);

    }
}
