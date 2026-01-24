package dp;

/*
You are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are arranged in a circle, i.e. the first house and the last house are neighbors.

You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [3,4,3]

Output: 4
Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses. The maximum you can rob is nums[1] = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 15
Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses. The maximum you can rob is nums[1] + nums[4] = 15.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100


Same as previous problem but max of 0-n-2 and 1 to n-1
 */


import java.util.Arrays;

public class HouseRobber2 {

    // ---------------- MAIN ENTRY ----------------
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        // Case 1: Rob from house 0 to n-2
        int case1 = robSpaceOptimized(nums, 0, nums.length - 2);

        // Case 2: Rob from house 1 to n-1
        int case2 = robSpaceOptimized(nums, 1, nums.length - 1);

        return Math.max(case1, case2);
    }

    // =====================================================
    // 1️⃣ RECURSION + MEMOIZATION (Top-down DP)
    // =====================================================
    private int robRecursion(int[] nums, int index, int start, int[] dp) {
        if (index < start) return 0;
        if (dp[index] != -1) return dp[index];

        int take = nums[index] + robRecursion(nums, index - 2, start, dp);
        int skip = robRecursion(nums, index - 1, start, dp);

        return dp[index] = Math.max(take, skip);
    }

    private int robUsingRecursion(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return robRecursion(nums, end, start, dp);
    }

    // =====================================================
    // 2️⃣ ITERATIVE DP (Bottom-up)
    // =====================================================
    private int robIteration(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[end];
    }

    // =====================================================
    // 3️⃣ SPACE OPTIMIZED DP (Best Solution)
    // =====================================================
    private int robSpaceOptimized(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        int prev2 = nums[start];                       // dp[i - 2]
        int prev1 = Math.max(nums[start], nums[start + 1]); // dp[i - 1]

        for (int i = start + 2; i <= end; i++) {
            int curr = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {
        HouseRobber2 obj = new HouseRobber2();

        System.out.println(obj.rob(new int[]{2, 3, 2}));        // 3
        System.out.println(obj.rob(new int[]{1, 2, 3, 1}));     // 4
        System.out.println(obj.rob(new int[]{2, 7, 9, 3, 1}));  // 11
    }
}
