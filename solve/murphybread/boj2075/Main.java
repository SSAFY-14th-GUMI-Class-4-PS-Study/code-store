import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{



	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		StringTokenizer st;


		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				pq.add(-Integer.parseInt(st.nextToken()));
				
			}
			
			
		}
		
		
		for (int i = 0; i < N-1; i++) {
			pq.poll();
			
		}
		System.out.println(-pq.peek());
		





		
		System.out.println(sb);


	}



}







