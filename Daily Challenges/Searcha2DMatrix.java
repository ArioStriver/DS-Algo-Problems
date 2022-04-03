/*
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

Example 1:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false


METHOD 1:(BRUTE FORCE)

TIME: O(M*N), at worst case, where M is the no. of rows and N is the no. of columns.

SPACE: O(1).
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        for(int i = 0; i < matrix.length; i++){
            
            // as each row is in sorted order
            // so if the 0th value < target then there is a possibility that the target is present in that row
            if(matrix[i][0] <= target){
                
                for(int j = 0; j < matrix[0].length; j++){
                    if(matrix[i][j] == target){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


/*
METHOD 2:
	APPROACH: Here we basically treat the 2D matrix as a 1D matrix.

TIME: O(log(M*N))

SPACE: O(1).
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int low = 0, high = m*n - 1;
        
        while(low <= high){
            
            int mid = low + (high - low) / 2;
            
            // calculating the row and the column number in the matrix
            int row = mid / n;
            int col = mid % n;
            
            if(matrix[row][col] == target){
                return true;
            }
            
            if(target < matrix[row][col]) {
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        
        return false;
    }
}


/*
ANOTHER VARIENT OF THIS PROBLEM:

Given an n x n matrix and a number x, find the position of x in the matrix if it is present in it. Otherwise, print “Not Found”. In the given matrix, every row and column is sorted in 
increasing order. The designed algorithm should have linear time complexity. 

Example 1: 

Input: mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 29

Output: Found at (2, 1)

Explanation: Element at (2,1) is 29

Example 2:

Input : mat[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              x = 100

Output : Element not found

Explanation: Element 100 is not found


METHOD 1: Just apply Simply linear serach on the Matrix and try to find that target element. Time : O(M*N).

METHOD 2: Go in each and every row, apply a binary search on each row. TIME: O(M * log(N)).

METHOD 3:
	APPROACH:
		The simple idea is to remove a row or column in each comparison until an element is found. Start searching from the top-right corner of the matrix. There are three possible 
		cases.
		
			case 1: The given number is greater than the current number
			case 2: The given number is smaller than the current number
		
		Algorithm: 
			1. Let the given element be x, create two variable i = 0, j = n-1 as index of row and column
			2. Run a loop until i = n
			3. Check if the current element is greater than x then decrease the count of j. Exclude the current column.
			4. Check if the current element is less than x then increase the count of i. Exclude the current row.
			5. If the element is equal, then print the position and end. 

TIME: O(M + N), bcz at maximum we can og upto M and N.

SPACE: O(1).
*/

class Solution 
{ 
	static boolean search(int matrix[][], int m, int n, int x) 
	{  
	    // i = row, j = column
	    int i = 0;
	    int j = n-1;  // starting from the top left corner
	    
	    while(i < m && j < n){
	        
	        // equal to target
	        if(matrix[i][j] == x){
	            return true;
	        }
	        
	        // as we start from the top left corner
	        // so from there if we go downwards we get higher value
	        // if we go towards left we get lesser value
	        // if the current element <= target
	        if(matrix[i][j] <= x){
	            // move down as we get higher value by moving downwards
	            i++;
	        }
	        else{
	            // as we move left we get lesser value
	            j--;
	        }
	    }
	    return false;
	} 
} 