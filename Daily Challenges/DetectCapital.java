// Q. DETECT CAPITAL

//We define the usage of capitals in a word to be right when one of the following cases holds:

//All letters in this word are capitals, like "USA".
//All letters in this word are not capitals, like "leetcode".
//Only the first letter in this word is capital, like "Google".
//Given a string word, return true if the usage of capitals in it is right.

//Example 1:

//Input: word = "USA"
//Output: true


//Example 2:

//Input: word = "FlaG"
//Output: false


//METHOD 1:

// TIME: O(N).

// SPACE: O(1).


class Solution {
    public boolean detectCapitalUse(String word) {
        
        int n = word.length();
        
        if(n == 1) return true;
        
        // case 1 : if all are capitals
        if(Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))){
            
            // now check for the rest of the characters
            for(int i = 2; i < n; i++){
                
                // if there is lower
                if(Character.isLowerCase(word.charAt(i))){
                    return false;
                }
            }
        }
        // case 2 & 3 : all lowercase or Only first character is capital
        else{
             // if there is any uppercase character then return false
            for(int i = 1; i < n; i++){
                if(Character.isUpperCase(word.charAt(i))){
                    return false;
                }
            }
        }
        
        return true;
    }
}


// METHOD 2:(USING REGULER EXPRESSION)

// TIME AND SPACE IS SAME

class Solution {
    public boolean detectCapitalUse(String word) {
        
        // using Regular Expression
        // where "." can matches any char.
        return word.matches("[A-Z]*|.[a-z]*");
    }
}