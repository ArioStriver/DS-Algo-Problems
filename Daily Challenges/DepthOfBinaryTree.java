/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: 3


Example 2:

Input: root = [1,null,2]
Output: 2

METHOD:(USING DFS)

TIME: O(N), total number of nodes

SPACE: O(1) + O(H), where H is the height of the Binary tree;
*/

class Solution {
    public int maxDepth(TreeNode root) {
        
        if(root == null) return 0;
        
        int left = maxDepth(root. left);
        int right = maxDepth(root.right);
        
        return 1 + Math.max(left, right);
    }
}