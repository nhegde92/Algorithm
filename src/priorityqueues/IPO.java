/*
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
LeetCode would like to work on some projects to increase its capital before the IPO.
Since it has limited resources, it can only finish at most k distinct projects before the IPO.
Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i]
and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project,
you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize
 your final capital, and return the final maximized capital.





Example 1:

Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Example 2:

Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6


The answer is guaranteed to fit in a 32-bit signed integer.
 */

package priorityqueues;

import java.util.Arrays;
import java.util.PriorityQueue;

public class IPO {
    static public class Project {
        int capital;
        int profit;

        Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        if (k == 0)
            return 0;
        PriorityQueue<Project> heap = new PriorityQueue<>((a, b) -> b.profit - a.profit);
        Project[] projects = new Project[capital.length];
        for (int i = 0; i < capital.length; i++) {
            projects[i] = new Project(capital[i], profits[i]);
        }
        Arrays.sort(projects, (a, b) -> a.capital - b.capital);

        int i = 0;
        while (k > 0) {
            while (i < projects.length) {
                if (projects[i].capital > w)
                    break;

                heap.offer(projects[i]);
                i++;
            }

            if (heap.size() == 0)
                return w;
            k--;
            w = w + heap.poll().profit;
        }
        return w;
    }

    public static void main(String[] args) {
        int k = 3;
        int w = 0;
        int[] profits = new int[]{1,2,3};
        int[] capital = new int[]{0,1,2};
        System.out.println(findMaximizedCapital(k,w,profits,capital));
    }
}

/*Time complexity
O(NLogN)
Space O(n)

 */
