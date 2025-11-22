package backtracking;

import java.util.*;


/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
 */
class CombinationSum {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target, boolean use1) {
        res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if (use1)
            findCombSum1(candidates, target, 0, temp);  // include/exclude style
        else
            findCombSum2(candidates, target, 0, temp);  // loop-based style
        return res;
    }

    // Method 1: Include/Exclude style recursion
    public void findCombSum1(int[] candidates, int target, int index, List<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (index >= candidates.length || target < 0)
            return;

        // Include the current element
        temp.add(candidates[index]);
        findCombSum1(candidates, target - candidates[index], index, temp);  // Can reuse the same element
        temp.remove(temp.size() - 1);

        // Exclude the current element
        findCombSum1(candidates, target, index + 1, temp);
    }

    // Method 2: Loop-based recursion (better for pruning)
    public void findCombSum2(int[] candidates, int target, int index, List<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (target < 0)
            return;

        for (int i = index; i < candidates.length; ++i) {
            if (candidates[i] > target)
                continue;

            temp.add(candidates[i]);
            findCombSum2(candidates, target - candidates[i], i, temp);  // Can reuse the same element
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        System.out.println("Using Method 1 (include/exclude):");
        List<List<Integer>> result1 = cs.combinationSum(candidates, target, true);
        for (List<Integer> comb : result1) {
            System.out.println(comb);
        }

        System.out.println("\nUsing Method 2 (loop-based):");
        List<List<Integer>> result2 = cs.combinationSum(candidates, target, false);
        for (List<Integer> comb : result2) {
            System.out.println(comb);
        }
    }
}
