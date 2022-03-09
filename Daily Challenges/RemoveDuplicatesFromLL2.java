/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list 
sorted as well.

Example 1:

Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:

Input: head = [1,1,1,2,3]
Output: [2,3]


METHOD:

TIME: O(N), where N is the no. of nodes.

SPACE: O(1).
*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
     
        // here we are using dummy node as the start of the linked list bcz 
        // if we duplicate is present in the head node
        ListNode dummyNode = new ListNode(0, head);
        
        // prev pointer to track current node's predecessor
        ListNode prev = dummyNode, curr = head;
        ListNode nextN = null;
        
        while(curr != null){
            
            // current's next node
            nextN = curr.next;
            
            // if it's a beginning of duplicates sublist 
            // skip all duplicates
            if(nextN != null && curr.val == nextN.val){
                
                // move till the end of duplicates sublist
                while(nextN != null && curr.val == nextN.val){
                    nextN = nextN.next;
                }
                
                // skip all duplicates
                prev.next = nextN;
            }
            else{
                prev = curr;  // move the pointer
            }
            
            // move the current pointer
            curr = nextN;
        }
        
        return dummyNode.next;
    }
} 