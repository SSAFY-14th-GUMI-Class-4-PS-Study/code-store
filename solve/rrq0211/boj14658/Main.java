package rrq0211.boj14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int left, right, under, over;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()) + 1; // n = 가로
		int m = Integer.parseInt(st.nextToken()) + 1; // m = 세로
		int l = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// i = 세로
		int[] pi = new int[m + 1];
		
		// j = 가로
		int[] pj = new int[n + 1];
		
		int[][] points = new int[k][2];
		
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pi[y]++;
			pj[x]++;
			
			points[i][0] = x;
			points[i][1] = y;
		}
		
		System.out.println(Arrays.toString(pj));
		System.out.println(Arrays.toString(pi));
		
		// 세로
		scan(pi, m, l, false);
		// 가로
		scan(pj, n, l, true);
		System.out.println(left + " " + right + " " + under + " " + over);
		int result = 0;
		for (int i = 0; i < k; i++) {
			if (left <= points[i][0] && points[i][0] <= right &&
					under <= points[i][1] && points[i][1] <= over
					) {
				result++;
			}
		}
		
		System.out.println(k - result);
	}
	
	static void scan(int[] p, int len, int k, boolean isWidth) {
		int psum = 0;
		int curr = 0;
		for (; curr < k; curr++) {
			psum += p[curr];
		}
		
		int nowNum = psum;
		int curl = 0;
		
		if (isWidth) {
			right = curr;
		} else {
			over = curr;
		}
		
		while (curr < len) {
			nowNum -= p[curl++];
			nowNum += p[++curr];
			
			if (nowNum > psum) {
				psum = nowNum;
				if (isWidth) {
					left = curl;
					right = curr;
				} else {
					under = curl;
					over = curr;
				}
			}
		}
	}
}
