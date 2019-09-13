package com.algo.tree;

public class BinaryTreeToDoublyLinkedList {

	/*
	 * Binary Tree (BT), convert it to a Doubly Linked List(DLL) In-Place. 
	 * The left and right pointers in nodes are
	 * previous and next pointers respectively in converted DLL. 
	 * 
	 * The order of nodes in DLL is same as Inorder of the given Binary Tree.
	 * The first node of Inorder traversal (left most node in BT) is head node of the DLL.
	 * 
	 */
	//Root of binary tree
	NodeTree<Integer> root;
	
	// head of linked list from tree node
	static NodeTree<Integer> head;
	
	
	/*
	 * Binary tree to circular Doubly linked list
	 */
	private static NodeTree<Integer> BinaryTreeToCirDLL(NodeTree<Integer> root) {
		//base case
		if(root == null) {
			return null;
		}
		// Recursively convert left and right subtrees 
		NodeTree<Integer> left = BinaryTreeToCirDLL(root.left); 
		NodeTree<Integer> right = BinaryTreeToCirDLL(root.right); 
  
		// Make a circular linked list of root.
		// i.e, make the right and left pointers of this node point to itself 
		root.left = root.right = root; 
		
		// Step 1 (concatenate the left list with the list  
        //         with single node, i.e., current node) 
        // Step 2 (concatenate the returned list with the 
        //         right List) 
		return concatenate(concatenate(left, root), right); 
		
	}
	
	/*
	 * concatenate both the lists and returns the head 
	 */
	private static NodeTree<Integer> concatenate(NodeTree<Integer> leftList, NodeTree<Integer> rightList) {
		// If either of the list is empty, then return the other list 
        if (leftList == null) 
            return rightList; 
        if (rightList == null) 
            return leftList; 
  
        // Store the last Node of left List 
        NodeTree<Integer> leftLast = leftList.left; 
  
        // Store the last Node of right List 
        NodeTree<Integer> rightLast = rightList.left; 
  
        // Connect the last node of Left List with the first Node of the right List 
        leftLast.right = rightList; 
        rightList.left = leftLast; 
        
        // left of first node refers to the last node in the list 
        leftList.left = rightLast;
        
        // Right of last node refers to the first node of the List 
        rightLast.right = leftList; 
		
        // Return the Head of the List 
        return leftList; 
	}
	/*
	 * Binary Tree to Doubly linked list
	 */
	private static NodeTree<Integer> BinaryTreeToDLL(NodeTree<Integer> root) {
		//base case
		if(root == null) {
			return null;
		}
		
		// Recursively convert right subtree 
		BinaryTreeToDLL(root.right); 
	  
		// insert root into DLL 
		root.right = head; 
		
		// Change left pointer of previous head 
        if (head != null) 
            (head).left = root; 
  
        // Change head of Doubly linked list 
        head = root; 
  
        // Recursively convert left subtree 
        BinaryTreeToDLL(root.left); 
        
		return head;	
	}
	
	public static void printTreeToDLinkedList(NodeTree<Integer> head) {
		System.out.println("Extracted Double Linked List is : "); 
		while (head != null)  
        { 
            System.out.print(head.key + " "); 
            head = head.right; 
        } 
	}
	
	public static void printTreeToCircularDLinkedList(NodeTree<Integer> head) {
		System.out.println("Circular Linked List is :"); 
		NodeTree<Integer> itr = head; 
        do
        { 
            System.out.print(itr.key+ " " ); 
            itr = itr.right; 
        } 
        while (itr != head); 
        System.out.println();   head = head.right; 
    } 
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(); 
        tree.root = new NodeTree<Integer>(5); 
        tree.root.left = new NodeTree<Integer>(3); 
        tree.root.right = new NodeTree<Integer>(6); 
        tree.root.left.right = new NodeTree<Integer>(4); 
        tree.root.left.left = new NodeTree<Integer>(1); 
        tree.root.right.right = new NodeTree<Integer>(8); 
        tree.root.left.left.right = new NodeTree<Integer>(2); 
        tree.root.left.left.left = new NodeTree<Integer>(0); 
        tree.root.right.right.left = new NodeTree<Integer>(7); 
        tree.root.right.right.right = new NodeTree<Integer>(9);

        printTreeToDLinkedList(BinaryTreeToDLL(tree.root));
        
        // Into a circular doubly linked list
        // Build the tree 
        tree = new BinaryTree<Integer>(); 
        tree.root = new NodeTree<Integer>(10); 
        tree.root.left = new NodeTree<Integer>(12); 
        tree.root.right = new NodeTree<Integer>(15); 
        tree.root.left.left = new NodeTree<Integer>(25); 
        tree.root.left.right = new NodeTree<Integer>(30); 
        tree.root.right.left = new NodeTree<Integer>(36); 
  
        printTreeToCircularDLinkedList(BinaryTreeToCirDLL(tree.root));
        
        //Check if it is binary tree or not
        tree = new BinaryTree<Integer>(); 
        tree.root = new NodeTree<Integer>(4); 
        tree.root.left = new NodeTree<Integer>(2); 
        tree.root.right = new NodeTree<Integer>(5); 
        tree.root.left.left = new NodeTree<Integer>(1); 
        tree.root.left.right = new NodeTree<Integer>(3); 
        //tree.root.left.right.right = new NodeTree<Integer>(12);  //not BST
        
        if (tree.isBST()) 
            System.out.println("IS BST"); 
        else
            System.out.println("Not a BST"); 
        
        
        // "maxDepth" of a tree 
        System.out.println("Height of tree is : " +  
                tree.maxDepth(tree.root)); 
       
	}

}
