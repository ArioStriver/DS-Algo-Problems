/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49

Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:

Input: height = [1,1]
Output: 1


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public int maxArea(int[] heights) {
     
        int n = heights.length;
        int maxAmountOfWater = 0;
        
       // explore for all the heights & calculate the max area means max. amt. of water
        for(int i = 0; i < n-1; i++){
            
            for(int j = i+1; j < n; j++){
                
                // finding the minimum height between them
                int height = Math.min(heights[i], heights[j]);
                int width = (j - i);   // width 
                maxAmountOfWater = Math.max(maxAmountOfWater, height * width);
            }
        }
        return maxAmountOfWater;
    }
}


/*
METHOD 2:
	APPROACH:	
		Start with the maximum width container and go to a shorter width container if there is a vertical line longer than the current containers shorter line. 
		This way we are compromising on the width but we are looking forward to a longer length container.
TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int maxArea(int[] heights) {
     
        int n = heights.length;
        int maxAmountOfWater = 0;
        int left = 0, right = n-1;
        
       /* here we basically start from the maximum width container and go to a shorter 
          width container if the next vertical line is longer than the current shorter line */
        while(left < right){
            
            int height = Math.min(heights[left], heights[right]);
            int width = (right - left);
            maxAmountOfWater = Math.max(maxAmountOfWater, height * width);
            
            // go to a shorter  width container 
            // if the next vertical line is longer than the current shorter line
            if(heights[left] < heights[right]){
                left++;
            }
            else{
                right--;
            }
        }
        
        return maxAmountOfWater;
    }
}