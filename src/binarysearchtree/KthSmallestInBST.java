package binarysearchtree;

import java.util.Stack;

/*
Here array is used as opposed to individual variable. If we need to use individual variable it needs to be
global variable.
 */
public class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        int[] tmp = new int[2];
        tmp[0] = k;
        dfs(root, tmp);
        return tmp[1];
    }

    private void dfs(TreeNode node, int[] tmp) {
        if (node == null) {
            return;
        }

        dfs(node.left, tmp);
        tmp[0] -= 1;
        if (tmp[0] == 0) {
            tmp[1] = node.val;
            return;
        }
        dfs(node.right, tmp);
    }

    public int kthSmallestStack(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            curr = curr.right;
        }

        return -1;
    }



    public static void main(String[] args) {
        /*
               Build BST:
                       5
                      / \
                     3   7
                    / \   \
                   2   4   8
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(8);

        KthSmallestInBST solver = new KthSmallestInBST();

        int k = 3;
        int result = solver.kthSmallest(root, k);
        int result2 = solver.kthSmallestStack(root, k);


        System.out.println(k + "th smallest element in BST = " + result);
        System.out.println(k + "th smallest element in BST - Stack = " + result2);
    }
}

