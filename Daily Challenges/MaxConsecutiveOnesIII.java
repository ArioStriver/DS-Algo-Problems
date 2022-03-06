/*
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6

Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10

Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


METHOD 1:(USING SLIDING WINDOW)

TIME: O(N), where N is the length of the array.

SPACE: O(1).
*/

class Solution {
    public int longestOnes(int[] nums, int k) {
        
        int maxConsecOnes = 0;
        int countZeroes = 0;  // to keep track of how many zeroes are there in a window
        int start = -1;      // to release extra zeros if there is present in the window 
        
        for(int end = 0; end < nums.length; end++){
            
            // count no. of zeros
            if(nums[end] == 0){
                countZeroes++;
            }
            
            // checking whether the no. of zeros is > k in the window
            // if so then we have release a zero in order to make it equal to k
            while(countZeroes > k){
                start++;
                
                if(nums[start] == 0){
                    countZeroes--;
                }
            }
            
            maxConsecOnes = Math.max(maxConsecOnes, end - start);
        }
        
        return maxConsecOnes;
    }
}