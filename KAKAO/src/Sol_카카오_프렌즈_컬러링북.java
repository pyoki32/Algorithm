import java.util.LinkedList;
import java.util.Queue;

public class Sol_카카오_프렌즈_컬러링북{
	static class Point {
		int row;
		int col;
		int color;

		public Point(int row, int col, int color) {
			this.row = row;
			this.col = col;
			this.color = color;
		}
	}

	static int r, c;
	static boolean[][] visited;
	static int[] drow = { 0, 0, -1, 1 };
	static int[] dcol = { 1, -1, 0, 0 };

	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		r = m;
		c = n;
		visited = new boolean[r][c];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && picture[i][j] != 0) {
					numberOfArea++;
					visited[i][j] = true;
					int cnt = bfs(new Point(i, j, picture[i][j]), picture);
					maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
				}
			}
		}
		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	static int bfs(Point startP, int[][] picture) {

		Queue<Point> q = new LinkedList<>();
		q.add(startP);
		int cnt = 1;
		while (!q.isEmpty()) {
			Point p = q.poll();
			int row = p.row;
			int col = p.col;
			int color = p.color;
			for (int k = 0; k < 4; k++) {
				int nrow = drow[k] + row;
				int ncol = dcol[k] + col;
				if (isRange(nrow, ncol)) {
					if (picture[nrow][ncol] == color && !visited[nrow][ncol]) {
						cnt++;
						visited[nrow][ncol] = true;
						q.add(new Point(nrow, ncol, color));
					}
				}
			}
		}
		return cnt;
	}

	static boolean isRange(int nrow, int ncol) {
		if (0 <= nrow && nrow < r && 0 <= ncol && ncol < c)
			return true;
		else
			return false;
	}
}
