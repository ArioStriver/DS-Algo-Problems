/*
Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.

Example 1:

Input: s = "leEeetcode"
Output: "leetcode"

Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".

Example 2:

Input: s = "abBAcC"
Output: ""

Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""

Example 3:

Input: s = "s"
Output: "s"


METHOD:

TIME: O(N), where N is the length of the string.

SPACE: O(N).
*/

class Solution {
    public String makeGood(String s) {
        
        if(s.length() < 2) return s;
        
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            
            char curChar = s.charAt(i);
            
            // if the difference b/w the current charcater and the previous charter is 32
            // it means that it is combination of lowe-upper character
            if(!st.isEmpty() && Math.abs(curChar - st.peek()) == 32) {
                st.pop();
            }
            else {
                st.push(curChar);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(char ch : st) {
            sb.append(ch);
        }
        
        return sb.toString();
    }
}