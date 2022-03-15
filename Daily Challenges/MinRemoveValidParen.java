/*
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 
Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"

Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.


METHOD 1:(USING STACK)

TIME: O(N), where N is the length of the string.

SPACE: O(N).
*/

class Solution {
    public String minRemoveToMakeValid(String S) {
        
        // to store the position of the open braces
        Stack<Integer> st = new Stack<>();
        
        char[] ch = S.toCharArray();
        
        // traversing the entire string
        for(int i = 0; i < ch.length; i++){
            
            // if '(', then store its position
            if(ch[i] == '('){
                st.push(i);
            }
            // if ')'
            else if(ch[i] == ')'){
                // then we need a '(' to pair with the ')', so we have to look for the '('
                // in the stack
                if(!st.isEmpty()){
                    st.pop();
                }
                // if the stack is empty it means that we unable to find a '(' 
                // for the current ')', so just put a '#' at that place.
                else{
                    ch[i] = '#';
                }
            }
        }
         // mark invalid indices
        // if the stack is not empty it means that the braces in the stack are invalid
        // so we need to remove those from the given string
        while(!st.isEmpty()){
            ch[st.pop()] = '#';
        }
        
        // biulding the final answer
        StringBuilder sb = new StringBuilder();
        
        for(char c : ch){
            if(c != '#'){
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}

/*
METHOD 2:(USING CONSTANT SPACE)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public String minRemoveToMakeValid(String S) {
        
        char[] ch = S.toCharArray();
        int countOpen = 0;
        
        // traversing the entire string
        for(int i = 0; i < ch.length; i++){
            
            if(ch[i] == '('){
                countOpen++;
            }
            else if(ch[i] == ')'){
                
                // there is open braces
                if(countOpen > 0){
                    countOpen--;
                }
                // there is no more open braces left
                else{
                    ch[i] = '#';
                }
            }
        }
        
        // mark invalid indices
        for(int i = ch.length - 1; i >= 0; i--){
            if(ch[i] == '(' && countOpen > 0){
                ch[i] = '#';
                countOpen--;
            }
        }
        
        // biulding the final answer
        StringBuilder sb = new StringBuilder();
        
        for(char c : ch){
            if(c != '#'){
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}