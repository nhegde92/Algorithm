package priorityqueues;

/*
The median is the middle value in a sorted list of integers. For lists of even length, there is no middle value, so the median is the mean of the two middle values.

For example:

For arr = [1,2,3], the median is 2.
For arr = [1,2], the median is (1 + 2) / 2 = 1.5
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far.
Example 1:

Input:
["MedianFinder", "addNum", "1", "findMedian", "addNum", "3" "findMedian", "addNum", "2", "findMedian"]

Output:
[null, null, 1.0, null, 2.0, null, 2.0]

Explanation:
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.findMedian(); // return 1.0
medianFinder.addNum(3);    // arr = [1, 3]
medianFinder.findMedian(); // return 2.0
medianFinder.addNum(2);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
*/

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Idea: Keep two heaps. Since the numbers incoming are not sorted we need two heaps. One heap will
have the first half of the numbers and the second heap will have the second half of the numbers.
Since we need the median, we want the highest element in the lower half and the lowest element in the
upper half. So the lower half will be a max heap and the upper half will be the min heap.

At any given time we can have the difference in the size to be 2.


easier way to read perhaps

    public void addNum(int num) {
        // Step 1: Add to max-heap (lower half)
        lowerHalf.add(num);

        // Step 2: Move max from lowerHalf to upperHalf
        upperHalf.add(lowerHalf.poll());

        // Step 3: Balance sizes (ensure lowerHalf >= upperHalf)
        if (lowerHalf.size() < upperHalf.size()) {
            lowerHalf.add(upperHalf.poll());
        }
    }

    public double findMedian() {
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else {
            return lowerHalf.peek();
        }
    }
 */


public class MedianFromDataStream {

    private PriorityQueue<Integer> firstHalfMaxHeap;
    private PriorityQueue<Integer> secondHalfMinHeap;

    public MedianFromDataStream() {
        firstHalfMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        secondHalfMinHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 1. Add to appropriate heap
        if (firstHalfMaxHeap.isEmpty() || num <= firstHalfMaxHeap.peek()) {
            firstHalfMaxHeap.add(num);
        } else {
            secondHalfMinHeap.add(num);
        }

        // 2. Rebalance sizes
        if (firstHalfMaxHeap.size() > secondHalfMinHeap.size() + 1) {
            secondHalfMinHeap.add(firstHalfMaxHeap.poll());
        } else if (secondHalfMinHeap.size() > firstHalfMaxHeap.size()) {
            firstHalfMaxHeap.add(secondHalfMinHeap.poll());
        }
    }

    public double findMedian() {
        if (firstHalfMaxHeap.size() == secondHalfMinHeap.size()) {
            return (firstHalfMaxHeap.peek() + secondHalfMinHeap.peek()) / 2.0;
        }
        return (double) firstHalfMaxHeap.peek();
    }

    // ✅ Main function to test
    public static void main(String[] args) {
        MedianFromDataStream mf = new MedianFromDataStream();

        int[] nums = {5, 2, 10, 4};

        for (int num : nums) {
            mf.addNum(num);
            System.out.println("Added: " + num + ", Current median: " + mf.findMedian());
        }
    }
}

