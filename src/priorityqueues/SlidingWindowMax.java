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

    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        if(k == 0 || nums.length == 0)
                return new ArrayList<Integer>();
        if(nums.length == 1)
            return new ArrayList<Integer>(nums[0]);
        List<Integer> res = new ArrayList<>();
        int sp = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int i = 0; i<k; i++){
            pq.offer(nums[i]);
        }
        res.add(pq.peek());
        for(int i = k; i<nums.length; i++){
            pq.remove(nums[sp]);
            sp++;
            pq.offer(nums[i]);
            if(pq.size() > 0)
                res.add(pq.peek());
        }

       return res;
    }

    public static void main(String []args){
        SlidingWindowMax obj = new SlidingWindowMax();
        System.out.println(obj.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));
    }
}
