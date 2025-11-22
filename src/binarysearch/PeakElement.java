package binarysearch;
/*
A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 */

/*
Check the mid-element, if it is peak return. If the element to the left is higher peak will be to the left, else
peak will be to the right;
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1])
            return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2])
            return nums.length - 1;

        int low = 1;
        int high = nums.length - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1])
                return mid;
            if (nums[mid] < nums[mid + 1])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return low;
    }

    public static void main(String[] args) {
        PeakElement solution = new PeakElement();

        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Peak at index: " + solution.findPeakElement(nums1)); // Output: 2

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Peak at index: " + solution.findPeakElement(nums2)); // Output: 1 or 5

        int[] nums3 = {1};
        System.out.println("Peak at index: " + solution.findPeakElement(nums3)); // Output: 0

        int[] nums4 = {1, 2};
        System.out.println("Peak at index: " + solution.findPeakElement(nums4)); // Output: 1

        int[] nums5 = {3, 2, 1};
        System.out.println("Peak at index: " + solution.findPeakElement(nums5)); // Output: 0
    }
}
