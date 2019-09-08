package com.algo.tree;

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
public class MinimumHeap {
	private int[] minHeap; 
	private int[] maxHeap;
	private int size; 
    private int maxsize; 
    
    private static final int FRONT = 1; 
    
    public MinimumHeap(int maxsize) {
    	this.maxsize = maxsize;
    	this.size = 0;
    	minHeap = new int[this.maxsize + 1];
    	minHeap[0] = Integer.MIN_VALUE;
    	
    	maxHeap = new int[this.maxsize + 1];
    	maxHeap[0] = Integer.MAX_VALUE;
    }
	
    /* 
     * return the position of the parent for the node currently at ith position 
     * index : i from 0, pos from 1
     * Arr[(i -1) / 2]
     */
    public int parent(int pos) {
    	return pos/ 2;
    }
    
    /*
     * return the position of the left child for the node currently at pos
     *  Arr[(2 * i) + 1]
     */
    public int leftChild(int pos) {
    	return (pos * 2);
    }
    
    /*
     * return the position of the right child for the node currently at pos
     *  Arr[(2 * i) + 2]
     */
    public int rightChild(int pos) {
    	return (pos * 2) + 1;
    }
    
    /*
     * returns true if the passed node is a leaf node 
     */
    public boolean isLeaf(int pos) {
    	if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    }
    
    /*
     * print the contents of the heap
     */
    public void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + minHeap[i] 
                             + " LEFT CHILD : " + minHeap[2 * i] 
                             + " RIGHT CHILD :" + minHeap[2 * i + 1]); 
            System.out.println(); 
        } 
    }
    
    /*
     * swap two nodes of the minheap 
     */
    public void minSwap(int fpos, int spos) 
    { 
        int tmp; 
        tmp = minHeap[fpos]; 
        minHeap[fpos] = minHeap[spos]; 
        minHeap[spos] = tmp; 
    } 
    
    /*
     * swap two nodes of the maxheap 
     */
    public void maxSwap(int fpos, int spos) 
    { 
        int tmp; 
        tmp = maxHeap[fpos]; 
        maxHeap[fpos] = maxHeap[spos]; 
        maxHeap[spos] = tmp; 
    } 
    
	/*
	 * to heapify the node at pos for min
	 */
    public void minHeapify(int pos) {
    	// If the node is a non-leaf node and > any of its child 
    	if (!isLeaf(pos)) { 
    		if (minHeap[pos] > minHeap[leftChild(pos)] 
                    || minHeap[pos] > minHeap[rightChild(pos)]) { 
    			// Swap with the left child and heapify  the left child 
    			if (minHeap[leftChild(pos)] < minHeap[rightChild(pos)]) { 
    				minSwap(pos, leftChild(pos)); 
                    minHeapify(leftChild(pos)); 
                } else {
                	// Swap with the right child and heapify the right child
                	minSwap(pos, rightChild(pos)); 
                    minHeapify(rightChild(pos)); 
                }
    		}
    	}
    }
    
    /*
     * to heapify the node at pos for max i.e 
     * to max heapify the given subtree.
     */
    public void maxHeapify(int pos) {
    	if (isLeaf(pos)) {
            return; 
    	}
    	
    	if (maxHeap[pos] < maxHeap[leftChild(pos)] ||  
    			maxHeap[pos] < maxHeap[rightChild(pos)]) { 
    		if (maxHeap[leftChild(pos)] > maxHeap[rightChild(pos)]) { 
    			maxSwap(pos, leftChild(pos)); 
                maxHeapify(leftChild(pos)); 
            } 
            else { 
            	maxSwap(pos, rightChild(pos)); 
                maxHeapify(rightChild(pos)); 
            } 
    	}
    	
    }
    
    /*
     * to insert a node into the minheap
     */
    public void insertMinHeap(int element) {
    	 if (size >= maxsize) { 
             return; 
         } 
    	 
    	 minHeap[++size] = element; 
         int current = size; 
   
         while (minHeap[current] < minHeap[parent(current)]) { 
        	 minSwap(current, parent(current)); 
             current = parent(current); 
         }  
    }
    
    /*
     * to insert a node into the maxheap
     */
    public void insertMaxHeap(int element) {
    	 if (size >= maxsize) { 
             return; 
         } 
    	 
    	 maxHeap[++size] = element; 
         int current = size; 
   
         while (maxHeap[current] > maxHeap[parent(current)]) { 
        	 maxSwap(current, parent(current)); 
             current = parent(current); 
         }  
    }
    
   
    
    /*
     * to build the min heap using the minHeapify 
     */
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 
    
    /*
     * to build the max heap using the minHeapify 
     */
    public void maxHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            maxHeapify(pos); 
        } 
    } 
    
    /*
     * to remove and return the max element from the heap
     */
    public int removeMax() 
    { 
        int popped = maxHeap[1]; 
        maxHeap[FRONT] = maxHeap[size--]; 
        maxHeapify(FRONT); 
        return popped; 
    } 
    
    /*
     * to remove and return the minimum element from the heap
     */
    public int removeMin() 
    { 
        int popped = minHeap[FRONT]; 
        minHeap[FRONT] = minHeap[size--]; 
        minHeapify(FRONT); 
        return popped; 
    } 
 
}
