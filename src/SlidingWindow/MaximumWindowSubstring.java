package SlidingWindow;

/*


You are given an array of integers nums and an integer k. There is a sliding window of size k that starts at the left edge of the array. The window slides one position to the right until it reaches the right edge of the array.

Return a list that contains the maximum element in the window at each step.

Example 1:

Input: nums = [1,2,1,0,4,2,6], k = 3

Output: [2,2,4,4,6]

Explanation:
Window position            Max
---------------           -----
[1  2  1] 0  4  2  6        2
 1 [2  1  0] 4  2  6        2
 1  2 [1  0  4] 2  6        4
 1  2  1 [0  4  2] 6        4
 1  2  1  0 [4  2  6]       6
Constraints:

1 <= nums.length <= 1000
-10,000 <= nums[i] <= 10,000
1 <= k <= nums.length

 */


/*
The idea to come up with O(n) solution is not intuitive. What is intuitive is however to use heap O(nlogn)

O(n)
Dequeue: We use DeQueue for this approach. The Dequeue is always a "monotonically decreasing queue". The first
element is always the maximum element. Furthermore, we pop from beginning if the window size is greater than k.

1,2,1,0,4,2,6 k = 3
i = 0 dq: 0
i = 1 dq: 1 : 2>1 so pop 1. Note we store only the indexes in Dequeue
i = 2 dq: 1,2 output nums[left] = nums[1] = 2.   2,1 is decreasing so okay.
i = 3. dq: 1,2,3. output nums[left] = nums[1] = 2
i = 4 left is out of index, so 1 is removed. Furthermore, we 2, 3 because nums[2] or nums[3] < nums[4]. The queue needs to be strictly decreasing.


 Heap approach.O(nlogn)
 We use max heap. We throw in the element value and element index to the heap. The heap keeps the max element at the top
 At each step we add the element into heap.
 We see if the peak element is with in the bounds. If so keep it if not remove it
 At each step when pointer is > k store the result.
 */


import java.util.*;

public class MaximumWindowSubstring {

    public int[] getMaxWindowSubStringQueue(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int n = nums.length;
        int[] output = new int[n - k + 1];
        int index = 0;
        int right = 0;
        for (right = 0; right < nums.length; right++) {

            //Remove from left if out of bound.
            if (!dq.isEmpty() && dq.getFirst() < right - k + 1)
                dq.removeFirst();

            //make sure the queue is decreasing
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[right]) {
                dq.pollLast();
            }

            // add the element to the queue
            dq.addLast(right);
            if (right >= k - 1)
                output[index++] = nums[dq.getFirst()];
        }
        return output;
    }


    public int[] getMaxWindowSubStringPQ(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        int index = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int right = 0; right < nums.length; right++) {

            //Check if the peek element is in the window size
            while (!pq.isEmpty() && pq.peek()[1] < right - k + 1)
                pq.poll();
            //Add the element into PQ
            pq.add(new Integer[]{nums[right], right});

            //add to output
            if (right >= k - 1)
                output[index++] = pq.peek()[0];

        }
        return output;
    }


    public static void main(String[] args) {
        MaximumWindowSubstring solver = new MaximumWindowSubstring();

        int[] nums = {1, 2, 1, 0, 4, 2, 6};
        int k = 3;

        int[] resultDeque = solver.getMaxWindowSubStringQueue(nums, k);
        int[] resultPQ = solver.getMaxWindowSubStringPQ(nums, k);

        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("Window size: " + k);
        System.out.println("Deque method output: " + Arrays.toString(resultDeque));
        System.out.println("PriorityQueue method output: " + Arrays.toString(resultPQ));
    }
}

