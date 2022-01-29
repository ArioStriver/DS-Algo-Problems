/*
Largest Rectangle in Histogram


Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(1).

*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        
        int n = heights.length;
        int maxArea = 0;
        
        for(int i = 0; i < n; i++){
            
            int leftSmaller = 0, rightSmaller = n-1;
            
            // we have to keep track of the minimum height till now on left
            for(int j = 0; j < i; j++){
                if(heights[j] < heights[i]){
                    leftSmaller = j+1;
                }
            }
            
            for(int k = n-1; k > i; k--){
                if(heights[k] < heights[i]){
                    rightSmaller = k-1;
                }
            }
            
            maxArea = Math.max(maxArea, (rightSmaller - leftSmaller + 1) * heights[i]);
            //System.out.println(maxArea);
        }
        
        return maxArea;
    }
}


// METHOD 2:(USING STACK)

// TIME: O(4N) == O(N).

// SPACE: O(3N) == O(N).


class Solution {
    public int largestRectangleArea(int[] heights) {
        
        int n = heights.length;
        
        if(heights.length == 0) return 0;
        
        // left and right to hold the left and the right boundary
        int[] leftSmall = new int[n], rightSmall = new int[n];
        
        // stack to hold the indexes
        Stack<Integer> st = new Stack<>();
        
        // for left boundary
        for(int curr = 0; curr < n; curr++){
            
            // if the stack is not empty
            // and if the current element is less than the stack top then simply pop
            while(!st.isEmpty() && heights[st.peek()] >= heights[curr]){
                st.pop();
            }
            
            if(st.isEmpty()){
                leftSmall[curr] = 0;
            }
            else{
                leftSmall[curr] = st.peek() + 1;
            }
            // then simply push the current index
            st.push(curr);   
        }
        
        // reuse the previous stack
        while(!st.isEmpty()) st.pop();
        
        // for left boundary
        for(int curr = n - 1; curr >= 0; curr--){
            
            // if the stack is not empty
            // and if the current element is less than the stack top then simply pop
            while(!st.isEmpty() &&  heights[st.peek()] >= heights[curr]){
                st.pop();
            }
            
            if(st.isEmpty()){
                rightSmall[curr] = n - 1;
            }
            else{
                rightSmall[curr] = st.peek() - 1;
            }
            // then simply push the current index
            st.push(curr);   
        }
        
        // now finding the maximum area
        int maxArea = 0;
        
        for(int i = 0; i < n; i++){
            maxArea = Math.max(maxArea, heights[i] * (rightSmall[i] - leftSmall[i] + 1));   
        }
        
        return maxArea;
    }
}


// METHOD 3:(ONE-PASS SOLUTION)

// TIME: O(N).

// SPACE: O(N).


class Solution {
    public int largestRectangleArea(int[] histo) {
        
        int n = histo.length; 
        
        if(histo.length == 0) return 0;
        
        Stack<Integer> st = new Stack<>();
        
        int maxArea = 0, width = 0;
        
        for(int i = 0; i <= n; i++){
            
            // check whether the stack is empty or not
            // also check whether the stack top is greater or equal to cuurent height or no
            // if so then the next smallest element on its right is that element
            // and the next smallest element on its left is at the (st.top() - 1) position
            // and also simply pop the greater element's index from the stack
            while(!st.isEmpty() && (i == n || histo[st.peek()] >= histo[i])){
                int height = histo[st.peek()];
                
                // pop the greater element
                st.pop();
                
                // if the stack becomes empty after poping then there is no element
                // smaller than that in its left, so then the width of the element
                // is equal to i
                // else width = (RightSmall - LeftSmall - 1)
                if(st.isEmpty()){
                    width = i;
                }
                else{
                    width = (i - st.peek() - 1);
                }
                // finding maximum area
                 maxArea = Math.max(maxArea, height * width);
            }
            
            // when the stack is empty
            st.push(i);
        }
        return maxArea;
    }
}
