package binaryTree;

import java.util.*;

/*
Implement an algorithm to serialize and deserialize a binary tree.

Serialization is the process of converting an in-memory structure into a sequence of bits so that it can be
stored or sent across a network to be reconstructed later in another computer environment.

You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to
the original tree structure. There is no additional restriction on how your serialization/deserialization
 algorithm should work.

Note: The input/output format in the examples is the same as how NeetCode serializes a binary tree. You do not
necessarily need to follow this format.
 */

/**
 * Definition for a binary tree node.
 */
class TreeNodeLocal {
    int val;
    TreeNodeLocal left;
    TreeNodeLocal right;
    TreeNodeLocal(int x) { val = x; }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNodeLocal root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNodeLocal> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNodeLocal curr = queue.poll();
            if (curr == null) {
                sb.append("null#");
                continue;
            }
            sb.append(curr.val).append("#");
            queue.offer(curr.left);
            queue.offer(curr.right);
        }

        // Remove trailing "null"s for compactness
        String[] parts = sb.toString().split("#");
        int end = parts.length - 1;
        while (end >= 0 && parts[end].equals("null")) {
            end--;
        }

        StringBuilder trimmed = new StringBuilder();
        for (int i = 0; i <= end; i++) {
            trimmed.append(parts[i]).append("#");
        }

        return trimmed.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNodeLocal deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] arr = data.split("#");
        TreeNodeLocal root = new TreeNodeLocal(Integer.parseInt(arr[0]));
        Queue<TreeNodeLocal> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < arr.length) {
            TreeNodeLocal curr = queue.poll();

            if (index < arr.length && !arr[index].equals("null")) {
                curr.left = new TreeNodeLocal(Integer.parseInt(arr[index]));
                queue.offer(curr.left);
            }
            index++;

            if (index < arr.length && !arr[index].equals("null")) {
                curr.right = new TreeNodeLocal(Integer.parseInt(arr[index]));
                queue.offer(curr.right);
            }
            index++;
        }

        return root;
    }

    // Helper: Print tree in level order to verify correctness
    private static void printLevelOrder(TreeNodeLocal root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        Queue<TreeNodeLocal> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNodeLocal node = q.poll();
            if (node == null) {
                System.out.print("null ");
                continue;
            }
            System.out.print(node.val + " ");
            q.offer(node.left);
            q.offer(node.right);
        }
        System.out.println();
    }

    // 🧪 MAIN METHOD WITH EXAMPLES
    public static void main(String[] args) {
        Codec codec = new Codec();

        // Example 1: Simple tree
        TreeNodeLocal root1 = new TreeNodeLocal(1);
        root1.left = new TreeNodeLocal(2);
        root1.right = new TreeNodeLocal(3);
        root1.right.left = new TreeNodeLocal(4);
        root1.right.right = new TreeNodeLocal(5);

        System.out.println("Original Tree 1 (Level Order):");
        printLevelOrder(root1);

        String serialized1 = codec.serialize(root1);
        System.out.println("Serialized 1: " + serialized1);

        TreeNodeLocal deserialized1 = codec.deserialize(serialized1);
        System.out.println("Deserialized Tree 1 (Level Order):");
        printLevelOrder(deserialized1);
        System.out.println("=====================================");

        // Example 2: Single node
        TreeNodeLocal root2 = new TreeNodeLocal(42);
        System.out.println("Original Tree 2:");
        printLevelOrder(root2);

        String serialized2 = codec.serialize(root2);
        System.out.println("Serialized 2: " + serialized2);

        TreeNodeLocal deserialized2 = codec.deserialize(serialized2);
        System.out.println("Deserialized Tree 2:");
        printLevelOrder(deserialized2);
        System.out.println("=====================================");

        // Example 3: Empty tree
        TreeNodeLocal root3 = null;
        String serialized3 = codec.serialize(root3);
        System.out.println("Serialized 3 (Empty Tree): '" + serialized3 + "'");
        TreeNodeLocal deserialized3 = codec.deserialize(serialized3);
        System.out.println("Deserialized 3 (Should be empty):");
        printLevelOrder(deserialized3);
    }
}
