/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: s = "(()"
Output: 2

Explanation: The longest valid parentheses substring is "()".

Example 2:

Input: s = ")()())"
Output: 4

Explanation: The longest valid parentheses substring is "()()".

Example 3:

Input: s = ""
Output: 0


METHOD 1:(USING STACK)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int longestValidParentheses(String s) {
        
        if(s == null || s.length() < 2) return 0;
        
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        
        int maxLength = 0;
        
        for(int i = 0; i < s.length(); i++) {
            
            char c = s.charAt(i);
            
            if(c == '(') {
                st.push(i);
            }
            else {
	
		    // first pop to match with a open brace
                st.pop();
                
		    // after popping if the stack is empty it means that there is '(' for the current ')', so pushing its index
                if(st.empty()) {
                    st.push(i);
                }
		    // otherwise calculate the maximum length of the valid parenthesis
                else {
                    int len = i - st.peek();
                    maxLength = Math.max(maxLength, len);
                }
            }
        }
        
        return maxLength;
    }
}


/*
METHOD 2:(WITHOUT EXTRA SPACE)\

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int longestValidParentheses(String s) {
        
        if(s == null || s.length() < 2) return 0;
        
        int open = 0, close = 0;
        int maxLength = 0;
        
        // first we traverse the string from left to right
        for(int i = 0; i < s.length(); i++) {
            
            char c = s.charAt(i);
            
            if(c == '(') {
                open++;
            }
            else {
                close++;
            }
            
            // if they are equal then it is valid parenthesis
            if(open == close) {
                int len = open + close;
                maxLength = Math.max(maxLength, len);
            }
            
            // if the no. of close > open, then it is a invalid parenthesis
            // then we have to reset the values of the open and close 
            if(close > open) {
                open = close = 0;
            }
        }
        
        open = close = 0;
        
        // then we traverse the string from right to left
        for(int i = s.length()-1; i >= 0; i--) {
            
            char c = s.charAt(i);
            
            if(c == '(') {
                open++;
            }
            else {
                close++;
            }
            
            // if they are equal then it is valid parenthesis
            if(open == close) {
                int len = open + close;
                maxLength = Math.max(maxLength, len);
            }
            
            // if the no. of close > open, then it is a invalid parenthesis
            // then we have to reset the values of the open and close 
            if(open > close) {
                open = close = 0;
            }
        }
        
        return maxLength;
    }
}