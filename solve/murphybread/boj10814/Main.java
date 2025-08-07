package murphybread.boj10814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Main {

	static int N;



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt( br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		ArrayDeque<String>[] buckets = new ArrayDeque[201];
		
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayDeque<>();
        }
		
		
		
		for (int i=0;i<N;i++) {
			String [] line = br.readLine().split(" ");
			int age = Integer.parseInt(line[0]);
			String name = line[1];
			
			buckets[age].add(name);
					
			
			
			//userAges.put(Integer.parseInt(line[0]), line[1]);
			
			
			
		}
		
		
		for (int i =0;i<201;i++) {
			if (buckets[i].size() > 0) {
				
				for (String name : buckets[i]) {
					sb.append(i).append(" ").append(name).append("\n");
					
				}
				
				
			}
		}
		
		


		System.out.println(sb);
		


		//계속움직이기에 어떻게든 시간안에 끝남




	}



}
