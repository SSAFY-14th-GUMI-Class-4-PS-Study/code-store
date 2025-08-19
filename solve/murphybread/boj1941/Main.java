import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N=5;
	static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	static char [][] map;
	static int answer = 0 ;
	
	static int[][] selectedPrincesses = new int[7][2];

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		
		map = new char[N ][N];
		
		
		for (int i = 0; i < map.length; i++) {
			char [] line = br.readLine().toCharArray();
			for (int j = 0; j < map.length; j++) {
				map[i][j]  = line[j]; 
				
			}
		}
		
		
		// 조건
		// 반드시 7명으로구성
		// 7명은 서로 가로나 세로로 인접
		// 반드시 4명이상아 S여야한다.
		
		
		//크기가 5*5라서 완전탐색 수행
		
		dfs(0,0);
		

			
		
		System.out.println(answer);
			
		}
	
    static boolean isConnected() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[7];

        // 시작점을 큐에 추가 {r, c}
        queue.add(selectedPrincesses[0]);
        visited[0] = true;
        int connectedCount = 1;

        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();
            int r = currentPos[0];
            int c = currentPos[1];

            // 4방향 탐색
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                // 지도 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                // 다음 위치 (nr, nc)가 우리 편 명단에 있는지 확인
                for (int i = 0; i < 7; i++) {
                    // 방문한 적 없고, row와 col이 모두 일치하는지 확인
                    if (!visited[i] && selectedPrincesses[i][0] == nr && selectedPrincesses[i][1] == nc) {
                        visited[i] = true;
                        // 큐에 {nr, nc} 좌표를 직접 추가
                        queue.add(new int[]{nr, nc});
                        connectedCount++;
                    }
                }
            }
        }
        return connectedCount == 7;
    }
		static void dfs( int depth , int start) {
			
			
			
			if (depth == 7) {
				

	            int sCount = 0;
	            for (int[] pos : selectedPrincesses) {
	                if (map[pos[0]][pos[1]] == 'S') {
	                    sCount++;
	                }
	            }
				
				if (sCount >=4) {
	                if (isConnected()) {
	                    answer++;
	                }
					
					


				}
				
				
				return;
				
			}
		
			

	       for (int i = start; i < 25; i++) {
	    	   selectedPrincesses[depth][0] = i / 5;
	    	   selectedPrincesses[depth][1] = i % 5;
	            dfs(depth + 1, i + 1); // 다음 공주를 뽑으러 갑니다.
	        }

		
		
		
	}


}
