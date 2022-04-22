/*
Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 

Example 1:

Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]

Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)


METHOD 1:(USING ARRAY)

TIME: O(1).

SPACE: O(N).
*/

class MyHashSet {
    
    // here we basically creating an array of size 1 million
    boolean[] set;
    
    public MyHashSet() {
        set = new boolean[1000001];
    }
    
    public void add(int key) {
        
        // using key as a index
        set[key] = true;
    }
    
    public void remove(int key) {
        
        // removing element
        set[key] = false;
    }
    
    public boolean contains(int key) {
        return set[key];
    }
}

/*
METHOD 2:

TIME: O(N).

SPACE: Requires less space as compared to the previous soltion, but here we have to comprpmise with the time.
*/

class MyHashSet {

    private int capacity = 0;
    private List<Integer>[] set = null;
    
    public MyHashSet() {
        capacity = 100;
        set = new List[capacity];
    }
    
    // creating function to get the hash value of the key
    private int getKeyHash(int key) {
        return key % capacity;
    }
    
    public void add(int key) {
        
        int hashIndex = getKeyHash(key);
        
        if(set[hashIndex] == null) {
            set[hashIndex] = new LinkedList<>();
        }
        
        // if the current element is not present
        if(!contains(key)) {
            set[hashIndex].add(key);
        }
    }
    
    public void remove(int key) {
        
        int hashIndex = getKeyHash(key);
        
        // removing the current element
        if(contains(key)) {
            set[hashIndex].remove(set[hashIndex].indexOf(key));
        }
    }
    
    public boolean contains(int key) {
        
        int hashIndex = getKeyHash(key);
        
        // either there is no list at the curent has index or the value is removed 
        // previously
        if(set[hashIndex] == null || set[hashIndex].indexOf(key) == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}