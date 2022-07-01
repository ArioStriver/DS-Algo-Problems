/*
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does 
not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8

Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.

Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91


METHOD 1:(USING GREEDY TECHNIQUE)
	APPROACH:
		 Here our goal is to put the type of boxes with the most units in order to achieve maximum units.

TIME: O(NlogN).

SPACE: O(1).
*/

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
       
        // first sorting the boxTypes array depending the units giving the maximum units priority first
        Arrays.sort(boxTypes, (a,b)->b[1]-a[1]);
        
        int maxUnits = 0;
        
        for(int i = 0; i < boxTypes.length; i++) {
            
            int boxes = boxTypes[i][0];
            
            // if the truck has capacity then take all the boxes of current type
            if(truckSize >= boxes) {
                maxUnits += (boxes * boxTypes[i][1]);
                truckSize -= boxes;
            }
            // otherwise take partial of the current box type
            else {
                maxUnits += (truckSize * boxTypes[i][1]);
                return maxUnits;
            }
        }
        
        return maxUnits;
    }
}


/*
METHOD 2:(COUNTING SORT)
	APPROACH:	
		Explanation: https://leetcode.com/problems/maximum-units-on-a-truck/discuss/999125/JavaPython-3-Sort-by-the-units-then-apply-greedy-algorithm.

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
       
        int[] bucket = new int[1001];
        
        // here we consider the units as the index of the bucket
        for(int[] unit : boxTypes) {
            
            // if there are no boxes
            if(bucket[unit[1]] == 0) {
                bucket[unit[1]] = unit[0];
            }
            // bcz it may happen that the different box types have same numberOfUnitsPerBox
            // otherwise there are boxes present, so add the current boxes also
            else {
                bucket[unit[1]] += unit[0];
            }
        }
        
        int maxUnitsLoaded = 0;
        
        // traversing from right to left bcz consider taking maximum units first
        for(int i = bucket.length-1; i >= 0; i--) {
            
            // it means that current unit has some boxes in it
            if(bucket[i] != 0) {
                
                // truck has the capacity to load the current boxes
                if(truckSize >= bucket[i]) {
                    maxUnitsLoaded += (bucket[i] * i);
                    truckSize -= bucket[i];
                }
                // truck don't have that much capacity to load all the boxes, so take partial
                else {
                    maxUnitsLoaded += (truckSize * i);
                    return maxUnitsLoaded;
                }
            }
        }
        
        return maxUnitsLoaded;
    }
}