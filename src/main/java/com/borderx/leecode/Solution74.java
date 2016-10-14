package com.borderx.leecode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 74. Search a 2D Matrix
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties: -Integers in each row are sorted
 * from left to right. -The first integer of each row is greater than the last
 * integer of the previous row.
 * 
 * For example, Consider the following matrix: 
 * [ 
 * 		[1, 3, 5, 7], 
 * 		[10, 11, 16, 20],
 * 		[23, 30, 34, 50] 
 * ] 
 * Given target = 3, return true.
 * 
 * @author borderx
 * 
 */
public class Solution74 {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null) {
			return false;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int start = 0;
		int end = rows * cols -1;
		while(start <= end) {
			int index = (start + end) >> 1;
			int num = matrix[index / cols][index % cols];
			if(num == target) {
				return true;
			}else if(num < target) {
				start = index + 1;
			}else{
				end = index - 1;
			}
		}
		return false;
	}
	
	@Test
	public void test(){
		int[][] matrix = {{1, 3, 5, 7},{10, 11, 16, 20},{23, 30, 34, 50}};
		int target = 3;
		Assert.assertEquals(true, searchMatrix(matrix, target));
	}
}
