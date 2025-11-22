package binarysearch;

/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 */

/*
We want to split the array, always one half is sorted if there are no duplicates. We find the sorted half and figure
out where the element could be.
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            //Element found.
            if (nums[mid] == target)
                return mid;

            //check if left side is sorted. Important use <= mid.
            if (nums[low] <= nums[mid]) {
                //element to the left side
                if (nums[low] <= target && target < nums[mid])
                    high = mid - 1;
                else // element to the right half
                    low = mid + 1;
            }
            //Right half is sorted.
            else {
                //Element to the right
                if (nums[mid] < target && target <= nums[high])
                    low = mid + 1;
                else // Element to the left half
                    high = mid - 1;

            }

        }
        return -1;
    }

    public static void main(String args[]) {
        SearchInRotatedSortedArray obj = new SearchInRotatedSortedArray();
        System.out.println(obj.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(obj.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 90));
    }
}

