package intervals;

/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [start_i, end_i] represents the start and the end time of the ith interval. intervals is initially sorted in ascending order by start_i.

You are given another interval newInterval = [start, end].

Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and also intervals still does not have any overlapping intervals. You may merge the overlapping intervals if needed.

Return intervals after adding newInterval.

Note: Intervals are non-overlapping if they have no common point. For example, [1,2] and [3,4] are non-overlapping, but [1,2] and [2,3] are overlapping.

Example 1:

Input: intervals = [[1,3],[4,6]], newInterval = [2,5]

Output: [[1,6]]
Example 2:

Input: intervals = [[1,2],[3,5],[9,10]], newInterval = [6,7]

Output: [[1,2],[3,5],[6,7],[9,10]]

“I iterate through three phases:
add intervals before the new one, merge all overlapping intervals, and finally add the remaining intervals.
Since the input is sorted, this works in linear time.”
 */

/*
Syntax
 return res.toArray(new int[res.size()][2]); to convert arrayList ot array.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        List<int[]> res = new ArrayList<>();
        while (i < intervals.length) {
            // add all the intervals that end before the newInterval start
            if (intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
                i++;
            } else
                break;
        }

        // Get the minimum&max bound of current interval and new Interval.
        //Reusing newInterval variable.
        //Do this until the current interval start is after new interval end
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the min&max bound computed
        res.add(newInterval);

        //Add any remaining
        while (i < intervals.length)
            res.add(intervals[i++]);
        return res.toArray(new int[res.size()][2]);


    }

    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();

        int[][] intervals1 = {{1, 3}, {4, 6}};
        int[] newInterval1 = {2, 5};
        printResult(sol.insert(intervals1, newInterval1));

        int[][] intervals2 = {{1, 2}, {3, 5}, {9, 10}};
        int[] newInterval2 = {6, 7};
        printResult(sol.insert(intervals2, newInterval2));
    }

    private static void printResult(int[][] result) {
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            System.out.print(Arrays.toString(result[i]));
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

