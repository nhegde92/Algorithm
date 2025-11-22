
/*Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with
val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.


An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes
 the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 */

package graphs;

import java.util.*;

class CloneNode {
    public int val;
    public List<CloneNode> neighbors;

    public CloneNode() {
        val = 0;
        neighbors = new ArrayList<CloneNode>();
    }

    public CloneNode(int _val) {
        val = _val;
        neighbors = new ArrayList<CloneNode>();
    }

    public CloneNode(int _val, ArrayList<CloneNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    public CloneNode cloneGraph(CloneNode node) {
        if (node == null)
            return null;
        HashMap<CloneNode, CloneNode> map = new HashMap<>();
        // Uncomment one of the two:
        // return deepCopyDFS(node, map);
        deepCopyBFS(node, map);
        return map.get(node);
    }

    public CloneNode deepCopyDFS(CloneNode node, HashMap<CloneNode, CloneNode> map) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node);
        CloneNode newNode = new CloneNode(node.val);
        map.put(node, newNode); // Mark visited important to do it here.
        for (int i = 0; i < node.neighbors.size(); i++) {
            CloneNode neighbor = node.neighbors.get(i);
            newNode.neighbors.add(deepCopyDFS(neighbor, map));
        }
        return newNode;
    }

    public void deepCopyBFS(CloneNode node, HashMap<CloneNode, CloneNode> map) {
        Queue<CloneNode> queue = new LinkedList<>();
        map.put(node, new CloneNode(node.val));
        queue.add(node);

        while (!queue.isEmpty()) {
            CloneNode currNode = queue.poll();
            CloneNode newNode = map.get(currNode);

            for (int i = 0; i < currNode.neighbors.size(); i++) {
                CloneNode neighbor = currNode.neighbors.get(i);
                if (map.containsKey(neighbor)) { // Node has been in queue alerady
                    newNode.neighbors.add(map.get(neighbor));
                } else { //Node has not been in queue. put it in map and queue.
                    CloneNode newNeighbor = new CloneNode(neighbor.val);
                    map.put(neighbor, newNeighbor);
                    queue.add(neighbor);
                    newNode.neighbors.add(newNeighbor);
                }
            }
        }
    }

    // ✅ Helper to print the graph (for verification)
    public static void printGraph(CloneNode node, Set<CloneNode> visited) {
        if (node == null || visited.contains(node)) return;

        visited.add(node);
        System.out.print("Node " + node.val + " -> ");
        for (CloneNode neighbor : node.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        for (CloneNode neighbor : node.neighbors) {
            printGraph(neighbor, visited);
        }
    }

    // ✅ Public static void main to test
    public static void main(String[] args) {
        // Create sample graph:
        // 1 -- 2
        // |    |
        // 4 -- 3

        CloneNode node1 = new CloneNode(1);
        CloneNode node2 = new CloneNode(2);
        CloneNode node3 = new CloneNode(3);
        CloneNode node4 = new CloneNode(4);

        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        CloneGraph graphCloner = new CloneGraph();
        CloneNode clonedGraph = graphCloner.cloneGraph(node1);

        System.out.println("Original graph:");
        printGraph(node1, new HashSet<>());

        System.out.println("\nCloned graph:");
        printGraph(clonedGraph, new HashSet<>());
    }
}
