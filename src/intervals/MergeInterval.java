package intervals;

/*
Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

You may return the answer in any order.

Note: Intervals are non-overlapping if they have no common point. For example, [1, 2] and [3, 4] are non-overlapping, but [1, 2] and [2, 3] are overlapping.

Example 1:

Input: intervals = [[1,3],[1,5],[6,7]]

Output: [[1,5],[6,7]]
Example 2:

Input: intervals = [[1,2],[2,3]]

Output: [[1,3]]

Logic:
sort all the interval in ascending order
Put the first interval as prevInterval
if the currentInterval from i = 1 is not overlapping add the prev Interval to result and make the current prevInterval
if not make the end of prevInterval the max of prev Interval and currInterval

Overlapping condition is checked when the start of current interval <= end of Prev interval

This code has similar version

 */

/*
Syntax
  Arrays.sort(intervals, (a, b) ->(Integer.compare(a[0],b[0])));
  res.toArray(new int[res.size()][2]);
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;

        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> (Integer.compare(a[0], b[0])));

        // add the first element to output
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int currInterval[] = intervals[i];
            int prev[] = res.get(res.size() - 1);
            int start = currInterval[0];
            int end = currInterval[1];
            int lastEnd = prev[1];

            //if currStart < preEnd update the output directly
            if (start <= lastEnd) {
                prev[1] = Math.max(end, lastEnd); //Note this works because of pass by Ref
            } else
                res.add(currInterval);
        }

        return res.toArray(new int[res.size()][2]);
    }

    public static void main(String[] args) {
        MergeInterval solver = new MergeInterval();

        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = solver.merge(intervals1);
        printIntervals(result1);

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = solver.merge(intervals2);
        printIntervals(result2);

        int[][] intervals3 = {{1, 2}, {3, 5}, {4, 6}, {7, 8}};
        int[][] result3 = solver.merge(intervals3);
        printIntervals(result3);
    }

    private static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(Arrays.toString(intervals[i]));
            if (i < intervals.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}


/*
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();

        // 2. Initialize previous interval
        int[] prev = intervals[0];

        // 3. Process remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            // Overlap condition
            if (curr[0] <= prev[1]) {
                // Merge intervals
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                // No overlap, add previous and move forward
                res.add(prev);
                prev = curr;
            }
        }

        // 4. Add last interval
        res.add(prev);

        return res.toArray(new int[res.size()][2]);
    }
}

 */