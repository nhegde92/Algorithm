package binarysearch;

/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.



Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23



 */
public class KoKoBanana {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Integer.MAX_VALUE; // Alternatively start from max of the array piles .
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currK = canConsumeBanana(mid, piles);
// Can Consume in less or equal  hours then intended, so reduce K . If equal hours find lowerboundl
            if (currK <= h)
                high = mid - 1;
            else
                low = mid + 1; // Can consume in more hours, so need to increase K
        }
        return low;
    }

    public int canConsumeBanana(int k, int[] piles) {
        int h = 0;
        for (int i = 0; i < piles.length; i++) {
            h += (int) Math.ceil((double) piles[i] / k); // Double is needed because int 5/2 is 2 and ceil of 2 is still 2
            //Alternatively piles[i]+k-1/k. Adding k-1 will push the value to ceil. This is better and efficient.
        }

        return h;
    }

    public static void main(String[] args) {
        KoKoBanana koko = new KoKoBanana();

        System.out.println(koko.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));         // ➞ 4
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));   // ➞ 30
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));   // ➞ 23

    }


}
