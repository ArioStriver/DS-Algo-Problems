/*
Given a string s, return the longest palindromic substring in s.

Example 1:

Input: s = "babad"
Output: "bab"

Explanation: "aba" is also a valid answer.

Example 2:

Input: s = "cbbd"


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Generating all substrings and check for palindrome.

TIME: O(N^3).

SPACE: O(1).
*/

class Solution {
    public String longestPalindrome(String s) {
        
        if(s.length() <= 1) {
            return s;
        }
        
        int maxLen = 0, start = 0;
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                
                String temp = s.substring(i, j+1);
                
                if(isPalindrome(temp)) {
                    if(temp.length() > maxLen) {
                        start = i;
                        maxLen = (j - i + 1);
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private boolean isPalindrome(String s) {
        
        int i = 0, j = s.length()-1;
        
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) 
                return false;
            i++;
            j--;
        }
        
        return true;
    }
}


/*
METHOD 2:(USING DP)

TIME: O(N^2).

SPACE: O(N^2).
*/

class Solution {
    public String longestPalindrome(String s) {
        
        if(s.length() <= 1) {
            return s;
        }
        
        int start = 0, end = 0, len = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        // gaps
        for(int g = 0; g < s.length(); g++) {
            
            for(int i = 0, j = g; j < s.length(); i++, j++) {
                
                // if length 1, substring
                if(g == 0) {
                    dp[i][j] = true;
                }
                // length 2 substring
                else if(g == 1) {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                // length > 2
                else {
                    // first check for extremes and then check for the middle part
                    if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                
                // means palindrome
                if(dp[i][j] == true) {
                    if(g + 1 > len) {
                        start = i;
                        end = g + 1;
                        len = end;
                    }
                }
            }
        }
        
        return s.substring(start, start + end);
    }
}


/*
METHOD 3:

TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public String longestPalindrome(String s) {
        
        if(s.length() <= 1) {
            return s;
        }
        
        int start = 0, end = 0;
        
        // start checking the palindrome from the center of each substring & go outwards
        // here i acts as a center
        for(int i = 0; i < s.length(); i++) {
            
            int len1 = expandFromMiddle(s, i, i);   // for odd length palindrome, there is one center
            int len2 = expandFromMiddle(s, i, i+1);  // for even length palindrome, (2n-1) centers
            
            int len = Math.max(len1, len2);
            
            // i represents center, so for start we have to move backward
            // and for end we have to move forward
            if(len > end - start) {
                start = i - (len-1) / 2;  // (len-1) for out of bound
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end+1);
    }
    
    private int expandFromMiddle(String s, int L, int R) {
        
        // expanding from middle & go outwards
        while(L >= 0 && R <  s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        
        return (R - L - 1);
    }
}