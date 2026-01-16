package SlidingWindow;

/*


You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.


*/
/*
Note: This is not a sliding window problem more of a greedy problem.
Assume the first day is the lowest day currMin.
If any subsequent days if the price  is less than currMin, set it as currMin.
Else in the subsequent days if the price is more than currMin sell get the profits and set that day as currMin.

ex 7 1 6 10 8
if you buy on 1 and sell on 10 is same as buy on 1 sell on 6 buy on 6 and sell on 10.

Assume a graph where is high low high low high -> buy low sell high buy high buy low sell high

*/

public class BestTimeToBuyAndSell2 {
    public int maxProfit(int[] prices) {
        int currMin = prices[0];
        int total = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < currMin) {
                currMin = prices[i];
            } else {
                total += prices[i] - currMin;
                currMin = prices[i];
            }

        }
        return total;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSell2 obj = new BestTimeToBuyAndSell2();

        // Example 1
        int[] prices1 = {7, 1, 6, 10, 8};
        System.out.println("Max Profit (Example 1): " + obj.maxProfit(prices1));

        // Example 2
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max Profit (Example 2): " + obj.maxProfit(prices2));

        // Example 3
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit (Example 3): " + obj.maxProfit(prices3));
    }
}

