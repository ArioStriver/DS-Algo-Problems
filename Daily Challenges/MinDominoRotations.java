/*
In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each 
half of the tile.)

We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.

Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.

If it cannot be done, return -1.

Example 1:

Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
Output: 2

Explanation: 
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.

Example 2:

Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
Output: -1

Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.


METHOD 1:
	APPROACH:
		Count the occurrence of all numbers in A and B,
		and also the number of domino with two same numbers.

		Try all possibilities from 1 to 6.
		If we can make number i in a whole row,
		it should satisfy that countA[i] + countB[i] - same[i] = n

TIME: O(N), where N is the total number of elements.

SPACE: O(1).
*/

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        
        int n = tops.length;
        int[] countTop = new int[7], countBottom = new int[7], same = new int[7];
        
        // storing the frequency of each number in the top and bottom
        for(int i = 0; i < n; i++){
            countTop[tops[i]]++;
            countBottom[bottoms[i]]++;
            
            if(tops[i] == bottoms[i]){
                same[tops[i]]++;
            }
        }
        
        // Try all possibilities from 1 to 6.
        // If we can make number i in a whole row
        // it should satisfy that countA[i] + countB[i] - same[i] = n
        for(int i = 1; i < 7; i++){
            
            if(countTop[i] + countBottom[i] - same[i] == n){
                return (n - Math.max(countTop[i], countBottom[i]));
            }
        }
        
        return -1;
    }
}

/*
METHOD 2:
	APPROACH:
		1. Try make A[0] in a whole row,
		   the condition is that A[i] == A[0] || B[i] == A[0]
		   a and b are the number of swap to make a whole row A[0]

		2. Try B[0]
    		   the condition is that A[i] == B[0] || B[i] == B[0]
		   a and b are the number of swap to make a whole row B[0]

		3. Return -1

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        
        int n = tops.length;
        
        // num1 = tops[0], nums2 = bottoms[0]
        // here we first try to make top[0] in a whole row
        // top as well as bottom row
        for(int i = 0, countTop = 0, countBottom = 0; i < n && (tops[i] == tops[0] || bottoms[i] == tops[0]); i++){
                
            // count the no. of swaps we need 
            if(tops[i] != tops[0]) countTop++;
                
            if(bottoms[i] != tops[0]) countBottom++;
            
            if(i == n-1){
                return Math.min(countTop, countBottom);
            }
        }
        
        // if we unable to make top[0] in a whole row, then we try bottom[0]
        // top as well as bottom row
        for(int i = 0, countTop = 0, countBottom = 0; i < n && (tops[i] == bottoms[0] || bottoms[i] == bottoms[0]); i++){
                
            // count the no. of swaps we need 
            if(tops[i] != bottoms[0]) countTop++;
                
            if(bottoms[i] != bottoms[0]) countBottom++;
            
            if(i == n-1){
                return Math.min(countTop, countBottom);
            }
        }
        
        return -1;
    }
}