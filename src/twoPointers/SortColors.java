package twoPointers;
/*
Write a function to sort a given integer array nums in-place (and without the built-in sort function),
where the array contains n integers that are either 0, 1, and 2 and represent the colors red, white, and blue.
Arrange the objects so that same-colored ones are adjacent, in the order of red, white, and blue (0, 1, 2).

Input:

nums = [2,1,2,0,1,0,1,0,1]
Output:

[0,0,0,1,1,1,1,2,2]
 */

import java.util.Arrays;

/*
We know the 0's would be in the beginning of the list, 1's in the middle and 2's in the end
we could use two pointers where we have left and right pointing to extreme ends
when we encounter a 0, we place it at left, when we encounter a 1 we place it at right
 */

public class SortColors {
    public int[] sortColors(int[] nums) {
        int left = 0; // holds the index where 0 should be inserted
        int right = nums.length - 1;//  holds the index where 2 should be inserted
        int index = 0;
        while (index <= right) {
            // put to left and increment the index ;
            if (nums[index] == 0) {
                int temp = nums[index];
                nums[index] = nums[left];
                nums[left++] = temp;
                index++; // this is important Whatever was at nums[left] (now at nums[index]) has already been processed or is a 0 — so it's safe to move on.

            } else if (nums[index] == 2) {
                int temp = nums[index];
                nums[index] = nums[right];
                nums[right--] = temp;
            } else
                index++;

        }
        return nums;

    }

    public static void main(String args[]) {
        SortColors sc = new SortColors();
        System.out.println(Arrays.toString(sc.sortColors(new int[]{2, 1, 2, 0, 1, 0, 1, 0, 1})));
        System.out.println(Arrays.toString(sc.sortColors(new int[]{2, 0, 2, 1, 1, 0})));


    }
}
