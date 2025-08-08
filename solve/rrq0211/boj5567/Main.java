package rrq0211.boj5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] friends;
	static int n, m;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		friends = new int[n + 1][n + 1];
		visit = new boolean[n + 1];
		
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			int jdx = Integer.parseInt(st.nextToken());
			
			friends[idx][jdx] = 1;
			friends[jdx][idx] = 1;
		}
		
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {1, 0});
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			visit[cur[0]] = true;
			
			for (int i = 2; i <= n; i++) {
				if (!visit[i] && friends[cur[0]][i] == 1 && cur[1] < 2) {
					que.add(new int[] {i, cur[1] + 1});
					visit[i] = true;
				}
			}
		}
		
		int result = 0;
		for (int i = 2; i <= n; i++) {
			if (visit[i]) {
				result++;
			}
		}

		System.out.println(result);
	}
}
