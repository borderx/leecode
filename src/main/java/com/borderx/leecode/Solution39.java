package com.borderx.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 39. Combination Sum
 * 
 * Given a set of candidate numbers (C) and a target number (T), 
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * 1.All numbers (including target) will be positive integers.
 * 2.The solution set must not contain duplicate combinations.
 * @author borderx
 *
 */
public class Solution39 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> combination = new LinkedList<Integer>();
		addCombination(candidates, combination, 0, target, 0, result);
		return result;
	}
	
	private void addCombination(int[] candidates, LinkedList<Integer> combination, int start, int expect, int sum, List<List<Integer>> result) {
		if(expect == sum) {
			result.add(new ArrayList<Integer>(combination));
			return;
		}
		
		for(int i = start; i < candidates.length; i ++) {
			int total = sum + candidates[i];
			if(total <= expect) {
				combination.addLast(candidates[i]);
				addCombination(candidates, combination, i, expect, total, result);
				combination.removeLast();
			}
		}
	}
	
	@Test
	public void test() {
		int[] candidates = {2, 3, 6, 7};
		int target = 7;
		List<List<Integer>> result = combinationSum(candidates, target);
		System.out.println(result);
	}
}
