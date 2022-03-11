/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. 
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]

Example 2:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]

Example 3:

Input: l1 = [0], l2 = [0]
Output: [0]


METHOD 1:
	APPROACH:
		Basically reverse both the list first and perform the add operation and then again reverse the answer list.

TIME: O(M + N) for reversing the both list + O(max(M, N)) for performing the add operation.

SPACE: O(max(M, N)).
*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // first here we reversing both the list
        ListNode head1 = reverse(l1);
        ListNode head2 = reverse(l2);
        
        // after reversing we basically perform the adding operation
        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        
        int carry = 0;
        
        // there are three conditions
        while(head1 != null || head2 != null || carry > 0){
            
            int sum = 0;
            
            if(head1 != null){
                sum += head1.val;
                head1 = head1.next;
            }
            
            if(head2 != null){
                sum += head2.val;
                head2 = head2.next;
            }
            
            // adding the sum with the carry
            sum += carry;
            
            // coverting the current value into the node
            ListNode val = new ListNode(sum % 10);
            prev.next = val;
            prev = prev.next;
            carry = sum / 10;
        }
        
        // now finally we reverse the answer 
        return reverse(dummyNode.next);
    }
    
    private ListNode reverse(ListNode root){
        
        ListNode curr = root, prev = null, nextN = null;
        
        while(curr != null){
            nextN = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextN;
        }
        return prev;
    }
}


// METHOD 2:(USING STACK)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        Stack<ListNode> st1 = new Stack<>();
        Stack<ListNode> st2 = new Stack<>();
        
        // pushing the elements of list1 and list2 in the stack
        while(l1 != null){
            st1.push(l1);
            l1 = l1.next;
        }
        
        while(l2 != null){
            st2.push(l2);
            l2 = l2.next;
        }
        
        // now we perform the adding operation
        ListNode dummy = null;
        int carry = 0;
        
        while(!st1.empty() || !st2.empty() || carry > 0){
            
            int a = 0, b = 0;
            
            if(!st1.empty()){
                a = st1.pop().val;
            }
            
            if(!st2.empty()){
                b = st2.pop().val;
            }
            
            int sum = (a + b + carry);
            
            ListNode val = new ListNode(sum % 10);
            val.next = dummy;       // making the final answer from last to first
            dummy = val;
            carry = sum / 10;
        }
        
        return dummy;
    }
}


// METHOD 3:(WITHOUT REVERSING AND STACK)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        int s1 = size(l1);
        int s2 = size(l2);
        ListNode dummy = null;
        
        while(l1 != null || l2 != null){
            
            int a = 0, b = 0;
            
            if(s1 >= s2){
                a = l1 != null ? l1.val : 0;
                l1 = l1.next;
                s1--;
            }
            
            // Comparing with s1 + 1 since s1 might be decremented previously
            if(s2 >= s1 + 1){
                b = l2 != null ? l2.val : 0;
                l2 = l2.next;
                s2--;
            }
            
            // Creating the resulting list in the reversed order.
            ListNode val = new ListNode(a+b);
            val.next = dummy;
            dummy = val;
        }
        
        /* Next we are going to normalize the resulting list. Now each node contains the addtion 
        of two nodes. But may be there are nodes those values are greater than 10, in that case 
        we are going to put the remainder value on that node and carry the forward */
        
        int carry = 0;
        ListNode curr = dummy;
        dummy = null;
        
        while(curr != null){
            
            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;
            
            // moving the pointer
            ListNode temp = curr.next;
            curr.next = dummy;
            dummy = curr;
            curr = temp;
        }
        
	// if carry is > 0, it means we need to add one more extra node with 1.
        if(carry > 0){
            curr = new ListNode(1);
            curr.next = dummy;
            dummy = curr;
        }
        
        return dummy;
    }
    
    private int size(ListNode root){
        
        int len = 0;
        while(root != null){
            len++;
            root = root.next;
        }
        return len;
    }
}