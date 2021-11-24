package com.algo.linkedList;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;


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
		//bad case 
        if(head == null) return null;
        
        NodeLinedList current = head; 
        
        //case 1 : single node
        if(current.next == null) return head;
        
        NodeLinedList prev = null; 
        NodeLinedList next = null; 
        
        //case 2 : iteration if multi node
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
	
    /*
     * 
     * MergeSort(headRef)
     * 1) If the head is NULL or there is only one element in the Linked List then return.
     * 2) Else divide the linked list into two halves.  
     *      FrontBackSplit(head, &a, &b); // a and b are two halves 
     * 3) Sort the two halves a and b.
     *     MergeSort(a);
     *      MergeSort(b);
     * 4) Merge the sorted a and b (using SortedMerge() discussed here) 
     *   and update the head pointer using headRef.
     *    *headRef = SortedMerge(a, b);
     *
     */
    private static NodeLinedList mergeSort(NodeLinedList h) {
    	// Base case : if head is null 
        if (h == null || h.next == null) { 
            return h; 
        } 
        
        // get the middle of the list 
        NodeLinedList middle = getMiddle(h); 
        
        NodeLinedList nextofmiddle = middle.next; 
        
        // set the next of middle node to null 
        middle.next = null; 
  
        // Apply mergeSort on left list 
        NodeLinedList left = mergeSort(h); 
  
        // Apply mergeSort on right list 
        NodeLinedList right = mergeSort(nextofmiddle); 
  
        // Merge the left and right lists 
        NodeLinedList sortedlist = sortedMerge(left, right); 
        
		return sortedlist;
	}
    
    private static NodeLinedList sortedMerge(NodeLinedList a, NodeLinedList b) {
    	NodeLinedList result = null; 
        
    	/* Base cases */
        if (a == null) 
            return b; 
        if (b == null) 
            return a; 
        
        /* Pick either a or b, and recur */
        if (a.data <= b.data) { 
            result = a; 
            result.next = sortedMerge(a.next, b); 
        } 
        else { 
            result = b; 
            result.next = sortedMerge(a, b.next); 
        } 
    	
		return result;
	}

	/*
     * To find the midPoint of the list
     */
	private static NodeLinedList getMiddle(NodeLinedList head) {
		if (head == null) 
            return head; 
		
		NodeLinedList slow = head, fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next; 
            fast = fast.next.next; 
		}

		return slow;
	}

	
	 /*
	  * Remove Linked List Elements and return the updated list
	  * Remove first occurrence 
	  */
	public static NodeLinedList removeElements(NodeLinedList head, int val) {
		 //bad case :
		if(head == null) return null;
		
		
		 // Case 1: if head is the value to be deleted
		 if(head.data == val) {
			 head = head.next;
			 return head; 

		 }
		 
		 // Case 2: if value is in the middle or end
		 // Initialize current to traverse through the link
		 NodeLinedList current = head;
		 while(current.next != null) {
			 if(current.next.data == val) {
				 current.next = current.next.next;
				 continue;
			 }
			 current = current.next;
		 }
			
		return head;
	        
	 }
	 
	 /*
	  * Remove Linked List Elements and return the updated list
	  * Remove all occurrence 
	  */
	public static NodeLinedList removeAllElements(NodeLinedList head, int val) {
		 //bad case :
		if(head == null) return null;
				
		 // Case 1: recursively look for the val and keep deleting
		 head.next = removeAllElements(head.next, val);
	        
		 // case 2: recursion break
			return head.data == val?head.next: head;
	 }
	
	/*
	 * Duplicate deletion from sorted linked list
	 */
	public static NodeLinedList deleteDuplicates(NodeLinedList head) {
		//bad case :
		if(head == null) return null;
		  
        NodeLinedList current = head; 
        
        //case 1 : single node
        if(current.next == null) return head;
        
        //two pointers to iterate
        NodeLinedList prev = head; 
        current = head.next;
        
        //case 2 : iteration if multi node
        while (current != null) { 
        	if(current.data == prev.data) {
        		NodeLinedList temp = current.next;
        		prev.next = temp;
        		current.next = null;
        		current = temp;
        	} else {
        		prev = prev.next;
        		current = current.next;
        	}
        }
       return head;
 
    }

	//Driver
	public static void main(String[] args) {
//		// Start with empty list 
//	    NodeLinedList head = null; 
//	  
//	    // singly linked list : 17.22.13.14.15
//	    head = push(head, 15); 
//	    head = push(head, 14); 
//	    head = push(head, 13); 
//	    head = push(head, 22); 
//	    head = push(head, 17);
//	    
//	    // O(n)
//	    circular(head);
//	    
//	    System.out.print("Display Circular list: \n"); 
//	    displayCircularList(head);
//	    
//	    // detect a loop - O(<=n))
//	    System.out.println(detectLoop(head));
//	   
//	    // list 1 
//        NodeLinedList n1 = new NodeLinedList(1); 
//        n1.next = new NodeLinedList(2); 
//        n1.next.next = new NodeLinedList(3); 
//        n1.next.next.next = new NodeLinedList(4); 
//        n1.next.next.next.next = new NodeLinedList(5); 
//        n1.next.next.next.next.next = new NodeLinedList(6); 
//        n1.next.next.next.next.next.next = new NodeLinedList(7); 
//        
//        // list 2 
//        NodeLinedList n2 = new NodeLinedList(10); 
//        n2.next = new NodeLinedList(9); 
//        n2.next.next = new NodeLinedList(8); 
//        n2.next.next.next = n1.next.next.next; 
//        
//        System.out.print("To find the merge point of lists: \n"); 
//        
//        displaySinglyList(n1);
//        displaySinglyList(n2);
//        
//        findMergeNode(n1, n2);
//        
//        displaySinglyList(n1);
//        displaySinglyList(n2);
//        
//        System.out.print("To find the reverse of list: \n"); 
//        NodeLinedList n2Reverse = reverse(n2);
//        displaySinglyList(n2Reverse);
//        
//        NodeLinedList n2ReverseRecur = reverseRecursively(n2Reverse);
//        displaySinglyList(n2ReverseRecur);
//        
//       
//	    // singly linked list :  1->2->3->4->5->6->7->8->8->9->null 
//        NodeLinedList reverseTest = null; 
//        reverseTest = push(reverseTest, 9);
//        reverseTest = push(reverseTest, 8);
//        reverseTest = push(reverseTest, 7);
//        reverseTest = push(reverseTest, 6);
//        reverseTest = push(reverseTest, 5);
//        reverseTest = push(reverseTest, 4);
//        reverseTest = push(reverseTest, 3);
//        reverseTest = push(reverseTest, 2); 
//        reverseTest = push(reverseTest, 1); 
//        displaySinglyList(reverseTest);
//       
//        NodeLinedList reverseK = reverseK(reverseTest, 3);
//        displaySinglyList(reverseK);
//        
//        NodeLinedList reverseStackK = reverseKUsingStack(reverseK, 3);
//        displaySinglyList(reverseStackK);
//        
//        NodeLinedList reverseCircular = new NodeLinedList(1); 
//        reverseCircular.next = new NodeLinedList(2); 
//        reverseCircular.next.next = new NodeLinedList(3); 
//        reverseCircular.next.next.next = new NodeLinedList(4);
//        reverseCircular.next.next.next.next = reverseCircular; 
//        
//        System.out.print("Display Circular reversed list: \n");
//	    displayCircularList(reverseCircular);
//	    reverseCircular(reverseCircular);
//	    displayCircularList(reverseCircular);
//	    
//	    
//	    // to add two linked list nodes
//	    System.out.println("Sum of 2 linked list : ");
//	    NodeLinedList l1 = null; 
//		// singly linked list : 365
//	    l1 = push(l1, 3); 
//	    l1 = push(l1, 6); 
//	    l1 = push(l1, 5); 
//	    
//	    NodeLinedList l2 = null; 
//		// singly linked list : 248
//	    l2 = push(l2, 2); 
//	    l2 = push(l2, 4); 
//	    l2 = push(l2, 8); 
//	    
//	    NodeLinedList l3 = sum(l1, l2); 
//	    displaySinglyList(l3);
//	   
	    /* 
		    * To check Sorting using MergeSort
		    *  
		    */
		    NodeLinedList li = null; 
			// singly linked list : 2->3->20->5->10->15 
//		    li = push(li, 15); 
//		    li = push(li, 10); 
//		    li = push(li, 5); 
//		    li = push(li, 20); 
//		    li = push(li, 3); 
//		    li = push(li, 2); 
		    
		    // Apply merge Sort 
//		    System.out.println("To merge Sort a linked list : ");
		    li = push(li, 6); 
		    li = push(li, 7); 
		    li = push(li, 7); 
		    li = push(li, 8);
		    
		    displaySinglyList(li);
//		    li = mergeSort(li); 
//		    displaySinglyList(li);
		    
		    
		    //remove element
//		    System.out.println("After removing 7: ");
//		    displaySinglyList(removeElements(li, 7));
//		    
		    //remove all occurrence element
//		    System.out.println("After removing 7: ");
//		    displaySinglyList(removeAllElements(li, 7));
		    
		    // remove all duplicates
		    System.out.println("duplicate removal: ");
		    displaySinglyList(deleteDuplicates(li));
		    
	}

	

	

}
