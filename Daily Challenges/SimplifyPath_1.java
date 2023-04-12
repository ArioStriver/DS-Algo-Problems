/*
Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.


Example 1:

Input: path = "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:

Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:

Input: path = "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.


METHOD 1:

TIME: O(N).

SPACE: O(2N), at worst case.
*/

class Solution {
    public String simplifyPath(String path) {
        
        // splitting the entire path for the slash
        String[] paths = path.split("/");

        Stack<String> st = new Stack<>();

        for(int i = 0; i < paths.length; i++) {
            // current string is '.' or "" ignore that
            if(paths[i].equals(".") || paths[i].equals("")) continue;

            // if '..' move to the parent directory, if the stack is not empty
            if(paths[i].equals("..")) {
                if(!st.isEmpty()) {
                    st.pop();
                }
            }
            // otherwise push the string into the stack
            else {
                st.push(paths[i]);
            }
        }

        // join '/' in b/w every string and at the first
        return "/" + String.join("/", st);
    }
}


/*
METHOD 2:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public String simplifyPath(String path) {
     
        Stack<String> st = new Stack<>();
        
        String ans ="";
        
        for(int i = 0; i < path.length(); i++){
            
            /* we will clear the temporary String
               every time to accommodate new directory
               name or command.
               dir will contain "a", "b", "..", "."; */
            String dir = "";
            
            // When we see multiple "////" we just ignore them 
            // as they are equivalent to one single "/".
            while(i < path.length() && path.charAt(i) == '/'){
                i++;
            }
            
            while(i < path.length() && path.charAt(i) != '/'){
                dir += path.charAt(i);
                i++;
            }
            
            // now we have check three conditions
            // c1 : ".."
            if(dir.equals("..")){
                
                // we have to move to our parent directory
                // here moving to the parent directory means performing pop opeartion
                if(!st.isEmpty()){
                    st.pop();
                }
            }
            // c2 : "."
            else if(dir.equals(".")){
                continue;
            }
            // c3 : "a" || "b" ...
            else{
                if(dir.length() != 0)
                    st.push(dir);
            }
        }
        
        while(!st.isEmpty()){
           ans = "/" + st.pop() + ans;
        }
        
        if(ans.length() == 0) return "/";
        
        
        return ans;
    }
}