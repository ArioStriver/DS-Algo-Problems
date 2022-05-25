/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:

Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


METHOD 1:(USING BOOLEAN ARRAY)

TIME: O(N * N)

SPACE: O(N * N)
*/

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // if the intial cell is not zero then return -1
        if(grid[0][0] == 1) return -1;
        
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        q.offer(new int[]{0, 0, 1});   // (row, cloumn, count)
        
        // marked the intial cell as visited (top-left)
        visited[0][0] = true;      
        
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            while(size-- > 0) {
                
                int[] curr = q.poll();
                int r = curr[0];       // curent row
                int c = curr[1];       // current column
                int count = curr[2];   // current count of cells
                
                // we reached the bottom-right, so return the count
                if(r == n-1 && c == n-1) {
                    return count;
                }
                
                // traversing in the all 8 directions
                for(int i = 0; i < 8; i++) {

                    // next row, new column
                    int nextR = r + dr[i];
                    int nextC = c + dc[i];

                    // out of bound checking 
                    if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= n || visited[nextR][nextC]) continue;

                    // if the cuurent cell is 0 then only consider
                    if(grid[nextR][nextC] == 0) {
                        
                         // updating the (row, column, count)
                        q.offer(new int[] {nextR, nextC, count+1});
                        
                        // marking the new row & column as visited
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}


// INSTEAD OF USING BOOLEAN ARRAY WE CAN MANUPULATE THE INPUT ARRAY

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // if the intial cell is not zero then return -1
        if(grid[0][0] == 1) return -1;
        
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{0, 0, 1});   // (row, cloumn, count)
        
        // marked the intial cell as visited (top-left)
        grid[0][0] = 1;      
        
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            while(size-- > 0) {
                
                int[] curr = q.poll();
                int r = curr[0];       // curent row
                int c = curr[1];       // current column
                int count = curr[2];   // current count of cells
                
                // we reached the bottom-right, so return the count
                if(r == n-1 && c == n-1) {
                    return count;
                }
                
                // traversing in the all 8 directions
                for(int i = 0; i < 8; i++) {

                    // new row, new column
                    int nR = r + dr[i];
                    int nC = c + dc[i];

                    // out of bound checking 
                    if(nR < 0 || nR >= n || nC < 0 || nC >= n || grid[nR][nC] == 1) continue;

                    // if the cuurent cell is 0 then only consider
                    if(grid[nR][nC] == 0) {
                        
                         // updating the (row, column, count)
                        q.offer(new int[] {nR, nC, count+1});
                        
                        // marking the new row & column as visited
                        grid[nR][nC] = 1;
                    }
                }
            }
        }
        
        return -1;
    }
}