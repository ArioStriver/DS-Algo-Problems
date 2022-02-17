/*
4 Sum II
Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 
Example 1:

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0


Example 2:

Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
 
Constraints:

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28


METHOD 1:(EXTREME BRUTE FORCE)

TIME: O(N^4).

SPACE: O(1).
*/

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        for(int i : nums1)
            for(int j : nums2)
                for(int k : nums3)
                    for(int l : nums4)
                        if(i + j + k + l == 0) count++;
        return count;
    }
}


// METHOD 2:(SMALL OPTIMIZATION)

// TIME: O(N^3)


class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int l : nums4)
            map.put(l, map.getOrDefault(l, 0) + 1); 

        int count = 0;
        for(int i : nums1)
            for(int j : nums2)
                for(int k : nums3)
                        count += map.getOrDefault(-(i + j + k), 0); 
        return count;
    }
}


// METHOD 3:(OPTIMIZED)

// TIME: O(N^2).

// SPACE: O(N^2), bcz at worst case if the sum of Num1 + Num2 are all different.


class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        
        Map<Integer, Integer> sum = new HashMap<>();
        
        // storing the sum of A[i] + B[j]
        for(int i : A){
            for(int j : B){
                
                sum.put(i + j, sum.getOrDefault(i + j, 0) + 1);
            }
        }
        
        // now checking if the C[i] + D[j] exists in the sum map
        // bcz A + B + C + D = 0
        // i.e. A + B = -C -D
        // A + B = -(C + D)
        
        int fourSumCount = 0;
        
        for(int i : C){
            for(int j : D){
                
                fourSumCount += sum.getOrDefault(-(i + j), 0);
            }
        }
        
        return fourSumCount;
    }
}