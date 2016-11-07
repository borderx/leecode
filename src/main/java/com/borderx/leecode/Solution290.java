package com.borderx.leecode;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 290. Word Pattern
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
Examples:
    pattern = "abba", str = "dog cat cat dog" should return true.
    pattern = "abba", str = "dog cat cat fish" should return false.
    pattern = "aaaa", str = "dog cat cat dog" should return false.
    pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space. 
 * @author borderx
 *
 */
public class Solution290 {
	public boolean wordPattern(String pattern, String str) {
		String[] pArray = new String[pattern.length()];
		for(int i = 0; i < pattern.length(); i ++) {
			pArray[i] = String.valueOf(pattern.charAt(i));
		}
		String p = summary(pArray);
		String m = summary(str.split(" "));
		return p.equals(m);
	}
	
	private String summary(String[] arrays){
		StringBuilder builder = new StringBuilder();
		Map<String, Integer> cache = new HashMap<String, Integer>();
		int number = 1;
		for(String s : arrays) {
			if(cache.containsKey(s)) {
				builder.append(cache.get(s));
			} else {
				cache.put(s, number ++);
				builder.append(cache.get(s));
			}
		}
		return builder.toString();
	}
	
	@Test
	public void test() {
		String s = "";
		String m = "beef";
		System.out.println(wordPattern(s, m));
	}
}
