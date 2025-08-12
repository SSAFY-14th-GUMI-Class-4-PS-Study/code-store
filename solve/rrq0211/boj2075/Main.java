package rrq0211.boj2075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n * n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = i * n; j < i * n + n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr);
		
		int number = arr[n * n - n];
		System.out.println(number);
		System.out.println(Arrays.toString(arr));
	}

}
