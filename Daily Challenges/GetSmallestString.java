/*
The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the 
numeric value of c is 3, and so on.

The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string 
"abe" is equal to 1 + 2 + 5 = 8.

You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.

Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first 
position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.

Example 1:

Input: n = 3, k = 27
Output: "aay"

Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.

Example 2:

Input: n = 5, k = 73
Output: "aaszz"


METHOD:
	APPROACH:
		Our main goal is to build a string lexiographically smallest---

			1. So the smallest possible string for any n would be full of 'a'.

			2. Now we have to fuflill another condition that is we want the sum of characters to be equal to k.

			3. By maintaining the lexiograhically smallest string we have to full fill this condition. So we start from rightmost part bcz it will always 
			   be optimal to put the highest possible character at the current index.

			4. If we have k greater than 25 ['z'] we replace it with 'z' and subtract it with 25 == 'z'.

			5. Else replace it with the required character with k as number.

			6. Do this until k > 0.

TIME: O(N).

SPACE: O(1), ignoring the output string.
*/

class Solution {
    public String getSmallestString(int n, int k) {
        
        char[] ans = new char[n];
        
        // So the smallest possible string for any n would be full of 'a'.
        Arrays.fill(ans, 'a');
        
        // we need to subtract k as we placed n no. of a's. 
        k = k - n;
        
        // Now we have to fuflill another condition that is we want the sum of characters 
        // to be equal to  k and also the string should be in lexicographical order.
        // so we start the traversal from the right towards left and build our final answer
        
        int i = n - 1;
        while(k > 0){
            
            // If we have k greater than 25, place z at the current index
            if(k > 25){
                ans[i] = 'z';
                k = k - 25;
            }
            else{
                ans[i] = (char)('a' + k);
                k = 0;
            }
            i--;
        }
        
        return new String(ans);
    }
}