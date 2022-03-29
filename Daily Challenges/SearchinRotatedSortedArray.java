/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], 
nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:

Input: nums = [1], target = 0
Output: -1


METHOD 1:(SIMPLE LINEAR SCAN)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int search(int[] nums, int target) {
        
        int index = -1;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                index = i;
            }
        }
        
        return index;
    }
}


/*
METHOD 2:(USING BINARY SERACH)

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    public int search(int[] nums, int target) {
        
        int n = nums.length;
        int low = 0, high = n-1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            // if the middle element is equal to target
            if(nums[mid] == target){
                return mid;
            }
            
            //otherwise find the target its left/right part
            // but before that we need to check whether the left/right part is sorted/not
            
            // left part is sorted
            if(nums[low] <= nums[mid]){
                
                // if sorted then we need to check whether the target exists in that part/not
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
        
        return -1;
    }
}