package smj1513.boj16562
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int[] friendCosts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		friendCosts = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			friendCosts[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
		}
		int resultCost = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				resultCost += friendCosts[i];
			}
		}
		if (resultCost > K) {
			System.out.println("Oh no");
		} else {
			System.out.println(resultCost);
		}
	}
	// 두 학생(그룹)을 하나로 합치는 union 연산
	public static boolean union(int a, int b) {
		// 각 학생이 속한 그룹의 대표(루트)를 찾음
		int fa = find(a);
		int fb = find(b);

		// 두 학생이 이미 같은 그룹이 아니라면 합침
		if (fa != fb) {
			// 두 그룹을 합칠 때, 친구비가 더 저렴한 학생을 새로운 그룹의 대표로 만듦
			if (friendCosts[fa] < friendCosts[fb]) {
				parents[fb] = fa; // b그룹이 a그룹으로 흡수됨 (대표는 fa)
			} else {
				parents[fa] = fb; // a그룹이 b그룹으로 흡수됨 (대표는 fb)
			}
			return true;
		} else {
			return false;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}
}
