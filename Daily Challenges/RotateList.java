/*
Given the head of a linked list, rotate the list to the right by k places.

Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:

Input: head = [0,1,2], k = 4
Output: [2,0,1]


METHOD 1:(BRUTE FORCE)
	APPROACH:
		1. Outer loop is for number of K elements from right we need.

		2. After that we find the last node of the list and then append it to the head of the list

TIME: O(K * N), where K is the no. of K elements from right and N is the total no. of elements in the list.

SPACE: O(1).
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null || head.next == null) return head;
        
        for(int i = 1; i <= k; i++){
            
            ListNode curr = head;
            ListNode prev = null;
            
            while(curr.next != null){
                prev = curr;
                curr = curr.next;
            }
            
            // make the last node to point to the head of the list
            curr.next = head;
            
            // now making the last node head of the list
            head = curr;
            
            // previous of last node will point to null
            prev.next = null;
        }
        return head;
    }
}


/*
METHOD 2:(OPTIMAL)
	APPROACH:
		First there are two cases --

		case 1:  if K < len, then there is no problem.
		case 2:  if K >= len, e.g: K = 1000, len = 5

		So if we rotate the linked list of length 5 1000 times then it will definitely increase the time complaxity. So to avoid that what we can do?

		If we look closely we say that if we rotate the linked list in multiple of length, then we will definitely get back the same linked list. 
		E.g: len = 5, then multiple of 5 is 5, 10, 15, 20 . . . . 
		Now for E.g: K = 12, len = 5, then the nearest multiple of len = 5 is 10. So we know that if we rotate it 10 times we will get bck the original linked 
		list & now the remainder is 2. So that many no. of auto rotation we will going to do in order to get our desired output.

		for K >= len, we have to do [K = K % len].

		Steps:

			1. We have to count the no. of nodes in the linked list

			2. point lastNode.next = head

			3. Get the (len - K) th node and point it to null.

TIME: O(N) for counting the no. of nodes + O(N - (N % K)) bcz we traverse that no. of nodes to make the next of (N - K)th node to NULL == O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null || head.next == null) return head;
        
        int len = 1;
        ListNode curr = head;
        
        // s1: counting the number of nodes
        while(curr.next != null){
            len++;
            curr = curr.next;
        }
        
        // s2: point lastNode.next = head
        curr.next = head;
        k = k % len;
        
        // s3: we need to point the (N - k)th node's next to null
        k = len - k;
        
        while(k > 0){
            curr = curr.next;
            k--;
        }
        
        // before doing anything we have to make the ((N - K) + 1)th node to head
        head = curr.next;
        
        // now make the next of the current to point null
        curr.next = null;
        
        return head;
    }
}