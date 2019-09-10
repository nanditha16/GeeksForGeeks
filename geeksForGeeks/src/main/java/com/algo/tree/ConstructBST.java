package com.algo.tree;

import java.util.ArrayList;

public class ConstructBST {

	/*
	 * 
	 * to construct all unique BSTs for keys from 1 to n 
	 * 
	 * For a number 'n', the loop will run for n * n-th catalan number times..so, 
	 * complexity will be in the order of O(n * n!) .
	 * O(n*n!) = O((n+1-1)*n!) = O((n+1)!-n!) = O((n+1)!) = O(n!)
	 * 
	 * 
	 * Initialize list of BSTs as empty.
	 * For every number i where i varies from 1 to N, do following
	 * ......a)  Create a new node with key as 'i', let this node be 'node'
	 * ......b)  Recursively construct list of all left subtrees.
	 * ......c)  Recursively construct list of all right subtrees.
	 * 
	 * 3) Iterate for all left subtrees
	 * a) For current leftsubtree, iterate for all right subtrees
	 *   Add current left and right subtrees to 'node' and add 'node' to list.
	 *   
	 */
	static ArrayList<NodeTree<Integer> > constructTrees(int start, int end) {
		ArrayList<NodeTree<Integer> > list = new ArrayList<NodeTree<Integer> >(); 
        
		/*  if start > end then subtree will be empty so returning NULL in the list */
		if(start > end) {
			list.add(null);
			return list;
		}
		
		/*  
		 * iterating through all values from start to end for constructing
		 * left and right subtree recursively  
		 */
		for (int i = start; i <= end; i++) {
			/*  constructing left subtree   */
            ArrayList<NodeTree<Integer> > leftSubtree  = constructTrees(start, i - 1);  
            
            /*  constructing right subtree  */
            ArrayList<NodeTree<Integer> > rightSubtree = constructTrees(i + 1, end);  
    
            /*  now looping through all left and right subtrees and connecting  
            them to ith root  below  */
	        for (int j = 0; j < leftSubtree.size(); j++) {  
	            NodeTree<Integer>  left = leftSubtree.get(j);  
	            for (int k = 0; k < rightSubtree.size(); k++) {  
	                NodeTree<Integer>  right = rightSubtree.get(k);  
	                NodeTree<Integer>  node = new NodeTree<Integer> (i);        // making value i as root  
	                node.left = left;              // connect left subtree  
	                node.right = right;            // connect right subtree  
	                list.add(node);                // add this tree to list  
	            }  
	        } 
    
		}
		return list;  
		
	}
	
	public static void main(String[] args) {
		 ArrayList<NodeTree<Integer> > totalTreesFrom1toN = constructTrees(1, 3); 
		 BinaryTree<Integer> binaryTree = new BinaryTree<Integer> ();
		 
	        /*  Printing preorder traversal of all constructed BSTs   */
	        System.out.println("Preorder traversals of all constructed BSTs are "); 
	        for (int i = 0; i < totalTreesFrom1toN.size(); i++)  
	        {  
	        	binaryTree.printPreorder(totalTreesFrom1toN.get(i));
	        	System.out.println(); 
	        } 

	}

}
