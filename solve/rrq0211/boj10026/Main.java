package rrq0211.boj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	
	static char[][] graph;
	static boolean[][] visit;
	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		graph = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		visit = new boolean[n][n];
		
		int result1 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j]) {
					continue;
				}
				bfs(i, j , true);
				result1++;
			}
		}
		
		int result2 = 0;
		
		for (boolean[] b : visit) {
			Arrays.fill(b, false);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j]) {
					continue;
				}
				bfs(i, j, false);
				result2++;
			}
		}
		
		System.out.println(result1 + " " + result2);
	}
	
	static void bfs(int idx, int jdx, boolean isNormal) {
		
		Queue<int[]> que = new ArrayDeque<>();
		
		que.add(new int[] { idx, jdx });
		visit[idx][jdx] = true;
		
		char ch = graph[idx][jdx];
		System.out.println(ch);
		
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int ni = cur[0] + di[i];
				int nj = cur[1] + dj[i];
				
				if (ni < 0 || ni >= n || nj < 0 || nj >= n) {
					continue;
				}
				
				if (visit[ni][nj]) {
					continue;
				}
				
				if (isNormal) {
					normal(ni, nj, ch, que);
					continue;
				}
				
				noneNormal(ni, nj, ch, que);
			}
		}
	}
	
	static void normal(int i, int j, char curr, Queue<int[]> que) {
		char next = graph[i][j];
		
		if (curr != next) {
			return;
		}
		
		visit[i][j] = true;
		que.add(new int[] {i, j});
	}
	
	static void noneNormal(int i, int j, char curr, Queue<int[]> que) {
		char next = graph[i][j];
		
		if ((curr == 'R' || curr == 'G') && (next == 'R' || next == 'G')) {
			visit[i][j] = true;
			que.add(new int[] {i, j});
			return;
		}
		
		if (curr == 'B' && next == 'B') {
			visit[i][j] = true;
			que.add(new int[] {i , j});	
		}
	}
}
