/*
Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]

The idea is every K interval put the current index into PQ and peek the top element

Overall Time Complexity: O(n * k). This is bad. A more efficient solution is to use
DeQueue.
 */



/*
Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]

The idea is every K interval put the current index into PQ and peek the top element

Overall Time Complexity: O(n * k). This is bad. A more efficient solution is to use
DeQueue.
 */

package priorityqueues;

import java.util.*;

public class SlidingWindowMax {

    /**
     * Returns the maximum element in each sliding window of size k.
     * <p>
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        // Edge cases
        if (nums == null || nums.length == 0 || k == 0) {
            return result;
        }

        // Max heap storing {value, index}
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < nums.length; i++) {

            // Add current element with its index
            maxHeap.offer(new int[]{nums[i], i});

            // Remove elements that are out of the current window
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] <= i - k) {
                maxHeap.poll();
            }

            // Start adding results once the first window is complete
            if (i >= k - 1) {
                result.add(maxHeap.peek()[0]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMax obj = new SlidingWindowMax();
        System.out.println(
                obj.maxSlidingWindow(
                        new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3
                )
        );
    }
}
