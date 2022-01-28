/*
Design Add and Search Words Data Structure

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.



METHOD: (USING TRIE)

TIME: 	addWord() - O(N), N = length of the new word
	search() - Worst case: O(M), M = the total number of characters in the Trie

*/

class WordDictionary {

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        
        TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }     
    }
    
    final private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        
        TrieNode curr = root;
        
	// adding the word in the trie data structure
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(curr.children[ch -'a'] == null){
                curr.children[ch -'a'] = new TrieNode();
            }
            curr = curr.children[ch -'a'];
        }

	// marked end of the word as true
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        
       return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] ch, int index, TrieNode cur){
        
        if(index == ch.length){
            return cur.isEnd;
        }
        
        // if the current character is '.'
        if(ch[index] == '.'){
            
            // then try out all possibilities means there are 26 childrens under a single charcater node
            // from there find the first not null one and from there move to the next one
            for(int i = 0; i < cur.children.length; i++){
                if(cur.children[i] != null && match(ch, index + 1, cur.children[i])){
                    return true;
                }
            }
        }
        // if the current character is not '.'
        else{
            return cur.children[ch[index] - 'a'] != null && match(ch, index + 1, cur.children[ch[index] - 'a']);
        }
        
        return false;
    }
}
