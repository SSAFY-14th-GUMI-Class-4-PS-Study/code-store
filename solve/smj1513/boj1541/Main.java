package smj1513.boj1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;


/*
* 그리디 하게 생각하면 풀리는 문제
* 괄호가 쳐지지 않은 수식에서 가장 작은 값을 만드는 경우는 빼는 값이 가장 크면 됨.
* 55-50+40 -> 55-(50+40) 처럼 - 뒤의 값을 무조건 먼저 연산 해서 +로 가장 크게 만들면 됨
* split에서 +는 안되니 \\+로 입력
* */
public class Main {
    static int[] sums;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] chunk = input.split("-");
        sums = new int[chunk.length];
        for (int i = 0; i < chunk.length; i++) {
            String[] numbers = chunk[i].split("\\+");
            int sum = 0;
            for (String num : numbers) {
                sum += Integer.parseInt(num);
            }
            sums[i] = sum;
        }
        int result = IntStream.of(sums).reduce((a, b) -> a - b).getAsInt();
        System.out.println(result);
    }
}
