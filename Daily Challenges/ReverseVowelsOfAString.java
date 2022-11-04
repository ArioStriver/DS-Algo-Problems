/*
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

Example 1:

Input: s = "hello"
Output: "holle"

Example 2:

Input: s = "leetcode"
Output: "leotcede"


METHOD:

TIME: O(N), where N is the length of the given string.

SPACE: O(N).
*/

class Solution {
    
    private boolean isLowercaseVowel (char c) {
        
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    private boolean isUppercaseVowel (char c) {
        
        return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }
    
    public String reverseVowels(String s) {
       
        int i = 0, j = s.length()-1;
        char[] ch = s.toCharArray();
        
        while(i < j) {
            
            // if the ith character is not a vowel increment it
            if(!isLowercaseVowel(ch[i]) && !isUppercaseVowel(ch[i])) {
                i++;
            }
            // if the jth character is not a vowel increment it
            else if(!isLowercaseVowel(ch[j]) && !isUppercaseVowel(ch[j])) {
                j--;
            }
            // otherwise swap both of them and increment them
            else {
                char temp = ch[i];
                ch[i] = ch[j];
                ch[j] = temp;
                i++;
                j--;
            }
        }
        
        return new String(ch);
    }
}
