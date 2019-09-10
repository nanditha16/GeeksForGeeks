package com.algo.tree;

public class BinaryMirrorTree {

	public static void main(String[] args) {
		/* creating a binary tree and entering the nodes */
		BinaryTree<Integer> tree = new BinaryTree<Integer> ();
		tree.root = new NodeTree<Integer> (1);
		tree.root.left = new NodeTree<Integer> (2);
		tree.root.right = new NodeTree<Integer> (3);
		tree.root.left.left = new NodeTree<Integer> (4);
		tree.root.left.right = new NodeTree<Integer> (5);

		/* print inorder traversal of the input tree */
		System.out.println("Inorder traversal of input tree is :");
		tree.printInorder();
		
		/* convert tree to its mirror */
        tree.mirror(); 
  
        /* print inorder traversal of the mirror tree */
        System.out.println("Inorder traversal of binary tree is : "); 
        tree.printInorder();
        System.out.println();
        
       
        
       	}

}
