package graphs;

import java.util.*;


/*

There are n cities connected by some number of flights. You are given an array flights where
flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi
 with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from
src to dst with at most k stops. If there is no such route, return -1.

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.





 */

class CheappestFlightWithKStops {

    class Node {
        int city;
        int cost;

        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst)
            return 0;
        HashMap<Integer, List<Node>> adjList = new HashMap<>();

        int cost[] = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;

        //Create adj list
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            adjList.computeIfAbsent(from, key -> new ArrayList<Node>()).add(new Node(to, price));
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(src, 0));
        int totalStops = 0;
        while (!queue.isEmpty() && totalStops <= k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (!adjList.containsKey(curr.city))
                    continue;
                List<Node> neighbors = adjList.get(curr.city);
                for (Node neighbor : neighbors) {
                    if (curr.cost + neighbor.cost < cost[neighbor.city]) {
                        cost[neighbor.city] = curr.cost + neighbor.cost;
                        queue.add(new Node(neighbor.city, cost[neighbor.city]));
                    }
                }


            }
            totalStops++;
        }

        if (cost[dst] == Integer.MAX_VALUE)
            return -1;
        return cost[dst];
    }


    public static void main(String[] args) {
        CheappestFlightWithKStops solver = new CheappestFlightWithKStops();

        // Example 1
        int n1 = 4;
        int[][] flights1 = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };
        System.out.println(
                solver.findCheapestPrice(n1, flights1, 0, 3, 1)
        ); // Expected: 700

        // Example 2
        int n2 = 3;
        int[][] flights2 = {
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };
        System.out.println(
                solver.findCheapestPrice(n2, flights2, 0, 2, 1)
        ); // Expected: 200

        // Example 3
        int n3 = 3;
        int[][] flights3 = {
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };
        System.out.println(
                solver.findCheapestPrice(n3, flights3, 0, 2, 0)
        ); // Expected: 500
    }
}


