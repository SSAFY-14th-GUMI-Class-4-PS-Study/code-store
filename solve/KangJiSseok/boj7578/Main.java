import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N + 1];
		int[] a = new int[N + 1];
		HashMap<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = 0;
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			map.put(value, i);
		}

		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);

		//		System.out.println(Arrays.toString(tree.tree));

		long ans = 0;

		for (int i = 1; i <= N; i++) {
			int idx = map.get(a[i]);
			long sum = tree.sum(1, 1, N, idx + 1, N);
			ans += sum;
			tree.update(1, 1, N, idx, 1);
			//			System.out.println(ans);
			//			System.out.println(Arrays.toString(tree.tree));
		}

		System.out.println(ans);

	}

	static class SegmentTree {
		long[] tree;
		int[] treeSize;

		public SegmentTree(int n) {
			tree = new long[4 * n];
		}

		public long init(long[] arr, int node, int start, int end) {
			//			System.out.println(node + " " + start + " " + end);
			if (start == end) {
				return tree[node] = arr[start];
			}

			int mid = (start + end) / 2;

			return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
		}

		// 호출하기 전 arr배열도 수정해주기
		public void update(int node, int start, int end, int idx, int diff) {
			// idx = 1 1~3 4~7
			//			System.out.println(node + " " + start + " " + end + " " + idx);
			if (idx < start || idx > end)
				return;

			tree[node] += diff;
			if(start == end) return;

			int mid = (start + end) / 2;

			update(node * 2, start, mid, idx, diff);
			update(node * 2 + 1, mid + 1, end, idx, diff);
		}

		public long sum(int node, int start, int end, int left, int right) {
			// left = 0, right = 4 -> 1 ~3 4 ~7
			if (right < start || end < left)
				return 0;

			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;

			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);

		}
	}
}
