/*
You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and 
there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b
 
Example 1:

Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]

Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"

Example 2:

Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]

Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"


METHOD:

TIME: O(N), where N is the number of elements.

SPACE: O(1), excluding the output array.
*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        
        int n = nums.length;
        List<String> res = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while(i < n){
            
            // taking the start of the range
            int a = nums[i];
            
            // check whether the current element + 1 == next element OR j recahed end of the loop
            if(j == n - 1 || (nums[j] + 1 != nums[j+1])){
                
                // mark the range end
                int b = nums[j];
                
                // if both are unequal
                if(a != b){
                    res.add(new String(a+"->"+b));
                }
                else{
                    res.add(String.valueOf(a));
                }
                
                // move i to next start index
                i = j + 1;
            }
            j++;
        }
        
        return res;
    }
}