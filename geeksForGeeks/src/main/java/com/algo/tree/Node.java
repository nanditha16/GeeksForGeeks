package com.algo.tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

//Tree Node
/*
 * Array representation - Binary Array Tree:  2^n - 1 elements
 * 
 * left_child(i) = 2*i+1
 * right_child = 2*i+2
 * parent(i) = (i-1)/2
 * 
 * Tree should be complete : 	(2^(height+1) - 1 ) elements
 */
public class Node<T> {
	T key;
	Node<T> left, right;
	
	
	public Node(T key) {
		super();
		this.key = key;
		left = right = null;
	}
	
	
	/*
	 * BFS search nodes level by level, starting from the root node. 
	 * Then checking its children. Then children for children and so on. 
	 * Until all nodes are processed or node which satisfies search condition is found.
	 * 
	 * 
	 * Queue data structure (LinkedList implements Queue)
	 * BFS on Binary Tree (linked list as queue)
	 */
	public Node<T> bfs(Predicate<T> predicate) {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.offer(this);
		
		while(!queue.isEmpty()) {
			Node<T>  node = queue.poll();
			if(predicate.test(node.key)) return node;
			if(node.left !=null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
		return null;	
	}
	
	/*
	 * DFS checks all nodes from leftmost path from the root to the leaf,
	 *  then jumps up and check right node and so on.
	 *  
	 *   The stack data structure (ArrayDequeue/Stack)
	 *   DFS on Binary Tree (stack)
	 */
	public Node<T> dfs(Predicate<T> predicate) {
		ArrayDeque<Node<T>> stack = new ArrayDeque<Node<T>>();
		stack.push(this);
		
		while(!stack.isEmpty()) {
			Node<T> node = stack.poll();
			if(predicate.test(node.key)) return node; //if found 
			if(node.right != null) stack.push(node.right);
			if(node.left != null) stack.push(node.left);
		}
		return null;	
	}
	
	/*
	 * bfs for binary tree array is just linear search -  without stack or recursion
	 * BFS on Binary Tree Array (linear search)
	 */
	public int bfsBTA(Predicate<T> predicate, Integer[] array) {
		for (int i = 0; i < array.length; i++) {
	        if (array[i] != null && predicate.test((T) array[i])) return i;
	    }
		return -1;
		
	}
	
	/*
	 * Iterative DFS on Binary Tree Array
	 * Total complexity is O(n)
	 */
	public int dfsBTA(Predicate<T> predicate, Integer[] array) {
	    int i = 0;
	    int leaf = 0;
	    while (i < array.length) {
	        if (array[i] != null && predicate.test((T) array[i])) return i; // node found
	        if (i < array.length / 2) { // not leaf node, can be advanced
	            i = 2 * i + 1; // try left child
	        } else { // leaf node, jump
	            int k = 1;
	            while (true) {
	                i = (i - 1) / 2; // jump to the parent
	                int p = k * 2;
	                if (leaf % p == k - 1) break; // correct number of jumps found
	                k = p;
	            }
	            // after we jumped to the parent, go to the right child
	            i = 2 * i + 2;
	            leaf++; // next leaf, please
	        }
	    }
	    return -1;
	}
	
}

