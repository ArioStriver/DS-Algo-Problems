/*
Subarray Sum Equals K

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7

METHOD 1:(BRUTE FORCE)
	APPROACH:
		A simple solution is to traverse all the subarrays and calculate their sum. If the sum is equal to the required sum then increment the count of subarrays

TIME: O(N^2), where N is the length of the subarray.

SPACE: O(1).
*/

class Solution {
     
    public static void main(String[] args)
    {    
        // calculate all subarrays
        for (int i = 0; i < n; i++) {
             
            int sum = 0;
            for (int j = i; j < n; j++) {
                 
                // calculate required sum
                sum += arr[j];
                 
                // check if sum is equal to
                // required sum
                if (sum == k)
                    totalSubarrays++;
            }
        }
     }
}


// METHOD 2:(USING PREFIX SUM AND HASHMAP)
//	APPROACH:
//		Here the approach is that suppose from 0 to j th index the current sum so far = x.
//		and the sum from the 0th index to the i th index is = (x - k)
//		then the sum from the (i+1) th index to the j th index is k.
//		How ? suppose k = 5, and from the 0 th to j the current sum so far is 15.
//		Now if the (x - k) exists in the map means (15 - 5) = 10 exists in the map it means that there is an subarray whose sum is equal to k = 5.
//		  (x - k) + k = x
//	       => k = x - (x - k) = k

// TIME: O(N).

// SPACE: O(N).


class Solution {
    public int subarraySum(int[] nums, int k) {
        
        int n = nums.length;
        int totalSubSumEqualsK = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        // why? bcz when there is nothing then 0 sum is present there
        map.put(0, 1);
        int sum = 0;
        
        for(int i = 0; i < n; i++){
            
            // computinf the prefix sum
            sum += nums[i];
            
            // checks wether the map contains the value = sum - k
            // if so then there exists subarry whose sum = k
            // bcz suppose total sum upto that index is X & if (X - k) exists in the map
            // then there exists a subarray whose sum = k
            if(map.containsKey(sum - k)){
                
                // getting the occurance of the (sum - k) value
                totalSubSumEqualsK += map.get(sum - k);
            } 
            
            // if the sum doesn't exists
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return totalSubSumEqualsK;
    }
}