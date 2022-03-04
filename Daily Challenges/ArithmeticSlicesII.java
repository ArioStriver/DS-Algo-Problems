/*
Given an integer array nums, return the number of all the arithmetic subsequences of nums.

A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.

For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.

Example 1:

Input: nums = [2,4,6,8,10]
Output: 7

Explanation: All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

Example 2:

Input: nums = [7,7,7,7,7]
Output: 16

Explanation: Any subsequence of this array is arithmetic.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Enumerating all possible subsequences to see if they are arithmetic sequences.

TIME: O(2^N).

SPACE: O(N).
*/

class Solution {
    
    private int ans;
    
    public int numberOfArithmeticSlices(int[] nums) {
        
        int n = nums.length;
        ans = 0;
        List<Long> curr = new ArrayList<>();
        generateSubsequences(0, nums, n, curr);
        
        return ans;
    }
    
    private void generateSubsequences(int idx, int[] a, int n, List<Long> curr){
        
        // base case
        if(idx == n){
            
            // if the length is less than 3 return
            if(curr.size() < 3) return;
            
            long diff = curr.get(1) - curr.get(0);
            
            for(int i = 1; i < curr.size(); i++){
                if(curr.get(i) - curr.get(i-1) != diff){
                    return;
                }
            }
            ans++;
            return;
        }
        
        // skipping the current element
        generateSubsequences(idx + 1, a, n, curr);
        
        // adding the current element
        curr.add((long)a[idx]);
        generateSubsequences(idx + 1, a, n, curr);    // move to the next index
        
        // removing the last we added to try out all possibilitie
        curr.remove(curr.size() - 1);  
    }
}


/*
METHOD 2:(USING DP)

TIME: O(N^2).

SPACE: O(N^2).
*/


class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        
        int n = nums.length;
        int answer = 0;
        
        /* first we take a hashmap array where we gonna 
           store the number of weak subsequences ending at the current ith location with a[i]
           and haveing a C common differenec */
        HashMap<Integer, Integer>[] map = new HashMap[n];
        
        // now we have to initialize a hash array at ecah ith location
        for(int i = 0; i < n; i++){
            map[i] = new HashMap();
        }
        
        // now we are tarversing the array and find the the weak subsequences at each location
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                
                // finding the common difference between them
                long cd = (long)nums[i] - (long)nums[j];
                
                // if the common diffference is out of range
                if(cd <= Integer.MIN_VALUE || cd >= Integer.MAX_VALUE){
                    continue;
                }
                
                // No. of Aps haveing a common difference cd ending at the current jth location
                int ApsEndingOnJ = map[j].getOrDefault((int)cd, 0);
                
                // No. of Aps haveing a common difference cd ending at the current ith location
                int ApsEndingOnI = map[i].getOrDefault((int)cd, 0);
                
                // adding to the answer
                answer += ApsEndingOnJ;
                
                // now incerementing the number of Aps at current i
                map[i].put((int)cd, ApsEndingOnJ + ApsEndingOnI + 1);
            }
        }
        
        return answer;
    }
}