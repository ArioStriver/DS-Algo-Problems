/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:

Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:

Input: n = 1
Output: [[1]]


METHOD:

TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public int[][] generateMatrix(int n) {
     
        int[][] ans = new int[n][n];
        
        // taking four pointers
        int startRow = 0;
        int endRow = n-1;
        int startColumn = 0;
        int endColumn = n-1;
        int count = 1;
        
        while(startRow <= endRow && startColumn <= endColumn){
            
            // direction 1: traverse from left to right
            for(int i = startColumn; i <= endColumn; i++){
                ans[startRow][i] = count++;
            }
            startRow++;
            
            // direction 2: traverse from top to bottom
            for(int i = startRow; i <= endRow; i++){
                ans[i][endColumn] = count++;
            }
            endColumn--;
            
            // direction 3: traverse from right to left
            for(int i = endColumn; i >= startColumn; i--){
                ans[endRow][i] = count++;
            }
            endRow--;
            
            // direction 4: traverse from bottom to top
            for(int i = endRow; i >= startRow; i--){
                ans[i][startColumn] = count++;
            }
            startColumn++;
        }
        
        return ans;
    }
}