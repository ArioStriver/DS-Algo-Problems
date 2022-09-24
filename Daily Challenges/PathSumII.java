/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a 
list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]

Explanation: There are two paths whose sum equals targetSum: 5 + 4 + 11 + 2 = 22, 5 + 8 + 4 + 5 = 22

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:

Input: root = [1,2], targetSum = 0
Output: []


METHOD:(BACKTRACKING)

TIME: O(N), where N is the total no. of nodes.

SPACE: O(H), where H is the hright of the tree, may be O(N) in worst case senario for skewed BT.
*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
      
        List<List<Integer>> paths = new ArrayList<>();
        
        generateSum(root, targetSum, paths, new ArrayList<>());
        
        return paths;
    }
    
    private void generateSum(TreeNode root, int targetSum, List<List<Integer>> paths, List<Integer> temp) {
        
	  // if null return
        if(root == null)
            return;
        
	  // else add the current value in the list
        temp.add(root.val);
        
	  // if the remaining sum == current root value and it is a leaf node then add this path
        if(targetSum == root.val && root.left == null && root.right == null) {
            paths.add(new ArrayList<>(temp));
        }
        
 	  // otherwise move to left or right
        generateSum(root.left, targetSum - root.val, paths, temp);
        generateSum(root.right, targetSum - root.val, paths, temp);
        
	  // trying out all possible combonation by removing the last element
        temp.remove(temp.size() - 1);
    }
}