package com.test.practice;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

	public static void main(String[] args) {
		int[] set = new int[]{1,2,3};
		//subsets(set); 
		System.out.println(subsets(set));
	}

	private static List<List<Integer>> subsets(int[] set) {
		int set_size = set.length;
		
		/*
		 * set_size of power set of a set with set_size n is (2**n -1)
		 * 
		 */
		long pow_set_size = (long)Math.pow(2, set_size);
		List<Integer> subset = new ArrayList<Integer>();
		List<List<Integer>> powerSubsetList = new ArrayList<List<Integer>>();
		int counter, j; 
		for(counter = 0; counter < pow_set_size; counter++) {
			for(j = 0; j < set_size; j++) 
            {
				/* Check if jth bit in the counter is set,
				 *  if set then print jth element from set 
				 */
				if((counter & (1 << j)) > 0) {
					 System.out.print(set[j]); 
					subset.add(set[j]);
				}	
                   
			}
			powerSubsetList.add(subset);
			System.out.println();
		}
		//return null;
		return powerSubsetList;
		
	}

}
