package com.borderx.leecode;

import org.junit.Test;

/**
 * 5. Longest Palindromic Substring
 * 
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 * 
 * @author borderx
 *
 */

public class Solution5 {
	
	@Test
	public void test() {
		long start = System.currentTimeMillis();
		String res = longestPalindrome("a");
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(res);
	}
	
	public String longestPalindrome(String s) {
		String res = "";
		if(s == null || s.length() == 0) {
			return res;
		}
		for(int i = 0; i < s.length(); i++) {
			String str = getMaxLengthPalindrome(s, i, i);
			if(str.length() > res.length()) {
				res = str;
			}
			str = getMaxLengthPalindrome(s, i, i + 1);
			if(str.length() > res.length()) {
				res = str;
			}
		}
		return res;
    }
	
	public String getMaxLengthPalindrome(String s, int left, int right) {
		int start = left;
		int end = right;
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
	        start --;
	        end ++;
	    }
		return s.substring(start + 1, end);
	}
	
}
