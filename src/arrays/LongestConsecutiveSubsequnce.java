package arrays;
/*
Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.

A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [2,20,4,10,3,4,5]

Output: 4
Explanation: The longest consecutive sequence is [2, 3, 4, 5].

Example 2:

Input: nums = [0,3,2,5,4,6,1,1]

Output: 7
Constraints:

0 <= nums.length <= 1000
-10^9 <= nums[i] <= 10^9
 */

/*
lets take an example  [2,20,4,10,3,4,5]

we know 2 is the start of the sequence, ( 1 is not present )
20 is the start of the sequence because 19 is not present
and also 10 because 9 is not present.
so from every start of the sequence we try to find the maximum possible sequence by checking of the number exisits in set

 */

import java.util.HashSet;

public class LongestConsecutiveSubsequnce {
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashSet<Integer> hs = new HashSet<>();
        for(int num: nums)
            hs.add(num);

        for(int curr: hs){
            if(hs.contains(curr-1))
                continue;  // Not the begining of the sequence
            int currCount = 1;
            while( hs.contains(curr+currCount))
                currCount++;
            max = Math.max(currCount, max);
        }

        return max;
    }

    public static void main(String args[]){
        LongestConsecutiveSubsequnce lcs = new LongestConsecutiveSubsequnce();

        int[] test1 = {2, 20, 4, 10, 3, 4, 5};
        System.out.println("Test 1 Result: " + lcs.longestConsecutive(test1));  // Expected: 4

        int[] test2 = {0, 3, 2, 5, 4, 6, 1, 1};
        System.out.println("Test 2 Result: " + lcs.longestConsecutive(test2));  // Expected: 7

        int[] test3 = {};
        System.out.println("Test 3 Result: " + lcs.longestConsecutive(test3));  // Expected: 0

        int[] test4 = {100};
        System.out.println("Test 4 Result: " + lcs.longestConsecutive(test4));  // Expected: 1

        int[] test5 = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        System.out.println("Test 5 Result: " + lcs.longestConsecutive(test5));  // Expected: 7

    }
}

