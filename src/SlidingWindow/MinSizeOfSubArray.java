
/*
Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

 */

/*
Tine complexity O(n)
Space complexity O(1)
 */
package SlidingWindow;

public class MinSizeOfSubArray {
    public void minSubArrayLen(int target, int[] nums) {
        int sp = 0;
        int currSum = 0;
        int res = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {

            //If anything is greater than or equal to target return 1.
            if (nums[i] >= target){
                res =  1;
                System.out.println(1);
                return;
            }

            currSum += nums[i];
            /*
            Reduce current Sum until it hits < target. Do it by reducing slow pointer
             */
            while (currSum >= target) {
                res = Math.min(res, i - sp + 1); // compute res then decrement.
                currSum = currSum - nums[sp++];
            }

        }
        //Nothing adds up to the sum
        res = res==nums.length + 1 ? 0 : res;
        System.out.println(res);

    }

    public static void main(String args[]){
        MinSizeOfSubArray obj =  new MinSizeOfSubArray();
        obj.minSubArrayLen(7, new int[] {2,3,1,2,4,3});
        obj.minSubArrayLen(4, new int[] {1,4,4});
        obj.minSubArrayLen(11, new int[] {1,1,1,1,1,1,1,1});
    }

}
