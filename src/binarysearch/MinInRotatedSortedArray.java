package binarysearch;
/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [1,3,5]
Output: 1
Example 2:

Input: nums = [2,2,2,0,1]
Output: 0
 */

/*
Note this works for duplicate and non duplicate.
Keep a variable which holds the min value. Check if mid is less then res. Check if low == mid then increment low.
This would help eliminating duplicates. Next do the same thing for high. Now check which side is sorted, pick the
minimum and go to the other side.
 */
public class MinInRotatedSortedArray {

    public int findMin(int nums[]) {
        if (nums.length == 1)
            //base case
            return nums[0];
        int low = 0;
        int high = nums.length - 1;
        int res = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[low] < nums[high]) {
                res = Math.min(res, nums[low]);
                break;
            }

            res = Math.min(res, nums[mid]);
            //left and mid are same increment low.
            while (low < mid && nums[low] == nums[mid]) low++;

            // right and mid are same decrement high
            while (high > mid && nums[high] == nums[mid]) high--;

            // left side is sorted, captured the min now go right
            if (nums[low] <= nums[mid]) {
                res = Math.min(nums[low], res);
                low = mid + 1;
            }

            // left side is sorted, captured the min now go right
            else {
                res = Math.min(nums[mid], res);
                high = mid - 1;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        MinInRotatedSortedArray obj = new MinInRotatedSortedArray();
        System.out.println(obj.findMin(new int[]{2, 3, 4, 5, 6, 7, 8, 1}));
        System.out.println(obj.findMin(new int[]{2, 3, 4, 5, 6, 7, 8,}));
    }
}
