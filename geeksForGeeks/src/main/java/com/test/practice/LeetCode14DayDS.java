package com.test.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LeetCode14DayDS {

	static int[] nums = { -2, 1, 2, 4, 7, 11 };

	public static void main(String[] args) {
		// System.out.println(isDuplicateArray(nums));
		// System.out.println(maxSubArraySum(nums));
		// System.out.println(twoSum(nums,13)[0]+", "+ twoSum(nums,13)[1]);
		int[] nums1 = { 4, 9, 5 };
		int m = 3;
		int[] nums2 = { 9, 4, 9, 8, 4 };
		int n = 3;
		// merge(nums1, m, nums2, n);

//		for(int num : intersect(nums2, nums1)) {
//			System.out.println(num + " ");
//		}

//		int[] prices = {7,1,5,3,6,4};
//		System.out.println(maxProfit(prices));

//		int[][] arr = {{1, 2}, {3, 4}};
//		matrixReshape(arr,2, 4) ;

//		generatePascalTriangle(5);

//		char[][] boardT = {{'5','3','.','.','7','.','.','.','.'},
//				{'6','.','.','1','9','5','.','.','.'},
//				{'.','9','8','.','.','.','.','6','.'},
//				{'8','.','.','.','6','.','.','.','3'},
//				{'4','.','.','8','.','3','.','.','1'},
//				{'7','.','.','.','2','.','.','.','6'},
//				{'.','6','.','.','.','.','2','8','.'},
//				{'.','.','.','4','1','9','.','.','5'},
//				{'.','.','.','.','8','.','.','7','9'}};
//		
//		char[][] boardF = {{'8','3','.','.','7','.','.','.','.'},
//				{'6','.','.','1','9','5','.','.','.'},
//				{'.','9','8','.','.','.','.','6','.'},
//				{'8','.','.','.','6','.','.','.','3'},
//				{'4','.','.','8','.','3','.','.','1'},
//				{'7','.','.','.','2','.','.','.','6'},
//				{'.','6','.','.','.','.','2','8','.'},
//				{'.','.','.','4','1','9','.','.','5'},
//				{'.','.','.','.','8','.','.','7','9'}};
//		
//		System.out.println(isValidSudoku(boardT));
//		
//		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
//		System.out.println(searchMatrix(matrix, 16));

//		System.out.println(firstUniqChar("aabb"));
//		System.out.println(canConstruct("aa", "ab"));
//		System.out.println(isAnagram("aa", "aa"));
		
		System.out.println(isValidParentheses("([)]"));

	}

	public static boolean isDuplicateArray(int[] nums) {
		Set<Integer> store = new HashSet<>();
		System.out.println((nums.length));
		for (int i = 0; i < nums.length; i++) {
			if (store.add(nums[i]) == false) {
				return true;
			}
		}
		return false;

	}

	public static int maxSubArraySum(int[] nums) {
		int cur_max = nums[0];
		int final_result = nums[0];

		for (int i = 1; i < nums.length; i++) {
			cur_max = Math.max(nums[i], cur_max + nums[i]);
			final_result = final_result > cur_max ? final_result : cur_max;
		}

		return final_result;
	}

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int comp = target - nums[i];
			if (numMap.containsKey(comp)) {
				return new int[] { numMap.get(comp), i };
			} else {
				numMap.put(nums[i], i);
			}
		}
		return new int[] {};
	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		// sorted in non-decreasing order,
		int leftIdx = 0;
		int rightIdx = 0;
		int ctr = 0;

		while (leftIdx < m && rightIdx < n) {
			if (nums1[leftIdx] > nums2[rightIdx]) {
				/*
				 * When we are adding elements from the right array we need to push the elements
				 * in num1 back. That is done in the push back function. After that we need to
				 * update m.
				 */
				pushBack(nums1, m, ctr);
				nums1[ctr] = nums2[rightIdx];
				rightIdx++;
				m++;
			}
			leftIdx++;
			ctr++;
		}
		// Add remaining elements from Right array.
		while (rightIdx < n) {
			nums1[ctr] = nums2[rightIdx];
			rightIdx++;
			ctr++;
		}

	}

	private static void pushBack(int[] nums1, int m, int stIdx) {

		for (int i = m - 1; i >= stIdx; i--) {
			nums1[i + 1] = nums1[i];
		}

	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> res = new ArrayList<>();

		if (nums1.length > nums2.length) {
			intersect(nums2, nums1);
		}

		// num1 is smaller than num2, hashmap of ele and count
		Map<Integer, Integer> freqCount = new HashMap<>();

		for (int ele1 : nums1) {
			if (freqCount.containsKey(ele1)) {
				freqCount.put(ele1, freqCount.get(ele1) + 1);
			} else {
				freqCount.put(ele1, 1);
			}
		}

		// traverse the second array to find the intersection
		for (int ele2 : nums2) {
			if (freqCount.containsKey(ele2) && (freqCount.get(ele2) > 0)) {
				freqCount.put(ele2, freqCount.get(ele2) - 1);
				res.add(ele2);
			}
		}

		return res.stream().mapToInt(i -> i).toArray();

	}

	public static int maxProfit(int[] prices) {
		// brute force
//		 int profit = 0;
//		 for(int i=0; i< prices.length; i++) {
//			 for(int j=i+1; j<prices.length; j++) {
//				 
//				 if((prices[j]-prices[i] > 0) && (prices[j]-prices[i] > profit )) {
//					 profit = (prices[j]-prices[i]);
//					 
//				 }
//			 }
//		 }
//		return profit;	     

		// Method 2 : O(n)
		int max_profit = 0, min_so_far = Integer.MAX_VALUE;

		for (int i = 0; i < prices.length; i++) {
			min_so_far = Math.min(min_so_far, prices[i]);
			max_profit = Math.max(max_profit, prices[i] - min_so_far);

		}
		return max_profit;
	}

	public static int[][] matrixReshape(int[][] mat, int r, int c) {
		int[][] matResult = new int[r][c];
		int[] mat1D = new int[r * c];
		int m = mat.length, n = mat[0].length;

		if (m * n != r * c) {
			return mat;
		}

		// 2d matrix is stored in 1d memory
		// M[i][j]=M[n*i+j] , where n is the number of cols.
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat1D[(i * n) + j] = mat[i][j];

			}
		}

		// 1d matrix is stored in 2d matric as per reshaping M[i] => M[i/n][i%n]
		// i = index in 1D, n = length of the row
		for (int i = 0; i < mat1D.length; i++) {
			matResult[i / c][i % c] = mat1D[i];
		}

		return matResult;

	}

	public static List<List<Integer>> generatePascalTriangle(int numRows) {
		List<List<Integer>> tri = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		// initial case
		row.add(1);
		tri.add(row);

		// iterative case
		for (int i = 1; i < numRows; i++) {
			List<Integer> rowTemp = new ArrayList<>();

			for (int j = 0; j <= i; j++) {
				// last and first elem in a row
				if (j == 0 || j == i) {
					rowTemp.add(1);
				} else {
					// mid value calculation
					List<Integer> prevRow = tri.get(i - 1);
					int temp = prevRow.get(j - 1) + prevRow.get(j);
					rowTemp.add(temp);
				}

			}

			tri.add(rowTemp);

		}

		return tri;

	}

	public static boolean isValidSudoku(char[][] board) {
		HashSet<String> seen = new HashSet<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char current_val = board[i][j];
				if (current_val != '.') {
					// Each row must contain the digits 1-9 without repetition
					if (!seen.add(current_val + "row" + i) ||
					// Each column must contain the digits 1-9 without repetition.
							!seen.add(current_val + "col" + j) ||
							// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
							// without repetition.
							!seen.add(current_val + "row" + i / 3 + "col" + j / 3)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		int row = 0;
		int col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) { // O(m+n)
			if (matrix[row][col] == target) {
				return true;
			}
			// move to next row, if the last element in the said row is < target
			if (matrix[row][col] < target) {
				row++;
			} else {
				// move the counter from last to first column if
				// we get the row in which it could possibly be. (Linear)
				col--;
			}

		}
		return false;
	}

	public static int firstUniqChar(String s) {
		Map<Character, Integer> freqCount = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			if (freqCount.containsKey(s.charAt(i))) {
				freqCount.put(s.charAt(i), freqCount.get(s.charAt(i)) + 1);
			} else {
				freqCount.put(s.charAt(i), 1);
			}

		}

		for (int i = 0; i < s.length(); i++) {
			if (freqCount.get(s.charAt(i)) == 1) {
				return i;
			}
		}
		return -1;

	}

	public static boolean canConstruct(String ransomNote, String magazine) {
		// Method 1:
		if (ransomNote.length() < magazine.length()) {
			return false;
		}

		Map<Character, Integer> magazineFreqCount = new HashMap<>();

		for (int i = 0; i < magazine.length(); i++) {
			if (magazineFreqCount.containsKey(magazine.charAt(i))) {
				magazineFreqCount.put(magazine.charAt(i), magazineFreqCount.get(magazine.charAt(i)) + 1);
			} else {
				magazineFreqCount.put(magazine.charAt(i), 1);
			}

		}

		for (int i = 0; i < ransomNote.length(); i++) {
			if (magazineFreqCount.containsKey(ransomNote.charAt(i))
					&& magazineFreqCount.get(ransomNote.charAt(i)) != 0) {
				magazineFreqCount.put(ransomNote.charAt(i), magazineFreqCount.get(ransomNote.charAt(i)) - 1);
			} else {
				return false;
			}
		}

		return true;

	}

	public static boolean isAnagram(String s, String t) {
		if(s.length() != t.length()) {
			return false;
		}
		
		Map<Character, Integer> freqCount = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			if (freqCount.containsKey(s.charAt(i))) {
				freqCount.put(s.charAt(i), freqCount.get(s.charAt(i)) + 1);
			} else {
				freqCount.put(s.charAt(i), 1);
			}

		}


		for (int i = 0; i < t.length(); i++) {
			if (freqCount.containsKey(t.charAt(i))
					&& freqCount.get(t.charAt(i)) != 0) {
				freqCount.put(t.charAt(i), freqCount.get(t.charAt(i)) - 1);
			} else {
				return false;
			}
		}
		return true;

	}

	public static boolean isValidParentheses(String s) {
		Stack<Character> stack=new Stack<>();
		
		 HashMap<Character,Character> map=new HashMap<>();
	        map.put('(',')');
	        map.put('[',']');
	        map.put('{','}');
	       for(int i=0;i<s.length();i++)
	       {
	           if(stack.isEmpty())
	               stack.push(s.charAt(i));
	           else if(map.containsKey(stack.peek()) && map.get(stack.peek())==s.charAt(i))
	               stack.pop();
	           else
	               stack.push(s.charAt(i));
	       }
	       return stack.isEmpty();       
	}

	

}
