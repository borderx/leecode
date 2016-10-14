package com.borderx.leecode;

import org.junit.Assert;
import org.junit.Test;


/**
 * 313. Super Ugly Number
 * 
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers 
 * given primes = [2, 7, 13, 19] of size 4.
 * 
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000. 
 * 
 * @author borderx
 *
 */
public class Solution313 {
	
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] times  = new int[primes.length];
		int[] ugly  = new int[n];
		ugly[0] = 1;
		for(int i=1; i<n; i++) {
			int minNum = Integer.MAX_VALUE;
			for(int j=0; j<primes.length; j++) {
				minNum = Math.min(minNum, primes[j] * ugly[times[j]]);
			}
			ugly[i] = minNum;
			for(int j=0; j<primes.length; j++) {
				if(primes[j] * ugly[times[j]] == minNum) {
					times[j] ++;
				}
			}
		}
		return ugly[n-1];
	}
	
	//将每次乘法结果缓存，避免重复计算
	public int nthSuperUglyNumber2(int n, int[] primes) {
		int[] times  = new int[primes.length];
		int[] ugly  = new int[n];
		int[] cash = new int[primes.length];
		ugly[0] = 1;
		for(int i=1; i<n; i++) {
			int minNum = Integer.MAX_VALUE;
			for(int j=0; j<primes.length; j++) {
				int num = cash[j];
				if(num == 0) {
					num = primes[j] * ugly[times[j]];
					cash[j] = num;
				}
				minNum = Math.min(minNum, num);
			}
			ugly[i] = minNum;
			for(int j=0; j<cash.length; j++) {
				if(cash[j] == minNum) {
					cash[j] =0;
					times[j] ++;
				}
			}
		}
		return ugly[n-1];
	}
	
	
	@Test
	public void test(){
		//1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32
		int[] array = new int[]{2, 7, 13, 19};
		int n = 12;
		Assert.assertEquals(32, nthSuperUglyNumber(n, array));
	}
	
}
