/*
Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

Example 1:

Input: s = "00110110", k = 2
Output: true

Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.

Example 2:

Input: s = "0110", k = 1
Output: true

Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 

Example 3:

Input: s = "0110", k = 2
Output: false

Explanation: The binary code "00" is of length 2 and does not exist in the array.


METHOD 1:(BRUTE FORCE)
	Approach:
		First we can generate the all the binarty codes of length k. Then we can check whether those codes are presen in the given string or not. 

TIME: O(2^K * N * K), where N is the length of the given string and 2^K for generating all the unique binary codes.


METHOD 2: (USING SET + SLIDING WINDOW)

TIME: O(N * K).

SPACE: O(2^K * K).
*/

class Solution {
    public boolean hasAllCodes(String s, int k) {
        
        if(s.length() < k) {
            return false;
        }
        
        // taking a hashset to generate all the unique binart codes of size k
        Set<String> codeSet = new HashSet<>();
        
        // usinf sliding window technique and then slide the window one by obe towards right
        int i = 0, j = k;
        
        // total unique binary codes
        int totalUniqueCodes = (int)Math.pow(2, k); 
        
        while(j <= s.length()) {
            
            // generating the binart code of size k
            String binCode = s.substring(i, j);
            
            // adding the codes
            codeSet.add(binCode);
            
            i++;
            j++;
        }
        
        // if the codeSet length is = totalUniquesCodes, it returns true
        return (codeSet.size() == totalUniqueCodes);
    }
}	