/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


METHOD: (USING HASHING)

TIME: O(N)

SPACE: O(N).
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        
        int maxLength = 0;
        Set<Integer> st = new HashSet<>();
        
        // first add all the elements in the hashset
        for(int a : nums) {
            st.add(a);
        }
        
        // now find the longest consecutive elements sequence
        for(int i = 0; i < nums.length; i++) {
            
            // first check whether the current element - 1 is present in the set or not
            // if so then then it is not going to create the longest consecutive subsequence
            if(!st.contains(nums[i]-1)) {
                
                int j = nums[i];
                
                // keep incramting the current number until it is present in the set
                while(st.contains(j)) {
                    j++;
                }
                
                // finding the maximum length of the sequence
                maxLength = Math.max(maxLength, j - nums[i]);
            }
        }
        
        return maxLength;
    }
}