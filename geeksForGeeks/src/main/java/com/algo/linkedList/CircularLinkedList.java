package com.algo.linkedList;

import java.util.HashSet;

public class CircularLinkedList {

	/* Single Linked list node */
	static class Node  
	{ 
	    int data; 
	    Node next; 
	    
	    public Node() {
			
		}
	    
	    public Node(int data) {
	    	this.data = data;
	    	this.next = null;
	    }
	
	}; 
	
	/*
	 * Convert Singly Linked list to circular linked list
	 */
	static Node circular(Node head) 
	{ 
	    // start as head
	    Node start = head; 
	  
	    // traverse till the end  
	    while (head.next != null) {
	        head = head.next; 
	    }    
	    // if head.next points to null  then link to the start i.e head of the linked list
	    head.next = start; 
	    //return the tail of the list where we add the head
	    return head; 
	} 
	
	/*
	 * Convert circular linked list to Singly Linked list at given point lastNodeN1
	 */
	private static Node singular(Node head, Node lastNodeN1) {
		// start as head
	    Node start = head; 
	  
		while (head != lastNodeN1) {
	        head = head.next; 
	    }  
		start = head.next;
		head.next = null;
		head = start;
		return head; 
	}
	
	/*
	 * Add at the start
	 */
	static Node push(Node head, int data) 
	{ 
	    // Allocate dynamic memory for newNode. 
	    Node newNode = new Node(); 
	  
	    // Assign the data into newNode. 
	    newNode.data = data; 
	  
	    // newNode.next assign the  address of head node.
	    newNode.next = head; 
	  
	    // newNode become the headNode. 
	    head = newNode; 
	      
	    return head; 
	}
	
	/* 
	 * Function that display the elements 
	 * of circular linked list.
	 */
	static void displayCircularList( Node node) 
	{ 
	    Node start = node; 
	  
	    while (node.next != start) 
	    { 
	        System.out.print(" "+ node.data); 
	        node = node.next; 
	    } 
	      
	    // Display the last node of circular linked list.   
	    System.out.println(" " + node.data); 
	} 
	
	/* 
	 * Function that display the elements 
	 * of singly linked list.
	 */
	static void displaySinglyList( Node node) 
	{ 
	    while (node != null) 
	    { 
	        System.out.print(" "+ node.data); 
	        node = node.next; 
	    } 
	    System.out.println();
	} 

	
	/*
	 * Floyd’s Cycle-Finding Algorithm only detects loop
	 * 
	 */
	public static int detectLoop(Node node) 
    { 
		Node head = node; 
        Node slow_p = head, fast_p = head; 
        
        while (slow_p != null && fast_p != null && fast_p.next != null) { 
            slow_p = slow_p.next; 
            fast_p = fast_p.next.next; 
            if (slow_p == fast_p) { 
                System.out.print("Found loop: "); 
                return 1; 
            } 
        } 
        System.out.print(" No Loop found : "); 
        return 0; 
    } 
	
	/*
	 * To get the point at which loop first starts using HashSet
	 */
	private static int detectFirstNodeInLoop(Node head) {
		HashSet<Node> start = new HashSet<Node>(); 
        
		while (head != null) { 
			// if present in HashSet, then loop is present
			if (start.contains(head)) {
                return head.data; 
			}
			// If first visit, save it in HashSet
			start.add(head);
			head = head.next;
		}
		return -1;
	}
	
	/*
	 * To find merge Node of two linked lists
	 */
	private static void findMergeNode(Node n1, Node n2) {
		 // Step 1: make a circular link from one link n1
		 Node lastNodeN1 = circular(n1);
		 
		 // Step 2: detect the loop point in second link n2
		 System.out.println(" Merge point is at : "+ detectFirstNodeInLoop(n2));
			
		 // Step 3: Undo the circular link from link n1
		 singular(n1, lastNodeN1);
		 
	}
	
	//Driver
	public static void main(String[] args) {
		// Start with empty list 
	    Node head = null; 
	  
	    // singly linked list : 17.22.13.14.15
	    head = push(head, 15); 
	    head = push(head, 14); 
	    head = push(head, 13); 
	    head = push(head, 22); 
	    head = push(head, 17);
	    
	    // O(n)
	    circular(head);
	    
	    System.out.print("Display Circular list: \n"); 
	    displayCircularList(head);
	    
	    // System.out.print("Display Singly list: \n"); 
	    //displaySinglyList(head);

	    // detect a loop - O(<=n))
	    System.out.println(detectLoop(head));
	   
	    // list 1 
        Node n1 = new Node(1); 
        n1.next = new Node(2); 
        n1.next.next = new Node(3); 
        n1.next.next.next = new Node(4); 
        n1.next.next.next.next = new Node(5); 
        n1.next.next.next.next.next = new Node(6); 
        n1.next.next.next.next.next.next = new Node(7); 
        
        // list 2 
        Node n2 = new Node(10); 
        n2.next = new Node(9); 
        n2.next.next = new Node(8); 
        n2.next.next.next = n1.next.next.next; 
        
        System.out.print("To find the merge point of lists: \n"); 
        
        displaySinglyList(n1);
        displaySinglyList(n2);
        
        findMergeNode(n1, n2);
        
        displaySinglyList(n1);
        displaySinglyList(n2);
        
	}

	

}
