/*
Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.

An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.

We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number 
less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is 
less than 5.

Example 1:

Input: nums = [3,5,2,6], k = 2
Output: [2,6]

Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.

Example 2:

Input: nums = [2,4,3,3,5,4,9,6], k = 4
Output: [2,3,3,4]


METHOD:(USING STACK)
	APPROACH:
		1. Keep a mono incrasing stack result. If current element a is smaller then the last element in the stack, we can replace it to get a smaller sequence.

		2. Before we do this,
			we need to check if we still have enough elements after. After we pop the last element from stack,
			we have stack.size() - 1 in the stack, there are A.size() - i can still be pushed.

TIME: O(N), where N is the total number of elements.

SPACE: O(N).
*/

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        
        int n = nums.length;
        
        Stack<Integer> st = new Stack<>();
        
        // traversing the all elements in the array
        for(int i = 0; i < n; i++){
            
            // finding how many elements do we require to create the subsequence
            int reqEle = k - st.size();
            
            // we need to check if we still have enough elements after the current elemnt
            // to fill the required places
            int remainEle = n - i - 1;
            
            // now we check if the current element is smaller than the previous elements we
            // have placed so far and also check whether it has enough elements after it
            while(!st.isEmpty() && remainEle >= reqEle && nums[i] < nums[st.peek()]){
                st.pop();

		// after poping the requiremet gets increased
                reqEle++;
            }
            
            if(st.size() < k){
                st.push(i);
            }
        }
        
        //System.out.println(st);
        
        int[] result = new int[k];
        
        for(int i = k - 1; i >= 0; i--){
            result[i] = nums[st.pop()];
        }
        
        return result;
    }
}