package com.borderx.leecode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write a function that takes a string as input and returns the string
 * reversed.
 * 
 * Example: Given s = "hello", return "olleh".
 * 
 * @author borderx
 * 
 */

public class Solution344 {
	public String reverseString(String s) {
		char[] charArray = s.toCharArray();
		int end = charArray.length - 1;
		int start = 0;
		while(start < end) {
			char tmp = charArray[start];
			charArray[start] = charArray[end];
			charArray[end] = tmp;
			start ++;
			end --;
		}
		return new String(charArray);
	}
	
	@Test
	public void test(){
		String s = "hellol";
		String res = reverseString(s);
		Assert.assertEquals("lolleh", res);
	}
}
