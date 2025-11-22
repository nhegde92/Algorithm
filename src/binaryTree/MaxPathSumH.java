package binaryTree;


/*

https://leetcode.com/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&envId=top-interview-150

Level: hard
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

 */

/*

The max sum can be following

Max of left branch
or Max of my right branch
or Max of my left and right branch + my value
or My value.

So I do a DFS and send the max of my left, right or current node to the top.
at each node we compute the max of left, right, left+right, myValue.
 */
public class MaxPathSumH {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        getMaxPathSum(root);
        return maxSum;
    }

    private int getMaxPathSum(TreeNode node) {
        if (node == null)
            return 0;
        int currNodeValue = node.value;
        int left = getMaxPathSum(node.left);
        int right = getMaxPathSum(node.right);
        // propogate the max of left or right child to the top.
        int childMax = Math.max(left, right);
        //return the max of nodes children plus currNode or currNode to top
        int totalMax = Math.max(childMax + currNodeValue, currNodeValue);
        //Final result can be max of my max child or sum of by both child
        maxSum = Math.max(maxSum, Math.max(totalMax, left + right + currNodeValue));
        return totalMax; // Dont return left + right + currNodeValue because its forked. We only return from one side.
    }

    public static void main(String[] args) {
        MaxPathSumH obj = new MaxPathSumH();
        BinaryTree tree = new BinaryTree();
        tree.insert(new int[]{1, 2, 3});
        System.out.println("Maximum sum is " + obj.maxPathSum(tree.head));
        tree = new BinaryTree();
        tree.insert(new int[]{-10, 9, 20, -1, -1, 15, 7});
        System.out.println("Maximum sum is " + obj.maxPathSum(tree.head));
    }
}
