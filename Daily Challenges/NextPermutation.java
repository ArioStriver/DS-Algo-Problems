/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are 
sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. 
If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]

Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]

Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


METHOD 1:
	APPROACH:
		We can generate all the possible permutation & then find the given permutaion in that group & return the next permutaion.

TIME: O(N * N!), where N is the no. of elements.

SPACE: O(1).
*/

METHOD 2:
	APPROACH:
		1. Find the largest index k such that nums[k] < nums[k + 1]. If no such index exists, just reverse nums and done.
		2. Find the largest index l > k such that nums[k] < nums[l].
		3. Swap nums[k] and nums[l].
		4. Reverse the sub-array nums[k + 1:].

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public void nextPermutation(int[] nums) {
        
        int n = nums.length;
        
        if(n == 1 || nums == null) return;
        
        int i = n-2;
        
         // find the breakpoint form where the incearsing order is breaks
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        
        /* next we have to find the next element which is greater than the current element
           present at that breakpoint, bcz that element is going to be the next
           element to take the place of the decreased element, and we must sure that there is an
           element in its right part which is greater than the current decreased element */
        if(i >= 0){
            
            int j = n-1;
            
            while(nums[j] <= nums[i]){
                j--;
            }
            
            // swapping their position
            swap(nums, i, j);
        }
        
        /* at last we have to reverse the array to get the exact nect permutation
           bcz what we got after swapping is the last permutation & we want the first permutaion
           which ranks in lower */
        reverse(nums, i+1, n-1);
    }
    
    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private void reverse(int[] a, int i, int j){
        while(i < j){
            swap(a, i, j);
            i++;
            j--;
        }
    }
}