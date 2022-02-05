/*
Merge k Sorted Lists

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


METHOD 1:(BRUTE FORCE)
	APPROACH:
		1. Traverse all the linkedlist and collect the values into a array or list
		2. Sort and iterate over this list to get the proper value of nodes.
		3. Create a new sorted linked list and extend it with the new nodes.

TIME: O(NlogN) where N is the total number of nodes.
	Collecting all the values costs O(N) time.
	A stable sorting algorithm costs O(NlogN) time.
	Iterating for creating the linked list costs O(N) time.

SPACE: O(N).Creating a new linked list costs O(N) space.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        List<Integer> li = new ArrayList<>();
        
        // 1. traversing the list to collect the values of the nodes into an list
        for(int i = 0; i < lists.length; i++){
            
            ListNode temp = lists[i];
            
            // traversing each sublist
            while(temp != null){
                li.add(temp.val);
                temp = temp.next;
            }
        }
        
        // 2. sort all the values of list
        Collections.sort(li);
        
        // 3. create a new sorted list and extend it with the new nodes
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        for(int i = 0; i < li.size(); i++){
            
            // converting the current value  into the node
            ListNode newVal = new ListNode(li.get(i));
            curr.next = newVal;
            curr = curr.next;
        }
        
        return dummy.next;
    }
}

/*
METHOD 2:
	APPROACH:
		All the given lists are sorted, which means that the head of each list would be the smallest element in its chain. So we could extract the minimum from 
		the k head nodes and append it to the result list.

TIME: O(kN) where k is the number of linked lists. Almost every selection of node in final linked costs O(k) (k-1 times comparison).There are N nodes in the final 
      linked list.

SPACE: O(N) Creating a new linked list costs O(n) space.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        while(true){
            
            
            ListNode minVal = null;
            int minIndex = -1;
            
            // 1. Compare every k nodes and get the node with the smallest value.
            for(int i = 0; i < lists.length; i++){
                
                ListNode curVal = lists[i];
                
                if(curVal == null) continue;
                
                if(minVal == null || curVal.val < minVal.val){
                    minVal = curVal;
                    minIndex = i;
                }
            }
            
            if(minVal == null) break;
            
            
            // 2. now after getting the minimum value append it to the result linked list
            curr.next = minVal;
            curr = curr.next;
            
            // delete the current minimum value with its next value
            lists[minIndex] = minVal.next;
        }
        
        return dummy.next;
    }
}

/*
METHOD 3:(UISNG PRIORITY QUEUE)
	APPROACH:
		1. Create a priority queue.
		2. Insert the first node from all the lists into the priority queue.
		3. Loop until the priority queue is not empty
			1. Extract the minimum node from the queue and add it to our result list.
			2. Add the next node (if present) from the extracted node list.
		4. Return the resultant list.

TIME: O(NlogK), where K is the number of linked lists.The comparison cost will be reduced to O(logK) for every pop and insertion to a priority queue. But finding the 
      node with the smallest value just costs O(1) time.There are N nodes in the final linked list.

SPACE: O(N) Creating a new linked list costs O(N) space.Priority queue (often implemented with heaps) costs O(K) space.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        // 1. creating a minHeap bcz minHeap always contains the minimum value as its root 
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) ->{
            return a.val - b.val;
        });
        
        // 2. Insert the first node from all the lists into the priority queue.
        for(int i = 0; i < lists.length; i++){
            
            if(lists[i] != null){
                minHeap.offer(lists[i]);
            }
        }
        
        // 3. Loop untill the queue is not empty
        while(!minHeap.isEmpty()){
            
            // now extract the minimum value from the priority queue
            ListNode minVal = minHeap.poll();
            
            // adding the minimum value into the result
            curr.next = minVal;
            curr = curr.next;
            
            // if the next of the minimum value is not null then add it to the queue
            if(minVal.next != null)
                minHeap.offer(minVal.next);
        }
        
        return dummy.next;
    }
}

/*
METHOD 4:(USING DIVIDE AND CONQUER)
	APPROACH:
		We don’t need to traverse most nodes many times repeatedly.

			1. Pair up k lists and merge each pair.
			2. After the first pairing, k lists are merged into k/2 lists with average 2N/k length, then k/4, k/8 and so on.
			3. Repeat this procedure until we get the final sorted linked list.
			   Thus, we’ll traverse almost N nodes per pairing and merging, and repeat this procedure about log​k times.

TIME: O(NlogK), where K is the number of linked lists.We can merge two sorted linked lists in O(N) time where N is the total number of nodes in two lists.

SPACE: O(logK), RECURSION STACK SPACE.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
     
        return divideAndConquer(lists, 0, lists.length - 1);
    }
    
    private ListNode divideAndConquer(ListNode[] li, int low, int high){
        
        if(low > high) return null;
        
        if(low == high) return li[low];
        
        int mid = low + (high - low) / 2;
        
        ListNode left = divideAndConquer(li, low, mid);
        ListNode right = divideAndConquer(li, mid+1, high);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode n1, ListNode n2){
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        while(n1 != null && n2 != null){
            if(n1.val < n2. val){
                curr.next = n1;
                n1 = n1.next;
            }
            else{
                curr.next = n2;
                n2 = n2.next;
            }
            
            curr = curr.next;
        }
        
        curr.next = (n1 != null) ? n1 : n2;
        
        return dummy.next;
    }
}