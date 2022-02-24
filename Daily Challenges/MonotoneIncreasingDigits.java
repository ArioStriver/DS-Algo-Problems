/*
An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.

Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.

Example 1:

Input: n = 10
Output: 9

Example 2:

Input: n = 1234
Output: 1234

Example 3:

Input: n = 332
Output: 299
 
Constraints:

0 <= n <= 10^9

METHOD:
	APPROACH:
		One initial thought that comes to mind is we can always have a candidate answer of d999...9 (a digit 0 <= d <= 9 followed by some number of nines.) For 
		example if N = 432543654, we could always have an answer of at least 399999999.

		We can do better. For example, when the number is 123454321, we could have a candidate of 123449999. It seems like a decent strategy is to take a 
		monotone increasing prefix of N, then decrease the number before the "cliff" (the index where adjacent digits decrease for the first time) if it exists, 
		and replace the rest of the characters with 9s.

		When does that strategy fail? If N = 333222, then our strategy would give us the candidate answer of 332999- but this isn't monotone increasing. However, 
		since we are looking at all indexes before the original first occurrence of a cliff, the only place where a cliff could exist, is next to where we just 
		decremented a digit.

		Thus, we can repair our strategy, by successfully morphing our answer 332999 -> 329999 -> 299999 with a linear scan.

		We'll find the first cliff S[i-1] > S[i]. Then, while the cliff exists, we'll decrement the appropriate digit and move i back. Finally, we'll make the 
		rest of the digits 9s and return our work.

		We can prove our algorithm is correct because every time we encounter a cliff, the digit we decrement has to decrease by at least 1. Then, the largest 
		possible selection for the rest of the digits is all nines, which is always going to be monotone increasing with respect to the other digits occurring 
		earlier in the number.

TIME: O(N), N is the length of the digit.

SPACE: O(N).
*/

class Solution {
    public int monotoneIncreasingDigits(int n) {
        
        // converting the interger into character array
        char[] ch = String.valueOf(n).toCharArray();
        
        
        // s1: finding the cliff(the index where the starts decreasing)
        int i = 1;
        
        while(i < ch.length && ch[i-1] <= ch[i]){
            i++;
        }
        
        // s2: decrement the value of adjacent digits of the cliff for the first time
        // keep checking whether there are any more cliff present in it's left side or not
        while(i > 0 && i < ch.length && ch[i-1] > ch[i]){
            
            // decrement the previous value
            ch[i-1]--;
            i--;
        }
        
        // s3: now replacing rest of the digits with 9
        for(int j = i + 1; j < ch.length; j++){
            ch[j] = '9';
        }
        
        return Integer.valueOf(String.valueOf(ch));
    }
}