package backtracking;

/*
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.



Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

import java.util.*;

/*
    if (i > index && nums[i] == nums[i - 1]) continue;
    this wont work because we change the array, during swap.
    We will not be able to garuntee the order in ascending.
    hence using used.
    At each level it will maintain the used list. Example for 112
    at index = 0, it would take 1,1,2 (the second one would be for index 1 and i = 1)
    it would not repeat because at index = 0 and i =1 the used will have 1.
*/
public class Permutation2 {
    List<List<Integer>> results;

    public List<List<Integer>> permuteUnique(int[] nums, boolean useSecond) {
        results  = new ArrayList<>();
        if (useSecond) {
            // count the occurrence of each number
            HashMap<Integer, Integer> counter = new HashMap<>();
            for (int num : nums) {
                if (!counter.containsKey(num))
                    counter.put(num, 0);
                counter.put(num, counter.get(num) + 1);
            }

            LinkedList<Integer> comb = new LinkedList<>();
            backtrack2(comb, nums.length, counter);
        } else
            backtrack(nums, 0);
        return results;
    }

    private void backtrack(int[] nums, int index) {
        if (index == nums.length - 1) {
            // Found a valid permutation
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            results.add(permutation);
            return;
        }


        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i]))
                continue;

            used.add(nums[i]);
            swap(nums, index, i);
            backtrack(nums, index + 1);
            swap(nums, index, i); // backtrack
        }
    }


    protected void backtrack2(LinkedList<Integer> comb, Integer N, HashMap<Integer, Integer> counter)
     {
        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);

            // continue the exploration
            backtrack2(comb, N, counter);

            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) return; // Avoid unnecessary swaps
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String args[]) {
        Permutation2 obj = new Permutation2();
        System.out.println(obj.permuteUnique(new int[]{1, 1, 3}, false));
        System.out.println(obj.permuteUnique(new int[]{1, 1, 3}, true));
        System.out.println(obj.permuteUnique(new int[]{0, 0, 3, 2}, false));
        System.out.println(obj.permuteUnique(new int[]{0, 0, 3, 2}, true));
    }
}
