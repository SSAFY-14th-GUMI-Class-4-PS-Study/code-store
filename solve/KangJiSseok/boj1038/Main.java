import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static int count = 0;
	static HashMap<String, Integer> map = new HashMap<>();
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());

		int count = 0;

		Queue<Long> q = new LinkedList<>();
		q.add(0L);
		q.add(1L);
		q.add(2L);
		q.add(3L);
		q.add(4L);
		q.add(5L);
		q.add(6L);
		q.add(7L);
		q.add(8L);
		q.add(9L);

		for(int t=0; t< 10; t++) {
			int size = q.size();
			for(int i= 0; i< size; i++) {
				long p = q.poll();
				if(count == N) {
					System.out.println(p);
					return;
				}
				//				System.out.println(p);
				long last = p % 10;
				for(int j=0; j < last; j++) {
					q.add(p * 10 + j);
				}
				count++;
			}
		}

		System.out.println(-1);

	}


}