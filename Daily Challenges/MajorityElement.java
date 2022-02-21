/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3

Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 
Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-2^31 <= nums[i] <= 2^31 - 1


METHOD 1:(BRUTE FORCE)
	
TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public int majorityElement(int[] nums) {

        int majorityCount = nums.length/2;

        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }

            if (count > majorityCount) {
                return num;
            }

        }

        return -1;    
    }
}

/*
METHOD 2:(USING HASHMAP)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int majorityElement(int[] nums) {
        
        int n = nums.length;
        
        Map<Integer, Integer> freq = new HashMap<>();
        
        for(int ele : nums){
            freq.put(ele, freq.getOrDefault(ele, 0) + 1);
        }
        
        for(Map.Entry<Integer, Integer> en : freq.entrySet()){
            if(en.getValue() > n / 2){
                return en.getKey();
            }
        }
        
        return -1;
    }
}


/*
METHOD 3:(Boyer-Moore Voting Algorithm)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int majorityElement(int[] nums) {
        
        int candidate = 0, count = 0;
        
        for(int num : nums){
            
            /* Whenever count equals 0, we effectively forget about everything in nums up 
               to the current index and consider the current number as the candidate for majority element */
            if(count == 0){
                candidate = num;
            }
            
            /* if the current element is equal to the current majority element
               then increment the count for majority element */
            if(candidate == num){
                count++;
            }
            /* otherwise it is an minority element
               so decrement the counter */
            else{
                count--;
            }
        }
        
        return candidate;
    }
}