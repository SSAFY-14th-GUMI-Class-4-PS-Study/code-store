package rrq0211.boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이미 지나갔던 알파벳은 지나갈 수 없기 때문에
 * 알파벳만 체크하면 된다.
 */
public class Mainv2 {
	
	static int[] di = { 0, 0, 1, -1 };
	static int[] dj = { 1, -1, 0, 0 };
	static boolean[] alphabet = new boolean[26];
	static int r, c, max;
	static char[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new char[r][c];
		
		for (int i = 0; i < r; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
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
			
			if (ni >= 0 && ni < r && nj >= 0 && nj < c) {
				int ch = graph[ni][nj] - 'A';
				if (alphabet[ch])
					continue;
				alphabet[ch] = true;
				dfs(ni, nj, value + 1);
			}
		}
		
		int ch = graph[idx][jdx] - 'A';
		alphabet[ch] = false;
	}
}
