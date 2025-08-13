package rrq0211.boj10974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] nums;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		nums = new int[n];
		
		for (int i = 0; i < n; i++) {
			nums[i] = i + 1;
		}
		
		Arrays.sort(nums);
		
		StringBuilder sb = new StringBuilder();
		do {
			for (int num : nums) {
				sb.append(num + " ");
			}
			sb.append("\n");
		} while (np());
		System.out.println(sb.toString());
	}
	
	
	static boolean np() {
		int i = n - 1;
		while (i > 0 && nums[i - 1] >= nums[i]) i--;
		
		if (i == 0) return false;
		
		int k = n - 1;
		while (nums[i - 1] >= nums[k]) k--;
		
		swap(i - 1, k);
		
		int j = n - 1;
		
		while(i < j) {
			swap(i++, j--);
		}
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	

}
