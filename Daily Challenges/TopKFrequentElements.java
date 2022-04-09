/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]


METHOD 1:(USING HEAP).
	APPROACH:
		1. First we take a hashMap and store the frequency of each element.
		2. After that we build a maxHeap and sort the the maxHeap according to the frequency of each element. Here we're building maxHeap, so element having 
		   highest frequency will ne at top.
		3. Last we are fetching the top k elements from the heap for our answer.

TIME: O(N + N + klogN) == O(klogN).
      For Hashing : O(N), For build heap : O(N), For extracting k frequent elements : O(klogN).

SPACE: O(N + N) == O(N). For hashmap and for heap of size N.
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
     
        if(nums.length == k) return nums;
        
        Map<Integer, Integer> map = new HashMap();
        
        // record the frquency of each element
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        /* building the maxheap depending on the frequency of element
           higher frequency means most frequency
           sorting the elements depending on the frequency */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)-> map.get(b)-map.get(a));
        
        for(int num : map.keySet()){
            maxHeap.offer(num);
        }
        
        int[] res = new int[k];
        int i = 0;
        
        // fetching the k frequent elements from the heap
        while(k > 0){
            res[i++] = maxHeap.poll();
            k--;
        }
        
        return res;
    }
} 

/*
METHOD 2:(UISNG QUICK SELECT ALGORITHM)
	APPROACH:
		Here basically the elements in the left of pivot having frquency less than pivot and right elements having frequency >= pivot.

TIME: O(N), on average, but at worst case it can O(N^2) if we continuosly choose a same element as pivot suppose the right most element. But in case of random pivot
      the probability of having such a worst-case is negligibly small.

SPACE: O(N), for hashing and unique elements.
*/

class Solution {
    
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private int partition(int[] uni, int left, int right, int pivotIndex, Map<Integer, Integer> freq) {
        
        int pivotFreq = freq.get(uni[pivotIndex]);
        int i = left;
        
        // 1. move pivot to end
        swap(uni, pivotIndex, right);
        
        /* 2. move less frequent to left and most frequent to right
              all elements in the left have frequency less than the pivot element
              and all elements in the rigth of pivot have >= frequency */
        for(int j = left; j < right; j++){
            
            if(freq.get(uni[j]) < pivotFreq){
                swap(uni, j, i);
                i++;
            }
        }
        
        // 3. now place the pivot at its correct position
        swap(uni, i, right);
        
        return i;  // pivot index
    }
    
    private void quickSelect(int left, int right, int k, Map<Integer, Integer> freq, int[] unique) {
        
        int n = unique.length;
        Random rand = new Random(0);
        
        while(left <= right){
            
            int pivotIndex = rand.nextInt(right - left + 1) + left;
            
            int finalPivotIndex = partition(unique, left, right, pivotIndex, freq);
            
            if(finalPivotIndex == n - k){
                return;
            }
            else if(finalPivotIndex < n - k){
                left = finalPivotIndex + 1;
            }
            else{
                right = finalPivotIndex - 1;
            }
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        
        // base case
        if(nums.length == k){
            return nums;
        }
        
        // creating a map to record the frequency of each element
        Map<Integer, Integer> freq = new HashMap();
        
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // storing the unique elements
        int n = freq.size();
        int[] unique = new int[n];
        int i = 0;
        
        for(int a : freq.keySet()){
            unique[i++] = a;
        }
        
        quickSelect(0, n - 1, k, freq, unique);
                    
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}

/*
METHOD 3:(USING BUCKET SORT)
	APPROACH:
		Here we basically create a array of list where we use the index of the bucket as the frequency and store elements having same frequecy in a same bucket.

TIME: O(N) + O(N) + O(N) == O(N).

SPACE: O(N + N) == O(N), one for hashing and one for bucket.
*/

class Solution {
    
    public int[] topKFrequent(int[] nums, int k) {
        
        // base case
        if(nums.length == k){
            return nums;
        }
        
        // creating a map to record the frequency of each element
        Map<Integer, Integer> freq = new HashMap();
        
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        
        /* now here we are going to use the bucket sort
           so basically we're going to use the index of the bucket as the frequency
           and store the elemnets having same frequecy in a bucket */
        List<Integer>[] freqBucket = new List[nums.length + 1];
        
        // storing the elements according to the bucket
        for(int num : freq.keySet()){
            
            int keyFreq = freq.get(num);
            
            if(freqBucket[keyFreq] == null){
                freqBucket[keyFreq] = new ArrayList();
            }
            
            // adding the elemnet into the bucket
            freqBucket[keyFreq].add(num);
        }
        
        
        int[] res = new int[k];
        int index = 0;
        
        /* now accd. to the question we have to find the most frequent elements
           so it is obvious that the most frequent element are towards the end of the array */
        for(int i = freqBucket.length - 1; i > 0; i--){
            
            // if the current bucket is not null, it means it has some elements
            if(freqBucket[i] != null){
                
                for(int j = 0; j < freqBucket[i].size() && index < k; j++){
                    res[index++] = freqBucket[i].get(j);
                }
            }
        }
        
        return res;
    }
}