package dp;

/*
You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase.
After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.

You may choose to start at the index 0 or the index 1 floor.

Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.

Example 1:

Input: cost = [1,2,3]

Output: 2
Explanation: We can start at index = 1 and pay the cost of cost[1] = 2 and take two steps to reach the top. The total cost is 2.

Example 2:

Input: cost = [1,2,1,2,1,1,1]

Output: 4
Explanation: Start at index = 0.

Pay the cost of cost[0] = 1 and take two steps to reach index = 2.
Pay the cost of cost[2] = 1 and take two steps to reach index = 4.
Pay the cost of cost[4] = 1 and take two steps to reach index = 6.
Pay the cost of cost[6] = 1 and take one step to reach the top.
The total cost is 4.

 */

import java.util.Arrays;

public class MinCostStaircase {

    public int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length + 1];
        Arrays.fill(dp, -1);
        // return minCostClimbingStairsRecursion(cost, cost.length, dp);
        //  return minCostClimbingStairsIteration(cost);
        return minCostClimbingStairsSpaceOptimized(cost);
    }

    private int minCostClimbingStairsIteration(int[] cost) {
        if (cost.length < 2)
            return 0;
        int dp[] = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            int oneStep = dp[i - 1] + cost[i - 1];
            int twoStep = dp[i - 2] + cost[i - 2];
            dp[i] = Math.min(oneStep, twoStep);
        }
        return dp[cost.length];
    }

    private int minCostClimbingStairsSpaceOptimized(int[] cost) {
        if (cost.length < 2)
            return 0;
        int minusOneCost = 0;
        int minusTwoCost = 0;
        int currStepCost = 0;
        for (int i = 2; i <= cost.length; i++) {
            int oneStep = minusOneCost + cost[i - 1];
            int twoStep = minusTwoCost + cost[i - 2];
            currStepCost = Math.min(oneStep, twoStep);
            minusTwoCost = minusOneCost;
            minusOneCost = currStepCost;
        }
        return currStepCost;
    }


    private int minCostClimbingStairsRecursion(int[] cost, int index, int dp[]) {
        // Cost to reach step 1 or step 2 i.e. index 0 or 1 is 0.
        if (index == 0 || index == 1)
            return 0;
        if (dp[index] != -1)
            return dp[index];
        int minusOne = cost[index - 1] + minCostClimbingStairsRecursion(cost, index - 1, dp);
        int minusTwo = cost[index - 2] + minCostClimbingStairsRecursion(cost, index - 2, dp);
        dp[index] = Math.min(minusOne, minusTwo);
        return dp[index];

    }

    public static void main(String args[]) {
        MinCostStaircase obj = new MinCostStaircase();
        System.out.println(obj.minCostClimbingStairs(new int[]{1, 2, 1, 2, 1, 1, 1}));
        System.out.println(obj.minCostClimbingStairs(new int[]{1, 2, 3}));

    }

}
