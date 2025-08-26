package rrq0211.boj1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int[] p;
	static int result;
	
	public static void main(String[] args) throws IOException {
		
		PriorityQueue<Edge> que = new PriorityQueue<>((x, y) -> y.cost - x.cost);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		
		p = new int[n + 1];
		Arrays.fill(p, -1);
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), 
					b = Integer.parseInt(st.nextToken()), 
					c = Integer.parseInt(st.nextToken());
			
			que.add(new Edge(a, b, c));
		}
		
		st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
		
		while (!que.isEmpty()) {
			Edge cur = que.poll();
			union(cur.from, cur.to);
			if (find(start) == find(end)) {
				System.out.println(cur.cost);
				return;
			}
		}
	}
	
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return;
		}
		
		if (a < b) {
			p[b] = a;
		} else {
			p[a] = b;
		}
		
	}
	
	static int find(int x) {
		if (p[x] == -1) {
			return x;
		}
		return p[x] = find(p[x]);
	}
}
