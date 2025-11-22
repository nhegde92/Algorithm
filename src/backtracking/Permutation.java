package backtracking;

/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 */

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums, boolean useSlow) {
        res = new ArrayList<>();
        //empty array
        if (nums.length == 0)
            return res;
        if (useSlow)
            backtrack2(nums, new ArrayList<Integer>());
        else
            backtrack1(nums, 0);
        return res;
    }

    /*
        I need each result to be of size nums.length. So recursively keep calling
        this function. I need to skip if the digit is
        already present. This is not efficient.
        Time complexity (n*n!)
    */
    private void backtrack2(int[] nums, ArrayList<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) //not efficient.
                continue;
            temp.add(nums[i]);
            backtrack2(nums, temp);
            temp.remove(temp.size() - 1);
        }
    }
    /*
    Time complexity is O(n!) At each recursive level,
    it swaps the current index with all possible positions and explores further.
    Once a full permutation is formed, it adds a copy to the result list and backtracks
    to explore other possibilities.
     */
    private void backtrack1(int[] nums, int index) {
        //End point reached add to result
        if (index == nums.length - 1) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums)
                list.add(num);
            res.add(list);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            backtrack1(nums, index + 1);
            swap(nums, i, index);
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String args[]) {
        Permutation obj = new Permutation();
        System.out.println(obj.permute(new int[]{1, 2, 3}, false));
        System.out.println(obj.permute(new int[]{1, 2, 3}, true));
    }

}
