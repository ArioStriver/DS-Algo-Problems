/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y 
with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the smallest possible weight of the left stone. If there are no stones left, return 0.

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1

Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

Example 2:

Input: stones = [1]
Output: 1


METHOD 1:(BRUTE FORCE)
	APPROACH:
		We will store the stones in a vector/list and run a while loop until the size of the vector/list is greater than 1

 			1. Find the index and weight of the two heaviest stones
			2. Remove those stones from the vector/list using their index
			3. Add their absolute difference to the vector/list if the absolute difference is greater than 0

TIME: O(N^2), The number of stones is N and so the first loop will run at most N times, to find the two heaviest stones it will take O(N), and to remove the two 
      heaviest stones it will again take O(N). Adding another stone if weight > 0 will cost again O(N) time.  So the overall time complexity is O(N * (N + N + N)) or 
      O(N ^ 2).

SPACE: O(1).


METHOD 2:(USING MAXHEAP)
	APPROACH:
		We can use a max priority queue to solve this question. Store the weights of all stones in the queue and then while its size is greater than 1 do the 
		following: 

 			1. Pop two elements from the queue. These will be the two largest elements in the queue.
			2. Then, find the positive difference between the two popped elements and if it's greater than zero, insert it into the queue.
 
			   After you've completed the above process, you should have one element left in the queue. Simply return it.

TIME: O(N*logN), N is the total number of stones we have in the beginning and inserting/popping operation takes O(logN) time.

SPACE: O(N). Since we're making a max priority queue to store the N stones.
*/

class Solution {
    public int lastStoneWeight(int[] stones) {
       
        if(stones.length == 1) return stones[0];
        
        // creating a maxheap to choose the heaviest two stones in each turn
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)-> b - a);
        
        for(int stone : stones){
            maxHeap.offer(stone);
        }
        
        while(maxHeap.size() > 1){
            
            int x = maxHeap.poll();
            int y = maxHeap.poll();

            // the stone of weight x is destroyed, and the stone of weight y has new weight y - x
            if(x - y > 0){
                maxHeap.offer(x - y);
            }
        }
        
	// if the heap is empty it means that all stones are destroyed
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}