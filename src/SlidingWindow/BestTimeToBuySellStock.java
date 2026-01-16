package SlidingWindow;

/*
You are given an integer array prices where prices[i] is the price of Neet-Coin on the ith day.

You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.

Return the maximum profit you can achieve. You may choose to not make any transactions, in which case
the profit would be 0.

Example 1:

Input: prices = [10,1,5,6,7,1]

Output: 6
Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.

Example 2:

Input: prices = [10,8,7,5,2]

Output: 0
 */

public class BestTimeToBuySellStock {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;   // Keeps track of the highest profit seen so far
        int sp = 0;          // sp = "start pointer" (buy day)

        for (int fp = 1; fp < prices.length; fp++) {  // fp = "forward pointer" (sell day)
            if (prices[fp] < prices[sp])
                sp = fp;  // Found a cheaper buy price — move buy pointer
            else
                maxProfit = Math.max(maxProfit, prices[fp] - prices[sp]);
            // Calculate profit if we sell today, and update maxProfit if it's better
        }

        return maxProfit;  // Return the best profit found
    }

    public static void main(String[] args) {
        BestTimeToBuySellStock solution = new BestTimeToBuySellStock();

        int[] prices1 = {10, 1, 5, 6, 7, 1};
        int[] prices2 = {10, 8, 7, 5, 2};

        System.out.println("Example 1 Output: " + solution.maxProfit(prices1)); // Expected: 6
        System.out.println("Example 2 Output: " + solution.maxProfit(prices2)); // Expected: 0
    }

}
