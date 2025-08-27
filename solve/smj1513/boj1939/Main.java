package smj1513.boj1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
* 다익스트라의 변형으로 해결. 목적지 경로 까지의 모든 가중치를 더하는 것이 아닌,
* 간선 중 가장 큰 값을 우선적으로 접근하여 현재 진행할 수 있는 간선 중에서는 가장 큰값으로 그리디 하게 접근,
* 이후 경로상에서는 최소값으로 distance 배열을 업데이트
* */
class Edge implements Comparable<Edge> {
    int to;
    long w;

    public Edge(int to, long w) {
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(o.w, this.w);
    }
}

public class Main {
    static List<List<Edge>> graph = new ArrayList<>();
    static int N, M;
    static int start, end;
    static long[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new long[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            graph.get(from).add(new Edge(to, w));
            graph.get(to).add(new Edge(from, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        bfs();
        System.out.println(distance[end]);
    }

    public static void bfs() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, Long.MAX_VALUE));
        distance[start] = Long.MAX_VALUE;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            visited[current.to] = true;
            if(current.to == end){
                return;
            }
            for (Edge next : graph.get(current.to)) {
                if (!visited[next.to]) {
                    //새로 계산된 경로의 중량이 기존보다 높으면 항상 갱신함.
                    if (Math.min(distance[current.to], next.w) > distance[next.to]) {
                        pq.add(next);//다음 탐색을 위해 pq에 입력
                        //시작점에서 다음 노드까지의 경로가 견딜 수 있는 최대 중량을 갱신
                        // 이 값은 [시작점 ~ 현재 노드 까지의 최대 중량]과 [현재 간선의 중량]중 더 작은 값(병목)으로 결정된다.
                        distance[next.to] = Math.min(distance[current.to], next.w);
                    }
                }
            }
        }
    }
}

