
package SlidingWindow;

/*
Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. If such a substring does not exist, return an empty string "".

You may assume that the correct output is always unique.

Example 1:

Input: s = "OUZODYXAZV", t = "XYZ"

Output: "YXAZ"
Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

Example 2:

Input: s = "xyz", t = "xyz"

Output: "xyz"
 */

/*
We count the characters in t. Also, we keep track of total characters in T.
We start counting for S. Everytime we hit at-least the minimum number of needed characters we increment
the need. Once when the have==need we know the t is in s. Now we start shrinking the left pointer
till a point when that is no longer the case.

 */

import java.util.HashMap;


public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.length() > s.length())
            return "";

        HashMap<Character, Integer> tCount = new HashMap<>();
        HashMap<Character, Integer> sCount = new HashMap<>();

        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }

        int have = 0;                 // unique characters matched
        int need = tCount.size();     // total unique characters needed
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (tCount.containsKey(c)) {
                sCount.put(c, sCount.getOrDefault(c, 0) + 1);

                if (sCount.get(c).equals(tCount.get(c))) {
                    have++;
                }
            }

            while (have == need) { // we found a match
                // update result
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                if (tCount.containsKey(leftChar)) {
                    sCount.put(leftChar, sCount.get(leftChar) - 1);
                    if (sCount.get(leftChar) < tCount.get(leftChar)) {
                        have--;
                    }
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solver = new MinimumWindowSubstring();
        String s = "OUZODYXAZV";
        String t = "XYZ";

        System.out.println(solver.minWindow(s, t)); // Output: "YXAZ"
    }
}

