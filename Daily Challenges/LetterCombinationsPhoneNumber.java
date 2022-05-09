/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:

Input: digits = ""
Output: []

Example 3:

Input: digits = "2"
Output: ["a","b","c"]


METHOD:(USING BACKTRACKING)

TIME: O(4^N*N), where N, is the length of input string. 4^N for building every possible string combination and N to form the string by joining each character. Here, 4 
      is chosen assuming the worst case where each digit will be 7 or 9 and we would have 4*4*4*4 total string combinations.

SPACE: O(N), the max recursion depth will be N, where N is the length of input string.
*/

class Solution {
    public List<String> letterCombinations(String digits) {
        
        List<String> ans = new ArrayList<>();
        
        if(digits == null || digits.length() == 0) return ans;
        
        // mapping the letters
        String[] numMap = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        generateCombinations(digits, numMap, 0, new StringBuilder(), ans);
        
        return ans;
    }
    
    private void generateCombinations(String digits, String[] numMap, int start, StringBuilder sb, List<String> ans){
        
        if(start == digits.length()){
            ans.add(new String(sb));
            return;
        }
        
        // getting the curent number form the digits string
        int index = digits.charAt(start) - '0';
            
        // this loop will goes from 0 to current index's string length
        for(int i = 0; i < numMap[index].length(); i++){
            
            // appending the current character
            sb.append(numMap[index].charAt(i));
            
            // backtrack
            generateCombinations(digits, numMap, start + 1, sb, ans);
            
            sb.deleteCharAt(sb.length() - 1);   // for trying out all possibilities
        }
    }
}


// ANOTHER METHOD:

class Solution {
    public List<String> letterCombinations(String digits) {
        
        LinkedList<String> res = new LinkedList<>();
        
        if(digits == null || digits.length() == 0) {
            return res;
        }
        
        // creating a map of where it represents the number & its corresponding value 
        String[] numMap = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        // adding a empty string
        res.add("");
        
        for(int i = 0; i < digits.length(); i++) {
            
            // extracting the current digit  form the given input
            int idx = Character.getNumericValue(digits.charAt(i));
            
            /* continue untill the length of the peek element is = i, it means that ith length 
               string is present in the string and now we have to create a new string of length
               (previous length + 1) to get out desired output */
            while(res.peek().length() == i) {
                
                String temp = res.remove();
                
                // traverse through the value at the current index
                for(char c : numMap[idx].toCharArray()) {
                    
                    // adding the previous and the current character
                    res.add(temp + c);
                }
            }
        }
        
        return res;
    }   
}