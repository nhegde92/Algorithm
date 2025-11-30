package arrays;

/*
Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
Each product is guaranteed to fit in a 32-bit integer.

Follow-up: Could you solve it in
O(n) time without using the division operation?

Example 1:

Input: nums = [1,2,4,6]

Output: [48,24,12,8]
Example 2:

Input: nums = [-1,0,1,2,3]

Output: [0,-6,0,0,0]
 */
/*

logic create forward and backward array. The forward will contain the forward product
and backward would have the backward product

the product of array for itself say nums[i]  will be forward[i-1]*backward[i+1]
 */

public class ProductArrayButSelf {

    public int[] productExceptSelf(int[] nums) {
        int forward[] = new int[nums.length];
        int backward[] = new int[nums.length];
        int res[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                forward[i] = nums[i];
                backward[nums.length - 1] = nums[nums.length - 1];
                continue;
            }
            forward[i] = nums[i] * forward[i - 1];
            backward[nums.length - 1 - i] = nums[nums.length - 1 - i] * backward[nums.length - i];
        }

        res[0] = backward[1];
        res[nums.length - 1] = forward[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            res[i] = forward[i - 1] * backward[i + 1];
        }
        return res;
    }

    public static void main(String args[]) {
        ProductArrayButSelf obj = new ProductArrayButSelf();
        int res[] = obj.productExceptSelf(new int[]{1, 2, 4, 6});
        for (int i = 0; i < res.length; i++)
            System.out.print(res[i] + " ");
    }
}
