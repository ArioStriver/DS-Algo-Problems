/*
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes 
themselves may be changed.)

Example 1:

Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:

Input: head = []
Output: []

Example 3:

Input: head = [1]
Output: [1]

METHOD:

TIME: O(N), bcz we are traversing the array only once and N is the total number of nodes.

SPACE: O(1).
*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        // here we have to swap nodes in a pair
        // a pair contains two elements
        // so if any element is = null then we can't swap them
        // in order to perform swap operation b/w two nodes of a pair the two nodes value should 
        // be = not null
        
        // first we create a dummy node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // now taking two pointer
        ListNode prev = dummy;
        ListNode curr = head;
        
        // if both the values are not != null then proceed further
        while(curr != null && curr.next != null){
            
            // save the start of the next pair and second node in the current pair
            ListNode nextPair = curr.next.next;
            ListNode second = curr.next;
            
            // now reverse this pair
            second.next = curr;
            curr.next = nextPair;
            prev.next = second;
            
            // update pointer 
            prev = curr;
            curr = nextPair;    
        }
        
        return dummy.next;
    }
}