/*
Intersection of Two Linked Lists:

METHOD 1:(BRUTE FORCE)
	Approach:
		We can use two loops to traverse the list and check for equal reference. If there is any then return it otherwise return null

TIME: O(M*N), where M is the length of the List1 & N is the length of the list2.

SPACE: O(1).


METHOD 2:(HASHING)

TIME: O(N + M).

SPACE: O(N).
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        Set<ListNode> nums = new HashSet<>();
        
        while(headA != null) {
            nums.add(headA);
            headA = headA.next;
        }
        
        while(headB != null) {
            if(nums.contains(headB))
               return headB;
               
            nums.add(headA);
            headB = headB.next;
        }
        
        return null;
    }
}


/*
METHOD 3:(USING TWO POINTER)

TIME: O(N + M).

SPACE: O(1).
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if(headA == null || headB == null) return null;
        
        ListNode l1 = headA;
        ListNode l2 = headB;
        
        while(l1 != l2) {
            
            // if the l1 reaches the enf od list1 then reset it to the head of list2 and vice-versa
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        
        return l1;
    }
}