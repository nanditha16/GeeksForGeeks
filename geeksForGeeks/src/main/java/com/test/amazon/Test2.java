package com.test.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test2 {

	public static void main(String[] args) {
System.out.println( "Hello World!" );
        
        ArrayList<Integer> packagesSpace =  new ArrayList<Integer>();
		packagesSpace.add(1);
		packagesSpace.add(10);
		packagesSpace.add(25);
		packagesSpace.add(35);
		packagesSpace.add(60);
		
		IDsOfPackages(90, packagesSpace);
		System.out.println(IDsOfPackages(90, packagesSpace));

	}

	public static ArrayList<Integer> IDsOfPackages(int truckSpace, ArrayList<Integer> packagesSpace) {
		
	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		int maxVal = Integer.MIN_VALUE;
		// exact 30
		truckSpace-=30;
		ArrayList<Integer> result = new  ArrayList<Integer>();
		 
		//O(n)
		 for(int i= 0; i < packagesSpace.size(); i++) {
		     int val = packagesSpace.get(i);
		     if(map.containsKey(truckSpace-val)) {
		         //update 
					int cMax = val > truckSpace-val ? val : truckSpace-val;
					if(cMax > maxVal) {
						maxVal = cMax;
						result = new ArrayList<Integer>();
						result.add(map.get(truckSpace-val));
						result.add(i);
					}
				}
				map.putIfAbsent(val, i);
		 }
		 
		 return result;
		
	}
}
