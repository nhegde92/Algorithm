package backtracking;

/*
Numbers can be regarded as the product of their factors
For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations of its factors. You may return the answer in any order.
Note that the factors should be in the range [2, n - 1].

Example 1:

Input: n = 1
Output: []
Example 2:

Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]
Example 3:

Input: n = 37
Output: []

 */


import java.util.ArrayList;
import java.util.List;

public class FactorCombination {

    public List<List<Integer>> findFactorCombination (int n){
        List<List<Integer>> result = new ArrayList<>();
        if( n < 2)
            return result;
        List<Integer> temp = new ArrayList<>();;
        backtrack(temp, n, result, 2);
        return result;
    }

    public void backtrack(List<Integer> temp, int n, List<List<Integer>> result, int index) {
        // Loop through possible factors starting from 'index' up to √n
        // Using 'i <= Math.sqrt(n)' avoids duplicated combinations like [2, 6] and [6, 2]
        for (int i = index; i <= Math.sqrt(n); i++) {
            // Check if 'i' is a factor of 'n'
            if (n % i == 0) {
                // Add the current factor 'i' to the temporary list
                temp.add(i);

                // Add the corresponding factor pair [i, n/i] to the result
                temp.add(n / i);
                result.add(new ArrayList<>(temp)); // store a copy of the current valid combination

                // Remove the last element (n / i) to prepare for deeper recursion
                temp.remove(temp.size() - 1);

                // Recurse with the reduced number (n / i) to find deeper factor combinations
                // Keep the starting index the same to allow repeated factors like [2, 2, 3]
                backtrack(temp, n / i, result, i);

                // Backtrack: remove the last added factor 'i' to explore other possibilities
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new FactorCombination().findFactorCombination(12));
        System.out.println(new FactorCombination().findFactorCombination(37));
        System.out.println(new FactorCombination().findFactorCombination(128));
    }
}


/*It uses a recursive backtracking approach, starting from the smallest factor 2 up to the square root
of n, to avoid duplicate combinations in different orders. For each factor i that divides n,
the code records the pair [i, n/i] as a valid factor combination and then recursively attempts to
further break down n/i using i as the new starting point. This ensures the factors in each combination
are in non-decreasing order, and avoids redundant permutations like [2, 6] and [6, 2].
The recursive path is managed using a list that is dynamically built and backtracked as the algorithm
explores different factor groupings.*/

//class Solution {
//    // Global result list to store all valid factor combinations
//    List<List<Integer>> result = new ArrayList<>();
//
//    // Main method to initiate backtracking
//    public List<List<Integer>> getFactors(int n) {
//        List<Integer> list = new ArrayList<>(); // To hold current combination
//        backtrack(2, list, n); // Start from factor 2
//        return result;
//    }
//
//    // Backtracking function to find factor combinations
//    public void backtrack(int index, List<Integer> list, int n) {
//        // Try all factors starting from 'index' up to sqrt(n). This also helps going over duplicates.
//        for (int i = index; i <= Math.sqrt(n); i++) {
//            // Check if 'i' is a valid factor
//            if (n % i == 0) {
//                // Create a new combination using current path and the factor pair (i, n/i)
//                ArrayList<Integer> temp = new ArrayList<>(list);
//                temp.add(i);
//                temp.add(n / i);
//
//                // Add the valid combination to the result
//                result.add(temp);
//
//                // Add current factor to the list and continue exploring deeper factorization
//                list.add(i);
//                backtrack(i, list, n / i); // Recurse with reduced number and same start factor
//
//                // Backtrack: remove last added factor to try next possibility
//                list.remove(list.size() - 1);
//            }
//        }
//    }
//}
