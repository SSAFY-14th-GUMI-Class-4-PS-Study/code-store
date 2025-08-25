package rrq0211.boj6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static boolean[] visit;
	static int len;
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		len = 0;
		
		while((len = Integer.parseInt(st.nextToken())) != 0) {
			arr = new int[len];
			visit = new boolean[len];
			
			for (int i = 0; i < len; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			for (int i = 0; i < len - 5; i++) {
				visit[i] = true;
				comb(i, 1);
				visit[i] = false;
			}
			st = new StringTokenizer(br.readLine());
			result.append("\n");
		}
		
		System.out.println(result.toString());

	}
	
	static void comb(int idx, int cnt) {
		if (cnt == 6) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < len; i++) {
				
				if (visit[i]) {
					sb.append(arr[i] + " ");
				}
			}
			result.append(sb.toString() + "\n");
			return;
		}
		
		for (int i = idx + 1; i < len; i++) {
			visit[i] = true;
			comb(i, cnt + 1);
			visit[i] = false;
		}
	}

}
