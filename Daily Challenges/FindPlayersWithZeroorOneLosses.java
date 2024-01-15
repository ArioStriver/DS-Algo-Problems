
// Solution:

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        
        // why TreeMap ? bcz TreeMap gives us the elements in sorted order
        Map<Integer, Integer> loosesList = new TreeMap<>();

        // players having 0 frequency means = all win(0 loose)
        // players having frequency > 0 = looses some matches
        for(int[] M : matches) {
            loosesList.put(M[0], loosesList.getOrDefault(M[0], 0));       // win count - no chnages
            loosesList.put(M[1], 1 + loosesList.getOrDefault(M[1], 0));  // loose count - record it
        }

        List<List<Integer>> ans = Arrays.asList(new ArrayList<>(), new ArrayList<>());

        for(Integer player : loosesList.keySet()) {
            // gievn condition in the question
            if(loosesList.get(player) <= 1) {
                ans.get(loosesList.get(player)).add(player);  // using frequency as an index of list
            }
        }

        return ans;
    }
}


// Alternate Solution:

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        
         int[] looses = new int[100001];

         for(int[] M : matches) {
             int win = M[0];
             int loss = M[1];

             if(looses[win] == 0) {
                looses[win] = -1;
             }

             if(looses[loss] == -1) {
                 looses[loss] = 1;
             }
             else {
                 looses[loss]++;
             }
         } 
        
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> zeroLoss = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();

        for(int i = 0; i < looses.length; i++) {
            if(looses[i] == -1) {
                zeroLoss.add(i);
            }
            else if(looses[i] == 1) {
                oneLoss.add(i);
            }
        }

        ans.add(zeroLoss);
        ans.add(oneLoss);

        return ans;
    }
}
