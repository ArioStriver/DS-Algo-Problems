// Q. All Elements in Two Binary Search Trees

//Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

//Example 1:

//Input: root1 = [2,1,4], root2 = [1,0,3]
//Output: [0,1,1,2,3,4]


//Example 2:

//Input: root1 = [1,null,8], root2 = [8,1]
//Output: [1,1,8,8]
 

//Constraints:

//The number of nodes in each tree is in the range [0, 5000].
//-10^5 <= Node.val <= 10^5


// METHOD 1:
//	APPROACH:
//		The 1st way is get the values in an array and sort that array.

// TIME: O( M + N + (M + N)log(M + N)) = O((M + N)log(M + N)), where M and N is length of the first and second tree.


class AllElementsInBST {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        List<Integer> res = new ArrayList<>();
        
        dfs(root1, res);
        dfs(root2, res);
        
        Collections.sort(res);
        
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> res){
        
        if(root == null) return;
        
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }
}


// METHOD 2:
//	APPROACH:
//		The 2nd way to solve this problem is we will traverse InOrderly and store the value of root1 tree in different array & root2 tree value in another array. Now what we have 
//		to do is, compare there values and merge them in another array. We will get our answer.

// TIME: O((M + N) + (M + N)) = O(M + N)

// SPACE: O(M + N).

//		But to solve the problem we will use more optimize way instead of using Arrays, we will use Stack.

// So, here in the given time a stack is holding at Max 3 elements which is proportional to depth of the tree

// TIME: is O(M + N). 

// SPACE: is O(H1 + H2).


class AllElementsInBST {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        List<Integer> ans = new ArrayList<>();
        
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        
        // inorder traversal
        // we will put all the elements in our stack until reach the leaf node
        // of both the root1 & root2 tree. First left, then right
        while(root1 != null || root2 != null || !st1.isEmpty() || !st2.isEmpty()){
            
            // filling our stack with all left values of root1 tree
            while(root1 != null){
                st1.push(root1);
                root1 = root1.left;
            }
            
            // filling our stack with all left values of root2 tree
            while(root2 != null){
                st2.push(root2);
                root2 = root2.left;
            }
            
            // either stack2 is empty we will pop all values from stack1 straight away but,
            // if stack2 is not empty then we will get their peek values
            // of both the stack1 & stack2 and compare them
            // if stack1 value is less then, add it into our result & move to right 
            if(st2.isEmpty() || !st1.isEmpty() && st1.peek().val <= st2.peek().val){
                
                root1 = st1.pop();
                ans.add(root1.val);
                root1 = root1.right;
            }
            else{
                root2 = st2.pop();
                ans.add(root2.val);
                root2 = root2.right;
            }
        }
        
        return ans;
    }
}








