/*
Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
 
Example 1:

Input: columnTitle = "A"
Output: 1

Example 2:

Input: columnTitle = "AB"
Output: 28

Example 3:

Input: columnTitle = "ZY"
Output: 701


METHOD:
	APPROACH: The process is similar to binary to decimal conversion. For example, to convert AB, the formula is 26 * 1 + 2. 

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int titleToNumber(String columnTitle) {
        
        int res = 0;
        
        /* process is similar to Binary to decimal conversion
           e.g. = CDA
           3*26*26 + 4*26 + 1
           = 26(3*26 + 4) + 1
           = 26(0*26 + 3*26 + 4) + 1
        */
        
        for(int i = 0; i < columnTitle.length(); i++){
            
            res = res * 26;
            res = res + (columnTitle.charAt(i) - 'A') + 1;
        }
        
        return res;
    }
}