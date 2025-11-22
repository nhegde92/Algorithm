/*Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
*/

package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetsWithDuplicates {
    public static void main(String[] args) {
        SubsetsWithDuplicates subsets = new SubsetsWithDuplicates();
        int []arr = {1,2,2};
        subsets.subsetsWithDup(arr);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(subsets, new LinkedList<Integer>(), 0, nums);
        System.out.println(subsets);
        return subsets;
    }

    public void backtrack(List<List<Integer>> subsets, List<Integer> currList, int start, int []arr) {
        subsets.add(new ArrayList<>(currList));
        for (int i=start; i<arr.length; i++) {
            if(i > start  && arr[i] == arr[i-1])
                continue;
            currList.add(arr[i]);
            backtrack(subsets, currList, i+1, arr);
            currList.removeLast();
        }
    }
}

/*
Logic: for every element we add it in the list, and go through the for loop. Every for loop starts from plus one of the
previous recursion stack. The condition of the for loop is up until the array length. We need to make sure we don't add
duplicate elements. Inorder to ensure this we need to sort the array. Once the array is sorted, we need to not add elements which
were previously added in the recursion stack, hence skip the elements.

here (start, data structure)
bt(0,{}) -> bt(1,[1]) -> bt(2, [1,2]) -> bt(3, [1,2,2]) (now the for loop will end because start is 3). The result would have [{}, 1, 12, 122]
now 2 is removed from bt(3,[1,2])->returns bt(2,[1]). Now for i = 3 we would process 1, 2 again. This is not needed. so we say, if arr[i] == arr[i-1] and
i != start we continue. here start would be the first instance of iteration in the recursion stack.
Time: O(2 POW N)
Space:  O(N)
[[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
 */
