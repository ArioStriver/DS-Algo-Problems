/*
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also 
counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.

Example 1:

Input: root = [1,3,2,5,3,null,9]
Output: 4

Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:

Input: root = [1,3,null,5,3]
Output: 2

Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:

Input: root = [1,3,2,5]
Output: 2

Explanation: The maximum width existing in the second level with the length 2 (3,2).


METHOD:(USING LEVEL-ORDER TRAVERSAL)

TIME: O(N), where N is the number of Nodes.

SPACE: O(N), for using the queue data structure.
*/

class Pair{
    TreeNode node;
    int num;
    
    Pair(TreeNode _node, int _num){
        node = _node;
        num = _num;
    }
}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        
        Queue<Pair> que = new LinkedList<>();
        
        int maxWidth = 0;
        
        // first push the root elemt
        que.offer(new Pair(root, 0));
        
        while(!que.isEmpty()){
            
            int size = que.size();
            
            // extracting the minimum index in the current level
            // and it is always the most left node in the current level
            // bcz we are indexing the nodes in each level staring from 0...
            // make id starting from 0
            int min = que.peek().num;
            
            int first = 0, last = 0;
            
            for(int i = 0; i < size; i++){
                
                // to avoid the overflow we used this
                int cur_id = que.peek().num - min;
                
                // extracting the current node
                TreeNode currNode = que.peek().node;
                que.poll();
                
                if(i == 0) first = cur_id;
                
                else if(i == size - 1) last = cur_id;
                
                // indexing the left and right node of the currrent node
                // for left: (2 * i + 1)
                if(currNode.left != null){
                    que.offer(new Pair(currNode.left, 2*cur_id + 1));
                }
                
                // for rigt : (2 * i + 2)
                if(currNode.right != null){
                    que.offer(new Pair(currNode.right, 2*cur_id + 2));
                }
            }
            
            // finding the maximum width
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        
        return maxWidth;
    }
}