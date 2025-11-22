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

    PriorityQueue<Integer> lowerHalf;
    PriorityQueue<Integer> upperHalf;

    // 🔧 Fix: Constructor name should match class name
    public MedianFromDataStream() {
        lowerHalf = new PriorityQueue<>((a, b) -> b - a); // max heap
        upperHalf = new PriorityQueue<>(); // min heap
    }

    public void addNum(int num) {
        int lowerSize = lowerHalf.size();
        int upperSize = upperHalf.size();

        // Base case
        if (lowerSize == 0) {
            lowerHalf.add(num);
            return;
        }

        // Base case
        if (upperSize == 0) { // important Rebalance
            upperHalf.add(num);
            if (upperHalf.peek() < lowerHalf.peek()) {
                lowerHalf.add(upperHalf.poll());
                upperHalf.add(lowerHalf.poll());
            }
            return;
        }

        // Place in the correct bucket
        if (num > upperHalf.peek()) {
            upperHalf.add(num);
            upperSize = upperHalf.size(); // imp update size
        } else {
            lowerHalf.add(num);
            lowerSize = lowerHalf.size(); // imp update size
        }

        // check if rebalance is needed
        if (Math.abs(lowerSize - upperSize) < 2)
            return;

        if (upperSize > lowerSize)
            lowerHalf.add(upperHalf.poll());
        else
            upperHalf.add(lowerHalf.poll());
    }

    public double findMedian() {
        int lowerSize = lowerHalf.size();
        int upperSize = upperHalf.size();
        if (lowerSize == 0 && upperSize == 0)
            return 0;
        if (lowerSize == upperSize)
            return (upperHalf.peek() + lowerHalf.peek()) / 2.0;
        if (upperSize > lowerSize)
            return upperHalf.peek();
        else
            return lowerHalf.peek();
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
