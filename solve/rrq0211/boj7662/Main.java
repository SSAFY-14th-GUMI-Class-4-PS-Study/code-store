package rrq0211.boj7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		TreeMap<Integer, Integer> set = new TreeMap<>();
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				switch (oper) {
				case "I" :
					set.put(num, set.getOrDefault(num, 0) + 1);
					break;
				case "D" : {
					if (set.isEmpty()) {
						break;
					}
					Entry<Integer, Integer> entry = null;
					if (num == 1) {
						entry = set.pollLastEntry();
					} else {
						entry = set.pollFirstEntry();
					}
					
					if (entry.getValue() > 1) {
						set.put(entry.getKey(), entry.getValue() - 1);
					}
					
				}
				break;
				}
			}
			
			if (set.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				Entry<Integer, Integer> max = set.pollLastEntry();
				Entry<Integer, Integer> min = max;
				if (!set.isEmpty()) {
					min = set.pollFirstEntry();
				}
				System.out.println(max.getKey() + " " + min.getKey());
			}
			
			set.clear();
		}
	}
}
