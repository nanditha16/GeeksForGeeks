package com.algo.array;

import java.util.ArrayList;
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
	/*
	boolean isListPalindrome(ListNode head) {
		if(head == null || head.next==null)
	        return true;
	 
	    //find list center
	    ListNode fast = head;
	    ListNode slow = head;
	 
	    while(fast.next!=null && fast.next.next!=null){
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	 
	    ListNode secondHead = slow.next;
	    slow.next = null;
	 
	    //reverse second part of the list
	    ListNode p1 = secondHead;
	    ListNode p2 = p1.next;
	 
	    while(p1!=null && p2!=null){
	        ListNode temp = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        p2 = temp;
	    }
	 
	    secondHead.next = null;
	 
	    //compare two sublists now
	    ListNode p = (p2==null?p1:p2);
	    ListNode q = head;
	    while(p!=null){
	        if(p.val != q.val)
	            return false;
	 
	        p = p.next;
	        q = q.next;
	 
	    }
	 
	    return true;
	    
	}

	public ListNode reverse(ListNode head){
	    ListNode prev = null;
	    while(head!=null){
	        ListNode temp = head.next;
	        head.next= prev;
	        prev= head;
	        head = temp;
	    }
	    return prev;
	}
	*/
	// Definition for singly-linked list:
	 class ListNode<T> {
	   ListNode(T x) {
		     value = x;
	   }
	   T value;
	   ListNode<T> next;
	 }
	
	boolean isListPalindrome(ListNode<Integer> l) {
	    if (l == null) return true;
	    if (l.next == null) return true;
	    
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    while (l != null) {
	    	list.add(l.value);
	        l = l.next;
	    }
	    
	    int last = list.size() -1;
	    int i = -1;
	    while (i++ < last - i) {
	        if (!list.get(i).equals(list.get(last - i))) {
	            return false;
	        }
	    }
	    return true;
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
