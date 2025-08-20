package rrq0211.boj2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());

		int[] a = new int[n];
		int[] preSumA = new int[n];
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			sum += a[i];
			preSumA[i] = sum;
		}

		int m = Integer.parseInt(br.readLine());

		int[] b = new int[m];
		int[] preSumB = new int[m];

		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
			sum += b[i];
			preSumB[i] = sum;
		}

		List<Integer> sumA = getPreSums(preSumA);
		List<Integer> sumB = getPreSums(preSumB);
		
		System.out.println(sumA);
		System.out.println(sumB);
		
		System.out.println(sumA.size());
		for (int i = 0; i < sumA.size(); i++) {
			int num = sumA.get(i);
			int left = getLowBound(sumA, i, num);
			int right = getHighBound(sumA, i, num);
			long size = right - left + 1;

			int want = t - num;
			
			System.out.println("idx: " + i + " " + num);
			binarySearch(sumB, 0, sumB.size() - 1, want, size);
			i = right;
			System.out.println(i);
		}
		System.out.println(result);
	}

	static void binarySearch(List<Integer> list, int left, int right, int want, long size) {
		if (left > right) {
			return;
		}

		int mid = (left + right) / 2;
		int num = list.get(mid);

		if (left == right && num != want) {
			return;
		}

		if (num == want) {
			int low = getLowBound(list, mid, want);
			int high = getHighBound(list, mid, want);
			
			System.out.println("find: " + size + " " + want);
			result += (high - low + 1) * size;
			return;
		}

		if (num > want) {
			mid = getLowBound(list, mid, num) - 1;
			if (mid < left) {
				return;
			}
			System.out.println("move: " + left + " " + mid);
			binarySearch(list, left, mid, want, size);
		} else {
			mid = getHighBound(list, mid, num) + 1;
			if (mid > right) {
				return;
			}
			System.out.println("move: " + mid + " " + right);
			binarySearch(list, mid, right, want, size);
		}
	}

	static List<Integer> getPreSums(int[] num) {
		List<Integer> list = new ArrayList<>();

		int len = num.length;

		for (int i = 0; i < len; i++) {
			list.add(num[i]);
			for (int j = i + 1; j < len; j++) {
				list.add(num[j] - num[i]);
			}
		}

		Collections.sort(list);
		return list;
	}

	static int getLowBound(List<Integer> list, int idx, int num) {
		while (idx >= 0 && list.get(idx) == num)
			idx--;

		return idx + 1;
	}

	static int getHighBound(List<Integer> list, int idx, int num) {
		while (idx < list.size() && list.get(idx) == num)
			idx++;

		return idx - 1;
	}
}
