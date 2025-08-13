package smj1513.boj10974;

import java.util.Scanner;

public class Main {
    static int[] origin;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        origin = new int[n];
        for (int i = 0; i < origin.length; i++) {
            origin[i] = i + 1;
        }
        do {
            for(int i = 0 ; i < origin.length; i++){
                sb.append(origin[i]).append(" ");
            }
            sb.append("\n");
        }while (nextPermutation());
        System.out.println(sb);
    }

    static boolean nextPermutation() {
        int i = origin.length - 1;
        while (i > 0 && origin[i - 1] >= origin[i]) {
            --i;
        }
        if (i == 0) {
            return false;
        }

        int j = origin.length - 1;
        while (origin[i - 1] >= origin[j]) {
            --j;
        }
        swap(i - 1, j);

        int k = origin.length - 1;
        while (i < k) {
            swap(i++, k--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int temp = origin[i];
        origin[i] = origin[j];
        origin[j] = temp;
    }
}

