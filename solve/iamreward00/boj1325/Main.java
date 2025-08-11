package iamreward00.boj1325;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    /// 백준 1325 효율적인해킹
    /// A B 입력이 오는데 A 가 B를 신뢰해서 B 를 해킹하면 A 도 해킹된다 근데 그 반대는 안됨
    /// 첫재쭐에는 N , M N개의 사람 M 개의 신뢰관계
    /// B 쪽으로 쭉 찾아가면 될듯? bfs dfs 둘다 일단 ㄱㄱ? ㅇㅋ ㄱㄱ
    /// 일단 관계를 만들어야겠네요
    /// 리스트 배열 또 써봅시다 ㄱㄱㄱ
    /// 아 해당 번호가 얼마나 많이 해킹되는지 담는 N+1개 배열 하나도 ㄱㄱ
    /// ㅇㅋ ㄱㄱㄱㄱ
    static int N,M;
    static boolean[] vis;
    static ArrayList<Integer>[] graph;
    static StringTokenizer st;
    static int[] ans;
    static int max = 0;
    static int cnt = 0;

    static int dfs(int idx){ // 너 왜터지냐? 왜 시초남? 이게 맞긴한데 bfs 여야만 시초 안뜨네 ㅋㅋ

        
        for(int i : graph[idx]){
            if(vis[i]) continue;
            vis[i] = true;
            cnt++;
            dfs(i);
            vis[i] = false;
        }
        return 0;
    }
    static int bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(idx);
        vis[idx] = true;
        while(!queue.isEmpty()){
            int num = queue.poll();
            for(int i : graph[num]){
                if(vis[i]) continue;
                vis[i] = true;
                cnt++;
                queue.add(i);
            }
        }
        
        return 0;

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vis = new boolean[N+1]; // 이것도 아래와 같은이유
        graph = new ArrayList[N+1]; // 왜냐면 1번사람 1, 2번사람 2이렇게쓸꺼라고 하나더필요함
        ans = new int[N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a); // B가 해킹될때 A 도 해킹되니까 B 에 a를 넣었음
        }
        //입력 끝
        // 1 부터 N 번호사람까지 해킹해봐야함 ㄱㄱㄱㄱ
        for(int i = 1; i <= N; i++){
            
            cnt = 0;
            // vis[i] = true;
            // dfs(i);
            // vis[i] = false;

            vis =new boolean[N+1];  // 방문배열 초기화해줘야함 원복안해줘서 ㅠㅠ
            vis[i] = true;
            bfs(i);

            ans[i] = cnt;
            if(cnt > max){
                max = cnt;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            if(ans[i] == max){
                sb.append(i+ " ");
            }
        }
        System.out.println(sb);
    }
}

/// dfs 에서 방문 체크가 필요한가? ㅇㅇ 신뢰관계가 서로 면 무한으로 즐겨요 해먹음 ㅇㅇ 그래서 이써야함