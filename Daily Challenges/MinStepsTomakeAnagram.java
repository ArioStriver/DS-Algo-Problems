/*
You are given two strings s and t. In one step, you can append any character to either s or t.

Return the minimum number of steps to make s and t anagrams of each other.

An anagram of a string is a string that contains the same characters with a different (or the same) ordering.

Example 1:

Input: s = "leetcode", t = "coats"
Output: 7

Explanation: 
- In 2 steps, we can append the letters in "as" onto s = "leetcode", forming s = "leetcodeas".
- In 5 steps, we can append the letters in "leede" onto t = "coats", forming t = "coatsleede".
"leetcodeas" and "coatsleede" are now anagrams of each other.
We used a total of 2 + 5 = 7 steps.
It can be shown that there is no way to make them anagrams of each other with less than 7 steps.

Example 2:

Input: s = "night", t = "thing"
Output: 0

Explanation: The given strings are already anagrams of each other. Thus, we do not need any further steps.


METHOD:(USING HASHING)
	APPROACH:
		Store the frequency of s stirng, then store the frequency of the t string. For each letter, its contribution to the answer is the absolute difference 
		between its frequency in s and t.

TIME: O(N1 + N2 + 26), where N1 denotes the length of s and N2 denotes length of t.

SPACE: O(1).
*/

class Solution {
    public int minSteps(String s, String t) {
        
        int[] freq = new int[26];
        
        // counting the frequencuy of s string
        for(char c : s.toCharArray()){
            freq[c-'a']++;
        }
        
        // counting the frequency of t string
        // and if it is not present in the s then mark it as negative
        // bcz later we just take the absolute
        for(char c : t.toCharArray()){
            freq[c-'a']--;
        }
        
        // couting the steps
        int count = 0;
        for(int i = 0; i < 26; i++){
            count += Math.abs(freq[i]);
        }
        
        return count;
    }
}