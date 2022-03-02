/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative 
positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.

METHOD 1:(USING TWO POINTER)

TIME: O(N + M), where N is the length s and M is the length of t.

SPACE: O(1).
*/

class Solution {
    public boolean isSubsequence(String s, String t) {
        
        // edge case
        // if s is null, then s is a subsquence of t
        if(s.length() == 0) return true;
        
        int i = 0, j = 0;
        
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        
        return i == s.length();
    }
}


/*
Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9, and you want to check one by one to see if t has its subsequence. In this 
scenario, how would you change your code?

So when k >= 10^9 then the first approach will become slow as it checking charcaters one by one. So in that case we can use Binary Search approach.

Record the indexes for each character in t, then if s[i] matches t[j], then s[i+1] should match a character in t with index bigger than j. This can be reduced to find 
the first element larger than a value in an sorted array (find upper bound), which can be achieved using binary search.

Read the problem statement again:

t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

log2(500,000) ~= 19
N = 100

NlogN = 19 * 100 = 1,900
N = 500,000

So no, NlogN is not larger than N, it is much smaller, going by the information given in the problem statement.

So if the query is 10^9 then for first approach it will be TIME: 10^9 * (N + M), where as for Binary search apparoach it will be given below, whicgh is less than A1.

TIME: O(M + NlogN).
*/

class Solution {
    public boolean isSubsequence(String s, String t) {
        
        // edge case
        // if s is null, then s is a subsquence of t
        if(s.length() == 0) return true;
        
        List<Integer>[] CharIdxes = new List[26];
        
        // Record the indexes for each character in t
        for(int i = 0; i < t.length(); i++){
            if(CharIdxes[t.charAt(i)-'a'] == null){
                
                // initializing a list for each character
                CharIdxes[t.charAt(i)-'a'] = new ArrayList<>();
            }
            // adding the index of the current character
            CharIdxes[t.charAt(i)-'a'].add(i);
        }
        
        // now we are traversing in the s string to check whether s is a subsequence of t or not
        
        int lastFound = -1;
        
        for(int i = 0; i < s.length(); i++){
            
            // if the current character is not present then return false
            if(CharIdxes[s.charAt(i)-'a'] == null) 
                return false;
            
            int index = binarySearchHelper(CharIdxes[s.charAt(i)-'a'], lastFound);
            
            if(index == -1) return false;
            
            lastFound = index;
        }
        
        return true;
    }
    
    private int binarySearchHelper(List<Integer> li, int lastFound) {
        
        int start = 0, end = li.size() - 1, result = -1;
        
        while(start <= end) {
            
            int mid = start + (end - start) / 2;
            
            // if the current charcater's index > the previous character occured then take it as answer
            if(li.get(mid) > lastFound){
                result = li.get(mid);
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        
        return result;
    }
}