import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static List<Integer>[] graph;
	static int[] result;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		result = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}

		for (int i = 1; i <= N; i++) {
			bfs(i);
		}

		int max = Arrays.stream(result).max().getAsInt();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (result[i] == max) {
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph[current]) {
				if (!visited[next]) {
					result[next]++;
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}
}