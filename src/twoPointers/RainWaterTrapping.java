package twoPointers;
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

/*
At every Index we need to compute the Min(leftMax, rightMax)-height(index) to get
the water stored at that index. WE could do prefix sum array to find the leftMax and right Max at every index. This would require two additional arrays.

We could optimize this at each point computing the minimum of leftMax and rightMax.
and for every iteration for the minimum side we compute the total water held in that
index.

Note that if the current value is greater then the max value we are comparing with
that would ne the new max value.
*/
class RainWaterTrapping {
    public int trap(int[] height) {
        if (height.length < 2)
            return 0;
        int leftMax = height[0]; // holds the max value left side
        int rightMax = height[height.length - 1]; // holds the max value right side
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int index = 0;
        while (left <= right) {
            // Find wich side has the least max value
            if (leftMax <= rightMax) {
                if (height[left] > leftMax) // water overflows
                    leftMax = height[left];
                else
                    res += leftMax - height[left];

                left++;
            } else {
                if (height[right] > rightMax) //water overflows.
                    rightMax = height[right];
                else
                    res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }

    public static void main(String args[]){
        RainWaterTrapping obj = new RainWaterTrapping();
        System.out.println(obj.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(obj.trap(new int[]{4,2,0,3,2,5}));
    }
}