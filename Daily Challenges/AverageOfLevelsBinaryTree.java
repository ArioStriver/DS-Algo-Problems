/*
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
 
Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [3.00000, 14.50000, 11.00000]

Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].

Example 2:

Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1


METHOD:(USING LEVEL-ORDER TRAVERSAL)

TIME: O(N), where N is the toal no. of nodes.

SPACE: O(M), where M is the maximum no. of nodes in a level.
*/

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        
        // level order traversal
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        
        while(!qu.isEmpty()) {
            
            // counting the no. of nodes in each level
            int count = qu.size();
            double sum = 0;
            
            // calculating the sum for each level and pushing their children into the queue
            for(int i = 0; i < count; i++) {
                TreeNode curr = qu.poll();
                sum += curr.val;
                
                if(curr.left != null) qu.offer(curr.left);
                
                if(curr.right != null) qu.offer(curr.right);
            }
            
            // caculating the average
            double avg = (sum / count);
            res.add(avg);
        }
        
        return res;
    }
}