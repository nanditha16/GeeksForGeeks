package com.test.ebay;

import java.util.LinkedList;
import java.util.Queue;

public class TreeProblem {

	// Function to perform preorder traversal of the binary tree
	public static void preorder(TreeNode root) {
		if (root == null) {
			return;
		}

		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);
	}

	// Utility function to swap left subtree with right subtree
	public static void swap(TreeNode root) {
		if (root == null) {
			return;
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}

	// Function to convert given binary Tree to its mirror
	public static void convertToMirror(TreeNode root) {
		// base case: if tree is empty
		if (root == null) {
			return;
		}

		// convert left subtree
		convertToMirror(root.left);

		// convert right subtree
		convertToMirror(root.right);

		// swap left subtree with right subtree
		swap(root);
	}

	public static void main(String[] args) {
		 /* Construct below tree
		        1
		      /   \
		     /     \
		    2       3
		   / \     / \
		  4   5   6   7   
		 */

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		convertToMirror(root);
		preorder(root);
		
	}

	
	 
	 
}
