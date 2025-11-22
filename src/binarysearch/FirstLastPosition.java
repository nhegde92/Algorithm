package binarysearch;

/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
 */

import java.util.Arrays;

/*
Logic: Since we need the first and last position of the same element, we need to rin binary search twice. One for
first element another for last element. Be careful about the if's condition.
 */
public class FirstLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int res[] = new int[2];
        Arrays.fill(res, -1);
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);
        return res;
    }

    public int binarySearch(int[] nums, int target, boolean firstElement) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // mid element less than target move right
            if (nums[mid] < target)
                low = mid + 1;
                // mid greater than target. Move to the left
            else if (nums[mid] > target)
                high = mid - 1;
            else {
                // first element consioder the left most target
                if (firstElement) {
                    //base case for first element
                    if (mid == 0 || nums[mid - 1] != target)
                        return mid;
                    else
                        //Continue in while loop.
                        high = mid - 1;
                } else { // For right most target

                    //base case
                    if (mid == nums.length - 1 || nums[mid + 1] != target)
                        return mid;
                    else //continue in the while loop
                        low = mid + 1;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        FirstLastPosition solution = new FirstLastPosition();

        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Output: " + Arrays.toString(solution.searchRange(nums1, target1))); // [3, 4]

        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Output: " + Arrays.toString(solution.searchRange(nums2, target2))); // [-1, -1]

        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Input: " + Arrays.toString(nums3) + ", Target: " + target3);
        System.out.println("Output: " + Arrays.toString(solution.searchRange(nums3, target3))); // [-1, -1]

        int[] nums4 = {1, 2, 2, 2, 3, 4, 5};
        int target4 = 2;
        System.out.println("Input: " + Arrays.toString(nums4) + ", Target: " + target4);
        System.out.println("Output: " + Arrays.toString(solution.searchRange(nums4, target4))); // [1, 3]
    }
}

