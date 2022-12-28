/*
You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:

Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
Notice that you can apply the operation on the same pile more than once.

Return the minimum possible total number of stones remaining after applying the k operations.

floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).

Example 1:

Input: piles = [5,4,9], k = 2
Output: 12

Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [5,4,5].
- Apply the operation on pile 0. The resulting piles are [3,4,5].
The total number of stones in [3,4,5] is 12.

Example 2:

Input: piles = [4,3,6,7], k = 3
Output: 12

Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [4,3,3,7].
- Apply the operation on pile 3. The resulting piles are [4,3,3,4].
- Apply the operation on pile 0. The resulting piles are [2,3,3,4].
The total number of stones in [2,3,3,4] is 12.


METHOD:
	APPROACH:
		To make the total number of stones remaining minimum, each time remove the most stones as possible. Therefore, each time select the pile with the maximum number of stones, and 
		remove half of the stones from the pile.

		ALGORITHM:
			Use a max heap.
			Each time pop the max value a,
			remove a / 2 from the number of stones res and push back the ceil half a - a / 2 to the heap.
			Repeat this operation k times.

TIME: O(N + KlogN).

SPACE: O(N).
*/

class Solution {
    public int minStoneSum(int[] piles, int k) {
        
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int num : piles) {
            pq.offer(num);
            res += num;
        }

        // remove a / 2 from the number of stones res
        // and push back the ceil half a - a / 2 to the heap.
        while(k-- > 0) {
            int pile = pq.poll();
            int newPile = pile - (pile/2);
            pq.offer(newPile);
            res -= (pile/2);
        }

        return res;
    }
}	