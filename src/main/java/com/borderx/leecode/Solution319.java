package com.borderx.leecode;

import org.junit.Test;

/**
 * 319. Bulb Switcher
 * 
 There are n bulbs that are initially off. You first turn on all the bulbs.
 Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 
Example:
Given n = 3. 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.

 *
 * @author borderx
 *
 */
public class Solution319 {
	
	public int bulbSwitch(int n) {
		int[] blubs = new int[n];
		for(int i = 1; i <= n; i ++) {
			for(int j = i - 1; j < n; j += i) {
				blubs[j] = ~blubs[j] & 1;
			}
		}
		int count = 0;
		for(int num : blubs) {
			count += num;
		}
		return count;
	}
	
	public int bulbSwitch2(int n) {
		return (int)Math.sqrt(n);
	}
	
	@Test
	public void test() {
		long start = System.currentTimeMillis();
		System.out.println(bulbSwitch(9999999));
		System.out.println(System.currentTimeMillis() - start);
	}
	
}
