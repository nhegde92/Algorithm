package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */

/*
time complexity is approx O4^n/sqrt(n);
 */
public class GenerateParentheses {

    public List<String> generateBrackets(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0)
            return res;
        backtrack(n, 0, 0, new StringBuffer(""), res);
        return res;

    }

    public void backtrack(int n, int open, int close, StringBuffer sb, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(new String(sb));
            return;
        }

        //case for open bracket
        if (open < n) {
            sb.append("(");
            backtrack(n, open + 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        //case for close brackets
        if (close < open) {
            sb.append(")");
            backtrack(n, open, close + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String args[]) {
        GenerateParentheses obj = new GenerateParentheses();
        System.out.println(obj.generateBrackets(3));
        System.out.println(obj.generateBrackets(1));
    }
}
