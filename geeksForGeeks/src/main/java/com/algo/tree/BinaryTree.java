package com.algo.tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.algo.tree.BinaryTree.Index;

public class BinaryTree {
	
	class Index { 
	    int index; 
	}
	
	//Root of binary tree
	Node root;
	
	//
	static int preIndex = 0; 
	static HashMap<Integer,Integer> mpPreIn = new HashMap<Integer,Integer>();
	static HashMap<Integer,Integer> mpPostIn = new HashMap<Integer,Integer>();
    
	
	// to maintain the node in which the next right subtree is expected
	static Set<Node> set = new HashSet<Node>(); 
	
	// to store the path we visited while traversing PreOrder array
    static ArrayDeque<Node> stack = new ArrayDeque<Node>(); 
	
	BinaryTree(){
		root = null;
	}
	
	/*
	 * Build a Tree given Pre-Order and InOrder 
	 */
	public Node buildTreePreIn(int[] in, int[] pre, int inStrt, int inEnd, HashMap<Integer,Integer> mpPreIn) 
    {
		// Base case 
		if (inStrt > inEnd) {
            return null; 
		}
		
		/* Pick current node from Preorder traversal using preIndex  
	    and increment preIndex */
		int curr = pre[preIndex++]; 
		Node tNode = new Node(curr); 

		/* If this node has no children then return */
		 if (inStrt == inEnd) {
	        	return tNode; 
	     }
		 
		 /* Else find the index of this node in Inorder traversal - O(1)*/
		 int inIndex = mpPreIn.get(curr);
		
		 /* Using index in Inorder traversal, construct left and 
	        right subtress */
	     tNode.left = buildTreePreIn(in, pre, inStrt, inIndex - 1,mpPreIn); 
	     tNode.right = buildTreePreIn(in, pre, inIndex + 1, inEnd,mpPreIn); 
  
		return tNode;
		
    }
	
	/*
	 * Build a Tree given Post-Order and InOrder 
	 */
	private Node buildTreePostIn(int[] in1, int[] post,  HashMap<Integer,Integer> mpPostIn) {
		Index pIndex = new Index();
		pIndex.index = in1.length-1;
		return buildTreePostIn(in1, post, 0, in1.length-1, pIndex, mpPostIn);
	}
	

	private Node buildTreePostIn(int[] in1, int[] post, int inStrt, int inEnd, Index pIndex, HashMap<Integer,Integer> mpPostIn) {
		// Base case 
		if (inStrt > inEnd) {
			return null; 
		}
					
		 /* Pick current node from Postrder traversal using 
        postIndex and decrement postIndex */
		int curr = post[pIndex.index]; 
		Node tNode = new Node(curr); 
		(pIndex.index)--;
		
		/* If this node has no children then return */
		 if (inStrt == inEnd) {
	        	return tNode; 
	     }
		 
		 /* Else find the index of this node in Inorder traversal - O(1)*/
		 int inIndex = mpPostIn.get(curr);
		 
		 /* Using index in Inorder traversal, construct left and 
	        right subtress */
		 tNode.right = buildTreePostIn(in1, post, inIndex + 1, inEnd,pIndex, mpPostIn); 
		 tNode.left = buildTreePostIn(in1, post, inStrt, inIndex - 1,pIndex, mpPostIn); 
	    
		return tNode;
	}
	
	
	
	/*
	 * Postorder traversal : DFS - Recursively
	 * Algorithm Postorder(tree)
	 * 		1. Traverse the left subtree, i.e., call Postorder(left-subtree)
	 * 		2. Traverse the right subtree, i.e., call Postorder(right-subtree)
	 * 		3. Visit the root.
	 * 
	 */
	void printPostorder(Node node) {
		if(node ==null) {
			return;
		}
		
		//step 1
		printPostorder(node.left);
		//step 2
		printPostorder(node.right);
		System.out.print(node.key + " ");
		
	}
	
	/*
	 * Inorder traversal : DFS - Recursively
	 * Algorithm Inorder(tree)
	 * 		1. Traverse the left subtree, i.e., call Inorder(left-subtree)
	 * 		2. Visit the root.
	 * 		3. Traverse the right subtree, i.e., call Inorder(right-subtree)
	 * 
	 */
	void printInorder(Node node) {
		if(node == null) {
			return;
		}
		
		//step 1
		printInorder(node.left);
		System.out.print(node.key + " ");
		//step 2
		printInorder(node.right);
		
	}
	
	/*
	 * Inorder traversal : DFS - Recursively
	 * Algorithm Preorder(tree)	
	 * 		1. Visit the root.
	 * 		2. Traverse the left subtree, i.e., call Preorder(left-subtree)
	 * 		3. Traverse the right subtree, i.e., call Preorder(right-subtree) 
	 * 
	 */
	void printPreorder(Node node) {
		if(node ==null) {
			return;
		}
		
		System.out.print(node.key + " ");
		//step 1
		printPreorder(node.left);
		//step 2
		printPreorder(node.right);
	}
	
	/*
	 * Mirror the binary tree
	 */
	private Node printMirror(Node node) {
		if(node == null) {
			return node;
		}
		
		/* do the subtrees */
        Node left = printMirror(node.left); 
        Node right = printMirror(node.right);
        
        /* swap left with right */
        node.left = right; 
        node.right = left; 
        
		return node; 
		
	}
	
	// Wrapper class
	void mirror() {
		root = printMirror(root);
	}
	

	void printPostorder() {
		printPostorder(root);
	}

	void printInorder() {
		printInorder(root);
	}

	void printPreorder() {
		printPreorder(root);
	}
	
	// Driver method 
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(); 
		tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Preorder(Root, Left, Right) traversal of binary tree is "); 
        tree.printPreorder(); 
  
        
        System.out.println("\nInorder(Left, Root, Right) traversal of binary tree is "); 
        tree.printInorder();
        
        System.out.println("\nPostorder(Left, Right, Root) traversal of binary tree is "); 
        tree.printPostorder(); 
        
        System.out.println("\nInorder(Left, Root, Right) traversal of tree build from Pre and In Order is "); 
        int in[] = new int[] { 9, 8, 4, 2, 10, 5, 10, 1, 6, 3, 13, 12, 7 }; 
        int pre[] = new int[] { 1, 2, 4, 8, 9, 5, 10, 10, 3, 6, 7, 12, 13 }; 
        for (int i = 0; i < in.length; i++) {
        	mpPreIn.put(in[i] , i);
        	
        }
       
        Node root = tree.buildTreePreIn(in, pre, 0, in.length - 1, mpPreIn); 
        tree.printInorder(root); 
        
        System.out.println("\nPreorder(Root, Left, Right) traversal of binary tree build from Post and In Order is "); 
        int in1[] = { 4, 8, 2, 5, 1, 6, 3, 7 };
        int post[] = { 8, 4, 5, 2, 6, 7, 3, 1 };
        for (int i = 0; i < in1.length; i++) {
        	mpPostIn.put(in1[i] , i);
        }
        root = tree.buildTreePostIn(in1, post, mpPostIn); 
        tree.printPreorder(root); 
        
        System.out.println("\nInorder(Left, Root, Right) traversal of FULL BINARY tree build from Pre and Post Order is "); 
        int preFull[] = { 1, 2, 4, 8, 9, 5, 3, 6, 7 }; 
        int postFull[] = { 8, 9, 4, 5, 2, 6, 7, 3, 1 }; 
        root = tree.buildFullTreePrePost(preFull, postFull); 
        tree.printInorder(root); 
           
	}

	private Node buildFullTreePrePost(int[] preFull, int[] postFull) {
		// TODO Auto-generated method stub
		preIndex = 0;
		
		return buildFullTreePrePost(preFull, postFull, 0, preFull.length-1, preFull.length);
	}

	private Node buildFullTreePrePost(int[] preFull, int[] postFull, int lowIndex, int highIndex, int length) {
		
		return null;
	}

	
}
