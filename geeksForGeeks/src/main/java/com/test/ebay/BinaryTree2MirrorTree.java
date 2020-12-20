package com.test.ebay;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree2MirrorTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		invertTree(root);
			
	}
	/*
	 * Convert a Binary Tree into its Mirror Tree
	 */
	 public static TreeNode invertTree(TreeNode root) {
	        if (root == null) return null;
	    Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.add(root);
	    while (!queue.isEmpty()) {
	        TreeNode current = queue.poll();
	        TreeNode temp = current.left;
	        current.left = current.right;
	        current.right = temp;
	        if (current.left != null) queue.add(current.left);
	        if (current.right != null) queue.add(current.right);
	    }
	    return root;
	    }

}
