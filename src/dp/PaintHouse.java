package dp;

/*

There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
The cost of painting each house with a certain color is different. You have to paint all the houses
such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of
painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.



Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:

Input: costs = [[7,6,2]]
Output: 2


Constraints:

costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20

 */

import java.util.Arrays;

public class PaintHouse {

    public int minCost(int[][] costs) {
        if (costs.length == 0)
            return 0;
        int dp[][] = new int[costs.length][costs[0].length + 1];

        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);

        // return minCostRecursion(costs, dp, costs.length - 1, costs[0].length);
        // return minCostTabulation(costs);
        return minCosOptimized(costs);
    }

    private int minCostTabulation(int[][] costs) {
        int dp[][] = new int[costs.length][costs[0].length];

        for (int col = 0; col < costs[0].length; col++)
            dp[0][col] = costs[0][col];


        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];

        }

        return Math.min(dp[costs.length - 1][0],
                Math.min(dp[costs.length - 1][1], dp[costs.length - 1][2]));
    }

    private int minCosOptimized(int[][] costs) {
        int r = costs[0][0];
        int b = costs[0][1];
        int g = costs[0][2];

        for (int i = 1; i < costs.length; i++) {
            int nr = Math.min(b, g) + costs[i][0];
            int nb = Math.min(r, g) + costs[i][1];
            int ng = Math.min(r, b) + costs[i][2];

            r = nr;
            b = nb;
            g = ng;
        }

        return Math.min(r, Math.min(b, g));
    }

    private int minCostRecursion(int[][] costs, int[][] dp, int row, int col) {
        if (dp[row][col] != -1)
            return dp[row][col];

        // base case
        if (row == 0) {
            int minZeroRow = Integer.MAX_VALUE;
            for (int currCol = 0; currCol < costs[0].length; currCol++) {
                if (col == currCol)
                    continue;
                minZeroRow = Math.min(minZeroRow, costs[0][currCol]);
            }
            return dp[0][col] = minZeroRow;
        }


        int res = Integer.MAX_VALUE;
        for (int j = 0; j < costs[0].length; j++) {
            if (j == col)
                continue;
            //consider other two options. 3 options in the first iteration
            res = Math.min(res, minCostRecursion(costs, dp, row - 1, j) + costs[row][j]);
        }

        return dp[row][col] = res;
    }

    public static void main(String args[]) {
        PaintHouse obj = new PaintHouse();
        System.out.println(obj.minCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
        System.out.println(obj.minCost(new int[][]{{7, 2, 6}}));
    }

}
