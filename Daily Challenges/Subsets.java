/*
Subsets

Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [0]
Output: [[],[0]]


METHOD 1:(USING BACKTRACKING)

TIME: O(N * 2^N), Where there are two choices for each element, either we can pick the current element or not. N is the length of the array.

SPACE: O(N).
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
       
        List<List<Integer>> res = new ArrayList<>();
        
        createAllSubsets(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void createAllSubsets(int[] a, int idx, List<Integer> ds, List<List<Integer>> res){
        
        // base case
        if(idx == a.length){
            res.add(new ArrayList<>(ds));
            return;
        }
        
        // taking the current one into subsequence
        ds.add(a[idx]);
        createAllSubsets(a, idx + 1, ds, res);
        ds.remove(ds.size() - 1);
        
        // not taking the current one into the subsequences
        createAllSubsets(a, idx + 1, ds, res);
    }
}


// METHOD 2:(USING POWER SET)

// TIME: O(2^N * N), 2^N for the outer loop bcz it loops 2^N no. of times and the inner for loop loops N times.

// SPACE: O(N).


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        
        int n = nums.length;
        List<List<Integer>> res= new ArrayList<>();
        
        // if there are n elements then 
        // the total number of subsequences is 2^n
        // so if we repewsent them in binary it goes from 0 to (n - 1)th element
        for(int i = 0; i < (1 << n); i++){
            
            List<Integer> ds = new ArrayList<>();
            
            // checking which bit is set
            // let us assume 0 means not considered and 1 means considered
            for(int j = 0; j < n; j++){
                
                // if the current j th bit is set
                // pick that number
                if((i & (1 << j)) != 0){
                    ds.add(nums[j]);
                }
            }
            
            res.add(ds);
        }
        
        return res;
    }
}