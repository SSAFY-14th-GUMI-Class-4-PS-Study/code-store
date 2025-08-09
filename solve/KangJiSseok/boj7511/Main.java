import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] parent;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());

		for(int t= 1; t<=T; t++) {
			sb.append("Scenario ").append(t).append(":").append("\n");

			int n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());
			parent = new int[n];
			for(int i = 0; i< n ; i++) {
				parent[i] = i;
			}

			for(int i=0; i< k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}

			int m = Integer.parseInt(br.readLine());
			for(int i=0; i< m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if(find(a) == find(b)) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}


	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}


	private static void union(int a, int b) {
		int findA = find(a);
		int findB = find(b);

		if(findA == findB) return;

		if(findA < findB) {
			parent[findB] = findA;
		}else {
			parent[findA] = findB;
		}
	}
}
