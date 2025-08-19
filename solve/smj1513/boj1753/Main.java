package smj1513.boj1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
 * 최단 경로 문제
 *
 * */

class Edge implements Comparable<Edge> {
    int vertax;
    int weight;

    public Edge(int vertax, int weight) {
        this.vertax = vertax;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}


public class Main {
    static List<LinkedList<Edge>> graph = new ArrayList<>();
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new LinkedList<>());
        }
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); //최단 경로 배열을 초기화
        distance[start] = 0; //시작점의 최단 경로는 0
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).addFirst(new Edge(v, w));
        }
        dijkstra(start);
        for (int i = 1; i <= V; i++) {
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
        }
    }

    //다익스트라는 기본적으로 그리디 알고리즘
    //현재의 최단 경로가 이후의 최단경로를 보장한다 가정하여 구현
    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(); //우선 순위 큐를 사용하여 구현
        pq.offer(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll(); //정점을 꺼낸 순간 해당 정점으로의 최단 경로는 확정
            if (!visited[current.vertax]) { // 확정되었기 때문에 방문한 정점으로는 들어가지 않음.
                visited[current.vertax] = true; // 정점 방문시 확정
                for (Edge next : graph.get(current.vertax)) {//현재 정점에서 갈 수 있는 모든 정점을 순회
                    if (distance[next.vertax] > distance[current.vertax] + next.weight) { //다음 정점까지의 거리가 현재 정점으로부터 다음 가중치 보다 크다면
                        distance[next.vertax] = distance[current.vertax] + next.weight;// 최단 경로 갱신
                        pq.add(new Edge(next.vertax, distance[next.vertax])); //우선 순위 큐를 사용하여 현재 정점에서 가장 가까운 정점 선택
                    }
                }
            }
        }
    }
}