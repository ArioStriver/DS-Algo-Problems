/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single 
digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]

Explanation: 342 + 465 = 807.

Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


METHOD:

TIME: O(max(M, N)), where M is the length of the list1 and N is the length of the list2.

SPACE: O(max(M, N)), The length of the new list is at most max(M, N) + 1.
*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // creating a dummy node to create the answer
        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;
        
        int carry = 0;
        
        // there are three conditions
        while(l1 != null || l2 != null || carry > 0){
            
            int sum = 0;
            
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            
            // adding the sum with the carry
            sum += carry;
            
            // coverting the current value into the node
            ListNode val = new ListNode(sum % 10);
            prev.next = val;
            prev = prev.next;
            carry = sum / 10;
        }
        
        return dummyNode.next;
    }
}