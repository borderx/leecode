package com.borderx.leecode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 377. Combination Sum IV
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.
Example:
nums = [1, 2, 3]
target = 4
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Therefore the output is 7.
 * @author borderx
 *
 */
public class Solution377 {
	public int combinationSum4(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		return addCombination(nums, target, new HashMap<Integer,Integer>());
	}
	
	private int addCombination(int[] nums, int target, Map<Integer,Integer> cache) {
		if(target == 0) {
			return 1;
		} else if(target < 0) {
			return 0;
		} else {
			int count = 0;
			if(cache.containsKey(target)) {
				return cache.get(target);
			}
			for(int i = 0; i < nums.length; i ++) {
				count += addCombination(nums, target - nums[i], cache);
			}
			cache.put(target, count);
			return count;
		}
	}

	
	@Test
	public void test() {
		int count = combinationSum4(new int[]{1, 2, 3}, 4);
		Assert.assertEquals(7, count);
	}

}
