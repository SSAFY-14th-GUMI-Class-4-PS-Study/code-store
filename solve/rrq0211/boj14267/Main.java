package rrq0211.boj14267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] employee;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		employee = new int[n + 1];
		dp = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			employee[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			dp[idx] += val;
		}
		
		for (int i = 2; i <= n; i++) {
			dp[i] += dp[employee[i]];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) {
			sb.append(dp[i] + " ");
		}
		
		System.out.println(sb.toString());
	}
}
