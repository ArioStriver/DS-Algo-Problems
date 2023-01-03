/*
You are given an array of n strings strs, all of the same length.

The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:

abc
bce
cae
You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 
('b', 'c', 'a') is not, so you would delete column 1.

Return the number of columns that you will delete.

Example 1:

Input: strs = ["cba","daf","ghi"]
Output: 1

Explanation: The grid looks as follows:
  cba
  daf
  ghi

Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.

Example 2:

Input: strs = ["a","b"]
Output: 0

Explanation: The grid looks as follows:
  a
  b
Column 0 is the only column and is sorted, so you will not delete any columns.

Example 3:

Input: strs = ["zyx","wvu","tsr"]
Output: 3

Explanation: The grid looks as follows:
  zyx
  wvu
  tsr
All 3 columns are not sorted, so you will delete all 3.


METHOD:

TIME: O(M * N).

SPACE: O(1).
*/

class Solution {
    public int minDeletionSize(String[] strs) {

        // Return 0 if the input array is null or empty
        if (strs == null || strs.length == 0) {
            return 0;
        }
        
        int numColumnsToDelete  = 0;
         // Get the number of rows and columns in the grid
        int n = strs.length;
        int m = strs[0].length();

        // Iterate through each column of the grid
        for(int c = 0; c < m; c++) {
            // Iterate through each element in the column
            for(int r = 0; r < n-1; r++) {
                // If the current element is lexicographically smaller than the next element,
                // increment the delete count and break out of the loop
                if(strs[r].charAt(c) > strs[r+1].charAt(c)) {
                    numColumnsToDelete++;
                    break;
                }
            }
        }

        return numColumnsToDelete;
    }
}