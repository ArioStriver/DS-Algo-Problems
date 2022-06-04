/*
Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner 
(row2, col2).
 
Example 1:

Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]

Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)


METHOD 1:(BRUTE FORCE)

TIME: O(M * N), where M is the no. of rows and N is the no. of columns.

SPACE: O(1).
*/

class NumMatrix {
    
    int[][] mat;
    
    public NumMatrix(int[][] matrix) {
        mat = matrix;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        
        int sum = 0;
        
        for(int i = row1; i <= row2; i++) {
            for(int j = col1; j <= col2; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }
}


/*
METHOD 2:(USING DP)(USING PREFIC SUM)

TIME: O(M) time per query, and for pre-computation the time is O(M*N). The pre-computation in the constructor takes O(mn)O(mn) time.

SPACE: O(M*N).
*/

class NumMatrix {
    
    int[][] mat;

    public NumMatrix(int[][] matrix) {
        
        int m = matrix.length;
        int n = matrix[0].length;
        mat = new int[m][n+1];
        
        // calculating the cumulative sum of each row
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                
                mat[r][c+1] = mat[r][c] + matrix[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        
        int sum = 0;
        
        for(int r = row1; r <= row2; r++) {
            sum += mat[r][col2+1] - mat[r][col1];
        }
        
        return sum;
    }
}


/*
METHOD 3:(USING DP + PREFIX SUM NIN A BETTER WAY)

TIME: O(1) per query, O(M*N) time pre-computation

SPACE: O(M*N).
*/

class NumMatrix {

    int[][] sum;
    
    public NumMatrix(int[][] matrix) {
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        sum = new int[m+1][n+1];
        
        for(int r = 1; r <= m; r++) {
            for(int c = 1; c <= n; c++) {
                
                // calculating the current sum
                // (current val at that index + sum(rectangle above it) + sum(rectangle before it) - sum(common rectangle)
                sum[r][c] = matrix[r-1][c-1] + sum[r-1][c] + sum[r][c-1] - sum[r-1][c-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        
        return  sum[row2+1][col2+1] - sum[row1][col2+1] - sum[row2+1][col1] + sum[row1][col1];  
    }
}
