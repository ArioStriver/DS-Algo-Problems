/*
Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an 
initially empty stack, or false otherwise.

Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true

Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4),
pop() -> 4,
push(5),
pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

Example 2:

Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false

Explanation: 1 cannot be popped before 2.


METHOD 1:(USING STACK)

TIME: O(N), where N is the total no. of elements.

SPACE: O(N).
*/

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
     
        int N = pushed.length;
        Stack<Integer> st = new Stack<>();
        
        // Intialise one pointer pointing on popped array
        int j = 0;
        
        for(int num : pushed){
            
            // insert the values in stack
            st.push(num);
            
            // now we're checking whether the current element == required poped element
            while(!st.empty() && j < N && popped[j] == st.peek()){
                st.pop();
                j++;
            }
        }
        
        // if j reaches the end of the popped array it means it is possible to create 
        // the popped sequence
        return j == N;
    }
}

/*
METHOD 2:(SPACE OPTIMIZED)
	APPROACH: Here instead of using external stack, we gonna use pushed array as a stack.

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
     
        // here we use the pushed array as a stack
        int i = 0, j = 0;
        
        for(int val : pushed){
            
            // using pushed as the stack and pushing the elements
            pushed[i] = val;
            i++;
            
            // if the current value we have entered = the required popped element in the sequence
            while(i > 0 && pushed[i-1] == popped[j]){                
                i--;      // performing pop operation
                j++;      // move to the next required element
            }
        }
        
        // Since pushed is a permutation of popped so at the end 
        // we are supposed to be left with an empty stack, it means that i = 0
        return i == 0;
    }
}