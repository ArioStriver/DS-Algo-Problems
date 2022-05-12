/*
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Example 1:

Input: nums = [1,1,2]

Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

Example 2:

Input: nums = [1,2,3]

Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


METHOD: (USING BACKTRACKING)
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        
        Arrays.sort(nums);
        
        generatePermutations(nums, visited, res, new ArrayList());
        
        return res;
    }
    
    private void generatePermutations(int[] nums, boolean[] visited, List<List<Integer>> res, List<Integer> temp) {
        
        // base case
        if(temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            
            // if previously visited then ignore
            if(visited[i]) continue;
            
            /* for skkiping duplicates
               here used[i-1] == false means we have used that number previously
               bcz after coming out from the recursion we mark tje cuurent element as false and then remove 
               thet element from the list */
            if(i > 0 && nums[i] == nums[i-1] && visited[i-1] == false) continue;
            
            // adding the current no.
            temp.add(nums[i]);
            visited[i] = true;   // mark it as visited
            generatePermutations(nums, visited, res, temp);
            
            // remove the last no. to try out all possibilities
            temp.remove(temp.size() - 1);
            visited[i] = false;             // after removing again unmark it
        }
    }
}