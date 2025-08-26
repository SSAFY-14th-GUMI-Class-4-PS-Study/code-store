package rrq0211.boj1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static Map<Integer, Integer>[] vertexs;
	static boolean[] visit;
	static int[] weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		
		vertexs = new HashMap[n + 1];
		visit = new boolean[n + 1];
		weight = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			vertexs[i] = new HashMap<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			
			vertexs[a].put(b, c);
			vertexs[b].put(a, c);
		}
		
		
		PriorityQueue<int[]> que = new PriorityQueue<>((x, y) -> y[1] - x[1]);
//		Queue<int[]> que = new ArrayDeque<>();	
		
		st = new StringTokenizer(br.readLine());
		
		int i1 = Integer.parseInt(st.nextToken()), i2 = Integer.parseInt(st.nextToken());
		
		visit[i1] = true;
		for (int key : vertexs[i1].keySet()) {
			que.add(new int[] { key, vertexs[i1].get(key) });
		}
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			int now = cur[0];
			int w = cur[1];
			
			visit[now] = true;
//			weight[now] = w;
			if (weight[now] < w) {
				weight[now] = w;
			}
			
			for (int next : vertexs[now].keySet()) {
				if (visit[next]) {
					continue;
				}
				
				int nw = vertexs[now].get(next);
				if (weight[now] < nw) {
					nw = weight[now];
				}
				
				if (weight[next] < nw) {
					que.add(new int[] { next, nw });
				}
			}
		}
		
		System.out.println(weight[i2]);
	}
}
