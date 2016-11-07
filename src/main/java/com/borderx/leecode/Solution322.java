package com.borderx.leecode;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

/**
 * 322. Coin Change
 * 
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1. 
 * 
 * @author borderx
 *
 */
public class Solution322 {
	public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for(int i = 1; i <= amount; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int num: coins){
            	if(i == num) {
            		dp[i] = 1;
            		break;
            	} else if(i > num) {
            		if(dp[i - num] != Integer.MAX_VALUE) {
            			dp[i] = Math.min(dp[i], dp[i - num] + 1);
            		}
            	}
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
	
	public int coinChange(int[] coins, int amount) {
		if(amount == 0) {
			return 0;
		}
		if(coins == null || coins.length == 0) {
			return -1;
		}
		int count = -1;
		LinkedList<Integer> cache = new LinkedList<Integer>();
		int target = amount;
		Arrays.sort(coins);
		int arraySize = coins.length;
		cache.addFirst(arraySize - 1);
		while(cache.size() > 0) {
			target -= coins[cache.getLast()];
			if(count > 0 && cache.size() >= count) {
				target = reduceUnSameLastAfterRemoveLast(coins, cache, target);
			} else {
				if(target == 0) {
					if(count < 0 || cache.size() < count) {
						count = cache.size();
					}
					target = reduceUnSameLastAfterRemoveLast(coins, cache, target);
				} else if (target > 0) {
					int last = cache.getLast();
					cache.addLast(last);
				} else {
					target = reduceLast(coins, cache, target);
				}
			}
		}
		return count;
	}
	
	public int reduceLast(int[] coins, LinkedList<Integer> cache, int target) {
		while(cache.size() > 0) {
			if(cache.getLast() > 0) {
				int last = cache.removeLast();
				target += coins[last];
				cache.addLast(last - 1);
				break;
			} else {
				int last = cache.removeLast();
				target += coins[last];
			}
		}
		return target;
	}
	
	public int reduceUnSameLastAfterRemoveLast(int[] coins, LinkedList<Integer> cache, int target) {
		//先移除最后一位
		int last = cache.removeLast();
		target += coins[last];
		if(cache.size() > 0){
			//获取移除后的最后一位值
			int t = cache.removeLast();
			target += coins[t];
			while(cache.size() > 0) {
				int l = cache.getLast();
				if(l != t) {
					break;
				}
				cache.removeLast();
				target += coins[l];
			}
			target = reduceLast(coins, cache, target);
		}
		return target;
	}
	
	@Test
	public void test() {
		long start = System.currentTimeMillis();
		int[] coins = new int[]{1,2,4};
		int result = coinChange2(coins, 10);
		System.out.println(result);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
