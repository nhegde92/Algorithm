package graphs;

import java.util.*;

/*
Single-Source Shortest Path (Non-Negative Weights)

Given a weighted graph with non-negative edge weights, find the minimum cost (shortest distance) from a source vertex to all other vertices.

Key constraints:

Graph can be directed or undirected

No negative edge weights

Once the shortest distance to a node is finalized, it never changes
 */

public class Dijkstras {

    // Pair class to store (node, distance)
    static class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    /**
     * Dijkstra's Algorithm
     *
     * @param n     number of vertices
     * @param edges edge list: [from, to, weight]
     * @param src   source vertex
     * @return shortest distance from src to every vertex
     */
    public static int[] dijkstra(int n, int[][] edges, int src) {

        // Step 1: Build adjacency list
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph
                    .computeIfAbsent(e[0], k -> new ArrayList<>())
                    .add(new Node(e[1], e[2]));
        }

        // Step 2: Distance array
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 3: Min-heap ordered by smallest distance
        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));

        pq.add(new Node(src, 0));

        // Step 4: Main loop
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // If we already found a better path, skip
            if (curr.distance > dist[curr.vertex]) continue;

            // Visit neighbors
            if (!graph.containsKey(curr.vertex)) continue;

            for (Node neighbor : graph.get(curr.vertex)) {
                int newDist = curr.distance + neighbor.distance;

                // Relaxation step
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        return dist;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        int n = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 4},
                {1, 2, 1},
                {1, 3, 7},
                {2, 4, 3},
                {3, 4, 1}
        };

        int src = 0;

        int[] result = dijkstra(n, edges, src);

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < result.length; i++) {
            System.out.println("To node " + i + " = " + result[i]);
        }
    }
}
