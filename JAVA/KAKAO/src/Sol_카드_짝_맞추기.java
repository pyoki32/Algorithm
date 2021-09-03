import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_카드_짝_맞추기 {
	static class Point {
		int row;
		int col;
		int cnt;

		public Point(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}

	// < ^ v >
	static int[] drow = { 0, -1, 1, 0 };
	static int[] dcol = { -1, 0, 0, 1 };
	static ArrayList<ArrayList<Point>> cardPoint;
	static ArrayList<ArrayList<Integer>> orderNum;
	static int[] pickNum;
	static int minCnt;
	static int cardMaxNum;

	public static void main(String[] args) {
		int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
		int r = 1;
		int c = 0;
		int answer = solution(board, r, c);
		System.out.println(answer);
	}

	public static int solution(int[][] board, int r, int c) {
		int answer = 0;
		cardPoint = new ArrayList<>();
		orderNum = new ArrayList<>();
		for (int i = 0; i <= 6; i++) {
			cardPoint.add(new ArrayList<Point>());
		}
		cardMaxNum = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					cardPoint.get(board[i][j]).add(new Point(i, j, 0));
					cardMaxNum = Math.max(cardMaxNum, board[i][j]);
				}
			}
		}

		pickNum = new int[cardMaxNum + 1];
		boolean[] visited = new boolean[cardMaxNum + 1];
		pickOrder(visited, 1);
		minCnt = Integer.MAX_VALUE;
		for (ArrayList<Integer> pickAl : orderNum) {
			calMin(pickAl, board, 0, 0, 0, r, c, false);
			calMin(pickAl, board, 0, 0, 1, r, c, false);
		}

		answer = minCnt;
		return answer;
	}

	static void calMin(ArrayList<Integer> pickAl, int[][] board, int totalCnt, int idx, int lowerNum, int r, int c,
			boolean sameCard) {

		int[][] nextBoard = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				nextBoard[i][j] = board[i][j];
			}
		}

		if (idx > cardMaxNum - 1) {

			minCnt = Math.min(minCnt, totalCnt);
			return;
		}
		int targetNum = pickAl.get(idx);
		if (sameCard) {
			Point p = cardPoint.get(targetNum).get(lowerNum);
			int cnt = bfs(nextBoard, r, c, p.row, p.col);
			nextBoard[p.row][p.col] = 0;
			calMin(pickAl, nextBoard, totalCnt + cnt + 1, idx + 1, 0, p.row, p.col, false);
			calMin(pickAl, nextBoard, totalCnt + cnt + 1, idx + 1, 1, p.row, p.col, false);

		} else {
			if (lowerNum == 0) {
				Point p = cardPoint.get(targetNum).get(0);
				int cnt = bfs(nextBoard, r, c, p.row, p.col);
				nextBoard[p.row][p.col] = 0;
				calMin(pickAl, nextBoard, totalCnt + cnt + 1, idx, 1, p.row, p.col, true);

			} else {
				Point p = cardPoint.get(targetNum).get(1);
				int cnt = bfs(nextBoard, r, c, p.row, p.col);
				nextBoard[p.row][p.col] = 0;
				calMin(pickAl, nextBoard, totalCnt + cnt + 1, idx, 0, p.row, p.col, true);
			}
		}
	}

	static void pickOrder(boolean[] visited, int depth) {
		if (depth > cardMaxNum) {
			orderNum.add(new ArrayList<>());
			int lastIdx = orderNum.size() - 1;
			for (int i = 1; i <= cardMaxNum; i++) {
				orderNum.get(lastIdx).add(pickNum[i]);
			}
			return;
		}
		for (int i = 1; i <= cardMaxNum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				pickNum[depth] = i;
				pickOrder(visited, depth + 1);
				visited[i] = false;
			}
		}
	}

	static int bfs(int[][] board, int startR, int startC, int endR, int endC) {

		int[][] visited = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		visited[startR][startC] = 0;

		if (startR == endR && startC == endC) {
			return 0;
		}
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startR, startC, 0));
		while (!q.isEmpty()) {
			Point p = q.poll();
			int row = p.row;
			int col = p.col;
			int cnt = p.cnt;
			for (int k = 0; k < 4; k++) {
				int nrow = row + drow[k];
				int ncol = col + dcol[k];
				if (isRange(nrow, ncol)) {
					if (visited[nrow][ncol] > cnt + 1) {
						visited[nrow][ncol] = cnt + 1;
						q.add(new Point(nrow, ncol, cnt + 1));
					}
				}
				int[] cPoint = ctralMove(board, k, row, col);
				int crow = cPoint[0];
				int ccol = cPoint[1];
				if (crow != row || ccol != col) {
					if (visited[crow][ccol] > cnt + 1) {
						visited[crow][ccol] = cnt + 1;
						q.add(new Point(crow, ccol, cnt + 1));
					}
				}
			}
		}
		int res = visited[endR][endC];
		return res;
	}

	static boolean isRange(int nrow, int ncol) {
		if (0 <= nrow && nrow < 4 && 0 <= ncol && ncol < 4)
			return true;
		return false;
	}

	static int[] ctralMove(int[][] board, int dir, int r, int c) {
		int[] point = new int[2];
		point[0] = r;
		point[1] = c;
		boolean cardCheck = false;
		switch (dir) {
		// <
		case 0:
			if (c - 1 >= 0) {
				for (int i = c - 1; i >= 0; i--) {
					if (board[r][i] != 0) {
						point[1] = i;
						cardCheck = true;
						break;
					}
				}
				if (!cardCheck)
					point[1] = 0;
			}
			break;
		// ^
		case 1:
			if (r - 1 >= 0) {
				for (int i = r - 1; i >= 0; i--) {
					if (board[i][c] != 0) {
						point[0] = i;
						cardCheck = true;
						break;
					}
				}
				if (!cardCheck)
					point[0] = 0;
			}

			break;
		// >
		case 2:
			if (c + 1 < 4) {
				for (int i = c + 1; i < 4; i++) {
					if (board[r][i] != 0) {
						point[1] = i;
						cardCheck = true;
						break;
					}
				}
				if (!cardCheck)
					point[1] = 3;
			}
			break;
		case 3:
			if (r + 1 < 4) {
				for (int i = r + 1; i < 4; i++) {
					if (board[i][c] != 0) {
						point[0] = i;
						cardCheck = true;
						break;
					}
				}
				if (!cardCheck)
					point[0] = 3;
			}
			break;
		}
		return point;
	}

	static void print(int[][] board) {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
