package com.test.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeetCode14DayDS {

	static int[] nums = {-2,1,2,4,7,11};
	public static void main(String[] args) {
		//System.out.println(isDuplicateArray(nums));
		//System.out.println(maxSubArraySum(nums));
		//System.out.println(twoSum(nums,13)[0]+", "+ twoSum(nums,13)[1]);
		int[]nums1 = {4,9,5};
		int m = 3;
		int[] nums2 = {9,4,9,8,4};
		int n = 3;
		// merge(nums1, m, nums2, n);
		
//		for(int num : intersect(nums2, nums1)) {
//			System.out.println(num + " ");
//		}
		
		
	}
	
	public static boolean isDuplicateArray(int[] nums) {
		 Set<Integer> store = new HashSet<>();
		 System.out.println((nums.length));
		    for(int i=0; i< nums.length;i++) {
		        if(store.add(nums[i]) == false) {
		            return true;
		        } 
		    }
		    return false;
   
	}
	
	public static int maxSubArraySum(int[] nums) {
		int cur_max = nums[0];
		int final_result = nums[0];
		
		 for(int i=1; i< nums.length;i++) { 
			 cur_max = Math.max(nums[i], cur_max+nums[i]);
			 final_result = final_result>cur_max?final_result:cur_max;
		 }
		
		return final_result;
	}
	
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numMap = new HashMap<>();
		for(int i=0;i<nums.length; i++) {
			int comp = target-nums[i];
			if(numMap.containsKey(comp)) {
				return new int[] {numMap.get(comp), i};
			} else {
				numMap.put(nums[i], i);
			}
		}
		return new int[] {};
    }

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //sorted in non-decreasing order,
		int leftIdx = 0;
        int rightIdx = 0;
        int ctr = 0;
        
        while (leftIdx<m && rightIdx<n){
            if(nums1[leftIdx]>nums2[rightIdx]){
			/*
            * When we are adding elements from the right array we need to push the elements in 
			* num1 back. That is done in the push back function. After that we need to update m.
			*/
                pushBack(nums1,m,ctr);
                nums1[ctr] = nums2[rightIdx];
                rightIdx++;
                m++;
            } 
            leftIdx++;
            ctr++;
        }
		// Add remaining elements from Right array.
        while(rightIdx<n ){
            nums1[ctr] = nums2[rightIdx];
            rightIdx++;ctr++;
        }
        
    }

	private static void pushBack(int[] nums1, int m, int stIdx) {

		for(int i = m-1;i>=stIdx;i--){
            nums1[i+1] = nums1[i];
        }
		
	}
	
	public static int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> res = new ArrayList<>();
		
		if(nums1.length > nums2.length) {
			 intersect(nums2, nums1);
		}
		
		//num1 is smaller than num2, hashmap of ele and count
		Map<Integer,Integer> freqCount = new HashMap<>();
		
		for(int ele1 : nums1) {
			if(freqCount.containsKey(ele1)) {
				freqCount.put(ele1, freqCount.get(ele1)+1);
			} else {
				freqCount.put(ele1, 1);
			}
		}
		
		//traverse the second array to find the intersection
		for (int ele2 : nums2) {
			if(freqCount.containsKey(ele2) && (freqCount.get(ele2) > 0)){
				freqCount.put(ele2, freqCount.get(ele2)-1);
				res.add(ele2);
			} 
		}
		
		return res.stream().mapToInt(i -> i).toArray();	
        
    }
	
	
	
}