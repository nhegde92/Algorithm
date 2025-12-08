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
        int maxSoFar = Integer.MIN_VALUE;
        int totalCount = 0;
        if (root == null)
            return totalCount;
        Queue<Tuple> queue = new LinkedList<>();
        Tuple currTuple = new Tuple(root, maxSoFar);
        queue.add(currTuple);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Tuple curr = queue.poll();
                if(curr == null)
                    continue;
                int maxValue = curr.value;
                if (curr.root.value >= maxValue) {
                    totalCount++;
                    maxValue = curr.root.value;
                }
                if (curr.root.left != null)
                    queue.add(new Tuple(curr.root.left, maxValue));
                if (curr.root.right != null)
                    queue.add(new Tuple(curr.root.right, maxValue));
            }
        }

        return totalCount;

    }


    public static void main(String args[]) {
        GoodNode obj = new GoodNode();
        BinaryTree tree = new BinaryTree();
        TreeNode root = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});
        System.out.println(obj.getTotalGoodNodesRecursion(root, Integer.MIN_VALUE));
        System.out.println(obj.getTotalGoodNodesIteration(root));
    }

}
