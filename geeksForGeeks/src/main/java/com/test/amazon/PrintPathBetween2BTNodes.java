package com.test.amazon;

import java.util.*;

/* Java program to print path between any  
 * two nodes in a Binary Tree  
 */

class PrintPathBetween2BTNodes {

// structure of a node of binary tree  
	static class Node {
		int data;
		Node left, right;
	}

	/*
	 * Helper function that allocates a new node with the given data and null left
	 * and right pointers.
	 */
	static Node getNode(int data) {
		Node newNode = new Node();
		newNode.data = data;
		newNode.left = newNode.right = null;
		return newNode;
	}

// Function to check if there is a path from root  
// to the given node. It also populates  
// 'arr' with the given path  
	static boolean getPath(Node root, Vector<Integer> arr, int x) {
		// if root is null
		// there is no path
		if (root == null)
			return false;

		// push the node's value in 'arr'
		arr.add(root.data);

		// if it is the required node
		// return true
		if (root.data == x)
			return true;

		// else check whether the required node lies
		// in the left subtree or right subtree of
		// the current node
		if (getPath(root.left, arr, x) || getPath(root.right, arr, x))
			return true;

		// required node does not lie either in the
		// left or right subtree of the current node
		// Thus, remove current node's value from
		// 'arr'and then return false
		arr.remove(arr.size() - 1);
		return false;
	}

// Function to print the path between  
// any two nodes in a binary tree  
	static void printPathBetweenNodes(Node root, int n1, int n2) {
		// vector to store the path of
		// first node n1 from root
		Vector<Integer> path1 = new Vector<Integer>();

		// vector to store the path of
		// second node n2 from root
		Vector<Integer> path2 = new Vector<Integer>();

		getPath(root, path1, n1);
		getPath(root, path2, n2);

		int intersection = -1;

		// Get intersection point
		int i = 0, j = 0;
		while (i != path1.size() || j != path2.size()) {

			// Keep moving forward until no intersection
			// is found
			if (i == j && path1.get(i) == path2.get(i)) {
				i++;
				j++;
			} else {
				intersection = j - 1;
				break;
			}
		}

		// Print the required path
		for (i = path1.size() - 1; i > intersection; i--)
			System.out.print(path1.get(i) + " ");

		for (i = intersection; i < path2.size(); i++)
			System.out.print(path2.get(i) + " ");
	}

// Driver program  
	public static void main(String[] args) {
		// binary tree formation
		Node root = getNode(0);
		root.left = getNode(1);
		root.left.left = getNode(3);
		root.left.left.left = getNode(7);
		root.left.right = getNode(4);
		root.left.right.left = getNode(8);
		root.left.right.right = getNode(9);
		root.right = getNode(2);
		root.right.left = getNode(5);
		root.right.right = getNode(6);

		int node1 = 7;
		int node2 = 4;
		printPathBetweenNodes(root, node1, node2);

	}
}