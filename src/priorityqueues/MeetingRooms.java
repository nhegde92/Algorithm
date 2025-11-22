/*
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 */

package priorityqueues;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        // If there are fewer than 2 meetings, only that many rooms are needed
        if (intervals.length < 2)
            return intervals.length;

        // Sort the intervals by their start times
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min-heap to track the end times of ongoing meetings
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 0; i < intervals.length; i++) {
            int value = intervals[i][1]; // end time of the current meeting

            if (pq.size() == 0) {
                // If no meeting room is in use, add the end time
                pq.add(value);
                continue;
            }

            if (intervals[i][0] < pq.peek()) {
                // If current meeting starts before the earliest ending meeting ends,
                // we need a new room
                pq.add(value);
            } else {
                // Else, we can reuse a room — remove the earliest end time
                pq.poll();
                pq.add(value);
            }
        }

        // The size of the heap tells us the number of meeting rooms needed
        return pq.size();
    }

    public static void main(String args[]){
        int[][] interval1 = {{0,30},{5,10},{15,20}};
        int[][] interval2 = {{5,10},{15,20}};
        MeetingRooms mr = new MeetingRooms();
        System.out.println(mr.minMeetingRooms(interval1));
        System.out.println(mr.minMeetingRooms(interval2));
    }
}
