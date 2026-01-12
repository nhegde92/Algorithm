package intervals;

/*
Given an array of intervals -  intervals where intervals[i] = [start_i, end_i], return the minimum number
 of intervals you need to remove to make the rest of the intervals non-overlapping.

Note: Intervals are non-overlapping even if they have a common point.
For example, [1, 3] and [2, 4] are overlapping, but [1, 2] and [2, 3] are non-overlapping.

Example 1:

Input: intervals = [[1,2],[2,4],[1,4]]

Output: 1
Explanation: After [1,4] is removed, the rest of the intervals are non-overlapping.

Example 2:

Input: intervals = [[1,2],[2,4]]

Output: 0

Keep a variable to track the previous end. If there is no overlap from current interval to previous we are good.
if there is an overlap we need to remove one interval  this should be the one which has the max end value.

if you remove the one with min end value and keeping the one with max value then, you end up removing more then one.
 */

/*
Syntax
  Arrays.sort(intervals, (a, b) ->(Integer.compare(a[0],b[0])));
  for collections
  Collections.sort(intervals, (a, b) ->(Integer.compare(a[0],b[0])));
  res.toArray(new int[res.size()][2]);
 */



import java.util.Arrays;

public class NonOverlappingInterval {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevEnd = intervals[0][1];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            //no overlap
            if (start >= prevEnd)
                prevEnd = end;
                //overlap remove the one that ends last, keep the one that ends first;
            else {
                res++; // need to remove one that ends last
                prevEnd = Math.min(end, prevEnd);
            }

        }

        return res;
    }

    public static void main(String[] args) {
        NonOverlappingInterval solver = new NonOverlappingInterval();

        int[][] intervals1 = {{1, 2}, {2, 4}, {1, 4}};
        System.out.println("Intervals to remove: " + solver.eraseOverlapIntervals(intervals1)); // 1

        int[][] intervals2 = {{1, 2}, {2, 4}};
        System.out.println("Intervals to remove: " + solver.eraseOverlapIntervals(intervals2)); // 0

        int[][] intervals3 = {{1, 3}, {2, 4}, {3, 5}, {1, 2}};
        System.out.println("Intervals to remove: " + solver.eraseOverlapIntervals(intervals3)); // 2

        int[][] intervals4 = {{1, 5}, {2, 6}, {3, 7}, {4, 8}, {5, 9}};
        System.out.println("Intervals to remove: " + solver.eraseOverlapIntervals(intervals4)); // 4
    }
}
