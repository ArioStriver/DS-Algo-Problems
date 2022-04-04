/*
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]


METHOD 1:(USING EXTRA SPACE)

TIME: O(N) + O(N) == O(N), first O(N) for putting the elements into the list and second O(N) for creating the final linked list.

SPACE: O(N), N is the no. of elements.
*/

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        List<ListNode> nums = new ArrayList<>();
        
        ListNode curr = head;
        
        // storing the elements into the list
        while(curr != null){
            nums.add(curr);
            curr = curr.next;
        }
        
        // swapping the values
        ListNode temp = nums.get(k-1);
        nums.set(k-1, nums.get(nums.size() - k));
        nums.set(nums.size()-k, temp);
        
        // now here creating the final linked list after swapping the values
        ListNode dummy = new ListNode(0);
        curr = dummy;
        
        int i = 0;
        while(i < nums.size()){
            ListNode val = nums.get(i);
            // ListNode node = new ListNode(val);
            curr.next = val;
            curr = val;
            i++;
        }
        
        curr.next = null;
        
        return dummy.next;
    }
}

/*
METHOD 2:(UISNG TWO POINTER)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        
        ListNode firstNode = null, secondNode = null;
        ListNode fast = head, slow = head;
        
        // first finding the (k-1)th node from the left(0-index)
        // starting i from 1 bcz we already standing at the first node
        for(int i = 1; i < k; i++){
            fast = fast.next;
        }
        
        // Save the node for swapping
        firstNode = fast;
        
        // Now finding the second node for swapping
        // Move until the end of the list
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        
	// save the second node
        secondNode = slow;
        
        // swap the first and the second node values
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
        
        return head;
    }
}