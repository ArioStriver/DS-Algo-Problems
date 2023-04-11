/*
METHOD 1: (USING STACK)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public String removeStars(String s) {
        
        Stack<Character> st = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c != '*') {
                st.push(c);
            }
            else {
                st.pop();
            }
        }

        String res = "";
        while(!st.isEmpty()) {
            res = st.pop() + res;
        }

        return res;
    }
}


/*
METHOD 2: (USING STRINGBUILDER FUNCTION)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public String removeStars(String s) {
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '*') {
                sb.deleteCharAt(sb.length()-1);
            }
            else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}


/*
METHOD 3: (USING TWO POINTER APPROACH)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public String removeStars(String s) {
        
        char[] ch = new char[s.length()];
        int j = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '*') {
                j--;   // overwriting the characters
            }
            else {
                ch[j++] = c;   // pushing the current characters
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < j; i++) {
            sb.append(ch[i]);
        }

        return sb.toString();
    }
}
