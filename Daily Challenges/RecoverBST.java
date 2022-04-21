/*
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its 
structure.

Example 1:

Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]

Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]

Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


METHOD 1:(USING EXTRA SPACE)
	APPROACH:
		1. We will have the in-order traversal of the given BST stored in a list.
		2. We will sort this list by making a copy of this list.
		3. Then, we will compare both the lists to find the elements which have been swapped.
		4. After that, search the nodes in the tree and update the value of those nodes.

TIME: O(N) + O(NlogN) + O(N) + O(N) + O(N) == O(NlogN).

SPACE: O(N) + O(N) == O(N).
*/

class Solution {
    private void inorder(TreeNode root, List<Integer> arr) {
        
        if(root == null) return;
        
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }
    
    private void updateTreeElements(TreeNode root, int ele1, int ele2) {
        
        if(root == null) return;
        
        updateTreeElements(root.left, ele1, ele2);
        
        // updating both the elements 
        if(root.val == ele1) {
            root.val = ele2;
        }
        else if(root.val == ele2) {
            root.val = ele1;
        }
        
        updateTreeElements(root.right, ele1, ele2);
    }
    
    public void recoverTree(TreeNode root) {
        
        List<Integer> arr = new ArrayList<>();
        
        // 1. getting the inorder travesal of the given tree in a list
        inorder(root, arr);
        
        // 2. make a copy of the current list
        List<Integer> copyArr = new ArrayList<>(arr);
        
        // 3. sorting the copied list
        Collections.sort(copyArr);
        
        // 4. we will compare both the lists to find the elements which have been swapped
        for(int i = 0; i < copyArr.size(); i++) {
            if(arr.get(i) != copyArr.get(i)) {
                updateTreeElements(root, arr.get(i), copyArr.get(i));
                break;
            }   
        }
    }
}

/*
METHOD 2:(WITHOUT USING EXTRA SPACE)
	APPROACH:
		We know that the inorder traversal of a  tree will give us the elements in sorted order. So what we can do we can find the first and the second swapped value
		and simply replace their values. To find the swapped values we need three pointer one for first, one for second and one for previous value.
   		Also we need another pointer middle. Why lets's see below. But here we have to consider two cases ---

			CASE 1: Swapped values are not adjacent to each other.
				In this situation we are going to swap the first and last value, but also keep track of the value by which the first value create the 
				violation.

			CASE 2: Swapped values are adjacent to each other.
				In this situation we are not able to find any second violation, it means that the swapped values are adjacent to each other. Here the 
				middle pointer will work as the second value.

TIME: O(N).

SPACE: O(1), withour considering the stack space.
*/

class Solution {
    
    TreeNode firstN, lastN, middleN, previous;
    
    private void inorder(TreeNode root) {
        
        if(root == null) return;
        
        inorder(root.left);
        
        // if the previous value > curent value
        // it means that it breaks the sorted order
        if(previous != null && previous.val > root.val) {
            
            // check whether it is the first vaolation or not
            if(firstN == null) {
                firstN = previous;
                middleN = root;
            }
            // it is the second vaolation, mark this as last node
            else {
                lastN = root;
            }
        }
        
        // Mark this node as previous
        previous = root;
        
        inorder(root.right); 
    }
    
    public void recoverTree(TreeNode root) {
         
        firstN = lastN = middleN = previous = null;
        
        // first find the first and second value to be swapped
        inorder(root);
        
        // Now here we have to consider two cases
        // CASE 1: swapped nodes are not adjacent to eachother
        if(firstN != null && lastN != null) {
            int temp = firstN.val;
            firstN.val = lastN.val;
            lastN.val = temp; 
        }
        // CASE 2: swapped nodes are adjacent to eachother
        else if(firstN != null && middleN != null) {
            int temp = firstN.val;
            firstN.val = middleN.val;
            middleN.val = temp; 
        }
    }
}