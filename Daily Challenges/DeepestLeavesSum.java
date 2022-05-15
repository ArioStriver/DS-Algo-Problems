/*
Given the root of a binary tree, return the sum of values of its deepest leaves.
 
Example 1:

Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]

Output: 15

Example 2:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]

Output: 19


METHOD 1:
*/

class Solution {
    
    public int deepestLeavesSum(TreeNode root) {
        
        if(root == null) return 0;
        
        // 1. first calculating the maximum depth 
        int maxDepth = dfs(root);
        
        // 2. then again traverse the tree and calculate the sum of the deepest level
        return calculateSum(root, maxDepth);
    }
    
    private int dfs(TreeNode root) {
        
        if(root == null) return 0;
        
        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }
    
    private int calculateSum(TreeNode root, int maxDepth) {
        
        if(root == null) return 0;
        
        if(maxDepth == 1) {
            return root.val;
        }
        
        maxDepth--;
        int leftSum = calculateSum(root.left, maxDepth);
        int rightSum = calculateSum(root.right, maxDepth);
        
        return leftSum + rightSum;
    }
}

// METHOD 2: (LEVEL ORDER TRAVERSAL)

class Solution {
    
    public int deepestLeavesSum(TreeNode root) {
        
        if(root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        
        int depestLevelSum = 0;
        
        // finding the sum of each and every level
        while(!q.isEmpty()) {
            
            int size = q.size();
            depestLevelSum = 0;
            
            // traversing in each and every level
            while(size-- > 0) {
                
                TreeNode currNode = q.poll();
                depestLevelSum += currNode.val;       // calculating the some of each level
                
                if(currNode.left != null) q.offer(currNode.left);
                
                if(currNode.right != null) q.offer(currNode.right);
            }
        }
        
        return depestLevelSum;
    }
}


// METHOD 3:

class Solution {
    
    private int maxLevel = 0;
    private int depestLevelSum = 0;
    
    public int deepestLeavesSum(TreeNode root) {
        
        if(root == null) return 0;
        
        calculateLevelSum(root, 0);
        
        return depestLevelSum;
    }
    
    private void calculateLevelSum(TreeNode root, int level) {
        
        if(root == null) return;
        
        // if current level > maxLevel, it means it is not the depth
        if(level > maxLevel) {
            depestLevelSum = 0;
            maxLevel = level;
        }
        
        // if they are equal then it means that current level is the deepest level
        if(level == maxLevel) {
            depestLevelSum += root.val;
        }
        
        calculateLevelSum(root.left, level + 1);
        calculateLevelSum(root.right, level + 1);
    }
}