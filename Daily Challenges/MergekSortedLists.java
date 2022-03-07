/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]

Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]

merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


METHOD 1:
	APROACH:
		Intuition: Compare all the K nodes and find the minimum from them and then Include it into the final answer.

		NOTE: here we don't know the number of nodes we have in each list

			Step1: First Compare all k node values and find the minimum from them.

			Step 2: After getting the minimum we have to include it in the final list

			Step 3: Atlast we have to replace the minimum value with its next value

TIME: O(N * K), if the total number of nodes is N then here we comparing them K times.

SPACE: O(1).
*/


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
     
        if(lists == null) return null;
        
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        
        while(true){
            
            ListNode minVal = null;
            int minIndex = -1;
            
            // s1: First Compare all k node values and find the minimum from them.
            for(int i = 0; i < lists.length; i++){
                
                // if null skip
                if(lists[i] == null) continue;
                
                // if greter
                if(minVal == null || minVal.val > lists[i].val){
                    minVal = lists[i];
                    minIndex = i;
                }
            }
            
            // it means that we cretaed our answer list
            if(minVal == null) break;
            
            // s2: include the minimum in the final list
            curr.next = minVal;
            curr = curr.next;
            
            // s3: Atlast we have to replace the minimum value with its next value
            lists[minIndex] = minVal.next;
        }
        
        return dummyNode.next;
    }
}

/*
METHOD 2:
	APPROACH: Join all the list into a one list and then sort the list.

TIME: O(NlogN)


METHOD 3:(USING DIVIDE AND CONQUER)
	APPROACH:
		Divide the list into two halves and then merge them in bottom-up manner.

TIME: O(NlogK), logK is for diving the K linked-list, & N is the no. of nodes
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
     
        if(lists == null) return null;
        
        return divideAndConquer(lists, 0, lists.length-1);
    }
    
    private ListNode divideAndConquer(ListNode[] lists, int low, int high){
        
        // base case
        if(low > high) return null;
        
        if(low == high) return lists[low];
        
        int mid = low + (high - low) / 2;
        
        ListNode left = divideAndConquer(lists, low, mid);
        ListNode right = divideAndConquer(lists, mid+1, high);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode l1, ListNode l2){
        
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }
            else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        curr.next = l1 != null ? l1 : l2;
        
        return dummyNode.next;
    }
}

/*
METHOD 4:(USING HEAP)

TIME: O(NlogK), logK for push/pop operation in the Heap and there are N no. of nodes, and for build the heap it takes O(k) and the heap size is also k.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
     
        if(lists == null) return null;
        
        // creating a minHeap to get the smallest element each time
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b)->{
            return a.val-b.val;
        });
        
        //s1: Push 1st element of all k linked-lists into Heap
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null)
                minHeap.offer(lists[i]);
        }
        
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        
        // s2: Pop the smallest and add it to the result. 
        while(!minHeap.isEmpty()){
            
            // extracting the minimum value
            ListNode minVal = minHeap.poll();
            
            // adding it to the answer list and update the pointer
            curr.next = minVal;
            curr = curr.next;
            
            // s3: push the next node of the current min value (if not NULL)
            if(minVal.next != null){
                minHeap.offer(minVal.next);
            }
        }
        
        return dummyNode.next;
    }
}