package rrq0211.boj2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		tracking(0);
		
		System.out.println(sb.toString());
	}
	
	static boolean tracking(int len) {
		if (len >= n) {
			return validate(1);
		}
		
		for (int i = 1; i <= 3; i++) {
			sb.append(i);
			// 현재의 값이 좋은 수열이면서 다음 수열들이 좋은 수열일때 true 반환 => 가지치기
			if (validate(1) && tracking(len + 1)) {
				return true;
			} else {
				sb.delete(sb.length() - 1, sb.length());
			}
		}
		
		return false;
	}
	
	static boolean validate(int size) {
		int len = sb.length();
		
		if (size > len / 2) {
			return true;
		}
		
		if (len < 2) {
			return true;
		}
		
		if (sb.substring(len - size).equals(sb.substring(len - size - size, len - size)))
			return false;
		
		return validate(size + 1);
	}
}