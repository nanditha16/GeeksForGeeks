package com.test.amazon;

import java.util.Collections;
import java.util.PriorityQueue;

import com.algo.tree.MinimumHeap;

/*
 *  Min-Heap is a complete binary tree in which the value in each internal node 
 *  is smaller than or equal to the values in the children of that node.
 *  
 *  Max-Heap is a complete binary tree in which the value in each internal node 
 *   is greater than or equal to the values in the children of that node.
 *  
 *  Tree to Binary Array :
 *  
 *  if a node is stored a index k, 
 *  then its left child is stored at index 2k + 1 and 
 *  its right child at index 2k + 2.
 *  
 *  i.e,
 *  
 *   The root element will be at Arr[0]. For any ith node, i.e., Arr[i]:
 *   Arr[(i -1) / 2] returns its parent node.
 *   Arr[(2 * i) + 1] returns its left child node.
 *   Arr[(2 * i) + 2] returns its right child node.
 *   
 *   getMin() - minimum most element - O(i)
 *   extractMin() - Removes the minimum element from MinHeap - O(Log n)
 *   	 as needs to call heapify() after remove
 *   insert() - Inserting a new key takes O(Log n) time.
 */
public class KthMinMaxValue {

	private static void FirstMaxK(int[] arr, int maxKEle) {
		System.out.println("Using the Max Heap datastructure Tree to BSA is "); 
		MinimumHeap maxHeap = new MinimumHeap(arr.length+1); 
       
		for(int i : arr) {
			maxHeap.insertMaxHeap(i);
		}
		maxHeap.maxHeap();
		System.out.println("The "+maxKEle+" Max vals are :");
		for(int i =0; i < maxKEle; i++) {
			System.out.print( maxHeap.removeMax()+ ", "); 
		}
	}

	private static void FirstMinK(int[] arr, int minKEle) {
		
		
		System.out.println("Using the Min Heap datastructure Tree to BSA is "); 
		MinimumHeap minHeap = new MinimumHeap(arr.length+1); 
        
		for(int i : arr) {
			minHeap.insertMinHeap(i);
		}
		minHeap.minHeap();
		System.out.println("The "+minKEle+" Min vals are :");
		for(int i =0; i < minKEle; i++) {
			System.out.print( minHeap.removeMin()+ ", "); 
		}	
		System.out.println();		
	}
	
	public static void main(String[] args) {
		int arr[] = {5,3,17,10,84,19,6,22,9}; 
		
		// Size of Min Heap and Max heaps
	    int minKEle = 3; 
	    int maxKEle = 3; 

	    /*
	     * NOTE:
	     * MinMaxHeap DataStructure in tree
	     */
	    FirstMinK(arr,minKEle); 
	    FirstMaxK(arr,maxKEle);
	    
	    /*
	     * NOTE:
	     * Using PriorityQueue which implements Heaps in Java
	     */
	   
	    // Creating empty priority queue for Minimum
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(); 
        
        // Adding items to the pQueue using add() 
        for(int i : arr) {
        	pQueue.add(i);
		}
         
        System.out.println("\nThe "+minKEle+" Min vals using PriorityQueue are :");
        for(int i =0; i < minKEle; i++) {
			System.out.print( pQueue.poll() + ", "); 
			
		}
		
		
		// Creating empty priority queue for Max - reverse order
		 pQueue = new PriorityQueue<Integer>(Collections.reverseOrder()); 
		 for(int i : arr) {
	        	pQueue.add(i);
			}
	        
        System.out.println("\nThe "+maxKEle+" Max vals using PriorityQueue are :");
        for(int i =0; i < maxKEle; i++) {
			System.out.print( pQueue.poll() + ", "); 
			
		}
		
	}

	
}
