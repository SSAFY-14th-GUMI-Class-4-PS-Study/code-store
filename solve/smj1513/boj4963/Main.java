package smj1513.boj4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * 섬(묶음)의 개수를 확인하는 대표적인 dfs + 시뮬레이션 문제
 * 8방 탐색을 수행하며 dfs를 수행하여 한번의 dfs를 통해 하나의 묶음을 방문하기 때문에
 * 각 요소에 대해 순회하며 dfs가 호출되는 횟수로 섬의 개수를 구할 수 있음.
 * */

public class Main {
    static int[][] graph;
    static boolean[][] visited;
    static int cnt = 0;
    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dy = {1, 0, -1, 0, 1, -1, 1,-1};
    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            w = input[0];
            h = input[1];
            if (w == 0 && h == 0) {
                break;
            }
            StringTokenizer st;
            graph = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && graph[i][j] == 1) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
            cnt = 0;
        }


    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (valid(drx, dry) && !visited[drx][dry] && graph[drx][dry] == 1) {
                dfs(drx, dry);
            }
        }
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x <h && 0 <= y && y < w;
    }
}
