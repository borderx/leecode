package com.borderx.other;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class QueensQuestion {
	
	public void addQueue(int row, int ld, int rd, Map<String, Integer> key) {
		if (row != key.get("upperlim")) {
			int pos = key.get("upperlim") & (~ (row | ld | rd));
			while(pos != 0) {
				int p = pos & -pos;	//pos & (~pos + 1)  取出最右边的那个1
				pos = pos - p;
				addQueue(row + p, (ld + p) << 1,(rd + p) >>> 1, key);
			}
		}else{
			key.put("count", key.get("count") + 1);
		}
	}
	
	public int resolve(int n) {
		Map<String, Integer> key = new HashMap<String, Integer>();
		key.put("upperlim", (1 << n) -1);
		key.put("count", 0);
		addQueue(0 ,0 , 0, key);
		return key.get("count");
	}
	
	@Test
	public void test() {
		int n = 2;
		int count = resolve(n);
		System.out.println(count);
	}
	
}
