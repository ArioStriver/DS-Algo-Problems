/*
AMAZON

You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]

Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

Example 2:

Input: s = "eccbbbbdec"
Output: [10]


METHOD:

TIME: O(N), where N is the length of S.

SPACE: O(1).
*/


class Solution {
    public List<Integer> partitionLabels(String S) {
        
        int n = S.length();
        List<Integer> res = new ArrayList<>();
        
        if(S == null || S.length() == 0){
            return null;
        }
        
        int[] lastOccurance = new int[26];
        
        // s1 : record the last occurance of each character, so that we can consider
        // the string upto that index under the current partition
        for(int i = 0; i < n; i++){
            int index = S.charAt(i) - 'a';      
            lastOccurance[index] = i;
        }
        
        // s2: now we have to partition the string
        int i = 0;
        
        while(i < n){
            
            char startChar = S.charAt(i);
            int terminalIndex = lastOccurance[startChar-'a'];
            
            // now finding is there any character upto the terminal index which occurs 
            // after the terminalIndex, if so then we need consider the string upto that
            // for current partition
            for(int j = i+1; j <= terminalIndex; j++){
                terminalIndex = Math.max(terminalIndex, lastOccurance[S.charAt(j)-'a']);
            }
            
            // finding the length of the partitioned string
            res.add(terminalIndex - i + 1);
            
            i = terminalIndex + 1;      // move the pointer to the next partition
        }
        
        return res;
    }
}