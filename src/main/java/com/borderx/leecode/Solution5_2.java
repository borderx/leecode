package com.borderx.leecode;

import org.junit.Test;

public class Solution5_2 {
	
	@Test
	public void test() {
		long start = System.currentTimeMillis();
		String res = longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(res);
	}
	
	public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chs = s.toCharArray();
        int[] result = new int[2];
        int position=0;
        /*判断条件：当回文串最大长度比postion到终点的距离两倍长的时候就可以停止判断*/
        while (chs.length-position>(result[1]-result[0])/2){
            int tmp=position+1;
            if (tmp==chs.length)break;
            while (chs[position]==chs[tmp]){
                tmp++;
                if (tmp==chs.length)break;
            }
            /*寻找以不相等的字符位置为left，right向两边扫描，获取更长的回文字符串，避免前面重复扫描*/
            int[] tmptag=findThelongest(position-1,tmp,chs);
            if (tmptag[1]-tmptag[0]>result[1]-result[0]) result=tmptag;
            position=tmp;
        }
        StringBuilder bd=new StringBuilder();
        for(int i=result[0];i<=result[1];i++){
            bd.append(chs[i]);
        }
        return bd.toString();
    }
	
	/*判断返回一次扫描中的最长字符串的位置*/
    private int[] findThelongest(int left, int right, char[] chs) {
        int[] r=new int[2];
        while (left>=0&&right<chs.length){
            if (chs[left]!=chs[right]){
                break;
            }
            left--;right++;
        }
        r[0]=left+1;r[1]=right-1;
        return r;
    }
}
