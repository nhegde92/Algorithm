package dp;

/*
You are given an integer array nums where nums[i] represents the amount of money the ith house has.
The houses are arranged in a straight line, i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.

You are planning to rob money from the houses, but you cannot rob two adjacent houses because the
security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [1,1,3,3]

Output: 4
Explanation: nums[0] + nums[2] = 1 + 3 = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 16
Explanation: nums[0] + nums[2] + nums[4] = 2 + 8 + 6 = 16.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

 */

import java.util.Arrays;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        //return HouseRobberRecursion(nums, nums.length-1, dp);
        // return HouseRobberIteration(nums);
        return HouseRobberOptimized(nums);
    }

    private int HouseRobberRecursion(int[] nums, int index, int[] dp) {
        if (index < 0) return 0;
        if (dp[index] != -1) return dp[index];
        int consider = HouseRobberRecursion(nums, index - 2, dp) + nums[index];
        int notConsider = HouseRobberRecursion(nums, index - 1, dp);
        return dp[index] = Math.max(consider, notConsider);

    }

    private int HouseRobberIteration(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); // Important.
        for (int i = 2; i < nums.length; i++) {
            int consider = nums[i] + dp[i - 2];
            int notConsider = dp[i - 1];
            dp[i] = Math.max(consider, notConsider);
        }

        return dp[nums.length - 1];

    }

    private int HouseRobberOptimized(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int firstHouse = nums[0];
        int secondHouse = Math.max(nums[0], nums[1]);
        int currHouse = -1;
        for (int i = 2; i < nums.length; i++) {
            int consider = nums[i] + firstHouse;
            int notConsider = secondHouse;
            currHouse = Math.max(consider, notConsider);
            firstHouse = secondHouse;
            secondHouse = currHouse;
        }

        return currHouse;

    }

    public static void main(String args[]) {
        HouseRobber obj = new HouseRobber();
        System.out.println(obj.rob(new int[]{1, 1, 3, 3}));
        System.out.println(obj.rob(new int[]{2, 9, 8, 3, 6}));
    }
}
