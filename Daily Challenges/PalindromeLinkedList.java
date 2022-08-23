/*
Given the head of a singly linked list, return true if it is a palindrome.

Example 1:

Input: head = [1,2,2,1]
Output: true

Example 2:

Input: head = [1,2]
Output: false
 
Constraints:

The number of nodes in the list is in the range [1, 10^5].
0 <= Node.val <= 9


METHOD 1:(USING STACK)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        
        Stack<Integer> st = new Stack<>();
        ListNode curr = head;
        
        while(curr != null) {
            st.push(curr.val);
            curr = curr.next;
        }
        
        while(!st.isEmpty()) {
            
            if(st.pop() != head.val) {
                return false;
            }
            head = head.next;
        }
        
        return true;
    }
}

/*
METHOD 2:

TIME: O(N/2) + O(N/2) + O(N/2).

SPACE: O(1).
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
       
        // BASE CASE:
        // if there is only one element
        if(head == null || head.next == null) return true;
        
        // STEP 1:
        // Finding the middle element
        //   a.) for even length there is two middle elements, so we need the first middle element
        //   b.) for odd length there is one middle element
        ListNode fast = head, slow = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // STEP 2:
        // after geting the middle elemnt we need to reverse the right part of the middle element
        slow.next = reverseList(slow.next);
        
        // STEP 3:
        // currently the slow pointer is standing in the middle element
        // so to check whether the left and right half both have same value we need to do the following
        // 3. Now set the slow pointer to the head of the right half
        slow = slow.next;
        
        
        //STEP 4:
        // now check for whether they are palindrome or not by comparing the values in the left and rigth half
        while(slow != null){
            
            // if they are not equal
            if(head.val != slow.val){
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        
        return true;
    }
    
    private ListNode reverseList(ListNode head){
        
        ListNode prev = null, next = null;
        
        while(head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}