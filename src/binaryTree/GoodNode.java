package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
Within a binary tree, a node x is considered good if the path from the root of the tree to the node x contains
no nodes with a value greater than the value of node x

Given the root of a binary tree root, return the number of good nodes within the tree.


 */
public class GoodNode {

    public int getTotalGoodNodesRecursion(TreeNode root, int maxSoFar) {
        if (root == null)
            return 0;
        int totalNodes = 0;
        if (root.value >= maxSoFar) {
            totalNodes++;
            maxSoFar = root.value;
        }
        totalNodes += getTotalGoodNodesRecursion(root.left, maxSoFar);
        totalNodes += getTotalGoodNodesRecursion(root.right, maxSoFar);
        return totalNodes;
    }

    class Tuple {
        TreeNode root;
        int value;

        Tuple(TreeNode root, int value) {
            this.root = root;
            this.value = value;
        }
    }

    public int getTotalGoodNodesIteration(TreeNode root) {
        if (root == null) return 0;

        int count = 0;

        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, root.value));  // max on the path starts at root.value

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            TreeNode node = curr.root;
            int maxSoFar = curr.value;

            // Check if this node is "good"
            if (node.value >= maxSoFar) {
                count++;
                maxSoFar = node.value;  // update max for children
            }

            // Push children with updated max
            if (node.left != null) {
                queue.offer(new Tuple(node.left, maxSoFar));
            }
            if (node.right != null) {
                queue.offer(new Tuple(node.right, maxSoFar));
            }
        }

        return count;
    }


    public static void main(String args[]) {
        GoodNode obj = new GoodNode();
        BinaryTree tree = new BinaryTree();
        TreeNode root = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});
        System.out.println(obj.getTotalGoodNodesRecursion(root, Integer.MIN_VALUE));
        System.out.println(obj.getTotalGoodNodesIteration(root));
    }

}
