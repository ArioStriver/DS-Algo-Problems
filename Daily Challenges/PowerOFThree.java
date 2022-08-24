/*
Given an integer n, return true if it is a power of three. Otherwise, return false.

An integer n is a power of three, if there exists an integer x such that n == 3x.

Example 1:

Input: n = 27
Output: true

Example 2:

Input: n = 0
Output: false

Example 3:

Input: n = 9
Output: true
 
Constraints:

-2^31 <= n <= 2^31 - 1


METHOD 1:
*/

class Solution {
    public boolean isPowerOfThree(int n) {
        
        if(n <= 0) return false;
        
        double num = Math.log(n) / Math.log(3);
        
        //System.out.println(num);
        
        return n == Math.pow(3, Math.ceil(num));
    }
}

// METHOD 2:

class Solution {
    public boolean isPowerOfThree(int n) {
        
        // Knowing the limitation of n, we can now deduce that the maximum value of n that is also 
        // a power of three is 1162261467. We calculate this as:
        // 3⌊log3MaxInt⌋ = 3⌊19.56⌋ = 3^19 = 1162261467
        // Therefore, the possible values of n where we should return true are 3^0, 3^13 ... 3^{19}3.
        // Since 3 is a prime number, the only divisors of 3^19 are 3^0, 3^1 ... 3^19 
        // therefore all we need to do is divide 3^19 by n. A remainder of 0 means n is a 
        // divisor of 3^19 and therefore a power of three.

        return n > 0 && 1162261467 % n == 0;
    }
}