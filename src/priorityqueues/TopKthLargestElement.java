
/*Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4*/

/*
In min heap we are removing n-k+1 elements because we are removing from priority queue
as soon as the size > k. we are removing smallest element there.
 */

/*
 Min heap is preferred over all. If k is closer to n then may be
 max heap is better. Furthermore, for max heap use n-k+1 as opposed
 to k. The idea is the fist element will give the answer.

 Time complexity O(NLogK)
 for loop will be n, heapify will be log k.




 */

package priorityqueues;

import java.util.PriorityQueue;
import java.util.Queue;

public class TopKthLargestElement {
    public static int topKthLargest(int[] nums, int k) {
        //min queue;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(topKthLargest(nums, 2));
        int []nums2 = {3,2,3,1,2,4,5,5,6};
        System.out.println(topKthLargest(nums2, 4));
    }
}



