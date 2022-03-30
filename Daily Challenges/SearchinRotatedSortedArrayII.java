/*
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], 
nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


METHOD 1:(USING LINEAR SEARCH)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public boolean search(int[] nums, int target) {
        
        boolean isExists = false;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                isExists = true;
                break;
            }
        }
        
        return isExists;
    }
}


/*
METHOD 2:(USING BINARY SERACH)

TIME : At worst case it is O(N), consider this input [1,1,1,1,1,1,1,1,0,1,1,1,1] you will have to scroll all '1' which is N-1 steps, at best case it is O(logN).

SPACE: O(1).
*/

class Solution {
    public boolean search(int[] nums, int target) {
        
        int n = nums.length;
        int low = 0, high = n-1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            if(nums[mid] == target) return true;
            
            // skipping the duplicates
            if(nums[low] == nums[mid]){
                low++;
                continue;
            }
            
            // left part is sorted
            if(nums[low] <= nums[mid]){
                
                // if exists
                if(target >= nums[low] && target <= nums[mid]){
                    high = mid - 1;
                }
                else{
                    low = mid + 1;
                }
            }
            // right part is sorted
            else{
                if(target >= nums[mid] && target <= nums[high]){
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
        }
        
        return false;
    }
}