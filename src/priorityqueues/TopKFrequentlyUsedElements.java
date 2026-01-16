/*Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */

/*
Using Priority Queue
O(NLogK) Time complexity.
O(N+K) space complexity
Idea is to make hashMap of digits and their frequency. Create a min heap of size k.
Remove the elements with the lowest frequency when the size exceeds k. For loop will
run n times. Insertion to the heap will add logk complexity so bringing the total to
O(NLogK) Time complexity.

You build a frequency map in O(N).

Then, for each unique element (at most N), you push it into a min-heap of size up to K. Each push/pop operation costs O(log K).

So total is O(N log K).

Using Bucket sort

Bucket sort is another idea where time is better for extra space O(N); However the disadvantage with this is with large
number of elements the bucket array can get very big




 */


package priorityqueues;

import java.util.*;

public class TopKFrequentlyUsedElements {

    /**
     * Returns the k most frequent elements from the array.
     * <p>
     * Time Complexity: O(n log k)
     * Space Complexity: O(n + k)
     */
    public static int[] getTopKElements(int[] nums, int k) {

        // Edge cases
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min-heap based on frequency
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        // Step 3: Keep heap size <= k
        for (int key : freqMap.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        // Step 4: Build result
        int[] result = new int[minHeap.size()];
        int index = result.length - 1;
        while (!minHeap.isEmpty()) {
            result[index--] = minHeap.poll();
        }

        return result;
    }


    public static int[] getTopUsingBucketSort(int[] nums, int k) {
        int[] res = new int[k];
        if (k == 0 || nums.length == 0)
            return res;
        List<Integer>[] freqBucket = new ArrayList[nums.length + 1]; // Important give it an extra space because if all the elements are same then you need nth index to hold n numbers
        // Modifying one will modify all Arrays.fill(freqBucket, new ArrayList<>());
        for (int i = 0; i < freqBucket.length; i++) {
            freqBucket[i] = new ArrayList<>();
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            freqBucket[value].add(key);
        }
        int index = 0;
        for (int i = freqBucket.length - 1; i >= 0; i--) {
            List<Integer> list = freqBucket[i];
            if (list.size() == 0)
                continue;
            for (int num : list) {
                if (index == k)
                    break;
                res[index++] = num;

            }
        }

        return res;

    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4};
        int k = 2;
        int res[] = getTopKElements(nums, k);
        for (int re : res) {
            System.out.print(re + " ");
        }
        System.out.println("-----");
        int res2[] = getTopUsingBucketSort(nums, k);
        for (int re : res2) {
            System.out.print(re + " ");
        }

        System.out.println("-----");

        System.out.println();
        int[] res3 = getTopKElements(nums2, k);
        for (int j : res3) {
            System.out.print(j + " ");
        }
        System.out.println("-----");
        int[] res4 = getTopUsingBucketSort(nums2, k);
        for (int j : res4) {
            System.out.print(j + " ");
        }

    }

}


