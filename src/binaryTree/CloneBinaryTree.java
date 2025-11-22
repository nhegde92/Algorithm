
/*
A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.

Return a deep copy of the tree.

The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
You will be given the tree in class Node and you should return the cloned tree in class NodeCopy. NodeCopy class is just a clone of Node class with the same attributes and constructors.
 */
package binaryTree;

import java.util.*;

/**
 * Definition for Node.
 */
class Node {
    int val;
    Node left;
    Node right;
    Node random;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

// Clone of Node
class NodeCopy {
    int val;
    NodeCopy left;
    NodeCopy right;
    NodeCopy random;

    NodeCopy() {
    }

    NodeCopy(int val) {
        this.val = val;
    }

    NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

class CloneBinaryTree {

    public NodeCopy copyRandomBinaryTree(Node root) {
        if (root == null)
            return null;
        HashMap<Node, NodeCopy> map = new HashMap<>();
        //deepCopyDFS(root, map);
        deepCopyBFS(root, map);
        return map.get(root);
    }

    public NodeCopy deepCopyDFS(Node root, HashMap<Node, NodeCopy> map) {
        if (root == null)
            return null;
        if (map.containsKey(root))
            return map.get(root);
        NodeCopy rootCopy = new NodeCopy(root.val);
        map.put(root, rootCopy); // important to do this here to avoid infinite  loop
        rootCopy.left = deepCopyDFS(root.left, map);
        rootCopy.right = deepCopyDFS(root.right, map);
        rootCopy.random = deepCopyDFS(root.random, map);

        return rootCopy;
    }

    public void deepCopyBFS(Node root, HashMap<Node, NodeCopy> map) {
        if (root == null) {
            map.put(root, null);
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, new NodeCopy(root.val));
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            NodeCopy currCopy = map.get(currNode);
            //Find all the neighbors
            if (currNode.left != null) {
                if (map.containsKey(currNode.left)) {
                    currCopy.left = map.get(currNode.left); //been to the queue already
                } else { // Not been in queue, put it in queue for processing.
                    NodeCopy left = new NodeCopy(currNode.left.val);
                    map.put(currNode.left, left);
                    currCopy.left = map.get(currNode.left);
                    queue.add(currNode.left);
                }
            }
            if (currNode.right != null) {
                if (map.containsKey(currNode.right)) {
                    currCopy.right = map.get(currNode.right);
                } else {
                    NodeCopy right = new NodeCopy(currNode.right.val);
                    map.put(currNode.right, right);
                    currCopy.right = map.get(currNode.right);
                    queue.add(currNode.right);
                }
            }

            if (currNode.random != null) {
                if (map.containsKey(currNode.random)) {
                    currCopy.random = map.get(currNode.random);
                } else {
                    NodeCopy random = new NodeCopy(currNode.random.val);
                    map.put(currNode.random, random);
                    currCopy.random = map.get(currNode.random);
                    queue.add(currNode.random);
                }
            }
        }
    }

    // 👇 Added only for testing
    public static void printTree(NodeCopy root, Set<NodeCopy> visited) {
        if (root == null || visited.contains(root)) return;
        visited.add(root);
        System.out.print("Node: " + root.val);
        if (root.random != null) {
            System.out.print(", Random: " + root.random.val);
        } else {
            System.out.print(", Random: null");
        }
        System.out.println();
        printTree(root.left, visited);
        printTree(root.right, visited);
    }

    // 👇 Minimal PSVM to test your logic
    public static void main(String[] args) {
        // Create a sample binary tree:
        //       1
        //      / \
        //     2   3
        // Random pointers:
        // 1.random -> 3
        // 2.random -> 1
        // 3.random -> 2

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.left = node2;
        node1.right = node3;

        node1.random = node3;
        node2.random = node1;
        node3.random = node2;

        CloneBinaryTree cloner = new CloneBinaryTree();
        NodeCopy clonedRoot = cloner.copyRandomBinaryTree(node1);

        System.out.println("Cloned Tree:");
        printTree(clonedRoot, new HashSet<>());
    }
}
