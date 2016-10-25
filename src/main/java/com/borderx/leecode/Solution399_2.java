package com.borderx.leecode;

import java.util.Arrays;
import java.util.HashMap;
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

public class Solution399_2 {
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] result = new double[queries.length];
        Map<String, Integer> converter = new HashMap<String, Integer>();
        int number = 0;
        for(String[] array : equations) {
        	if(!converter.containsKey(array[0])) {
        		converter.put(array[0], number ++);
        	}
        	if(!converter.containsKey(array[1])) {
        		converter.put(array[1], number ++);
        	}
        }
        Double[][] graph = createGraph(equations, values, converter);
        for(int i = 0; i < queries.length; i ++) {
        	if(converter.get(queries[i][0]) == null || converter.get(queries[i][1]) == null) {
        		result[i] = -1D;
        	}else{
        		Double res = dfsFind(graph, converter.get(queries[i][0]), converter.get(queries[i][1]), new boolean[number][number]);
            	result[i] = res == null ? -1D : res;
        	}
        }
        return result;
    }
	/**
	 * 这里用Double可以判断 负值和0 的问题，而double则无法判断
	 * @param equations
	 * @param values
	 * @param converter
	 * @return
	 */
	private Double[][] createGraph(String[][] equations, double[] values, Map<String, Integer> converter) {
		Double[][] graph = new Double[converter.size()][converter.size()];
		for(int i = 0; i < equations.length; i ++) {
			graph[converter.get(equations[i][0])][converter.get(equations[i][1])] = values[i];
			if(values[i] != 0) {
				graph[converter.get(equations[i][1])][converter.get(equations[i][0])] = 1 / values[i];
			}
		}
		return graph;
	}
	
	
	/**
	 * 只需要找到一个路径即可
	 * @param graph
	 * @param start
	 * @param end
	 * @param visited
	 * @return
	 */
	private Double dfsFind(Double[][] graph, int start, int end, boolean[][] visited) {
		if(start == end) {
			return 1D;
		}
		if(graph[start][end] != null) {
			return graph[start][end];
		}
		for(int i = 0; i < graph[start].length; i ++) {
			if(graph[start][i] != null && !visited[start][i]) {
				visited[start][i] = true;
				visited[i][start] = true;
				Double res = dfsFind(graph, i, end, visited);
				if(res != null) {
					return res * graph[start][i];
				} else {
					visited[start][i] = false;
					visited[i][start] = false;
				}
			}
		}
		return null;
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
