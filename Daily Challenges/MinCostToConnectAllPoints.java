/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:

Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20

Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18


METHOD : (USING PRIM'S ALOGORITHM)

TIME: O(N^2 * logN), Where O(N^2) is bcz for each node we add the every possible edge that is possible for that node and logN is for push/pop operation in Heap.

SPACE: O(N), but at worst case it may go upto  O(N^2), bcz we push/pop N*(N−1)/2 ≈ N^2 edges into the heap.
*/

class Solution {
    public int minCostConnectPoints(int[][] points) {
        
        /* first take a priority queue to find the mincost travelled from current point to each point
           here we sort the queue depending on the mincost 
           so basiclly we are creating a minHeap to fetch the minimum cost */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        int minCost = 0;
        
        /* we also have to keep track of the no. of nodes we have visited so far
           bcz if we visited all the nodes than don't need to visit them again
           to keep track of the visited nodes */
        Set<Integer> visited = new HashSet<>();
        
        int num = points.length;
        
        minHeap.offer(new int[] {0, 0, 0});   // Intialization = (startIndex, endIndex, DistanceCost);
        
        // once the edges is equal to num, it means that there is a cycle
        while(!minHeap.isEmpty() && visited.size() < num) {
           
            int[] curr = minHeap.poll();
            int endId = curr[1];
            int currCost = curr[2];
            
            // check whether we visit this node previously or not
            if(visited.contains(endId)) continue;
            
            // otherwise it is not visited, it means it is able to create a edge with another node
            
            minCost += currCost;
            visited.add(endId);     // mark the current node as visited
            
            // now visiting each node from the current node and calculate the cost
            for(int i = 0; i < num; i++) {
                if(!visited.contains(i)) {
                    minHeap.offer(new int[] {endId, i, CalDistance(points, endId, i)});
                }
            }
        }
        
        return minCost;
    }
    
    private int CalDistance(int[][] points, int i, int j) {
        
        // calculating the machattan distance
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}