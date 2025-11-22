package backtracking;

import java.util.*;

/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.


Constraints:

1 <= n <= 20
1 <= k <= n
 */

/*
The inclusion and exclusion has a time complexity of 2pown
the loop has a complexity of
n!/n-k!*k!


 */
class Combinations {
    List<List<Integer>> res;
    int size = -1;

    public List<List<Integer>> combine(int n, int k, boolean use1) {
        this.size = k;
        res = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();

        if (use1)
            getCombination(1, n, arr);   // loop-based backtracking
        else
            getCombination2(1, n, arr);  // include/exclude method

        return res;
    }

    // Method 1: Loop-based backtracking
    public void getCombination(int index, int n, ArrayList<Integer> arr) {
        if (arr.size() == size) {
            res.add(new ArrayList<>(arr));
            return;
        }

        for (int i = index; i <= n; i++) {
            arr.add(i);
            getCombination(i + 1, n, arr);
            arr.remove(arr.size() - 1);
        }
    }

    // Method 2: Include/Exclude-based backtracking
    public void getCombination2(int index, int n, ArrayList<Integer> arr) {
        if (arr.size() == size) {
            res.add(new ArrayList<>(arr));
            return;
        }

        if (arr.size() > size || index > n)
            return;

        // Include current index
        arr.add(index);
        getCombination2(index + 1, n, arr);

        // Exclude current index
        arr.remove(arr.size() - 1);
        getCombination2(index + 1, n, arr);
    }

    public static void main(String[] args) {
        Combinations comb = new Combinations();
        int n = 4;
        int k = 2;

        System.out.println("Using getCombination (loop-based):");
        List<List<Integer>> result1 = comb.combine(n, k, true);
        for (List<Integer> list : result1) {
            System.out.println(list);
        }

        System.out.println("\nUsing getCombination2 (include/exclude):");
        List<List<Integer>> result2 = comb.combine(n, k, false);
        for (List<Integer> list : result2) {
            System.out.println(list);
        }
    }
}
