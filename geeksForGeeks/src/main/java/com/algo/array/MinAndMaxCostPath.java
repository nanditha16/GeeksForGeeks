package com.algo.array;

public class MinAndMaxCostPath {

	/*
	 * Given a matrix with each cell containing each number of candies,
	 *  and a constraint that you can move only right or down,
	 *   from the top left corner to the bottom right corner, 
	 *   find the path that gets you maximum candies.
	 *  -  Maximum path - O(m*n)
	 */  
	private static int maxCost(int[][] cost, int m, int n) {
		int i, j; 
		int res = -1; 
		 
		// find max in first row
        for (i = 0; i < n+1; i++) {
        	res = Math.max(res, cost[0][i]); 
        }
        
        for (i = 1; i < m+1; i++) {
        	res = -1; 
        	for(j = 0; j < n+1; j++) {
        		// When all paths are possible 
        		if (j > 0 && j < n) {
        			cost[i][j] += Math.max(cost[i - 1][j], 
                                 Math.max(cost[i - 1][j - 1],  
                                		 cost[i - 1][j + 1])); 
        		} else if (j > 0) {
        			// When diagonal right is not possible
        			cost[i][j] += Math.max(cost[i - 1][j], 
        					cost[i - 1][j - 1]); 
        		} else if (j < n ) {
        			// When diagonal left is not possible 
        			cost[i][j] += Math.max(cost[i - 1][j], 
        					cost[i - 1][j + 1]); 
        		}
                    
        		// Store max path sum 
                res = Math.max(cost[i][j], res);  	
        	}
        }
       return res;
	}
	
	 
	 
	/*   Given a cost matrix cost[][] and a position (m, n) in cost[][], 
	 *   write a function that returns cost of minimum cost path 
	 *   to reach (m, n) from (0, 0). Each cell of the matrix represents a
	 *    cost to traverse through that cell. Total cost of a path to reach 
	 *    (m, n) is sum of all the costs on that path (including both source
	 *     and destination). You can only traverse down, right and diagonally lower 
	 *     cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), 
	 *     (i, j+1) and (i+1, j+1) can be traversed. You may assume that all costs
	 *      are positive integers.
	 *  - Minimum path -  Dynamic Programming(DP) problems,
	 *  DP implementation is O(mn)
	 *  
	 *   
	 */
	private static int minCost(int[][] cost, int m, int n) {
		int i, j; 
        int tempCost[][]=new int[m+1][n+1];
        
        tempCost[0][0] = cost[0][0]; 
        
        /* Initialize first column of total cost(tempCost) array */
        for (i = 1; i <= m; i++) {
        	tempCost[i][0] = tempCost[i-1][0] + cost[i][0]; 
        }
        
        /* Initialize first row of tempCost array */
        for (j = 1; j <= n; j++) {
        	tempCost[0][j] = tempCost[0][j-1] + cost[0][j];
        }
        
        /* Construct rest of the tempCost array */
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
            	tempCost[i][j] = min(tempCost[i-1][j-1],  
            			tempCost[i-1][j], 
            			tempCost[i][j-1]) + cost[i][j]; 
            }
        }
		return tempCost[m][n]; 
	}
	
	private static int min(int x, int y, int z) {
		if (x < y) 
            return (x < z)? x : z; 
        else
            return (y < z)? y : z; 
	}

	public static void main(String[] args) {
		int cost[][]= {{1, 2, 3}, 
                {4, 8, 2}, 
                {1, 5, 3}}; 
		System.out.println(minCost(cost,2,2)); //row,col index 0
		
		int mat[][] = { { 10, 10, 2, 0, 20, 4 }, 
                { 1, 0, 0, 30, 2, 5 }, 
                { 0, 10, 4, 0, 2, 0 }, 
                { 1, 0, 2, 20, 0, 4 }  
            };
        
		System.out.println(maxCost(mat,3,5)); //row,col index 0
 
		int mat1[][] = {{348,391},{618,193}};
		System.out.println(maxCost(mat1,1,1)); //row,col index 0

	}

	

}
