/*
Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

Example 1:

Input

["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]

Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]

Constraints:
	0 <= key, value <= 10^6
	At most 10^4 calls will be made to put, get, and remove.


METHOD 1:(USING LARGE ARRAY)

TIME: O(1),  average and O(N) worst case - for all get(),put() and remove() methods.

SPACE: O(N), where N is the number of entries in HashMap
*/

class MyHashMap {
    
    int[] map;
    
    public MyHashMap() {
        map = new int[1000001];
        Arrays.fill(map, -1);
    }
    
    public void put(int key, int value) {
        map[key] = value;
    }
    
    public int get(int key) {
        
        if(map[key] != -1) {
            return map[key];
        }
        return -1;
    }
    
    public void remove(int key) {
        
        if(get(key) != -1) {
            map[key] = -1;
        }
    }
}


/*
METHOD 2:(USING ARRAY OF LINKEDLIST)

TIME: O(1),  average and O(N) worst case - for all get(),put() and remove() methods.

SPACE: O(N), where N is the number of entries in HashMap, but space complaxity is better than the previous one.
*/

class MyHashMap {

    // create a listNode class for (key, value) pair
    class ListNode {
        
        int key, val;
        
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    // Here we use a Array of LinkedList
    private List<ListNode>[] nodes;
    
    // intialize it with 10^4 as there are maximum 10^4 calls
    public MyHashMap() {
        nodes = new List[10000];
    }
    
    // fetching the hash code for the current key
    private int getKeyHash(int key) {
        return Integer.hashCode(key) % nodes.length;
    }
    
    public void put(int key, int value) {
        
        int hashIndex = getKeyHash(key);
        
        // if the there is nothing then first intialize a LinkedList there 
        // and then put the current (key, value) pair
        if(nodes[hashIndex] == null) {
            nodes[hashIndex] = new LinkedList<>();
            nodes[hashIndex].add(new ListNode(key, value));
        }
        // if it not null
        else {
            // check for the existence of the current key
            for(ListNode l : nodes[hashIndex]) {
                
                // if present then update its value
                if(l.key == key) {
                    l.val = value;
                    return;
                }
            }
            
            // if the key is not present then put the (key, value) pair
            nodes[hashIndex].add(new ListNode(key, value));
        }
    }
    
    public int get(int key) {
        
        int hashIndex = getKeyHash(key);
        
        // if not null
        if(nodes[hashIndex] != null) {
            
            // finding the key
            for(ListNode li : nodes[hashIndex]) {
                
                // if present return the value
                if(li.key == key) {
                    return li.val;
                }
            }
        }
        
        // otherwise return -1
        return -1;
    }
    
    public void remove(int key) {
        
        int hashIndex = getKeyHash(key);
        ListNode removedEle = null;
        
        // if null
        if(nodes[hashIndex] == null) {
            return;
        }
        // if not null
        else {
            
            // find the key
            for(ListNode li : nodes[hashIndex]) {
                
                // if present then simply remove that object
                if(li.key == key) {
                    removedEle = li;
                }
            }
            
            // not present
            if(removedEle == null) return;
            
            // present, remove
            nodes[hashIndex].remove(removedEle);
        }
    }
}


// GENERIC VERSION

import java.util.*;
import java.lang.*;
import java.io.*;


class Codechef
{
    static class HashMap<K, V> {
        
	// creating a class for hashnode holds the (key, value) pair
        private class HMNode {
            K key;
            V val;
            
            HMNode(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }
        
        int size = 0; // n --> denoting the total no. of nodes

        LinkedList<HMNode>[] buckets;  // N --> no. of buckets
        
        public HashMap() {
            initBuckets(16);
            size = 0;
        }
        
	// initializing the buckets
        private void initBuckets(int N) {

            buckets = new LinkedList[N];
            
            for(int i = 0; i < N; i++) {
		
		// at each bucket initializing a new linkedlist
                buckets[i] = new LinkedList<>();
            }
        }
        
        // why  abs ? bcz the hashcode may be '-'
        private int getKeyHash(K key) {

            int hc = key.hashCode();
            return Math.abs(hc) % buckets.length; 
        }
        
        private int getKeyIndexinBucket(K key, int hashIndex) {
            
            int i = 0;
            
	    // finding the index of the key in the bucket[i]
            for(HMNode hm : buckets[hashIndex]) {
                if(hm.key.equals(key)) {
                    return i;
                } 
                i++;
            }
            return -1;
        }
        
        public void put(K key, V value) {
            
	    // getting the hash value of the current key
            int hashIndex  = getKeyHash(key);

	    // getting the index of the current key in the current bucket
            int idx = getKeyIndexinBucket(key, hashIndex);
            
	    // if the key is already present then simply update its value
            if(containsKey(key)) {
                HMNode node = buckets[hashIndex].get(idx);
                node.val = value;
            }
	    // if not present then create a new (key, value) pair
            else {
                HMNode node = new HMNode(key, value);
                buckets[hashIndex].add(node);
                size++;
            }
            
  	    // if the lamba value cross the given threshold, then we have to perfom the rehashing
            double lambda = size * 1.0 / buckets.length;
            
            if(lambda > 8.0) {
                rehash();
            }
        }
        
        public void rehash() {
            
            // firts copy the old buckets
            LinkedList<HMNode>[] oldBucket = buckets;
            
            // initializing a  new bucket with double of previous size 
            initBuckets(oldBucket.length * 2);
            
            // now again rearranging the old buckets elements and put them into the new bucket array 
            for(int i = 0 ; i < oldBucket.length; i++) {
                for(HMNode node : oldBucket[i]) {
                    put(node.key, node.val);
                }
            }
        }
        
        public V get(K key) {
            
            int hashIndex  = getKeyHash(key);
            int idx = getKeyIndexinBucket(key, hashIndex);
            
	    // if the key is present
            if(idx != -1) {
                HMNode node = buckets[hashIndex].get(idx);
                return node.val;
            }
	    // not present
            else {
                return null;
            }
        }
        
        public K remove(K key) {
            
            int hashIndex  = getKeyHash(key);
            int idx = getKeyIndexinBucket(key, hashIndex);
            
	    // if the key is present 
            if(idx != -1) {
                HMNode node = buckets[hashIndex].remove(idx);
                size--;
                return node.key;
            }
	    // not present
            else {
                return null;
            }
        }
        
        public boolean containsKey(K key) {
            
            int hashIndex  = getKeyHash(key);
            int idx = getKeyIndexinBucket(key, hashIndex);
            
	    // if the key is present
            if(idx != -1) {
                return true;
            }
	    // not present
            else {
                return false;
            }
        }
        
        public ArrayList<K> keySet() {
            
            ArrayList<K> keys = new ArrayList<>();
            
	    // fetching all the keys from each bucket
            for(int i = 0; i < buckets.length; i++) {
                for(HMNode node : buckets[i]) {
                    keys.add(node.key);
                }
            }
            return keys;
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		HashMap myHashMap  = new HashMap();

        	myHashMap.put("India", 300);
        	myHashMap.put("Pakistan", 100);
        	System.out.println(myHashMap.keySet());
        	System.out.println(myHashMap.get("India"));
        	System.out.println(myHashMap.get("Australia"));
        	myHashMap.put("Pakistan", 50);
        	System.out.println(myHashMap.keySet());
        	System.out.println(myHashMap.get("Pakistan"));
        	System.out.println("Removed: "+ myHashMap.remove("Pakistan"));
        	System.out.println(myHashMap.get("Pakistan"));
        	System.out.println(myHashMap.keySet());
	}
}
