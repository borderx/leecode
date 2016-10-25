package com.borderx.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 399. Evaluate Division
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, 
 * and k is a real number (floating point number). Given some queries, return the answers. 
 * If the answer does not exist, return -1.0. 
 * 
 * @author borderx
 *
 */
public class Solution399 {
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] result = new double[queries.length];
        Map<String, Map<String, Double>> cache = new HashMap<String, Map<String,Double>>();
        for(int i = 0; i < equations.length; i ++) {
        	if(!cache.containsKey(equations[i][0])) {
        		Map<String, Double> map = new HashMap<String, Double>();
        		map.put(equations[i][1], values[i]);
        		map.put(equations[i][0], 1D);
        		cache.put(equations[i][0], map);
        		if(values[i] != 0D){
        			if(!cache.containsKey(equations[i][1])) {
        				map = new HashMap<String, Double>();
                		map.put(equations[i][0], 1 / values[i]);
                		map.put(equations[i][1], 1D);
                		cache.put(equations[i][1], map);
            		}else {
            			cache.get(equations[i][1]).put(equations[i][0], 1 / values[i]);
            		}
        		}
        	}else {
        		cache.get(equations[i][0]).put(equations[i][1], values[i]);
        		if(values[i] != 0D){
        			if(!cache.containsKey(equations[i][1])) {
        				Map<String, Double> map = new HashMap<String, Double>();
                		map.put(equations[i][0], 1 / values[i]);
                		map.put(equations[i][1], 1D);
                		cache.put(equations[i][1], map);
            		}else {
            			cache.get(equations[i][1]).put(equations[i][0], 1 / values[i]);
            		}
        		}
        	}
        	List<String> leftAddList = new ArrayList<String>();
        	for(Map.Entry<String,Map<String, Double>> tmp : cache.entrySet()){
        		if(tmp.getValue().containsKey(equations[i][0]) && tmp.getKey() != equations[i][1] && tmp.getKey() != equations[i][0]) {
        			leftAddList.add(tmp.getKey());
        		}
        	}
        	for(String s : leftAddList) {
        		double value = values[i] * cache.get(s).get(equations[i][0]);
        		cache.get(s).put(equations[i][1], value);
        		if(value != 0) {
        			if(!cache.containsKey(equations[i][1])) {
        				Map<String, Double> map = new HashMap<String, Double>();
                		map.put(s, 1 / value);
                		map.put(equations[i][1], 1D);
                		cache.put(equations[i][1], map);
            		}else {
            			cache.get(equations[i][1]).put(s, 1 / value);
            		}
        		}
        		if(cache.containsKey(equations[i][1])) {
        			for(Map.Entry<String, Double> entry : cache.get(equations[i][1]).entrySet()){
        				if(entry.getKey().equals(equations[i][0]) || entry.getKey().equals(equations[i][1])) {
        					continue;
        				}
        				if(cache.get(equations[i][0]).containsKey(entry.getKey())) {
        					continue;
        				}
        				value = values[i] * entry.getValue();
        				cache.get(equations[i][0]).put(entry.getKey(), value);
        				if(value != 0) {
        					if(!cache.containsKey(entry.getKey())) {
                				Map<String, Double> map = new HashMap<String, Double>();
                        		map.put(equations[i][0], 1 / value);
                        		map.put(entry.getKey(), 1D);
                        		cache.put(entry.getKey(), map);
                    		}else {
                    			cache.get(entry.getKey()).put(equations[i][0], 1 / value);
                    		}
                		}
        				
        				value = value * cache.get(s).get(equations[i][0]);
        				cache.get(s).put(entry.getKey(), value);
        				if(value != 0) {
        					if(!cache.containsKey(entry.getKey())) {
                				Map<String, Double> map = new HashMap<String, Double>();
                        		map.put(s, 1 / value);
                        		map.put(entry.getKey(), 1D);
                        		cache.put(entry.getKey(), map);
                    		}else {
                    			cache.get(entry.getKey()).put(s, 1 / value);
                    		}
                		}
        			}
        		}
        	}
        }
        for(int i = 0; i < result.length; i ++) {
        	if(!cache.containsKey(queries[i][0])) {
        		result[i] = -1D;
        	} else {
        		if(!cache.get(queries[i][0]).containsKey(queries[i][1])) {
        			result[i] = -1D;
        		} else {
        			result[i] = cache.get(queries[i][0]).get(queries[i][1]);
        		}
        	}
        }
        return result;
    }
	
	@Test
	public void test() {
		String[][] equations = { {"a", "b"}, {"c", "e"}, {"b", "c"} };
		double[] values = {2.0, 3.0, 2.0};
		String[][] queries = { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} };
		double[] res = calcEquation(equations, values, queries);
		System.out.println(Arrays.toString(res));
	}
}
