package com.algo.linkedList;

import java.util.ArrayDeque;
import java.util.HashSet;

public class CircularLinkedList {
	
	/*
	 * Convert Singly Linked list to circular linked list
	 */
	static NodeLinedList circular(NodeLinedList head) 
	{ 
	    // start as head
	    NodeLinedList start = head; 
	  
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
	private static NodeLinedList singular(NodeLinedList head, NodeLinedList lastNodeN1) {
		// start as head
	    NodeLinedList start = head; 
	  
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
	static NodeLinedList push(NodeLinedList head, int data) 
	{ 
	    // Allocate dynamic memory for newNode. 
	    NodeLinedList newNode = new NodeLinedList(); 
	  
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
	static void displayCircularList( NodeLinedList node) 
	{ 
	    NodeLinedList start = node; 
	  
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
	static void displaySinglyList( NodeLinedList node) 
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
	public static int detectLoop(NodeLinedList node) 
    { 
		NodeLinedList head = node; 
        NodeLinedList slow_p = head, fast_p = head; 
        
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
	private static int detectFirstNodeInLoop(NodeLinedList head) {
		HashSet<NodeLinedList> start = new HashSet<NodeLinedList>(); 
        
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
	private static void findMergeNode(NodeLinedList n1, NodeLinedList n2) {
		 // Step 1: make a circular link from one link n1
		 NodeLinedList lastNodeN1 = circular(n1);
		 
		 // Step 2: detect the loop point in second link n2
		 System.out.println(" Merge point is at : "+ detectFirstNodeInLoop(n2));
			
		 // Step 3: Undo the circular link from link n1
		 singular(n1, lastNodeN1);
		 
	}
	
	/*
	 * Reverse a linked List - Iterative method
	 */
	static NodeLinedList reverse(NodeLinedList head){
		NodeLinedList current = head; 
        NodeLinedList prev = null; 
        NodeLinedList next = null; 
        
        while (current.next != null) { 
        	//save next value
            next = current.next; 
            
            // Inserting node at start of new list
            current.next = prev; 
            prev = current; 
            
            //advance to next
            current = next; 
        } 
        head = current; 
        head.next = prev;
       return head;
		
	}
	
	/*
	 * Reverse a linked List - Recursivly method
	 * Requires extra space.
	 */
	static NodeLinedList reverseRecursively(NodeLinedList head){
		NodeLinedList newHead; 
        
		//base case
        if (head.next == null) { 
        	return head;
        }
        newHead = reverseRecursively(head.next);
        
        // reverse the link
        head.next.next = head;
        head.next = null;
       return newHead;
	}

	/*
	 * Reverse a Linked List in groups of given size
	 */
	static NodeLinedList reverseK(NodeLinedList head, int k){
		NodeLinedList current = head; 
	    NodeLinedList next = null; 
	    NodeLinedList prev = null; 
	 
	    int count = 0;
	    
	    /* Reverse first k nodes of linked list */
	    while(count < k && current != null) {
	    	//save next value
            next = current.next; 
            
            // Inserting node at start of new list
            current.next = prev; 
            prev = current; 
            
            //advance to next
            current = next; 
            count++;
	    }
	    
	    /*
	     * next is now a pointer to (k+1)th node 
	     * Recursively call for the list starting from current.
	     * making rest of the list as next of first node 
	     */
	    if (next != null)  {
	          head.next = reverseK(next, k); 
	    }
		return prev;
	}
	
	/*
	 * Reverse a Linked List in groups of given size using Stack
	 */
	static NodeLinedList reverseKUsingStack(NodeLinedList head, int k){
		// Create a stack of Node*  
		ArrayDeque<NodeLinedList> mystack = new ArrayDeque<NodeLinedList> ();  
		NodeLinedList current = head;  
	    NodeLinedList prev = null;  
	  
	    while (current != null) {
	    	int count = 0; 
	    	//current == NULL or count >= k end loop - push k elements
	    	while (current != null && count < k) 
	        {
	    		mystack.push(current);  
	            current = current.next;  
	            count++; 
	        }
	    	
	    	//pop k elemenst
	    	while (mystack.size() > 0)  
	        {  
	    		// If final list has not been started yet.
	    		if (prev == null) 
	            {  
	                prev = mystack.peek();  
	                head = prev;  
	                mystack.pop();  
	            }  
	            else
	            {  
	                prev.next = mystack.peek();  
	                prev = prev.next;  
	                mystack.pop();  
	            }  
	        }
	    	
	    }
	    // Next of last element will point to NULL.  
	    prev.next = null;  
	  
		return head;
	
	}
	
	/*
	 * Reverse a circular List
	 */
	static NodeLinedList reverseCircular(NodeLinedList head){
		// if list is empty  
	    if (head == null) {
	    	return null; 
	    }
	    
	    NodeLinedList current = head; 
	    NodeLinedList next = null; 
	    NodeLinedList prev = null; 
	 
	    while (current.next != null) { 
        	//save next value
            next = current.next; 
            
            // Inserting node at start of new list
            current.next = prev; 
            prev = current; 
            
            //advance to next
            current = next; 
        } 
        //last node point to the first node  
	    (head).next = prev;
	    return head;
		
	}
		
	/*
     * Sum of 2 linked list : 
     * 
     * Time: O(max(m, n)) where m and n are number of nodes in list l1 and list l2 respectively.
     * Space: O(1)
     */
    public static NodeLinedList sum(NodeLinedList l1, NodeLinedList l2) {
    	 if (l1 == null) {
 	    	return l2; 
 	    }
    	 
    	 if (l2 == null) {
 	    	return l1; 
 	    }
    	
    	 // reverse l1 and l2
    	 l1 = reverse(l1);
    	 l2 = reverse(l2);
    	 
    	 //storing head whose reverse is to be returned
    	 NodeLinedList head = l1; 
         NodeLinedList prev = null; 
         int carrier = 0, sum; 
         
         while(l1 != null && l2 != null) {
        	 sum = carrier + l1.data + l2.data;
        	 l1.data = sum % 10;
        	 carrier = sum / 10; 
        	 prev = l1; 
        	 l1 = l1.next; 
             l2 = l2.next; 
         }
         
         if(l1 != null||l2 != null) 
         { 
             if(l2 != null) prev.next = l2; 
             l1 = prev.next; 
             while(l1 != null) 
             { 
                 sum = carrier + l1.data; 
                 l1.data = sum % 10; 
                 carrier = sum / 10; 
                 prev = l1; 
                 l1 = l1.next; 
             } 
         } 
         
         if(carrier > 0) prev.next = new NodeLinedList(carrier); 
         return reverse(head); 
    
    }
	
	//Driver
	public static void main(String[] args) {
		// Start with empty list 
	    NodeLinedList head = null; 
	  
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
	    
	    // detect a loop - O(<=n))
	    System.out.println(detectLoop(head));
	   
	    // list 1 
        NodeLinedList n1 = new NodeLinedList(1); 
        n1.next = new NodeLinedList(2); 
        n1.next.next = new NodeLinedList(3); 
        n1.next.next.next = new NodeLinedList(4); 
        n1.next.next.next.next = new NodeLinedList(5); 
        n1.next.next.next.next.next = new NodeLinedList(6); 
        n1.next.next.next.next.next.next = new NodeLinedList(7); 
        
        // list 2 
        NodeLinedList n2 = new NodeLinedList(10); 
        n2.next = new NodeLinedList(9); 
        n2.next.next = new NodeLinedList(8); 
        n2.next.next.next = n1.next.next.next; 
        
        System.out.print("To find the merge point of lists: \n"); 
        
        displaySinglyList(n1);
        displaySinglyList(n2);
        
        findMergeNode(n1, n2);
        
        displaySinglyList(n1);
        displaySinglyList(n2);
        
        System.out.print("To find the reverse of list: \n"); 
        NodeLinedList n2Reverse = reverse(n2);
        displaySinglyList(n2Reverse);
        
        NodeLinedList n2ReverseRecur = reverseRecursively(n2Reverse);
        displaySinglyList(n2ReverseRecur);
        
       
	    // singly linked list :  1->2->3->4->5->6->7->8->8->9->null 
        NodeLinedList reverseTest = null; 
        reverseTest = push(reverseTest, 9);
        reverseTest = push(reverseTest, 8);
        reverseTest = push(reverseTest, 7);
        reverseTest = push(reverseTest, 6);
        reverseTest = push(reverseTest, 5);
        reverseTest = push(reverseTest, 4);
        reverseTest = push(reverseTest, 3);
        reverseTest = push(reverseTest, 2); 
        reverseTest = push(reverseTest, 1); 
        displaySinglyList(reverseTest);
       
        NodeLinedList reverseK = reverseK(reverseTest, 3);
        displaySinglyList(reverseK);
        
        NodeLinedList reverseStackK = reverseKUsingStack(reverseK, 3);
        displaySinglyList(reverseStackK);
        
        NodeLinedList reverseCircular = new NodeLinedList(1); 
        reverseCircular.next = new NodeLinedList(2); 
        reverseCircular.next.next = new NodeLinedList(3); 
        reverseCircular.next.next.next = new NodeLinedList(4);
        reverseCircular.next.next.next.next = reverseCircular; 
        
        System.out.print("Display Circular reversed list: \n");
	    displayCircularList(reverseCircular);
	    reverseCircular(reverseCircular);
	    displayCircularList(reverseCircular);
	    
	    
	    // to add two linked list nodes
	    System.out.println("Sum of 2 linked list : ");
	    NodeLinedList l1 = null; 
		// singly linked list : 365
	    l1 = push(l1, 3); 
	    l1 = push(l1, 6); 
	    l1 = push(l1, 5); 
	    
	    NodeLinedList l2 = null; 
		// singly linked list : 248
	    l2 = push(l2, 2); 
	    l2 = push(l2, 4); 
	    l2 = push(l2, 8); 
	    
	    NodeLinedList l3 = sum(l1, l2); 
	    displaySinglyList(l3);
        
	    
	   
	}

	

}
