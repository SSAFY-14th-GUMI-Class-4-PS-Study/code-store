package rrq0211.boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };
	static boolean[] alphabet = new boolean[26];
	static int r, c, max;
	static boolean[][] visit;
	static char[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new char[r][c];
		visit = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		visit[0][0] = true;
		int ch = graph[0][0] - 'A';
		alphabet[ch] = true;
		
		dfs(0,0,1);
		
		System.out.println(max);
	}
	
	static void dfs(int idx, int jdx, int value) {
		max = Math.max(value, max);
		
		for (int i = 0; i < 4; i++) {
			int ni = idx + di[i];
			int nj = jdx + dj[i];
			
			if (ni >= 0 && ni < r && nj >= 0 && nj < c && !visit[ni][nj]) {
				int ch = graph[ni][nj] - 'A';
				if (alphabet[ch])
					continue;
				visit[ni][nj] = true;
				alphabet[ch] = true;
				dfs(ni, nj, value + 1);
			}
		}
		
		visit[idx][jdx] = false;
		int ch = graph[idx][jdx] - 'A';
		alphabet[ch] = false;
	}

}
