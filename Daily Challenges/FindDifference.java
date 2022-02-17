/*
Find the Difference

You are given two strings s and t.

String t is generated by random shuffling string s and then add one more letter at a random position.

Return the letter that was added to t.

Example 1:

Input: s = "abcd", t = "abcde"
Output: "e"
Explanation: 'e' is the letter that was added.
Example 2:

Input: s = "", t = "y"
Output: "y"
 

Constraints:

0 <= s.length <= 1000
t.length == s.length + 1
s and t consist of lowercase English letters.


METHOD 1:

TIME: O(N + M), where N is the length of the string S and M is the length of the string t.

SPACE: O(1).
*/

class Solution {
    public char findTheDifference(String s, String t) {
     
        int[] freq = new int[26];
        
        // storing the frequency of s string
        for(char c : s.toCharArray()){
            freq[c-'a']++;
        }
        
        for(char d : t.toCharArray()){
            
            // decreasing the frequency of the current character
            freq[d-'a']--;
            
            // if frequency becomes negative it means it is the added characters
            if(freq[d-'a'] < 0)
                return d;
        }
        
        return '0';
    }
}


// METHOD 2:(OPTIMIZED)

// TIME: O(N), where N is the length of the String s.

// SPACE: O(1).


class Solution {
    public char findTheDifference(String s, String t) {
     
        // Initially, char code is the last t character, since that won't be included in the loop
        char charCode = t.charAt(s.length());
        
        // For all positions (except the "bonus" one handled above), change running char total
        for(int i = 0; i < s.length(); i++){
            charCode -= s.charAt(i);
            charCode += t.charAt(i);
        }
        
        // Leftover amount is the value of bonus letter
        return (char)charCode;
    }
}