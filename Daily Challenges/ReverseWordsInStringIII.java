/*
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:

Input: s = "God Ding"
Output: "doG gniD"


METHOD:(TWO POINTER)

TIME: O(N), where N is the length of the given string.

SPACE: O(1).
*/

class Solution {
    
    private void reverse(char[] c, int i, int j) {
        
        while(i < j) {
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            i++; j--;
        }
    }
    
    public String reverseWords(String s) {
        
        char[] ch = s.toCharArray();
        int L = 0, R = 0;
        
        while(L < s.length()) {
            
            // keep incrementing the pointer untill we find a space
            while(R < s.length() && ch[R] != ' ') R++;
            
            // after finding the space reverse the string
            reverse(ch, L, R-1);
            
            // updating the left & right pointer for the next word
            L = R + 1;   
            R = L;
        }
        
        return new String(ch);
    }
}


// SHORTER VERSION

class Solution {
    public String reverseWords(String s) {
        
        String[] words = s.split(" ");
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < words.length; i++) {
            StringBuilder temp = new StringBuilder(words[i]);
            temp.reverse();
            sb.append(temp+ " ");
        }
        
        return sb.toString().trim();
    }
}