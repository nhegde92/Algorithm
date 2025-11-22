/*
Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a
reference to a node in the cloned tree.
 */

/*
Notice two ways of doing it.
1) using a global pointer and doing a preorder traversal. return the result once its found
2)  better. Check if the cloned is in left. If not it mifht be in right
 */

/*
Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a
reference to a node in the cloned tree.
 */

/*
Notice two ways of doing it.
1) using a global pointer and doing a preorder traversal. return the result once its found
2)  better. Check if the cloned is in left. If not it mifht be in right
 */
package binaryTree;

class FindClonedNode {
    TreeNode res = null;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        getClone(original, cloned, target);
        return res;
        // return getClone2(original, cloned, target);
    }

    public void getClone(TreeNode original, TreeNode cloned, TreeNode target) {
        if (res != null) return; //Early termination.
        if (original != null) {
            getClone(original.left, cloned.left, target);
            if (original.value == target.value) res = cloned;
            getClone(original.right, cloned.right, target);
        }
    }

    public TreeNode getClone2(TreeNode original, TreeNode cloned, TreeNode target) {
        if (original == null) return null;
        if (original == target) return cloned;
        TreeNode left = getClone2(original.left, cloned.left, target);
        if (left != null) return left;
        return getClone2(original.right, cloned.right, target);
    }

    public static void main(String args[]) {
        FindClonedNode fc = new FindClonedNode();
        BinaryTree tree = new BinaryTree();
        TreeNode root = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});
        BinaryTree tree2 = new BinaryTree();
        TreeNode root2 = tree.insert(new int[]{1, 2, 5, 3, 4, -1, 6});
        System.out.println(fc.getTargetCopy(root, root2, new TreeNode(2)).value);
    }
}



