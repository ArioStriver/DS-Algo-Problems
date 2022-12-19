/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a 
warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


METHOD 1:(USING STACK, FIND THE NEXT GREATER ELEMENT APPROACH)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        int n = temperatures.length;
        int[] ans = new int[n];

        // bcz there is no warmer temperature in the future for the last day
        ans[n-1] = 0;

        Stack<Integer> st = new Stack<>();

        for(int i = n-1; i >= 0; i--) {

            // if the current temparature is greater than the previous days temperature
            // then remove the previous days temeperature until u get a temperature > current
            while(!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }

            if(st.isEmpty()) {
                ans[i] = 0;
            }
            else {
                ans[i] = (st.peek() - i);
            }

            // push the current temperature also
            st.push(i);
        }

        return ans;
    }
}

/*
METHOD 2:(SPACE OPTIMIZED)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int[] dailyTemperatures(int[] temp) {
        
        int n = temp.length;
        int hottestTemp = 0;
        int[] ans = new int[n];
        
        for(int i = n-1; i >= 0; i--){
            
            // if the cuurent day is the hottest day or not
            // if so it means that there is no day after the current day which is warmer
            int currentDayTemp = temp[i];
            if(currentDayTemp >= hottestTemp){
                hottestTemp = currentDayTemp;
                continue;
            }
            
            // why day = 1 bcz first we check whether the next day is the warmer day or not
            int day = 1;
            
            // if the next day is not the warmer day then 
            // we have to jump to the next greater element's index of the next element and so on
            while(temp[i + day] <= currentDayTemp){
                day += ans[i + day];
            }
            
            ans[i] = day;
        }
        
        return ans;
    }
}