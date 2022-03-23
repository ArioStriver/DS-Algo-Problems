/*
There is a broken calculator that has the integer startValue on its display initially. In one operation, you can:

multiply the number on display by 2, or
subtract 1 from the number on display.
Given two integers startValue and target, return the minimum number of operations needed to display target on the calculator.

Example 1:

Input: startValue = 2, target = 3
Output: 2

Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.

Example 2:

Input: startValue = 5, target = 8
Output: 2

Explanation: Use decrement and then double {5 -> 4 -> 8}.

Example 3:

Input: startValue = 3, target = 10
Output: 3
Explanation: Use double, decrement and double {3 -> 6 -> 5 -> 10}.
 
Constraints:

1 <= x, y <= 10^9


METHOD:
	we basically can create the target from the startvalue by multiplying and subtracting 2 & 1, but it will create lot of unnecessary numbers and the time 
	complexity will gradually increase.

	We're only allowed to do "Double" and "Decrement" operation to startValue,
	In the case of change startValue to target, considering target = startValue * (2^n) + 1, where n can be any given number.
	We can double startValue for n + 1 times, then, do decrement for startValue * (2^n) - 1 times, which make startValue = target.
	When n get bigger, which means we perform more double operations, the number of required decrement operation would increase exponentially in this case.

	On the other hand, if we change target to startValue,
	we can do operations to target which are exactly opposite to what we can do to startValue.
	That is:

	Division: Divide by 2
	Increment: Add by 1
	In the same case of target = startValue*(2^n) + 1
	more division operations we perform, the number of increment operation would decrease exponentially.
	Therefore, do as many division operations as we can which would lead to minumum number of operation needed to change target to startValue.

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    public int brokenCalc(int startValue, int target) {
     
        int ans = 0;
        
        while(target > startValue){
            
            // if even
            if(target % 2 == 0){
                target /= 2;
            }
            // odd
            else{
                target++;
            }
            
            ans++;
        }
        
        return ans + (startValue - target);
    }
}