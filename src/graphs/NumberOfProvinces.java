package graphs;

/*

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

 */

/*
A normal DFS loop. Note that this is a DAG, so no need to handle processing, visited and not visited state.
Just vis and not vis should suffice. A Graph can be represented as a adj list or a 2D array. This is an example
of 2D array.
 */
public class NumberOfProvinces {

    public int findNumberOfProvinces(int[][] connection) {
        if (connection.length == 0)
            return 0;
        int vis[] = new int[connection.length];
        int count = 0;
        for (int i = 0; i < connection.length; i++) {
            if (vis[i] == 0) {
                dfs(i, connection, vis);
                count++;
            }

        }

        return count;
    }

    public void dfs(int index, int[][] connection, int[] vis) {
        if (vis[index] == 1)
            return;
        vis[index] = 1;
        int[] node = connection[index];
        for (int i = 0; i < node.length; i++) {
            //skip if there is no connection || visited || its the same index
            if (node[i] == 0 || vis[i] == 1 || i == index)
                continue;
            dfs(i, connection, vis);
        }
    }

    public static void main(String args[]) {
        NumberOfProvinces np = new NumberOfProvinces();
        System.out.println(np.findNumberOfProvinces(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(np.findNumberOfProvinces(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));

    }

}
