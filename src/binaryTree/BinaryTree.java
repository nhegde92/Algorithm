package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    TreeNode head;

    public BinaryTree() {
        System.out.println("Binary tree object constructed");
        head = null;
    }

    public BinaryTree(int value) {
        System.out.println("Binary tree object constructed");
        this.head = new TreeNode(value);
    }


    public TreeNode insert(int[] arr) {
        //Nothing to insert;
        if (arr == null || arr.length == 0) {
            System.out.println("Nothing to insert.");
            return null;
        }

        //Initialize head;
        head = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (index < arr.length) {
                curr.left = new TreeNode(arr[index]);
                queue.add(curr.left);
                index++;
            }
            if (index < arr.length) {
                curr.right = new TreeNode(arr[index]);
                queue.add(curr.right);
                index++;
            }
            if (index == arr.length) {
                break;
            }
        }
        System.out.println("Binary tree object inserted");
        this.printLevelOrder();
        return this.head;
    }

    public void printPreOrder() {
        System.out.println("Pre Order traversal is: ");
        preOrder(head);
        System.out.println();
    }

    public void printInOrder() {
        System.out.println("In Order traversal is: ");
        inorder(this.head);
        System.out.println();
    }

    public void printPostOrder() {
        System.out.println("Post Order traversal is: ");
        postOrder(this.head);
        System.out.println();
    }

    public void printLevelOrder() {
        System.out.println("Level Order traversal is: ");
        levelOrder(this.head);
        System.out.println();
    }

    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(" " + root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(" " + root.value + " ");
        inorder(root.right);
    }

    private void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(" " + root.value + " ");
    }

    protected void levelOrder(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.value + " ");
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    public int findMaxDepth(TreeNode root){
        if(root == null)
            return 0;
        return 1+Math.max(findMaxDepth(root.left), findMaxDepth(root.right));
    }

    /*
    Given two treees p & q check if thy are the same tree or not
     */
    public boolean checkSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null)
            return true;
        if(p == null || q == null )
            return false;
        if(p.value != q.value)
            return false;
        return checkSameTree(p.left, q.left) &&  checkSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode root){
        if(root == null)
            return true;
        return checkIsSymmetrical(root.left, root.right);
    }

    private boolean checkIsSymmetrical(TreeNode leftNode, TreeNode rightNode) {
        if(leftNode == null && rightNode == null)
            return true;
        if(leftNode == null || rightNode == null)
            return false;
        if(leftNode.value != rightNode.value)
                return false;
        return checkIsSymmetrical(leftNode.left, rightNode.right) &&  checkIsSymmetrical(leftNode.right, rightNode.left);
    }



}

