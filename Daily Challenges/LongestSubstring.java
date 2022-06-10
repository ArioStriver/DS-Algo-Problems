/*
Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3

Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1

Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3

Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


METHOD 1: (BRUTE FORCE)
	Approach:
		Generating all the substrings and then check.

TIME: O(N^2).

SPACE: O(N).
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if(s == null || s.length() == 0) return 0;
       
        int maxLength = 0;
        
        // generating all the substrings
        for(int i = 0; i < s.length(); i++) {
            
             Set<Character> ch = new HashSet<>();
            
            int j;
            for(j = i; j < s.length(); j++) {
                
                if(!ch.contains(s.charAt(j))) {
                    ch.add(s.charAt(j));
                }
                else {
                    // if conatins then no need to check further
                    break;
                }
            }
            
            // finding the max length
            maxLength = Math.max(maxLength, j - i);
        }
        
        return maxLength;
    }
}

/*
METHOD 2: (USING SET)

TIME: O(2N).

SPACE: O(N).
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if(s == null || s.length() == 0) return 0;
       
        Set<Character> seen = new HashSet<>();
        int maxLength = 0;
        int left = 0, right = 0;
        
        // using sliding window technique
        // will slide the window by one position at a time, and then check whether the character is 
        // present in the Hashset or not
        while(right < s.length()) {
            
            // remove the characters from left utill we able to remove the current character
            while(seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }
            
            // not present, then add it
            seen.add(s.charAt(right));
            
            maxLength = Math.max(maxLength, right - left + 1);
            
            right++;
        }
        
        return maxLength;
    }
}

/*
METHOD 3:(MOST OPTIMIZED)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if(s == null || s.length() == 0) return 0;
     
        // to keep track of each and every characters last index we came across
        Map<Character, Integer> lastSeen = new HashMap<>();
        
        int maxLength = 0;
        int left = 0, right = 0;
        
        while(right < s.length()) {
            
            char curr = s.charAt(right);
            
            // if present (the current character)
            if(lastSeen.containsKey(curr)) {
                
                // then check last index we have seen & if it is within current(left - right) range
                // then simply go one step ahead of lastSeen of current character i.e. take a jump
                if(left <= lastSeen.get(curr)) {
                    left = lastSeen.get(curr) + 1;
                }
            }
            
            // otherwise update the current character last position / add the current character if not present
            lastSeen.put(curr, right);
            
            // calculating the max length
            maxLength = Math.max(maxLength, right - left + 1);
            
            right++;
        }
        
        return maxLength;
    }
}