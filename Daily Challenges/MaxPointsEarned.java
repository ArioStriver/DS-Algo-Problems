/*
(ASKED BY GOOGLE)

There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12

Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on 
the right, giving a final score of 1 + 6 + 5 = 12.

Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4

Explanation: Regardless of which two cards you take, your score will always be 4.

Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55

Explanation: You have to take all the cards. Your score is the sum of points of all cards.


METHOD:(SLIDING WINDOW)

TIME: O(N), where N is the no. of elements.

SPACE: O(1).
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        
        int n = cardPoints.length;
        
        // finding the sum of the k elements from right
        int right = 0;
        for(int i = n-1; i >= n-k; i--) {
            right += cardPoints[i];
        }
        
        int maxScore = right;
        int left = 0;
        int i = 0, 
	  int j = n - k;    // window size
        
	  // find the sum for the rest elements
        for(int p = 1; p <= k; p++) {
            
            // while sliding we have to discard element from left and take element from right
            left += cardPoints[i];
            right -= cardPoints[j];
            
            // find the maximum score we have got so far
            maxScore = Math.max(maxScore, left + right);
            
            // sliding the window by one
            i++;
            j++;
        }
        
        return maxScore;
    }
}