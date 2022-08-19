/*
You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2

Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.

Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1

Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 
Constraints:

2 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5


METHOD 1: (USING HASHING AND SORT FUNCTION)
	APPROACH:
		We need to choose smallest set of numbers such that deleting all occurences of those numberswill be reduce the array size by half. It should be obvious that the number having 
		the maximum frequency should be deleted first so that size of set of numbers is minimized.
		
		For this, we can use hashmap to find frequency of each number, then sort the frequencies using sort function and keep deleting numbers untill the array size is atleast n / 2.

TIME: O(NlogN).

SPACE: O(N).
/*

class Solution {
    public int minSetSize(int[] arr) {
       
        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();
        
        // storing the frequency of each element
        for(int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        int[] freqValue = new int[freq.values().size()];
        int i = 0;
        
        // now storing the frequency values in an array
        for(int val : freq.values()) {
            freqValue[i++] = val;
        }
        
        // sorting the values in the ascending order
        Arrays.sort(freqValue);
        
        i = i-1;
        int currentArraySize = 0, count = 0;
        
        // looping through untill the current size of array become half
        while(currentArraySize < (n/2)) {
            count++;
            currentArraySize += freqValue[i];
            i--;
        }
        
        return count;
    }
}


/*
METHOD 2: (USING PRIORITY QUEUE & HASHMAP)

TIME: O(NlogN).

SPACE: O(N).
*/

class Solution {
    public int minSetSize(int[] arr) {
       
        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();
        
        // storing the frequency of each element
        for(int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // storing the frequencies in the priority queue and creating a maxHeap
        PriorityQueue<Integer> maxHp = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int num : freq.values()) {
            maxHp.offer(num);
        }
        
        int currentArraySize = 0, count = 0;
        
        // looping through untill the current size of array become half
        while(currentArraySize < (n/2)) {
            count++;
            currentArraySize += maxHp.poll();
        }
        
        return count;
    }
}

/*
METHOD 3:(USING BUCKET SORT AND HASHING)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int minSetSize(int[] arr) {
        
        Map<Integer, Integer> mp = new HashMap<>();
        
        for(int num : arr)
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        
        // Instead of using in built sort function
        // we can use bucket sort to sort the frequency array
        int[] bucket = new int[(int)(1e5 + 1)];
        int maxFreq = 1;
        
        // here we use the frequency as index of bucket
        for(int freq : mp.values())
        {
            bucket[freq]++;
            maxFreq = Math.max(maxFreq, freq);
        }
        
        int current_size = 0, half = arr.length / 2, count = 0;
        int freq = maxFreq;
        
        // check whether the current size is less than the half or not
        // is so then we have to delete elements 
        // and here we delete elements which has highe frequency first
        // means we go in th decreasing order i.e. why we find the max Frequency
        // so that we can use it to traverse in decreasing order
        while(current_size < half)
        {
            count++;
            
            // if the current frequncy has zero value or not
            // if not then we have to make it 0 in order to remove it
            // and if it has 0 value then we simply decrease the current frequncy value
            // in order to move to the next frequency value
            while(bucket[freq] == 0)
                freq--;
            
            current_size += freq;
            bucket[freq]--;
        }
        
        return count;
    }
}