package SlidingWindow;


/*
Note: This is not a sliding window problem more of a greedy problem.
Assume the first day is the lowest day currMin.
If any subsequent days if the price is is less than currMin, set it as currMin.
Else in the sunsequent days if the price is more than currMin sell get the profits and set that day as currMin.

ex 7 1 6 10 8
if you buy on 1 and sellon 10 is same as buy on 1 sell on 6 buy on 6 and sell on 10.

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

