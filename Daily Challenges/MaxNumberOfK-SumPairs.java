/*
You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.

Example 1:

Input: nums = [1,2,3,4], k = 5

Output: 2

Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.

Example 2:

Input: nums = [3,1,3,4,3], k = 6

Output: 1

Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(N).
*/

class Solution {
    public int maxOperations(int[] nums, int k) {
        
        // storing the index of the pairs
        Set<Integer> st = new HashSet();
        int maxOp = 0;
        
        // traversing through the array
        for(int i = 0; i < nums.length-1; i++) {
            
            // if the i th index is present in the set, it means that there is pair 
            // with the current index, so ignore the current number
            if(st.contains(i)) continue;
            
            for(int j = i+1; j < nums.length; j++) {
                
                // if the j th index is present in the set, it means that there is pair 
                // with the current index, so ignore the current number
                if(st.contains(j)) continue;
                
                if(nums[i] + nums[j] == k) {
                    
                    // if the (i, j) is no pesent then add them into the set
                    if(!st.contains(i) && !st.contains(j)) {
                        st.add(i);
                        st.add(j);
                        maxOp++;
                    }
                }
            }
        }
        
        return maxOp;
    }
}

/*
METHOD 2:(TWO POINTER)

TIME: O(NlogN).

SPACE: O(1).
*/

class Solution {
    public int maxOperations(int[] nums, int k) {
        
        int maxOperation = 0;
        int start = 0, end = nums.length-1;
        
        // sorting the array
        Arrays.sort(nums);
        
        while(start < end) {
            
            // sum is equal to k
            if(nums[start] + nums[end] == k) {
                start++;
                end--;
                maxOperation++;
            }
            // sum is less than k, then move the start pointer towards left to get greater value
            else if(nums[start] + nums[end] < k) {
                start++;
            }
            // sum is greater than k, move the pointer towards right to get smaller value
            else{
                end--;
            }
        }
        
        return maxOperation;
    }
}

/*
METHOD 3:
	APPROACH:
		1. Initialize a hash table, say S, to store the frequency of elements in arr[].
		2. Traverse the array arr[], and perform the following steps:
			1. If the frequency of (K – arr[i]) is > 0, then increment ans by 1 and decrement the frequency of (K – arr[i]) by 1.
			2. Otherwise, insert arr[i] with frequency 1 in the Hash Table.
TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int maxOperations(int[] nums, int k) {
        
        int maxOperation = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        for(int num : nums) {
            
            // i + j = k
            // j = (k - i)
            int remain = k - num;
            
            // if the (k - nums[i]) is present and it's frequency is > 0, it means that it will make a 
            // pair with the current number nums[i]
            if(freq.containsKey(remain) && freq.get(remain) > 0) {
                
                // increamenting the no. of pairs whose (sum == k)
                maxOperation++;
                
                // decrementing the freq of remain, bcz it creates a pair with the current nums[i]
                freq.put(remain, freq.get(remain) - 1);  
            }
            // otherwise put the current number into the freq map
            else {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }
        
        return maxOperation;
    }
}