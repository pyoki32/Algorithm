import java.util.ArrayList;

public class Sol_자물쇠와_열쇠 {
	static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		boolean answer = solution(key, lock);
		System.out.println(answer);
	}

	static int N, M;

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		M = key.length;
		N = lock.length;
		int startRow = 0;
		int startCol = 0;
		boolean startCheck = false;
		int zeroCnt = 0;
		ArrayList<Point> interval = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 0 && !startCheck) {
					startRow = i;
					startCol = j;
					zeroCnt++;
					startCheck = true;
				} else if (lock[i][j] == 0 && startCheck) {
					zeroCnt++;
					interval.add(new Point(startRow - i, startCol - j));
				}
			}
		}
		ArrayList<Point> spin = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 1) {
					spin.add(new Point(startRow - i, startCol - j));
				}
			}
		}
		boolean pass = true;
		pass = answerCheck(key, interval, spin);
		int[][] temp = rotate(key);
		if (!pass) {
			int cnt = 3;
			while (cnt > 0) {
				pass = answerCheck(temp, interval, spin);
				if (pass) {
					break;
				} else {
					temp = rotate(temp);
				}
				cnt--;
			}
		}

		answer = pass;
		if (zeroCnt == 0)
			answer = true;
		return answer;
	}

	public static boolean answerCheck(int[][] key, ArrayList<Point> interval, ArrayList<Point> spin) {
		boolean keyCheck = false;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (key[i][j] == 1) {
					for (Point p : spin) {
						int spinR = i - p.row;
						int spinC = j - p.col;
						if (isRange(spinR, spinC)) {
							if (key[spinR][spinC] == 1) {
								System.out.println("ewqeqwe");
								keyCheck = false;
								break;
							}
						}
					}
					keyCheck = true;
					for (Point p : interval) {
						int checkR = i - p.row;
						int checkC = j - p.col;
						if (isRange(checkR, checkC)) {
							if (key[checkR][checkC] != 1) {
								keyCheck = false;
								break;
							}
						} else {
							keyCheck = false;
							break;
						}

					}
					if (keyCheck)
						return true;
				}
			}
		}
		return keyCheck;
	}

	public static int[][] rotate(int[][] key) {
		int[][] res = new int[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				res[i][j] = key[(M - 1) - j][i];
			}
		}
		return res;
	}

	public static boolean isRange(int row, int col) {
		if (0 <= row && row < M && 0 <= col && col < M)
			return true;
		return false;
	}
}
