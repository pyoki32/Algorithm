import java.util.LinkedList;
import java.util.Queue;

class Sol_키패드누르기 {
	static class Point {
		int row;
		int col;
		int dist;
		char hand;

		public Point(int row, int col, int dist, char hand) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.hand = hand;
		}
	}

	static int[] drow = { 0, 0, -1, 1 };
	static int[] dcol = { -1, 1, 0, 0 };
	static char[][] keypad = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' }, { '*', '0', '#' } };

	public static String solution(int[] numbers, String hand) {
		String answer = "";
		Point leftP = new Point(3, 0, 0, 'L');
		Point rightP = new Point(3, 2, 0, 'R');
		for (int i = 0; i < numbers.length; i++) {

			char num = (char) (numbers[i] + '0');
			Point cal_lp = cal_dist(leftP, num);
			Point cal_rp = cal_dist(rightP, num);

			if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				leftP = new Point(cal_lp.row, cal_lp.col, 0, cal_lp.hand);
				answer += cal_lp.hand;
			} else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				rightP = new Point(cal_rp.row, cal_rp.col, 0, cal_rp.hand);
				answer += cal_rp.hand;
			} else {
				if (cal_lp.dist > cal_rp.dist) {
					rightP = new Point(cal_rp.row, cal_rp.col, 0, cal_rp.hand);
					answer += cal_rp.hand;

				} else if (cal_lp.dist == cal_rp.dist) {
					if (hand.equals("right")) {
						rightP = new Point(cal_rp.row, cal_rp.col, 0, cal_rp.hand);
						answer += cal_rp.hand;
					} else {
						leftP = new Point(cal_lp.row, cal_lp.col, 0, cal_lp.hand);
						answer += cal_lp.hand;
					}

				} else {
					leftP = new Point(cal_lp.row, cal_lp.col, 0, cal_lp.hand);
					answer += cal_lp.hand;
				}
			}
		}
		return answer;
	}

	static Point cal_dist(Point p, char target) {

		boolean[][] visited = new boolean[4][3];
		Queue<Point> q = new LinkedList<>();
		q.add(p);
		visited[p.row][p.col] = true;

		while (!q.isEmpty()) {
			Point np = q.poll();
			int row = np.row;
			int col = np.col;
			int dist = np.dist;
			char hand = np.hand;
			if (keypad[row][col] == target) {
				return np;
			}
			for (int k = 0; k < 4; k++) {
				int nrow = row + drow[k];
				int ncol = col + dcol[k];
				if (isRange(nrow, ncol)) {
					if (!visited[nrow][ncol]) {
						visited[nrow][ncol] = true;
						q.add(new Point(nrow, ncol, dist + 1, hand));
					}
				}
			}
		}

		return p;
	}

	static boolean isRange(int nrow, int ncol) {
		if (0 <= nrow && nrow < 4 && 0 <= ncol && ncol < 3) {
			return true;
		}
		return false;
	}
}