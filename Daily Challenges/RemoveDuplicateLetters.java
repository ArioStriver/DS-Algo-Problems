/*
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order 
among all possible results.

Example 1:

Input: s = "bcabc"
Output: "abc"

Example 2:

Input: s = "cbacdcbc"
Output: "acdb"

METHOD: (USING STACK)
	APPROACH:
		Now, your first Question is which Data Structure do we use in order to solve this problem?? 

		First Of all, we have to pick the character's if it is not already visited. If, that's the case we'll try to pick these character's. We'll also make 
		sure, the previously picked character is smaller then the current character in order to maintain lexicographically order. But, how we can check the 
		previously picked character is best for!! And the answer is Stack!!

		What we'll do, use the stack to keep track of selected character's. We try to put the character's only once & maintain the lexicographicall smallest 
		one. So, how we do that :-

			1. If the stack is empty, we'll put the current character into our stack

			2. We'll also keep here boolean array which will mark, whether we have seen this character or not. So, that if we are getting again the same 
			   character and we have already seen that. We'll ignore that character.

			3. So, the length of boolean array will be 26

TIME: O(N), where N is the length of the String S.

SPACE: O(N).
*/

class Solution {
    public String removeDuplicateLetters(String S) {
        
        // s1 : to keep track of each charcater's last index means in which index it occurs last
        int[] lastIndex = new int[26];
        
        for(int i = 0; i < S.length(); i++){
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        Stack<Integer> st = new Stack<>(); // to keep of characters in lexicographical order
        
        // s2 : traverse the entire string
        for(int i = 0; i < S.length(); i++){
            
            int curChar = S.charAt(i) - 'a';
            
            // if we have not seen this character previously
            if(!seen[curChar]){
                
                // 1. if the stack is not empty
                // 2. then if the (previously picked element is > current element) (ASCII values)
                // and if the previously picked element occures after the current element 
                // then perform the pop opeartion
                // bcz we need the answer in lexicographical order
                while(!st.isEmpty() && st.peek() > curChar && i < lastIndex[st.peek()]){
                    
                    // pop out and mark as unseen
                    seen[st.pop()] = false;
                }
                
                // otherwise add the current charcater in the stack
                // and mark it as seen
                st.push(curChar);
                seen[curChar] = true;
            }
        }
        
        // s3 : now we need to build the answer string
        StringBuilder ans = new StringBuilder();
        
        while(!st.isEmpty()){
            ans.insert(0, (char)(st.pop()+'a'));
        }
        
        return ans.toString();
    }
}