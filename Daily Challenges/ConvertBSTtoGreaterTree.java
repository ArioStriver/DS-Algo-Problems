/*
Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all 
keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
Example 1:

Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Example 2:

Input: root = [0,null,1]
Output: [1,null,1]

METHOD:

TIME: O(N), where N is the no. of nodes.

SPACE: O(H), H is the height of the tree.
*/

// USING GLOBAL VARIABLE

class Solution {
    
    private int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
      
        if(root == null){
            return null;
        }
        
        // Reverse Inorder traversal
        convertBST(root.right);
        
        // updating the node's value
        root.val = root.val + sum;
        sum = root.val;   // updating the sum
        
        convertBST(root.left);
        
        return root;
    }
}


// USING LOCAL VARIABLE

class Solution {
    
    public TreeNode convertBST(TreeNode root) {
      
        convert(root, 0);
        return root;
    }
    
    private int convert(TreeNode root, int sum){
        
        if(root == null){
            return sum;
        }
        
        sum = convert(root.right, sum);
        sum += root.val;
        root.val = sum;
        
        // bcz it contains the updated sum upto left
        return convert(root.left, sum);
    }
}