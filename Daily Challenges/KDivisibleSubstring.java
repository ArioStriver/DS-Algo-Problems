/*
Takahashi has a string S of length N consisting of digits from 0 through 9.

He loves the prime number P. He wants to know how many non-empty (contiguous) substrings of S - there are N×(N+1)/2 of them - are divisible by P when regarded as 
integers written in base ten.

Here substrings starting with a 0 also count, and substrings originated from different positions in S are distinguished, even if they are equal as strings or integers.

Compute this count to help Takahashi.

Constraints
1≤N≤2×10^5
S consists of digits.
∣S∣=N
2≤P≤10000
P is a prime number.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Initialize count = 0. Take all the sub-strings of str and check whether they are divisible by K or not. If yes then update count = count + 1. Print the 
		count in the end.

TIME: O(N^2).

SPACE: O(1).
*/

import java.util.*;
import java.io.*;

public class Main{
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] in = br.readLine().split(" ");
    String s = br.readLine();
    
    int n = Integer.parseInt(in[0]);
    int k = Integer.parseInt(in[1]);
    
    int count = 0;
    
    // generating each substring
    for(int i = 0; i < n; i++){

      int num = 0;
      
      for(int j = i; j < n; j++){
        
        num = num * 10 + (s.charAt(j) - '0');
        
        if(num % k == 0) 
          count++;
      }
    }
    
    System.out.println(count);
  }
}

/*
METHOD 2:
