package com.algo.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BinaryMirrorTree {

	public static void main(String[] args) {
		/* creating a binary tree and entering the nodes */
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);

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
