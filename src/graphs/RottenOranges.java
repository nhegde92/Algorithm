package graphs;

import java.util.LinkedList;
import java.util.Queue;

/*You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

 */

/*
when the question is usually get the "MIN_NUMBER" use BFS
 */
public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0)
            return -1;
        int min = 0;
        int totalOnes = 0;
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1)
                    totalOnes++; //count it now so no need to loop again.
                if (grid[i][j] == 2)
                    queue.add(new int[]{i, j});
            }
        }
        // If there are no rotten oranges or no fresh orange result is always 0.
        if (queue.isEmpty() || totalOnes == 0)
            return min;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;
            for (int i = 0; i < size; i++) {
                int[] currOrange = queue.poll();
                int currRow = currOrange[0];
                int currCol = currOrange[1];

                for (int j = 0; j < dirs.length; j++) {
                    int newRow = currRow + dirs[j][0];
                    int newCol = currCol + dirs[j][1];
                    if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length || grid[newRow][newCol] != 1)
                        continue;
                    grid[newRow][newCol] = 2; // change the state or use vis
                    totalOnes--; //reuce it here as opposed to at the top
                    queue.add(new int[]{newRow, newCol});
                    rottedThisMinute = true; // this will if not always add extra 1
                }
            }
            if (rottedThisMinute)
                min++;
        }

        if (totalOnes != 0)
            return -1;

        return min;

    }

    public static void main(String args[]) {
        RottenOranges obj = new RottenOranges();
        System.out.println(obj.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(obj.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        System.out.println(obj.orangesRotting(new int[][]{{0, 2}}));

    }


}
