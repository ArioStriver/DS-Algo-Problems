/*
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 

Example 1:

Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]

Output
[null, 4, 5, 5, 8, 8]

Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


METHOD:(USING MINHEAP)
	APPROACH:
		1. Creating a heap of size k.
		pq in java is self-order queue by elements' natural order, smallest at head, largest at rear. by defining pq in k size, the head of pq is kth largest 
		element.

		2. Also we are checking when adding new element, the smallest element(the current kth largest) will pop if new added element is larger. (nothing required 
		   if added element is smaller than kth largest). So the head of pq will always be kth largest.

TIME: O(N*log(k) + M*log(k)), where N is the no. of elements in the nums, requires O(logk) time to build the minHeap, and  if there are M no. of calls to add function
      that means M calls to add() costs O(M*log(k)).

SPACE: O(K), bcz our heap is of size k.
*/

class KthLargest {

    int K;
    PriorityQueue<Integer> minHeap;
    
    public KthLargest(int k, int[] nums) {
        K = k;
        minHeap = new PriorityQueue<>();
        
        // adding elements into the heap
        for(int num : nums){
            add(num);
        }
    }
    
    public int add(int val) {
        
	// creating a heap of size k
        if(minHeap.size() < K){
            minHeap.add(val);
        }
	// if the top element in the heap is < the current val, then pop() the top element and add the current val, bcz here we need the largest element.
        else if(minHeap.peek() < val){
            minHeap.poll();
            minHeap.offer(val);
        }
        
        return minHeap.peek();
    }
}