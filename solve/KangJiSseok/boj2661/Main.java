import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] visited;
	static boolean finished = false;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		visited = new int[N];
		dfs("");

	}

	private static void dfs(String str) {

		if(str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}

		for(int i=1; i<= 3; i++) {
			String a = str;
			a += i;
			if(isValid(a)) {
				dfs(a);
			}
		}
	}

	private static boolean isValid(String str) {

		for(int i=1; i<= str.length() / 2; i++) {
			String left = str.substring(str.length() - i * 2, str.length() - i);
			String right = str.substring(str.length() - i , str.length());
			if(left.equals(right)) return false;
		}

		return true;

	}
}