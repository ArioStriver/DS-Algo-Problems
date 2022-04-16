/*
Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the 
tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven 
that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

Example 1:

Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Example 2:

Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]

METHOD:
	APPROACH:
		In this situation, all we're doing is collapsing any branches that fall outside our given range from low L to high H, which should be simple enough.

		The first thing we almost always need to deal with in a recursive function is the endpoint, so if root R is null, we should stop the recursion and 
		return R back up. Then, we have a branch, depending on whether or not the value of R is < L or > H. If the value is too low, we want to pull up the 
		branch to the right and continue the recursion, and vice versa if the value is too high. Otherwise, we just want to continue the recursion down each 
		branch.

TIME: O(N), N is the total no. of nodes.

SPACE: O(H), H is the height of the binart tree but at worst case it will be O(N).
*/

class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        
        // base case
        if(root == null) return null;
        
        /* if the root's value > high, don't need to visit the right subtree of root
           bcz there we'll get values that is greater than high */
        if(root.val > high){
            return trimBST(root.left, low, high);
        }
        
        /* if the root's value < low, don't need to visit the left subtree of root
           bcz there we'll get values that is lesser than low */
        if(root.val < low){
            return trimBST(root.right, low, high);
        }
        
        // otherwise the value is in the range
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}