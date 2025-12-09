package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*

You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.



Example 1:


Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
Example 2:


Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).


Time complexity is O(2n) = O(n)
space is O(h)
 */
class MaxZigZagPath {
    public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;
        // make it an array to make it pass by reference. Alternatively make it global variable.
        int max[] = new int[1];
        computeLongestZingZag(root, 0, true, max);
        return max[0];
    }

    private void computeLongestZingZag(TreeNode root, int stage, boolean goLeft, int max[]) {
        if (root == null)
            return;
        max[0] = Math.max(stage, max[0]);
        if (goLeft) {
            computeLongestZingZag(root.left, stage + 1, false, max);
            //Start a new computation from the current branch.
            computeLongestZingZag(root.right, 1, true, max);
        } else {
            computeLongestZingZag(root.right, stage + 1, true, max);
            //Start a new computation from the current branch
            computeLongestZingZag(root.left, 1, false, max);
        }
    }

    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();

            // left child
            if (i < arr.length && arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            // right child
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String args[]) {
        MaxZigZagPath sol = new MaxZigZagPath();

        // Example 1
        Integer[] ex1 = {
                1, null, 1, 1, 1, null, null, 1, 1, null, 1, null, null, null, 1
        };
        TreeNode root1 = buildTree(ex1);
        System.out.println("Example 1 Output: " + sol.longestZigZag(root1));  // Expected: 3

        // Example 2
        Integer[] ex2 = {
                1, 1, 1, null, 1, null, null, 1, 1, null, 1
        };
        TreeNode root2 = buildTree(ex2);
        System.out.println("Example 2 Output: " + sol.longestZigZag(root2));  // Expected: 4
    }

}
