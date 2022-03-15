/*
Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical 
path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive 
slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

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

TIME: O(lenght(path)).
*/

class Solution {
    public String simplifyPath(String path) {
     
        Stack<String> st = new Stack<>();
        
        String ans ="";
        ans += "/";
        
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
        
        // a temporary Stack (st1) which will contain
        // the reverse of original Stack(st).
        Stack<String> st1 = new Stack<String>();
        while (!st.empty())
        {
             
            st1.push(st.pop());
            // st.pop();
        }
        
        while(!st1.isEmpty()){
            if(st1.size() != 1){
                ans += (st1.pop() + "/");
            }
            // for last character there is no "/" at the end
            else{
                ans += st1.pop();
            }
        }
        
        return ans;
    }
}


// METHOD 2:(WE CAN REDUCE THE SPACE COMPLEXITY BY AVOIDING USING ANOTHER STACK)

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


// METHOD 3:

class Solution {
    public String simplifyPath(String path) {
     
        Stack<String> st = new Stack<>();
        
        // splitting the string from the '/' 
        for(String str : path.split("/")){
            
            // c1 : if ".."
            if(str.equals("..")){
                if(!st.empty()){
                    st.pop();
                }
            }
            // in case of "." ignore, else for file name push it inot the stack.
            else{
                if(str.length() > 0 && !str.equals(".")){
                    st.push(str);
                }
            }
        }
        
        // each path starts with a single "/" and there is "/" at the end of the each directory except the last one
        return "/" + String.join("/", st);
    }