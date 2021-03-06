package com.algo.array;

import java.util.Arrays;

public class ProductExceptSelf {

	/*
	 * LeetCode Solutions -
	 * https://leetcode.com/problems/product-of-array-except-self/solution/ DCP Prob
	 * 1.1 --> practice 1
	 */

	public static void main(String[] args) {
		int[] nums = new int[] { 4, 5, 1, 8, 2 }; // expected output [80,64,320,40,160]
		nums = new int[] { 0, 1, 2, 3 }; // expected output [6,0,0,0]
		nums = new int[] { 0, 0, 2, 3 }; // expected output [0,0,0,0]
		nums = new int[] { 0, -1, 2, 3 }; // expected output [-6,0,0,0]
		nums = new int[] { 0, -1, -2, 3 }; // expected output [6,0,0,0]

		System.out.println(Arrays.toString(productExceptSelf(nums)));
		System.out.println(Arrays.toString(productExceptSelf2(nums)));
	}

	/*
	 * Time = O(n); Space = O(n)
	 */
	public static int[] productExceptSelf(int[] nums) {

		// The length of the input array
		int length = nums.length;

		// The left and right arrays as described in the algorithm
		int[] L = new int[length];
		int[] R = new int[length];

		// Final answer array to be returned
		int[] answer = new int[length];

		// L[i] contains the product of all the elements to the left
		// Note: for the element at index '0', there are no elements to the left,
		// so L[0] would be 1
		L[0] = 1;
		for (int i = 1; i < length; i++) {

			// L[i - 1] already contains the product of elements to the left of 'i - 1'
			// Simply multiplying it with nums[i - 1] would give the product of all
			// elements to the left of index 'i'
			L[i] = nums[i - 1] * L[i - 1];
		}

		// R[i] contains the product of all the elements to the right
		// Note: for the element at index 'length - 1', there are no elements to the
		// right,
		// so the R[length - 1] would be 1
		R[length - 1] = 1;
		for (int i = length - 2; i >= 0; i--) {

			// R[i + 1] already contains the product of elements to the right of 'i + 1'
			// Simply multiplying it with nums[i + 1] would give the product of all
			// elements to the right of index 'i'
			R[i] = nums[i + 1] * R[i + 1];
		}

		// Constructing the answer array
		for (int i = 0; i < length; i++) {
			// For the first element, R[i] would be product except self
			// For the last element of the array, product except self would be L[i]
			// Else, multiple product of all elements to the left and to the right
			answer[i] = L[i] * R[i];
		}

		return answer;
	}

	/*
	 * Time = O(n); Space = O(1)
	 */
	public static int[] productExceptSelf2(int[] nums) {

		// The length of the input array
		int length = nums.length;

		// Final answer array to be returned
		int[] answer = new int[length];

		// answer[i] contains the product of all the elements to the left
		// Note: for the element at index '0', there are no elements to the left,
		// so the answer[0] would be 1
		answer[0] = 1;
		for (int i = 1; i < length; i++) {

			// answer[i - 1] already contains the product of elements to the left of 'i - 1'
			// Simply multiplying it with nums[i - 1] would give the product of all
			// elements to the left of index 'i'
			answer[i] = nums[i - 1] * answer[i - 1];
		}

		// R contains the product of all the elements to the right
		// Note: for the element at index 'length - 1', there are no elements to the
		// right,
		// so the R would be 1
		int R = 1;
		for (int i = length - 1; i >= 0; i--) {

			// For the index 'i', R would contain the
			// product of all elements to the right. We update R accordingly
			answer[i] = answer[i] * R;
			R *= nums[i];
		}

		return answer;
	}

}
