package arrays;

public class ReadMeGenericProblems {


    /*  ANAGRAM

        Check for length. If not the same then it is not anagram.
        Use an Array of size 26. Increase the size of the element in one string and decrease in another
        At the end it should be 0.

        char-'a' would give index;

        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int c : count) {
                if (c != 0) return false;
            }

            return true;
    }

     */

    /*   2SUM. Use HashMAp to look up the complement. Its faster.

        class Solution {
            public int[] twoSum(int[] nums, int target) {
                HashMap<Integer, Integer> hm = new HashMap<>();

                for (int i = 0; i < nums.length; i++) {
                    int complement = target - nums[i];
                    if (hm.containsKey(complement)) {
                        return new int[] { hm.get(complement), i };
                    }
                    hm.put(nums[i], i);
                }

                // Should never be reached if there's always one solution
                return new int[] { -1, -1 };
            }
        }

     */


}
