/*
Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].

For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 

Example 1:

Input: s = "ab", goal = "ba"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.

Example 2:

Input: s = "ab", goal = "ab"
Output: false
Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.

Example 3:

Input: s = "aa", goal = "aa"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.


METHOD:

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public boolean buddyStrings(String s, String goal) {

        // if lengths are different, return false
        if(s.length() != goal.length()) return false;

        // Case 1: check whether both the gievn strings are equal or not
        if(s.equals(goal)) {
            // now check whether we are able to get the goal string or not by swapping two letters
            int[] freq = new int[26];
            for(char c : s.toCharArray()) {
                freq[c - 'a']++;
                // swapping same characters will result same string
                if(freq[c - 'a'] == 2) {
                    return true;
                }
            }
            return false;
        }

        // Case 2: ther are not equal
        int firstIdx = -1, secondIdx = -1;
        for(int i = 0; i < goal.length(); i++) {
            // if the both the strings have different characters in the current position
            // then mark it as first Idx
            if(s.charAt(i) != goal.charAt(i)) {
                if(firstIdx == -1) {
                    firstIdx = i;
                }
                else if(secondIdx == -1) {
                    secondIdx = i;
                }
                // there are more then two different characters,so can't possible to make them equal in one swap 
                else {
                    return false;
                }
            }
        }

        // swapping not possible
        if(secondIdx == -1) return false;

        return s.charAt(firstIdx) == goal.charAt(secondIdx) && s.charAt(secondIdx) == goal.charAt(firstIdx);
    }
}