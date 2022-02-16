/*
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]
 
Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 10^5
0 <= Node.val <= 100


METHOD 1:(USING ARRAY)

TIME: O(2N) == O(N).

SPACE: O(N).
*/

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
     
        List<Integer> li = new ArrayList<>();
        
	// first copy all the elements into the list
        ListNode curr = head;
        while(curr != null){
            li.add(curr.val);
            curr = curr.next;
        }
        
        int n = li.size();
        
	// swap the nodes
        int temp = li.get(k-1);
        li.set(k-1, li.get(n-k));
        li.set(n-k, temp);
        
        System.out.println(li);
        
	// again creating the new linked list
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        int i = 0;
        
        while(i < li.size()){
            prev.next = new ListNode(li.get(i));
            i++;
            prev = prev.next;
        }
        
       return dummy.next;
    }
}


/*
METHOD2:
	APPROACH:
		Our main task is to determine the locations of the nodes whose values are to be swapped. This can be done by traversing the list using two pointers - 
		fast and slow, with a certain distance between them. The pointers move one node at a time. The distance should be chosen such that:

		1. when slow is at head, fast points at the first node for swapping
		2. when fast is at the end, slow points at the second node for swapping

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode firstNode = null, secondNode = null;
        ListNode fast = head, slow = head;
        
        // s1: when slow is at head, fast points at the first node for swapping
        // i starts from 1 bcz we already standing on the head node
        for(int i = 1; i < k; i++){
            fast = fast.next;
        }
        
        firstNode = fast;
        
        // s2: when fast is at the end, slow points at the second node for swapping
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        secondNode = slow;
        
        // swap
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
        
        return head;
    }
}