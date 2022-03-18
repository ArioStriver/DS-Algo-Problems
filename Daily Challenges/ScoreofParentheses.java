/*
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2

METHOD 1:(USING STACK)
	APPROCH:
		Whenever we encounter a '(' we basically push '0' and whenever we encounter a ')' we will be calculating the result for everthing that is enclosed 
		within that ')' and it's corresponding '('.

TIME: O(N), where N is the length of the string.

SPACE: O(N).
*/

class Solution {
    public int scoreOfParentheses(String S) {
        
        int score = 0;
        Stack<Integer> st = new Stack<>();
        
        // now traversing the entire given string
        for(char c : S.toCharArray()){
            
            int val = 0;
            
            // if we encounter a '('
            if(c == '('){
                st.push(0);
            }
            // if we encounter a ')'
            else{
                
                // we will be calculating the result for everthing that is enclosed 
                // within that ')' and its corresponfing '('
                while(st.peek() != 0){
                    val += st.pop();
                }
                
                // () -> 1, (()) -> nester brace -> 2 * (1)
                val = Math.max(2 * val, 1);
                st.pop();
                st.push(val);
            }
        }
        
        // now calculating the entire score
        while(!st.isEmpty()){
            score += st.pop();
        }
        
        return score;
    }
}

/*
METHOD 2:
	APPROACH:
		The key idea is that:

			1. the balance tells you what "depth" you are at since with each '(' we are increasing the depth by 1.

			2. the "cores" () are the only structure that provides value, the outer parentheses just add some multiplier to the cores. So we only need to 
			   be concerned with those.
			
			With those 2 ideas in mind, we are able to calculate how much the "core" is worth directly without having to calculate substructures recursively 
			and then apply multipliers.

			E.g. For the example of ( ( ) ( ( ) ) ), with the stack method, we are calculating the inner structure ( ) ( ( ) ) first and then multiplying 
			the score by 2. With the 3rd method, by knowing the depth of the core, we are actually performing this calculation ( ( ) ) + ( ( ( ) ) ).

		Every () is a tree node. ()() can be treated as two sibling nodes while (()) can be treated as a parent and a child node. Each leaf node has value 1 
		while non-leaf node has double the value of all of its direct children's value. All we have to do is to calculate the root node value via post-order 
		traverse. And this is exactly what the approach 1 did as it always get the value of deeper layers before gets its' own value! 
		For example, the S = '(()(()()))' can be treated as the tree below.

                            (10)            layer 0
                            ／ \
                          (1)  (4)          layer 1
                               /  \
                             (1)  (1)       layer 2
	
		Basically, the approach 2 is using the same idea. As you can see, the value of the root node is the sum of each leaf node value to the power of it's 
		depth. There are three leaf nodes in the tree. One leaf in layer 1 and two leafs in layer 2. So the final answer is 2^1 + 2^2 + 2^2 = 10.
		By the way, approach 2 runs faster than approach 1 theoretically as it dose not calculate the intermediate nodes value.

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int scoreOfParentheses(String S) {
        
        int score = 0, depth = 0;
        
        for(int i = 0; i < S.length(); i++){
            
            // with each '(' we are increasing the depth by 1
            if(S.charAt(i) == '('){
                depth++;
            }
            // ')'
            else{
                depth--;
                
                // if the previous is open braces
                // it means it is the core, and we just need to add the multiplier to the cores
                // result
                if(S.charAt(i-1) == '('){
                    score += 1 << depth;
                }
            }
        }
        
        return score;
    }
}