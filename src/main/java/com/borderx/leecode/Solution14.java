package com.borderx.leecode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 14. Longest Common Prefix
 * 
 * Write a function to find the longest common prefix string amongst an array of strings. 
 * 
 * @author borderx
 *
 */
public class Solution14 {
	public String longestCommonPrefix(String[] strs) {
	    if(strs == null || strs.length == 0)    return "";
	    String pre = strs[0];
	    int i = 1;
	    while(i < strs.length){
	        while(strs[i].indexOf(pre) != 0)
	            pre = pre.substring(0,pre.length()-1);
	        i++;
	    }
	    return pre;
	}
	
	@Test
	public void test(){
		String[] strs = new String[]{"apple","applu","appdd","apkiu","apojh","ap"};
		String prefix = longestCommonPrefix(strs);
		Assert.assertEquals("ap", prefix);
	}
	
}
