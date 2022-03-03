/*
An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
Given an integer array nums, return the number of arithmetic subarrays of nums.

A subarray is a contiguous subsequence of the array.

Example 1:

Input: nums = [1,2,3,4]
Output: 3

Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.

Example 2:

Input: nums = [1]
Output: 0


METHOD 1:(BRUTE FORCE)
	APPROACH:
		The Idea is Simple , the array is called arithmetic if it consists of at least 3 elements.
			1. So, firstly if arr_size is < 3 , we will return false.
			2. The second requirement of the question is, that the difference b/w any two consecutive elements should be same.
			3. so running a loop from i = 0 to nums.size() - 2 as we need at least 2 consecutive elements.
			4. now we will store the difference of first 2 elements in a diff variable.
			5. Again, we will traverse a loop to find how many consecutive pairs are with same diff.
			6. running a inner loop from i + 2 as the outer for loop has calculcated the diff of first 2 elements for us.
			7. now if the difference of arr[j] - arr[j-1] ,current and previous element is same , this means we find a consecutive pair, with same diff, 
                           therefore we will increase the count.
			8. If the diff is not same then we will break the loop, as we need consecutive elements diff to be same.
			9. Return the Count.

TIME: O(N * N), where N is the length of the array.

SPACE: O(1).
*/

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        
        // if the length of the array is < 3 then return 0
        // bcz accd. to question there should be at least three elements in the subarray
        if(nums.length < 3) return 0;
        
        int n = nums.length;
        int count = 0, diff = 0;
        
        for(int i = 0; i < n - 2; i++){
            
            // storing diff of first 2 elements
            diff = nums[i+1] - nums[i];
            
            // checking for consecutive elements with same difference.
            for(int j = i+2; j < n; j++){
                
              /*if we find the same diff of next 2 elements
				this means we  find consecutive elements
				increase the Count */
                if(nums[j] - nums[j-1] == diff){
                    count++;
                }
                else{
                    break;
                }
            }
        }
        
        return count;
    }
}

/*
METHOD 2:(OPTIMIZED)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        
        // if the length of the array is < 3 then return 0
        // bcz accd. to question there should be at least three elements in the subarray
        if(nums.length < 3) return 0;
        
        int n = nums.length;
        int count = 0, ans = 0;
        int prevDiff = Integer.MAX_VALUE;
        
        for(int i = 1; i < n; i++){
            
            // calculatinn the current difference
            int curDiff = nums[i] - nums[i-1];
            
            // if the current difference is equal to the previous difference then 
            // we find the consecutive element of same diff
            if(curDiff == prevDiff){
                ans += count;
                count++;
            }
            else{
                // update prev diff with curr diff
                // as we don't find consecutive elements with same diff
                prevDiff = curDiff;
                count = 1;          // make count to 1 as we get a new diff
            }
        }
        
        return ans;
    }
}