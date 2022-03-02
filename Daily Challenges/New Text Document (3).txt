/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative 
positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


METHOD 1:(USING TWO POINTER)

TIME: O(max(N, M)), where N is the length s and M is the length of t.

SPACE: O(1).
*/

class Solution {
    public boolean isSubsequence(String s, String t) {
        
        // edge case
        // if s is null, then s is a subsquence of t
        if(s.length() == 0) return true;
        
        int i = 0, j = 0;
        
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        
        return i == s.length();
    }
}