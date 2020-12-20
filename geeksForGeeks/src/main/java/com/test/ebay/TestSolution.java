package com.test.ebay;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

public class TestSolution {
	
	// Structure of linked list Node  
	static class Node  
	{  
	    int data;  
	    Node next,random;  
	    Node(int x)  
	    {  
	        data = x;  
	        next = random = null;  
	    }  
	} 
	
	// Utility function to print the list.  
	static void print(Node start)  
	{  
	    Node ptr = start;  
	    while (ptr != null)  
	    {  
	        System.out.println("Data = " + ptr.data + 
	                    ", Random = "+ptr.random.data);  
	        ptr = ptr.next;  
	    }  
	}  
	
	// This function clones a given   
	// linked list in O(1) space  
	static Node clone(Node start)  
	{  
	    Node curr = start, temp = null;  
	  
	    // insert additional node after  
	    // every node of original list  
	    while (curr != null)  
	    {  
	        temp = curr.next;  
	  
	        // Inserting node  
	        curr.next = new Node(curr.data);  
	        curr.next.next = temp;  
	        curr = temp;  
	    }  
	    curr = start;  
	  
	    // adjust the random pointers of the  
	    // newly added nodes  
	    while (curr != null)  
	    {  
	        if(curr.next != null)  
	            curr.next.random = (curr.random != null) 
	                      ? curr.random.next : curr.random;  
	  
	        // move to the next newly added node by  
	        // skipping an original node  
	        curr = (curr.next != null) ? curr.next.next  
	                                        : curr.next;  
	    }  
	  
	    Node original = start, copy = start.next;  
	  
	    // save the start of copied linked list  
	    temp = copy;  
	  
	    // now separate the original list and copied list  
	    while (original != null && copy != null)  
	    {  
	        original.next = (original.next != null)?  
	                    original.next.next : original.next;  
	  
	        copy.next = (copy.next != null) ? copy.next.next  
	                                            : copy.next;  
	        original = original.next;  
	        copy = copy.next;  
	    }  
	    return temp;  
	} 
	
	// Below arrays details all 8 possible movements from a cell
	// (top, right, bottom, left and 4 diagonal moves)
	private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
	private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

	public static void main(String[] args) {
		countNoOfIsland();
		cloneLinkedList();
		System.out.println();
	}

	/*
	 * Print a given m*n matrix in spiral form Recursively
	 */
	public static List < Integer > spiralOrder(int[][] matrix) {
		List<Integer> l=new ArrayList<>();
        if(matrix.length==0) return l;
        int m=matrix.length;
        int n=matrix[0].length;
        int c=0;
        while(c<(Math.min(m,n)+1)/2){
            for(int i=c;i<=n-c-1;i++) l.add(matrix[c][i]);
            for(int i=c+1;i<m-c-1;i++) l.add(matrix[i][n-c-1]);
            for(int i=n-c-1;m-c-1!=c&&i>=c;i--) l.add(matrix[m-c-1][i]);//notice one row
            for(int i=m-c-2;n-c-1!=c&&i>c;i--) l.add(matrix[i][c]);//notice one col
            c++;
        }
        System.out.println(l);
        return l;
    
    }
	
  
    // Function for printing matrix in spiral 
    // form i, j: Start index of matrix, row 
    // and column respectively m, n: End index 
    // of matrix row and column respectively 
    static void print(int arr[][], int i, int j, int m, 
                      int n) 
    { 
        // If i or j lies outside the matrix 
        if (i >= m || j >= n) { 
            return; 
        } 
  
        // Print First Row 
        for (int p = i; p < n; p++) { 
            System.out.print(arr[i][p] + " "); 
        } 
  
        // Print Last Column 
        for (int p = i + 1; p < m; p++) { 
            System.out.print(arr[p][n - 1] + " "); 
        } 
  
        // Print Last Row, if Last and 
        // First Row are not same 
        if ((m - 1) != i) { 
            for (int p = n - 2; p >= j; p--) { 
                System.out.print(arr[m - 1][p] + " "); 
            } 
        } 
  
        // Print First Column, if Last and 
        // First Column are not same 
        if ((n - 1) != j) { 
            for (int p = m - 2; p > i; p--) { 
                System.out.print(arr[p][j] + " "); 
            } 
        } 
        print(arr, i + 1, j + 1, m - 1, n - 1); 
    } 
	/*
	 * Given a linked list with each node pointing to next &amp; some random node within the linked list,
	 * create a copy (clone) of the linkedlist
	 */
	private static void cloneLinkedList() {
		Node start = new Node(1);  
	    start.next = new Node(2);  
	    start.next.next = new Node(3);  
	    start.next.next.next = new Node(4);  
	    start.next.next.next.next = new Node(5);  
	  
	    // 1's random points to 3  
	    start.random = start.next.next;  
	  
	    // 2's random points to 1  
	    start.next.random = start;  
	  
	    // 3's and 4's random points to 5  
	    start.next.next.random = start.next.next.next.next;  
	    start.next.next.next.random = start.next.next.next.next;  
	  
	    // 5's random points to 2  
	    start.next.next.next.next.random = start.next;  
	  
	    System.out.println("Original list : ");  
	    print(start);  
	  
	    System.out.println("Cloned list : ");  
	    Node cloned_list = clone(start);  
	    print(cloned_list);  
	    
	}

	// Function to check if it is safe to go to position (x, y)
	// from current position. The function returns false if (x, y)
	// is not valid matrix coordinates or (x, y) represents water or
	// position (x, y) is already processed

	public static boolean isSafePosition(int[][] mat, int x, int y, boolean[][] processed) {
		return (x >= 0) && (x < processed.length) && (y >= 0) && (y < processed[0].length)
				&& (mat[x][y] == 1 && !processed[x][y]);
	}

	public static void BFS(int[][] mat, boolean[][] processed, int i, int j) {
		// create an empty queue and enqueue source node
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));
		
	
		// mark source node as processed
		processed[i][j] = true;

		// loop till queue is empty
		while (!q.isEmpty()) {
			// pop front node from queue and process it
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			// check for all 8 possible movements from current cell
			// and enqueue each valid movement
			for (int k = 0; k < 8; k++) {
				// Skip if location is invalid or already processed
				// or has water
				if (isSafePosition(mat, x + row[k], y + col[k], processed)) {
					// skip if location is invalid or it is already
					// processed or consists of water
					processed[x + row[k]][y + col[k]] = true;
					q.add(new Pair(x + row[k], y + col[k]));
				}
			}
		}
	}

	/*
	 * Given a m*n matrix with 0s and 1s, consider 1s as land and 0s as water, count
	 * the number of island
	 * 
	 */
	private static void countNoOfIsland() {
   
		int[][] mat = { { 1, 0, 1, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
				{ 1, 1, 1, 1, 0, 0, 1, 0, 0, 0 }, { 1, 0, 0, 1, 0, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
				{ 0, 1, 0, 1, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 1, 0, 0, 1, 1, 1, 0 },
				{ 1, 0, 1, 0, 1, 0, 0, 1, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 } };

		int M = mat.length;
		int N = mat[0].length;

		// stores if cell is processed or not
		boolean[][] processed = new boolean[M][N];

		int island = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// start BFS from each unprocessed node and
				// increment island count
				if (mat[i][j] == 1 && !processed[i][j]) {
					BFS(mat, processed, i, j);
					island++;
				}
			}
		}

		
		System.out.println("Number of islands are " + island);
		
		System.out.println("spiralOrder of the mat : ") ;	
		spiralOrder(mat);
		
		
		//isListPalindrome
		//mutateTheArray
		//binaryPatternMatching
		//treeRightView
	}

	/*
	public List<Integer> rightSideView(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	 
	    if(root == null) return result;
	 
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.add(root);
	 
	    while(queue.size() > 0){
	        //get size here
	        int size = queue.size();
	 
	        for(int i=0; i<size; i++){
	            TreeNode top = queue.remove();
	 
	            //the first element in the queue (right-most of the tree)
	            if(i==0){
	                result.add((Integer)top.val);
	            }
	            //add right first
	            if(top.right != null){
	                queue.add(top.right);
	            }
	            //add left
	            if(top.left != null){
	                queue.add(top.left);
	            }
	        }
	    }
	 
	    return result;
	}	
*/
	
	public int countOfOccurrences(String str, String subStr) {
		  return (str.length() - str.replaceAll(Pattern.quote(subStr), "").length()) / subStr.length();
		}
}
