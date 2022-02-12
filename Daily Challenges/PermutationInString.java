/*
Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.
*/


METHOD 1:
	APPROACH:
		The simplest method is to generate all the permutations of the short string and to check if the generated permutation is a substring of the longer string.

TIME: O(N!).
*/

public class Solution {
    boolean flag = false;
    
    public boolean checkInclusion(String s1, String s2) {
       
        permute(s1, s2, 0);
        return flag;
    }
    
    private void permute(String s1, String s2, int l){
        
        // base case
        if(l == s1.length()){
            // finding the index of current s1 into the s2 string to check whether it conatins s1
            if(s2.indexOf(s1) >= 0){
                flag = true;
            }
        }
        else{
            for(int i = l; i < s1.length(); i++){
                s1 = swap(s1, l, i);
                permute(s1, s2, l+1);
                s1 = swap(s1, l, i);
            }
        }
    }
    
    private String swap(String s1, int i, int j){
        
        char[] t = s1.toCharArray();
        char p = t[i];
        t[i] = t[j];
        t[j] = p;
        
        return String.valueOf(t);
    }
}


// METHOD 2:(USING HASHING)

// TIME: O(L1 + 26*L1*(L2 - L1)), where L1 and L2 is the length of the s1 and s2.

// SPACE: O(1). s1map and s2map of size 26 is used.


class Solution {
    
    public boolean checkInclusion(String s1, String s2) {
        
        // edge case
        if(s1.length() > s2.length()) return false;
        
        int[] s1Freq = new int[26];
        
        // 1. storing the frequency of each characetr in the s1 string
        for(char c : s1.toCharArray()){
            s1Freq[c - 'a']++;
        }
        
        // 2. now here we are creating the all substring of size s1 from s2
        // then we compare their frequecy with the s1's frequecy array
        // if there is a match it  means that the s2 string contains a permutation of s1 string
        
        
        for(int i = 0; i <= s2.length() - s1.length(); i++){
            
            int[] s2Freq = new int[26];
            
            // creating the substring od size s1 from s2
            for(int j = 0; j < s1.length(); j++){
                s2Freq[s2.charAt(i + j) - 'a']++;   
            }
            
            // if bothe have the same frequency then it contains the permution of s1
            if(freqMatches(s1Freq, s2Freq)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean freqMatches(int[] s1, int[] s2){
        
        for(int i = 0; i < 26; i++){
            if(s1[i] != s2[i])
                return false;
        }
        
        return true;
    }
}


// METHOD 3:(USING HASHING & SLIDING WINDOW)

// TIME: O(L1 + 26*(L2 - L1)).

// SPACE: O(1).


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        
        // base case
        if(s1.length() > s2.length()) return false;
        
        int[] s1s2Freq = new int[26];
        
        // storing the frequency of s1
        for(int i = 0; i < s1.length(); i++){
            s1s2Freq[s1.charAt(i) - 'a']++;
            s1s2Freq[s2.charAt(i) - 'a']--;
        }
        
        // if all seros it means that s2 contains a permutation of s1
        if(allZeros(s1s2Freq)) return true;
        
        int windowSize = s1.length();  // size of the sliding window
        
        // now here we are traversing through each substring of size s1
        // and then check if the array conatins all zeros it means we found our answer

        for(int i = windowSize; i < s2.length(); i++){
            
            // considering the current element in the window 
            // which is coming from the right side

            s1s2Freq[s2.charAt(i) - 'a']--;
            
            // then the left most element is going out from the window 
            // bcz basically when we slide the window the left most elemnt goes out of the window
            // and a element adding into the window from the right side

            s1s2Freq[s2.charAt(i - windowSize) - 'a']++;
            
            if(allZeros(s1s2Freq))
                return true;
        }
        
        return false;
    }
    
    private boolean allZeros(int[] a){
        
        for(int n : a){
            if(n != 0)
                return false;
        }
        return true;
    }
}