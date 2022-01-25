// Q. Min Cost Climbing Stairs

//You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

//You can either start from the step with index 0, or the step with index 1.

//Return the minimum cost to reach the top of the floor.

 
//Example 1:

//Input: cost = [10,15,20]
//Output: 15
//Explanation: You will start at index 1.
//- Pay 15 and climb two steps to reach the top.

//The total cost is 15.


//Example 2:

//Input: cost = [1,100,1,1,1,100,1,1,100,1]
//Output: 6
//Explanation: You will start at index 0.
//- Pay 1 and climb two steps to reach index 2.
//- Pay 1 and climb two steps to reach index 4.
//- Pay 1 and climb two steps to reach index 6.
//- Pay 1 and climb one step to reach index 7.
//- Pay 1 and climb two steps to reach index 9.
//- Pay 1 and climb one step to reach the top.

//The total cost is 6.


//METHOD 1:

class Solution {
    public int minCostClimbingStairs(int[] cost) {
      
        int n = cost.length;
        
        // we can either start from the step with index 0, or the step with index 1
        // whicever will give us minimum will take that one 
        return Math.min(minCost(cost, n - 1), minCost(cost, n - 2));
    }
    
    private int minCost(int[] cost, int n){
        
        if(n < 0) return 0;
        
        // base case
        if(n == 0 || n == 1) return cost[n];
        
        // taking the minimum from the left and right and add the curre t cost
        return cost[n] + Math.min(minCost(cost, n - 1), minCost(cost, n - 2));
    }
}


//METHOD 2:(USING MEMOIZATION)

class Solution {
    public int minCostClimbingStairs(int[] cost) {
      
        int n = cost.length;
        int[] memo = new int[n+1];
        
        Arrays.fill(memo, -1);
        
        // we can either start from the step with index 0, or the step with index 1
        // whicever will give us minimum will take that one 
        return Math.min(minCost(cost, n - 1, memo), minCost(cost, n - 2, memo));
    }
    
    private int minCost(int[] cost, int n, int[] memo){
        
        if(n < 0) return 0;
        
        // base case
        if(n == 0 || n == 1) return cost[n];
        
        if(memo[n] != -1) return memo[n];
        
        // taking the minimum from the left and right and add the curre t cost
        return memo[n] = cost[n] + Math.min(minCost(cost, n - 1, memo), minCost(cost, n - 2, memo));
    }
}


//METHOD 3:(USING DYNAMIC PROGRAMMING)

//TIME: O(N).

//SPACE: O(N).

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        int n = cost.length;
        
        int[] dp = new int[n];
        
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        for(int i = 2; i < n; i++){
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }
        
        return Math.min(dp[n-1], dp[n-2]);
    }
}


//METHOD 4:(SPACE OPTIMIZED)

//TIME: O(N).

//SPACE: O(1).

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        int n = cost.length;
        int prev1 = cost[0], prev2 = cost[1];
        
        for(int i = 2; i < n; i++){
            int currentSum = cost[i] + Math.min(prev1, prev2);
            
            prev1 = prev2;
            prev2 = currentSum;
        }
        
        return Math.min(prev1, prev2);
    }
}
