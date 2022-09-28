/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []

Example 3:

Input: head = [1,2], n = 1
Output: [1]


METHOD 1:

TIME: O(2N).

SPACE: O(1).
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        int count1 = 0;
        ListNode curr = head;
        
        // counting the total no. of nodes in a list
        while(curr != null) {
            count1++;
            curr = curr.next;
        }
        
        int count2 = 1;
        curr = head;
        
        // if the nth node is the first node, then we have to shift the head pointer
        if((count1 - n) == 0) {
            head = head.next;
        }
        // otherwise go till (count - n), and point the current's next pointer to the next of next
        else {
            while(count2 != (count1-n)) {
                curr = curr.next;
                count2++;
            }
            
            curr.next = curr.next.next;
        }
        
        return head;
    }
}

/*
METHOD 2:

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        //Move fast in front so that the gap between slow and fast becomes n
        // First move the first pointer N steps ahead
        for(int i = 1; i <= n; i++){
            fast = fast.next;
        }
        
        // now if the fast pointers next is not null
        // then simply move the fast and slow pointer one by one untill the fast pointer next becomes null
        //Move fast to the end, maintaining the gap
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        //Skip the desired node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}