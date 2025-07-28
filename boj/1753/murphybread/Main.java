import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {


	

	static int V,E,K;
	public static void main (String [] args) throws Exception{
		
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		

		String [] line = br.readLine().split(" ");
		V = Integer.parseInt(line[0]);
		E = Integer.parseInt(line[1]);
		K = Integer.parseInt(  br.readLine());
		
		
		int [] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		
		
		// (출발지에서 A정점까지의 , 누적거리)
		
		// 간선 정보 저장하는 ArrayList 기존의 간선정보가 추가되거나 삭제는 잘 안되니 검색에 빠른 ArrayList
		// [ [[번쨰인덱스에서 v1으로갈떄의W1,]  , [] , [] , []]
		ArrayList<int []>[] adj = new ArrayList[V+1];
		
		for (int i = 0; i <= V; i++) {
		    adj[i] = new ArrayList<>();
		}
		
		for (int i=0;i<E;i++) {
			String [] input = br.readLine().split(" ");
			
	        int u = Integer.parseInt(input[0]);
	        int v = Integer.parseInt(input[1]);
	        int w = Integer.parseInt(input[2]);
	        
	     // [ [0],[v1에서 가능한 경우의 배열목록], [v2에서 진행가능한 배열목록]
	        adj[u].add(new int[]{v, w});


		}
		
		PriorityQueue<int []> pQueue = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pQueue.add(new int[]{K, 0});
		
		
		while(!pQueue.isEmpty()) {
			int [] info = pQueue.remove();
			int vertex = info[0];
			int cost = info[1];
			// info {vertex, cost}
			
			if (cost > dist[vertex]) {
				continue;
			}
			
			for (int [] neighbor: adj[vertex]) {
				
				int neighborNode = neighbor[0];
				int weight = neighbor[1];
				
				
				if(dist[neighborNode] > cost+weight) {
					pQueue.add(new int[] {neighborNode, cost+weight});
					dist[neighborNode] = cost+weight;
					
				}

				
			}
			
			
			
		}
		
		for (int i=1;i<=V;i++) {
			
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(dist[i]).append("\n");				
			}

		}

		System.out.println(sb);

		

		
	}
	
	
	

}
