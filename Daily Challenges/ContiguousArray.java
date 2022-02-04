/*
Contiguous Array

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 
Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

METHOD 1:(BRUTE FORCE)
	APPROACH:
		The brute force approach in these type of questions is to generate all the possible sub-arrays. Then firstly check whether the sub-array has equal 
		number of 0’s and 1’s or not.

TIME: O(N^2), where N is the length of the array.

SPACE: O(1).
*/

class Solution {
    public int findMaxLength(int[] nums) {
        
        int n = nums.length;
        int maxLength = 0;
        
        for(int i = 0; i < n-1; i++){
            
            int count01 = 0;
            for(int j = i; j < n; j++){
                
                if(nums[j] == 1){
                    count01++;
                }
                else{
                    count01--;
                }
                
                if(count01 == 0){
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
}

/*
METHOD 2:(USING HASHMAP)
	APPROACH:
		The concept of taking cumulative sum, taking 0’s as -1 will help us in optimizing the approach. While taking the cumulative sum, there are two cases 
		when there can be a sub-array with equal number of 0’s and 1’s. 

		1. When cumulative sum=0, which signifies that sub-array from index (0) till present index has equal number of 0’s and 1’s.

		2. When we encounter a cumulative sum value which we have already encountered before, which means that sub-array from the previous index+1 till the 
		   present index has equal number of 0’s and 1’s as they give a cumulative sum of 0 .

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int findMaxLength(int[] nums) {
     
        // to store the sum
        Map<Integer, Integer> map = new HashMap<>();
        
        int maxLength = 0;
        int sum = 0;
        
        // initialize the map
        map.put(0, -1);
        
        // traversing the array 
        for(int i = 0; i < nums.length; i++){
            
            // here we consider 0 as -1
            if(nums[i] == 0){
                sum += -1;
            }
            else{
                sum += 1;
            }
            
            // now we are checking whether the sum is present in the map or not
            // if it is previously present
            if(map.containsKey(sum)){
                
                // extract the index where it was previously occured
                int index = map.get(sum);
                
                maxLength = Math.max(maxLength, i - index);
            }
            // otherwise put that sm into the map
            else{
                map.put(sum, i);
            }
        }
        
        return maxLength;
    }
}
