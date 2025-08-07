import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int N,M ;
	static ArrayList<Integer> [] graph;
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		int cnt = 0;


		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i = 1;i<=N;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
				
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(  st.nextToken());
			int b = Integer.parseInt(  st.nextToken());
			
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		if(graph[1].size() == 0) {
			System.out.println(0);
			return;
		}
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		HashSet<Integer> s = new HashSet<>();
		
		for (int i=0;i<graph[1].size();i++) {
			int num = graph[1].get(i);
			queue.offer(num);
			s.add(num);
		}
		
		
		for (int a : queue) {
			
			for(int i=0;i<graph[a].size();i++) {
				int num = graph[a].get(i);
				if (num == 1) {
					continue;
				}
				s.add(num);
			}
		}

		
		System.out.println(s.size());





	}





}