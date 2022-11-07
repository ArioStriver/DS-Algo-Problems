/*
You are given a positive integer num consisting only of digits 6 and 9.

Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

 

Example 1:

Input: num = 9669
Output: 9969

Explanation: 
Changing the first digit results in 6669.
Changing the second digit results in 9969.
Changing the third digit results in 9699.
Changing the fourth digit results in 9666.
The maximum number is 9969.

Example 2:

Input: num = 9996
Output: 9999

Explanation: Changing the last digit 6 to 9 results in the maximum number.

Example 3:

Input: num = 9999
Output: 9999

Explanation: It is better not to apply any change.


METHOD 1:

TIME: O(L), where L is the length of the given number.

SPACE: O(L), We convert num to a string of length L, therefore, the space complexity is O(L).
*/

class Solution {
    public int maximum69Number (int num) {
        
        String nStr = "" + num;
        
        int res = Integer.parseInt(nStr.replaceFirst("6", "9"));
        
        return res; 
    }
}

/*
METHOD 2:

TIME: O(L).

SPACE: O(1).
*/

class Solution {
    public int maximum69Number (int num) {
        
        int tempNum = num;
        int idxOfSix = -1, k = 0;
        
        while(tempNum > 0) {
            
            // find the last index of the 6 from right to left
            if(tempNum % 10 == 6) {
                idxOfSix = k;
            }
            
            tempNum /= 10;
            k++;
        }
        
        // if the index of the six is -1, it means that there is no six present in the given number
        return idxOfSix == -1 ? num : num + 3 * (int)Math.pow(10, idxOfSix);
    }
}