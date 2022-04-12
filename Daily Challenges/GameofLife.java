/*
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with 
its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the 
current state of the m x n grid board, return the next state.

Example 1:

Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

Example 2:

Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]


METHOD 1:
	APPROACH:
		We can do this porblem using extra space instead of modifying the original array, Which makes spacecomplexity as O(M * N). The total approach is same 
		as maethod 2.

METHOD 2:

TIME: O(M * N), M are the no. of rows and N is the no. of columns.

SPACE: O(1).
*/

class Solution {
    
    private void updateBoard(int[][] b){
        
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                
                // cell = -2 : dead cell
                if(b[i][j] == -2){
                    b[i][j] = 0;
                }
                // cell = 3 : new live cell
                else if(b[i][j] == 3){
                    b[i][j] = 1;
                }
            }
        }
    }
    
    private int countActiveCells(int[][] b, int r, int c){
        
        // 8 directions
        int[][] dirs = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
        
        int activeNeigh = 0;
        
        for(int[] dir : dirs){
            
            // calculating new row and column    
            int nR = r + dir[0];
            int nC = c + dir[1];
            
            // out of bound checking
            if(nR < 0 || nR >= b.length || nC < 0 || nC >= b[0].length) continue;
            
            // cell = 1 or -2 : means it is a active cell
            if(b[nR][nC] == 1 || b[nR][nC] == -2){
                activeNeigh++;   // counting active neighbours
            }
        }
        return activeNeigh;
    }
    
    public void gameOfLife(int[][] board) {
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                
                // if the current cell is active cell
                if(board[i][j] == 1){
                    
                    // count the no. of active cells in its neightbours
                    int NoOfActiveCells = countActiveCells(board, i, j);
                    
                    /* accd. to the given condition
                       if the no. of active cells in it's neighbour < 2 && > 3 
                       then the current cell becomes dead cell */
                    if(NoOfActiveCells < 2 || NoOfActiveCells > 3){
                        board[i][j] = -2;  // 0
                    }
                }
                // the current cell is dead cell
                else{
                    int NoOfActiveCells = countActiveCells(board, i, j);
                    
                    /* if the no. of active cells on it's neightbours is exactly 3
                       then the current dead cell will become live cell */
                    if(NoOfActiveCells == 3){
                        board[i][j] = 3;  // 1;
                    }
                }
            }
        }
        
        // atlast updating the final board
        updateBoard(board);
    }
}