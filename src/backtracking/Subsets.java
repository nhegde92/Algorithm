/*Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
*/


package backtracking;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = {1, 2, 3};
        Subsets obj = new Subsets();
        result = obj.computeSubsets(nums);
        System.out.println(result);
    }

    public List<List<Integer>> computeSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        //backtrack(nums, 0, res, new LinkedList<>());
        backtrack2(nums, 0, res, new LinkedList<>());
        return res;
    }


    public void backtrack(int[] nums, int start, List<List<Integer>> res, LinkedList<Integer> curr) {
        if (start == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[start]);
        backtrack(nums, start + 1, res, curr);
        curr.removeLast();
        backtrack(nums, start + 1, res, curr);
    }


    public void backtrack2(int[] nums, int start, List<List<Integer>> res, LinkedList<Integer> curr) {
        res.add(new ArrayList<>(curr));
        for (int i = start; i < nums.length; i++) {
            curr.addLast(nums[i]);
            backtrack2(nums, i + 1, res, curr);
            curr.removeLast();
        }
    }
}

/*
backtrack
time complexity 2PowN
Space O(N) recursion stack
[[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
*/

/*

This approach uses a for loop. It first gets an empty list, followed
time complexity 2PowN
Space O(N) recursion stack
[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
 */