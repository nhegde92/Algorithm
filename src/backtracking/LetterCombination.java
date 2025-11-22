 package backtracking;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]

 */


/* Time complexity  O(4^n) each code is multiplied n times. can be 3^N for average case
For non backtrackng method Time Complexity = O(n × 4^n), additional n because of copying
to string.
*/

/*
two approaches. 1) backtracking where I find codes of each digit, cross multiply with codes of others.
approach 2) git first letter of all the codes. Add to it the second letter so on and so forth.
 */

import java.util.*;

public class LetterCombination {
    private static final String[] codes = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    List<String> findCombinationThroughBacktracking(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty())
            return result;
        backtrack(0, digits, new StringBuffer(""), result);
        return result;
    }

    private void backtrack(int index, String digits, StringBuffer sb, List<String> result) {

        //Size reached
        if (sb.length() == digits.length()) {
            result.add(new String(sb));
            return;
        }

        //Get the index of the current digit in int
        int currentDigit = digits.charAt(index) - '0';

        //No codes.
        if (currentDigit < 2)
            return;

        for (int i = 0; i < codes[currentDigit].length(); i++) {
            String currCode = codes[currentDigit];
            sb.append(currCode.charAt(i));
            backtrack(index + 1, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /* understand this logic and type it out Not as efficient though. */
    List<String> findCombinationWithoutBacktracking(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) ;
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            if (digit < 2)
                continue;
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < codes[digit].length(); j++) {
                for (int k = 0; k < result.size(); k++) {
                    temp.add(result.get(k) + codes[digit].charAt(j));
                }
            }
            result = temp;
        }
        return result;
    }


    public static void main(String args[]) {
        LetterCombination lc = new LetterCombination();
        System.out.println(lc.findCombinationThroughBacktracking("23"));
        System.out.println(lc.findCombinationThroughBacktracking("2"));
        System.out.println(lc.findCombinationWithoutBacktracking("23"));
        System.out.println(lc.findCombinationWithoutBacktracking("2"));
    }
}
