package rrq0211.boj18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] cities;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		cities = new ArrayList[n + 1];
		List<Integer> results = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			cities[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			cities[u].add(v); 
		}
		
		Queue<Integer> que = new ArrayDeque<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[x] = 0;
		
		for (int city : cities[x]) {
			dist[city] = dist[x] + 1;
			que.add(city);
		}
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			for (int next : cities[cur]) {			
				if (dist[next] > dist[cur] + 1) {
					dist[next] = dist[cur] + 1;
					que.add(next);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (dist[i] == k) {
				sb.append(i + "\n");
			}
		}
		
		System.out.println(sb.toString().length() != 0 ? sb.toString() : -1);
	}

}
