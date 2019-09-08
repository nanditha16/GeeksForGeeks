package com.test.amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	static int minimumDistance = 0;
	
    public static void main( String[] args )
    {
       int numRows = 3;
       int numColumns =3;
       //2D
       List<List<Integer>> area = new LinkedList<List<Integer>>();
       
       Integer[] row1 = { 1,0,0};
       List<Integer> rowList1 = Arrays.asList(row1);  
		
       Integer[] row3 = { 1,9,1};
       List<Integer> rowList3 = Arrays.asList(row3);  
		
       area.add(rowList1);
       area.add(rowList1);
       area.add(rowList3);
       
      
    	System.out.println(minimumDistance( numRows,  numColumns, area));
    	
    }
    
    
    public static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area)
    {
    	// WRITE YOUR CODE HERE
    	
    	
    	//track visited
    	
    	boolean[][] visted = new boolean[numRows][numColumns];
    	
    	int initialDist = 0;
    	
    	//currentRow, currentCol initially 0
    	if(checkPath(numRows, numColumns, area, visted, 0, 0, initialDist)) {
    		return minimumDistance;
    	} else {
    		//no path
    		return -1;
    	}
        
    }


	private static boolean checkPath(int numRows, int numColumns, List<List<Integer>> area, boolean[][] visted, int currentRow,
			int currentCol, int initialDist) {
		
		//check for edge cases
		if(numRows < 0 || numColumns < 0 || currentRow >= numRows || currentCol >= numColumns) {
			return false;
		}
		
		//if already cell is not visited
		if(visted[currentRow] [currentCol]) {
			return false;
		}
		
		// if value at cell is 0
		if(area.get(currentRow).get(currentCol) == 0) {
			return false;
		}
		
		if(area.get(currentRow).get(currentCol) == 9) {
			return true;
		}
		
		initialDist++;
		
		visted[currentRow] [currentCol] = true;
		
		//BFS for all directions
		if((checkPath(numRows, numColumns, area, visted, currentRow++, currentCol, initialDist)) || (checkPath(numRows, numColumns, area, visted, currentRow--, currentCol, initialDist)) || 
				(checkPath(numRows, numColumns, area, visted, currentRow, currentCol++, initialDist)) || (checkPath(numRows, numColumns, area, visted, currentRow, currentCol--, initialDist))){
    		
			if( minimumDistance == 0) {
				minimumDistance = initialDist;
			}
			return true;
    	} else {
    		return false;
    	}
		
		
		
	}
	
	
    
//	public static ArrayList<Integer> IDsOfPackages(int truckSpace, ArrayList<Integer> packagesSpace) {
//		//System.out.println("Input:" + truckSpace + packagesSpace);
//
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		
//		// exactly 30 space
//		int temp = truckSpace - 30;
//		
//		for(int i= 0; i < packagesSpace.size(); i++) {
//			//pick exactly 2 packages
//			if(result.size() == 2) {
//				break;
//			}
//			int initialSpace = packagesSpace.get(i);
//			
//			for(int j=i+1; j < packagesSpace.size(); j++) {
//				int sum = initialSpace+packagesSpace.get(j);
//				if(sum == temp) {
//					result.add(i);
//					result.add(j);
//					break;
//				}
//			}
//			
//		}
//		
//		
//		return result;
//	}
}
