package binaryTree;
/*Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]


Constraints:

The number of nodes in the tree is in the range [0, 2000].
        -100 <= Node.val <= 100


Follow up: Can you flatten the tree in-place (with O(1) extra space)?*/

/*
 Approach 1
Find the left tail of the node, right tail of the node.
Attach the left tail of the node to the right node.
Make the left parent of the left tail as the right node.
In this approach the left tree is fully skewed. Then the right
tree would be skewed

Note we need to always return the rightTail because we would have made the tree right skewed.
When you flatten a binary tree, you're transforming each subtree into a right-skewed linked list
 (like a singly linked list using .right pointers). So, for any subtree rooted at a node root,
 you need to return the tail (i.e., the last node in the flattened subtree). That tail is necessary so that:


 Approach 2 better space complexity


Find the rightmost node of the left subtree
Attach the rightmost node to nodes right tree
Make the parent of this rightmost node as the right subtree
Move to the next right node.

 */
public class FlattenBinaryTreeIntoLinkedList {
    public void flattenRecursively(TreeNode root) {
        flattenBinaryTreeRecursively(root);
    }

    private TreeNode flattenBinaryTreeRecursively(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root;
        //Find the last node in the left subtree
        TreeNode leftTail = flattenBinaryTreeRecursively(root.left);

        //Find the last node in right subTree
        TreeNode rightTail = flattenBinaryTreeRecursively(root.right);

        //attach the left tail to right node. Make left node null
        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        //return the rightmost tail
        if (rightTail == null)
            return leftTail;
        return rightTail;

    }

    public void printFlattenedList(TreeNode root) {
        System.out.println("Flattened tree as linked list:");
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.right;
        }
        System.out.println();
    }

    private TreeNode flattenBinaryTreeIteratively(TreeNode root) {
        if (root == null)
            return null;
        TreeNode node = root;
        while (node != null) {
            TreeNode leftNode = node.left;
            if (leftNode == null) {
                node = node.right;
                continue;
            }
            TreeNode rightNode = leftNode;

            //Find rightMost node of leftsubtree
            while (rightNode.right != null)
                rightNode = rightNode.right;

            //Attach rightmost node to right node. make its parent the right child of node
            rightNode.right = node.right;
            node.right = leftNode;
            node.left = null;
            node = node.right;
        }
        return root;
    }

    public static void main(String[] args) {
        // Build tree from input [1, 2, 5, 3, 4, null, 6]
        BinaryTree tree = new BinaryTree();
        TreeNode root = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});
        BinaryTree tree2 = new BinaryTree();
        TreeNode root2 = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});

        // Flatten and print
        FlattenBinaryTreeIntoLinkedList flattener = new FlattenBinaryTreeIntoLinkedList();
        flattener.flattenRecursively(root);
        flattener.flattenBinaryTreeIteratively(root2);
        flattener.printFlattenedList(root);
        flattener.printFlattenedList(root2);
    }

}
