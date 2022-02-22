/*

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"

Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:

Input: num = "10200", k = 1
Output: "200"

Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:

Input: num = "10", k = 2
Output: "0"

Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 
Constraints:

1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.


METHOD:(USING STACK)

TIME: O(N).

SPACE: O(N).
*/


class Solution {
    public String removeKdigits(String num, int k) {
        
        // here we are using stack to store the elements in ascending order
        Stack<Character> st = new Stack<>();
        
        // Edge case
        // if k == num.length, it means 0 is the answer
        if(k == num.length())
            return "0";
       
 
        // CASE 1: NORMAL NUMBER
        // traversing the string
        for(char nm : num.toCharArray()){
            
            /*  1. if the stack is empty or not
                2. here we are trying to remove all the k maximas form left to right
                   bcz we know that the element in the MSD has most weightage & as we moving
                   from left to right the weightage of number decreases
                3. Wether there are any more k digits remains to remove
                4. When we get a dip means minima we try to remove all elements which are greter than
                   the current minima */
            
            while(!st.isEmpty() && k > 0 && st.peek() > nm){
                st.pop();
                k--;
            }
            
            /* otherwise if the stack is not empty or the current number is not zero then 
               push the current number into the stack, this case will help us to remove the leading zeros */
            
            if(!st.isEmpty() || nm != '0'){
                st.push(nm);
            }
        }
        
        
        /* CASE 2: GIVEN NUMBER IS IN INCREASING ORDER OR ALL NUMBERS ARE SAME
                  In that case k digits are not removed 
                  so we have to remove the largest k digits from stack top*/
        
        while(!st.isEmpty() && k > 0){
            st.pop();
            k--;
        }
        
        /* CASE 3: after removing the K digits from the stack if the stack is empty it means 
                   the remaing element/s is zero 
                   e.g = 1001, k = 2 , st = 00 */
        if(st.isEmpty()){
            return "0";
        }
        
        
        // poping the rest of the elements after removing the k digits & covert it to string
        StringBuilder res = new StringBuilder();
        
        while(!st.isEmpty()){
            res.append(st.pop());
        }
        
        return res.reverse().toString();
    }
}