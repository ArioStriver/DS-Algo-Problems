/*
Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.

Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20

Explanation: 
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.

Example 2:

Input: arr = [1,1,2,2,2,2], target = 5
Output: 12

Explanation: 
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.


METHOD 1:(UISNG THREE POINTER)

TIME: O(N^2), where N is the length of A.

SPACE: O(1).
*/

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        
        int n = arr.length;
        int MOD = 1000000007;

        Arrays.sort(arr);   // sorting the array will help us to find our answer efficiently
        
        long totalNoOfTuples = 0;
        
        // traversing the elements
        for(int i = 0; i < n-2; i++){
            
            // using two pointer approach
            int j = i+1;    
            int k = n-1;
            int remSum = target - arr[i];  // remaining sum
            
            while(j < k){
                
                // we need greater value
                if(arr[j] + arr[k] < remSum){
                    j++;
                }
                // need lesser value
                else if(arr[j] + arr[k] > remSum){
                    k--;
                }
                /* case : a[j] + a[k] == remSum
                   if (j th element != k th element), then we need to count their multiplicities */
                else if(arr[j] != arr[k]){
                    
                    int countJ = 1, countK = 1;
                    
                    // counting the occurance of jth element
                    while(j+1 < k && arr[j] == arr[j+1]){
                        countJ++;
                        j++;
                    }
                    
                    // counting the occurance of kth element
                    while(k-1 > j && arr[k] == arr[k-1]){
                        countK++;
                        k--;
                    }
                    
                    // calculate no. of pairs
                    totalNoOfTuples += (countJ * countK) % MOD;
                    j++;
                    k--;
                }
                /* special case : if (a[j] == a[k]), then the manner of counting would be
                   M * (M - 1) / 2, where M is the Multiplicity of A[j]  */
                else{
                    totalNoOfTuples += (k - j + 1) * (k - j) / 2;
                    totalNoOfTuples = totalNoOfTuples % MOD;
                    break;
                }
            }
        }
        
        return (int)totalNoOfTuples;
    }
}

/*
METHOD 2:(OPTIMIZED)
	APPROACH:
		Count the occurrence of each number.
		using hashmap or array up to you.

		Loop i on all numbers,
		loop j on all numbers,
		check if k = target - i - j is valid.

		Add the number of this combination to result.
		3 cases covers all possible combination:

			1. i == j == k
			2. i == j != k
			3. i < k && j < k

		Case 1: three numbers are the same. Then you will have i == j == k.
	
		Case 2: two of the three numbers are equal. For example, 5, 4, 5. To avoid duplicates, you can choose to make i=5, j=5, k = 4. Once this is chosen, 
		        you do not want to have others (e.g. i = 5, k =5, j = 4, or j = 5, k =5, i =4). Otherwise, You will make duplicates. In brief, because two 
                        numbers are the same, you only want to have either i, j, or j, k, or k, i to be the same numbers. Here we picked i, j.

		Case 3: all three numbers are different. For example, 3, 4, 5. Similar as case 2, you only want to have one assumption, though you have 6 (i < j < k, 
			i < k < j, j < i < k, j < k < i, k < i <j, k <j < i).Here we picked i < j < k.

TIME: O(N + 101 * 101).

SPACE: O(101) == O(1).
*/

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        
        int n = arr.length;
        int MOD = 1000000007;
        
        long totalNoOfTuples = 0;
        
        long[] count = new long[101];
        
        // storing the frequency of ecah number
        for(int a : arr){
            count[a]++;
        }
        
        for(int i = 0; i <= 100; i++){
            for(int j = i; j <= 100; j++){
                
                int k = target - i - j;
                
                // out of bound check
                if(k < 0 || k > 100) continue;
                
                // case 1: (n! / k! * (n-k)!)
                if(i == j && j == k){
                    totalNoOfTuples += (count[i] * (count[i]-1) * (count[i]-2)) / 6;
                }
                // case 2:
                // if two of them are equal out of three
                else if(i == j && j != k){
                    totalNoOfTuples += (count[i] * (count[i]-1) / 2) * count[k];
                }
                //case 3: three of them are distinct
                else if(i < j && j < k){
                    totalNoOfTuples += count[i] * count[j] * count[k];
                }
            }
        }
        
        return (int)(totalNoOfTuples % MOD);
    }
}