//Q. Climbing Stairs

//You are climbing a staircase. It takes n steps to reach the top.

//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

//Example 1:

//Input: n = 2
//Output: 2

//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps


//Example 2:

//Input: n = 3
//Output: 3

//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step


METHOD 1:(UISNG RECURSION)(GIVES TLE)

class Solution {
    public int climbStairs(int n) {
        
        // if we are at 0th step then there is pnly one way
        // also if we are at 1st step then we need only 1 jump to reach 0 th step
        if(n <= 1) return 1;
        
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}


// METHOD 2:(UISNG MEMOIZATION)

class Solution {
    public int climbStairs(int n) {
        
        int[] memo = new int[n+1];
        
        Arrays.fill(memo, -1);
        
        return distinctWays(n, memo);
    }
    
    private int distinctWays(int n, int[] memo){
        
        // if we are at 0th step then there is pnly one way
        // also if we are at 1st step then we need only 1 jump to reach 0 th step
        if(n <= 1) return 1;
        
        // whether the vaule is previously computed or not
        // if so return
        if(memo[n] != -1) return memo[n];
        
        return memo[n] = climbStairs(n - 1) + climbStairs(n - 2);
    }
}


// METHOD 3:(UISNG DYNAMIC PROGRAMMING)

// TIME: O(N).

// SPACE: O(N).

class Solution {
    public int climbStairs(int n) {
        
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}


// METHOD 4: (SPACE OPTIMIZED)

// TIME: O(N).

// SPACE: O(1).

class Solution {
    public int climbStairs(int n) {
        
        //int[] dp = new int[n+1];
        
        int prev1 = 1;
        int prev2 = 1;
        
        for(int i = 2; i <= n; i++){
            int curSum = prev1 + prev2;
            
            prev2 = prev1;
            prev1 = curSum;
        }
        
        return prev1;
    }
}

 