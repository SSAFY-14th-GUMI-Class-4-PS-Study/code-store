import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{


	static int W,H;
	static int [][] dir = {{0,-1},{0,1},{-1,0},{1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		StringTokenizer st;


		st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		while(a != 0 && b != 0 ) {

			W = a;
			H = b;

			int [][] map = new int[H][W];
			boolean [][] visited = new boolean[H][W];


			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < W; j++) {
					map[i][j] =  Integer.parseInt(st.nextToken());


				}

			}

			// bfs

			ArrayDeque<int []> queue = new ArrayDeque<>();

			int count = 0;


			for (int i = 0; i < H; i++) {

				for (int j = 0; j < W; j++) {
					if(visited[i][j] == true || map[i][j] == 0) {
						continue;
					}


					count +=1; // 섬개수 업데이트

					queue.add(new int [] {i,j});


					// 섬탐색
					while(!queue.isEmpty()) {

						int current [] = queue.poll();
						int x = current[0];
						int y = current[1];
						visited[x][y] = true;

						for(int [] d: dir) {
							
							int nx = x + d[0];
							int ny = y + d[1];

							if( nx <0 || nx >=H || ny <0 || ny>=W) {
								continue;
							}

							if (map[nx][ny] == 0 || visited[nx][ny] == true) {
								continue;
							}	
							
							visited[nx][ny] = true;
							queue.add(new int [] {nx,ny});

						}


					}



				}

			}
			
			sb.append(count).append("\n");
			



			// 다음 입력 받기
			st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());


		}
		System.out.println(sb);


	}



}







