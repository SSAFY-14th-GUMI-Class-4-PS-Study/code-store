package rrq0211.boj16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] cost;
	static List<Integer>[] friends;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		
		cost = new int[n + 1];
		visit = new boolean[n + 1];
		friends = new ArrayList[n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			friends[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			friends[a].add(b);
			friends[b].add(a);
		}
		int result = 0;
		for (int i = 1; i <= n; i++) {
			if (!visit[i]) {
				result += dfs(i);
			}
		}
		
		System.out.println(result <= k ? result : "Oh no");
	}
	
	static int dfs(int idx) {
		int result = cost[idx];
		visit[idx] = true;
		
		for (int num : friends[idx]) {
			if (visit[num]) {
				continue;
			}
			result = Math.min(result, dfs(num));
		}
		
		return result;
	}
}
