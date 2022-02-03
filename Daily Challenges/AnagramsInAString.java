/*
Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.


METHOD:(USING SLIDING WINDOW TECHNIQUE)

TIME: O(length(S)).

SPACE: O(26) = O(1).
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        
        List<Integer> indexes = new ArrayList<>();
        
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()){
           return new ArrayList<Integer>();
        }
        
        int[] pCount = new int[26];
        
        // count the frequency of the characters in the p string
        for(char c : p.toCharArray()){
            pCount[c - 'a']++;
        }
        
        int left = 0, right = 0;
        int window_size = p.length();
        
        // anagramLen = length of currently found anagram. If it equals
       // the length of anagram to find, it must have been found
        int anagramLen = window_size;
        
        //Before we begin this, the "window" has a length of 0, start and
       //end pointers both at 0
        for(right = 0; right < window_size; right++){
            
            //Process current char
            char curChar = s.charAt(right);
            
            pCount[curChar - 'a']--;
            
            // If it's still >= 0, the anagram still "needed" it so we count it towards the anagram by
           // decrementing anagramLen
            if(pCount[curChar - 'a'] >= 0){
                anagramLen--;
            }
        }
        
        //This would mean that s began with an anagram of p
        if(anagramLen == 0){
            indexes.add(0);
        }
        
        // At this point, left remains at 0, right has moved so that the window is the length of the anagram
       // from this point on we are going to be moving start AND end on each iteration, to shift the window
       // along the string
        while(right < s.length()){
            
            // Now curChar represents the current first character of the window. The character that is
           // going to be "left behind" as the window moves. 
            char curChar = s.charAt(left);
            
            // If it's not negative, this means that the character was part of the anagram. That means we
           // are one step "farther away" from completing an anagram. So we must increment diff.
            if(pCount[curChar - 'a'] >= 0){
                anagramLen++;
            }
            
            //Increment the hash value for this character, because it is no longer contained in the window
            pCount[curChar - 'a']++;
            
            //Increment left to start shifting the window over by 1
            left++;
            
            // now curChar represents the last character of the window, the "new" character from the window shift.
           // This character "replaces" the one we removed before so the window stays the same length (p.length())
            curChar = s.charAt(right);
            
            // Decrement hash value for this character, because it is now a part of the window
            pCount[curChar - 'a']--;
            
            //Again, if it's not negative it is part of the anagram. So decrement diff
            if(pCount[curChar - 'a'] >= 0){
                anagramLen--;
            }
            
            // If anagramLen has reached zero, that means for the last p.length() iterations, anagramLen was decremented and
           // not decremented, which means every one of those characters was in the anagram, so it must be an anagram
           
           //Note: If many windows in a row find anagrams, then each iteration will have diff incremented then decremented again
            if(anagramLen == 0){
                indexes.add(left);
            }
            
            // move to the next character
            right++;
        }
        
        return indexes;
    }
}