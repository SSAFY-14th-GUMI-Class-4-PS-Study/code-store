import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	


	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int count =0;
	
	public static void main(String[] args) throws Exception {
    
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		
		
		
		
		// 먼저 데이터 입력받기 및 초기화
		
		N = Integer.parseInt(br.readLine());
		
		
		// 상하좌우dir사용하기
		int [][] dir = {{0,-1},{0,1},{1,0},{-1,0}};
		
		int max = 0;
		int result =0;
		map = new int [N][N];
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
//			System.out.println(st);
			
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max =Math.max(max, map[i][j]);

			}
			
		}
		

		
//		System.out.println("max" + max)
		;
		// 높이 0부터 max까지
		
		for (int h = 0; h<=max;h++) {
			count = 0;
			ArrayDeque<int []> queue = new ArrayDeque<> ();

//			System.out.println("h = "+h);
			visited = new boolean[N][N];
			
			// 높이 이상인곳 체크하기
			// 	visited[i][j] true만들기
			
			for (int i=0;i<N;i++) {
				
				for (int j=0;j<N;j++) {
					if (map[i][j] > h) {
						
						visited[i][j] = true;
					}
				}
			}
			
			
			
			// queue사용 queue의 첫 원소는? i에서 j까지 돌아보기 queue <[x,y]>  좌표 3번째 인자는 필요 없을듯
			for(int i=0;i<N;i++) {
				
				for (int j=0;j<N;j++) {
					
					if (visited[i][j] == false) {
						continue;
					}
					
					queue.offer(new int [] {i,j});
					visited[i][j] = false;
					
					count +=1;
//					System.out.println("count " + count + " i,  j " +i + " "+ j);
					
//					for(int [] q: queue) {
//						System.out.println(Arrays.toString(q));
//					}
//					
					
					
					// for문에서 false인 부분 체크하기
					// 모든 i,j에대하여 bfs하며 수행할때마다 +1
						// 방문 할떄마다 false
					
					
					while(!queue.isEmpty()) {
						
						int [] cur = queue.poll();
						int x = cur[0];
						int y = cur[1];
						
						
						for (int [] d: dir) {
							
							int nx = x+ d[0];
							int ny = y+ d[1];
							
							if (nx < 0 || nx >=N || ny<0 || ny>=N  || visited[nx][ny]== false) {
								continue;
							}
							
							
							if (visited[nx][ny] == true) {
								visited[nx][ny] = false;
								queue.offer(new int[] {nx,ny});
								
							}
							
							
							
							
							
						}
						
						
					}

					
				}
				
			}
			result = Math.max(result, count);

			
			
			
			
		}
		



		
		System.out.println(result
				);
        

    }
	


    

}