import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] arr;
	static int N, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		dfs(1, 1, 0);

		System.out.println(min);
	}

	private static void dfs(int idx, int mul, int sum) {
		if(idx == N + 1 && mul == 1 && sum == 0) return;
		if(idx == N + 1){
			min = Math.min(min, Math.abs(mul - sum));
			return;
		}

		dfs(idx + 1, mul * arr[idx][0], sum + arr[idx][1]);
		dfs(idx + 1, mul , sum);
	}


}