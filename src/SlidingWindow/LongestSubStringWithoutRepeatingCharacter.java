package SlidingWindow;

/*
Given a string s, find the length of the longest substring without duplicate characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/

/*
This program uses the sliding window technique with two pointers (sp for start and i for end) and
a HashMap to track the last seen index of each character.
As we iterate through the string, if a character repeats within the current window, we move the start pointer
(sp) to one position after the previous occurrence of that character,
ensuring the substring stays duplicate-free. At each step, we calculate the length of the current window (i - sp + 1) and update the maximum found so far.
This ensures we get the length of the longest substring without repeating characters in O(n) time and O(min(n, charset size)) space.


Easy to forget why hm.get(curr) >= sp is needed
Example: "abba"
In the string "abba", when we reach the last 'a' (index 3), its previous occurrence was at
index 0. By that time, our sliding window start pointer (sp) had already moved to index 2 after handling
the duplicate 'b'. If we didn’t check hm.get(curr) >= sp, we would incorrectly move sp back to 1,
including characters we’d already excluded, which breaks the logic. The condition hm.get(curr) >= sp
ensures we only shift sp forward when the duplicate’s last occurrence is still inside the current window,
preventing backward movement and keeping the substring calculation correct.
 */

import java.util.HashMap;

public class LongestSubStringWithoutRepeatingCharacter {

    public static void main(String[] args) {
        LongestSubStringWithoutRepeatingCharacter obj = new LongestSubStringWithoutRepeatingCharacter();

        System.out.println(obj.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(obj.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));   // 3
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len < 2)
            return len;
        int res = -1;
        char[] cArray = s.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        int sp = 0;
        for (int i = 0; i < len; i++) {
            char curr = cArray[i];
            // if you see a repeating character reset the sp to old character index+1.
            //We also should not count anything less than the current sp from previous value
            if (hm.containsKey(curr) && hm.get(curr) >= sp)
                sp = hm.get(curr) + 1;

            hm.put(curr, i);
            res = Math.max(res, i - sp + 1);

        }

        return res;
    }

}
