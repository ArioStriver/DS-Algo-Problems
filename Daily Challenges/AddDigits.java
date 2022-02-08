/*
Add Digits

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:

Input: num = 38
Output: 2

Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.

Example 2:

Input: num = 0
Output: 0


METHOD 1:

TIME: O(logN)
*/

class Solution {
    public int addDigits(int num) {
        
        int sum = 0;
        
        while(num > 0){
            sum += (num % 10);
            num /= 10;
            
	    // if the number becomes 0 but the sum still greater than 10, it means it contains the double digit
            while(num == 0 && sum > 9){
                num = sum;
                sum = 0;
            }
        }
        
        return sum;
    }
}


// METHOD 2:

// TIME: O(1).

// SPACE: O(1).


class Solution {
    public int addDigits(int num) {
        
        if(num == 0) return 0;
        
        if(num % 9 == 0) return 9;
        
        return num % 9;
    }
}