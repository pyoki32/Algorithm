import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_거리두기_확인하기 {
	static class Point {
		int row;
		int col;
		int d;

		public Point(int row, int col, int d) {
			this.row = row;
			this.col = col;
			this.d = d;
		}
	}
	static int[] drow = { 0, 0, -1, 1 };
	static int[] dcol = { -1, 1, 0, 0 };
	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		int[] ans = solution(places);

		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	public static int[] solution(String[][] places) {
		
		ArrayList<Integer> res = new ArrayList<>();
		for (int k = 0; k < places.length; k++) {
			ArrayList<Point> person = new ArrayList<>();
			char[][] room = new char[5][5];
			for (int i = 0; i < 5; i++) {
				String str = places[k][i];
				for (int j = 0; j < 5; j++) {
					room[i][j] = str.charAt(j);
					if (room[i][j] == 'P')
						person.add(new Point(i, j, 0));
				}
			}
			boolean ansCheck = false;
			for (Point p : person) {
				if (bfs(room, p, person)) {
					ansCheck = true;
					break;
				}
			}
			if (ansCheck) {
				res.add(0);
			} else {
				res.add(1);
			}
		}
		int[] answer = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			answer[i] = res.get(i);
		}
		return answer;
	}

	static boolean bfs(char[][] room, Point start, ArrayList<Point> person) {
		
		int[][] dist = new int[5][5];
		boolean[][] visited = new boolean[5][5];
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		dist[start.row][start.col] = 0;
		visited[start.row][start.col] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			int row = p.row;
			int col = p.col;
			int d = p.d;
			for (int k = 0; k < 4; k++) {
				int nrow = row + drow[k];
				int ncol = col + dcol[k];
				if (isRange(nrow, ncol)) {
					if (!visited[nrow][ncol] && room[nrow][ncol] != 'X') {
						visited[nrow][ncol] = true;
						dist[nrow][ncol] = d + 1;
						q.add(new Point(nrow, ncol, d + 1));
					}
				}
			}
		}
		for (Point p : person) {
			int row = p.row;
			int col = p.col;
			if (1 <= dist[row][col] && dist[row][col] <= 2) {
				return true;
			}
		}

		return false;
	}

	static boolean isRange(int nrow, int ncol) {
		if (0 <= nrow && nrow < 5 && 0 <= ncol && ncol < 5)
			return true;
		return false;
	}
}
