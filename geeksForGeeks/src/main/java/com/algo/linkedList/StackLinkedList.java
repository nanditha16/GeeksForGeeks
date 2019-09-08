package com.algo.linkedList;

import java.util.EmptyStackException;
import java.util.PriorityQueue;

public class StackLinkedList {

	StackNode root; 
	static PriorityQueue<Integer> minEle = new PriorityQueue<Integer>(); 
	
	StackLinkedList(){
		minEle.add(Integer.MIN_VALUE);
	}
	
	static class StackNode { 
		int data; 
        StackNode next; 
  
        StackNode(int data) 
        { 
        	minEle.add(data);
        	this.data = data; 
            this.next = null;
        }
	} 
	
	public boolean isEmpty() {
		if(root == null) {
			return true;
		}
		return false;
	}
	
	public void push(int data) {
		//create a new node
		StackNode newNode = new StackNode(data);
		
		//if it's root itself
		if(root == null) {
			root = newNode;
		} else {
			
			StackNode temp = root;
			newNode.next = temp;
			root = newNode;
		}
	}
	
	public int pop() {
		int popped = Integer.MIN_VALUE; 
        
		if(root == null) {
			throw new EmptyStackException();
		} else {
			popped = root.data;
			if(minEle.contains(popped)) {
				minEle.remove(popped);
			}
			root = root.next;
		}
		return popped;
		
	}
	
	/*
	 * Looks at the object at the top of this stack without removing it
	 */
	public int peek() {
		int peek = Integer.MIN_VALUE; 
		if(root == null) {
			System.out.println("Stack is Empty.");
		}else {
			peek = root.data;
		}
		
		return peek;
		
	}
	
	public boolean search(StackNode root, int data) {
		StackNode temp = root;
		if(root == null) {
			System.out.println("Stack is Empty.");
			return false;
		} else if (root.data == data) {
			return true;
		} else {
			temp = root.next;
			search(temp, data);
		}
		
		return true;
	}
	public int getMin() {
		if(minEle.isEmpty()) {
			return -1;
		}
		int temp = minEle.peek();
		
		if(search(root, temp)) {
			return temp;
		}
		return minEle.poll();
	}
	
	public static void main(String[] args) {
		StackLinkedList myStack = new StackLinkedList();
		
		System.out.println("Initially isEmpty: " +myStack.isEmpty());
		System.out.println("Min Element  : "  + myStack.getMin());
		
		myStack.push(10);
		myStack.push(20);
		myStack.push(30);
		System.out.println("isEmpty: " + myStack.isEmpty());
		System.out.println("pop : "  +myStack.pop());
		System.out.println("peek : "  +myStack.peek());
		
		System.out.println("Min Element  : "  + myStack.getMin());
	
		System.out.println("pop : "  +myStack.pop());
		System.out.println("Min Element  : "  + myStack.getMin());
		
		System.out.println("pop : "  +myStack.pop());
		System.out.println("Min Element  : "  + myStack.getMin());
		
	}

	
}
