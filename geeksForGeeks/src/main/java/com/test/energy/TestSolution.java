package com.test.energy;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestSolution {

	static int solution(int[] A) {
        int ans = A[0];
        for (int i = 1; i < A.length; i++) {
            if (ans > A[i]) {
                ans = A[i];
            }
        }
        return ans;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		solutionSpriralMatrix(3);
	}
	
	public static int[][] solutionSpriralMatrix(int X) {
		int[][] result = new int[X][X];
		
		int k = 1;
		int top=0;
		int bottom = X-1;
		int left = 0;
		int right = X-1;
		
		
		while (k <= X*X) {
			for(int i= left; i<=right; i++) {
				result[top][i] =k;
				k++;
			}
			top++;
			
			for(int i= top; i<=bottom; i++) {
				result[i][right] =k;
				k++;
			}
			right--;
			
			for(int i= right; i>=left; i--) {
				result[bottom][i] =k;
				k++;
			}
			bottom--;
			
			for(int i= bottom; i>=top; i--) {
				result[i][left] =k;
				k++;
			}
			left++;
			
		}
		
		System.out.println(Arrays.deepToString(result));
		
		int[] array = Stream.of(result)
				.flatMapToInt(IntStream::of)
				.toArray();
		System.out.println(Arrays.toString(array));
		return result;

    }
	

}
