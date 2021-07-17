import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_17086 {
	static class Point {
		int row;
		int col;
		int dist;

		public Point(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}

	static int N, M;
	static int[][] map;
	static int[] drow = { -1, 1, 0, 0, 1, 1, -1, -1 };
	static int[] dcol = { 0, 0, -1, 1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		int ans =Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0)
				 ans = Math.max(ans, bfs(i,j));
			}
		}
		
			
		System.out.println(ans);
	}

	static int bfs(int srow,int scol) {
		int res = 0;
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		visited[srow][scol] = true;
		q.add(new Point(srow,scol,0));
		while (!q.isEmpty()) {
			Point p = q.poll();
			int row = p.row;
			int col = p.col;
			int dist = p.dist;
			for (int k = 0; k < 8; k++) {
				int nrow = row + drow[k];
				int ncol = col + dcol[k];
				if (isRange(nrow, ncol)) {
					if (!visited[nrow][ncol]) {
						visited[nrow][ncol] = true;
						q.add(new Point(nrow, ncol, dist + 1));
						if (map[nrow][ncol] == 1) {
							return dist+1;
						}
					}
				}
			}
		}
		return res;
	}

	static boolean isRange(int nrow, int ncol) {

		if (0 <= nrow && nrow < N && 0 <= ncol && ncol < M)
			return true;
		return false;
	}

}
