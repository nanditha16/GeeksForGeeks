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
	
	/*
	 * To find the Kth element in 2Dmatrix 
	 * 
     * Build a min heap which takes O(n) time
     * Heapify k times which takes O(kLogn) time.
     * time complexity is O(n + kLogn) time.
     */
	private static int kthSmallestIn2DMatix(int[][] matrix, int minKEle) {
		int result = -1;
		
		
		if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return result;
		}
		int m = matrix.length;
        int n = matrix[0].length;
 
        MinimumHeap minHeap = new MinimumHeap(m*n+1); 
            
        for(int i=0; i < m; i++) {
        	for(int j =0; j<n;j++) {
        		minHeap.insertMinHeap(matrix[i][j]);
        	}
        }
        minHeap.minHeap();
        
        System.out.println("\nThe "+minKEle+" Min vals in a given 2D matrix are :");
		for(int i =0; i < minKEle; i++) {
			result = minHeap.removeMin();
			System.out.print( result+ ", "); 
			
		}	
		System.out.println();
		return result;
      
      }
	
	private static boolean binarySearch(int[][] matrix, int k) {
		if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return false;
		}
		
		int m = matrix.length;
        int n = matrix[0].length;
 
        int start = 0;
        int end = m*n-1;
 
        
        while(start<=end){
        	int mid=(start+end)/2;
            int midX=mid/n;
            int midY=mid%n;
            
            if(matrix[midX][midY]==k) {
            	 return true;
            }
              
            if(matrix[midX][midY]<k){
                start=mid+1;
            }else{
                end=mid-1;
            }
 
        }
		return false;
		 
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
        
        
        int mat[][] = { {10, 20, 30, 40}, 
                {15, 25, 35, 45}, 
                {25, 29, 37, 48}, 
                {32, 33, 39, 50}, 
              }; 
        int k = 7;
        
        System.out.println(k+"th smallest element in 2d array matrix: " + kthSmallestIn2DMatix(mat, k));
        System.out.println("binarySearch 30 in 2Dmatrix: " + binarySearch(mat, 30));
        
		
	}

	
}
