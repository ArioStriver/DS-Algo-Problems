/*
(AMAZON)

You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large 
number, return this modulo 109 + 7.

Example 1:


Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.

Example 2:

Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the 
maximum area.

Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 
Constraints:

2 <= h, w <= 10^9
1 <= horizontalCuts.length <= min(h - 1, 10^5)
1 <= verticalCuts.length <= min(w - 1, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
All the elements in horizontalCuts are distinct.
All the elements in verticalCuts are distinct.


METHOD:

TIME: O(v log v + h log h), where v and h are the number of vertical and horizontal cuts.

SPACE: O(1), plus memory required by the sort algorithm (from O(log n) to O(n), where n is the number of elements we sort).
*/

class Solution {
    
    int MOD = 1000000007;
    
    public int maxArea(int h, int w, int[] hCuts, int[] vCuts) {
        
        Arrays.sort(hCuts);
        Arrays.sort(vCuts);
        
        int maxHeight = hCuts[0];
        int maxWidth = vCuts[0];
        int maxArea = 0;
        
        // finding the maximum height 
        for(int i = 1; i < hCuts.length; i++) {
            maxHeight = Math.max(maxHeight, hCuts[i] - hCuts[i-1]);
        }
        
        maxHeight = Math.max(maxHeight, h - hCuts[hCuts.length-1]);
        
        // finding the maximum width 
        for(int i = 1; i < vCuts.length; i++) {
            maxWidth = Math.max(maxWidth, vCuts[i] - vCuts[i-1]);
        }
        
        maxWidth = Math.max(maxWidth, w - vCuts[vCuts.length-1]);
        
        return (int)((long) maxHeight * maxWidth % MOD);
    }
}