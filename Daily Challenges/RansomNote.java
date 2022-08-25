/*
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true


METHOD: (USING HASHING)

TIME: O(L1 + L2), where L1 & L2 are lengths of ransomNote & magazine.

SPACE: O(1).
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
        if(magazine.length() < ransomNote.length()) {
            return false;
        }
          
        int[] freq = new int[26];
        
        for(int i = 0; i < magazine.length(); i++) {
            int c = magazine.charAt(i)-'a';
            freq[c]++;
        }
        
        for(int i = 0; i < ransomNote.length(); i++) {
            int c = ransomNote.charAt(i)-'a';
            if(freq[c] <= 0) {
                return false;
            }
            freq[c]--;
        }
        
        return true;
    }
}