package com.algo.array;

import java.util.Arrays;

public class Palindrome {

	public static int isArrayPalindrome(int arr[]) 
    { 
		 int n = arr.length; 
		  
        // Initialise flag to zero. 
        int flag = 0; 
  
        // Loop till array size n/2. 
        for (int i = 0; i <= n / 2 && n != 0; i++) { 
  
            // Check if first and last element are different 
            // Then set flag to 1. 
            if (arr[i] != arr[n - i - 1]) { 
                flag = 1; 
                break; 
            } 
        } 
  
        // If flag is set then print Not Palindrome 
        // else print Palindrome. 
        return flag;
        
    } 
  
	
	public static int isNumberPalindrome(int n) 
	{ 
	  
	    // get the reverse of n 
	    int rev_n = reverseDigits(n); 
	  
	    // Check if rev_n and n are same or not. 
	    if (rev_n == n) 
	        return 1; 
	    else
	        return 0; 
	} 
	
	private static int reverseDigits(int num) {
		 int rev_num = 0; 
	    while (num > 0) { 
	        rev_num = rev_num * 10 + num % 10; 
	        num = num / 10; 
	    } 
	    return rev_num; 
	}


	public static int isStringPalindrome(String s) 
	  { 
	    // reverse the given String 
	    String reverse = new StringBuffer(s).reverse().toString(); 
	  
	    // check whether the string is palindrome or not 
	    if (s.equals(reverse)) 
	      return 1; 
	  
	    else
	      return 0;
	  } 
	
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 2, 1 }; 
       
        System.out.println("Is " + Arrays.toString(arr) + " a Palindrome array? -> " + 
                (isArrayPalindrome(arr) == 1 ? "true" : "false")); 
      
        
        int n = 4562; 
        System.out.println("Is " + n + " a Palindrome number? -> " + 
            (isNumberPalindrome(n) == 1 ? "true" : "false")); 
      
        n = 2002; 
          
        System.out.println("Is " + n + " a Palindrome number? -> " + 
            (isNumberPalindrome(n) == 1 ? "true" : "false")); 
  
        System.out.println("Is " + "malayalam" + " a Palindrome array? -> " + 
                (isStringPalindrome("malayalam") == 1 ? "true" : "false")); 
        
        System.out.println("Is " + "GeeksforGeeks" + " a Palindrome array? -> " + 
                (isStringPalindrome("GeeksforGeeks") == 1 ? "true" : "false")); 
      
	}

}
