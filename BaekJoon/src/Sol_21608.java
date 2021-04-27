import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_21608 {
	static class Point {
		int row;
		int col;
		int emptyCnt;
		int adjCnt;

		public Point(int row, int col, int emptyCnt, int adjCnt) {
			this.row = row;
			this.col = col;
			this.emptyCnt = emptyCnt;
			this.adjCnt = adjCnt;
		}
	}

	static int N;
	static int[][] classRoom;
	static ArrayList<Integer> order;
	static boolean[][] visited;
	static boolean[] pickPoint;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] drow = { 0, 0, 1, -1 };
	static int[] dcol = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		classRoom = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		order = new ArrayList<>();
		adjList = new ArrayList<>();
		pickPoint = new boolean[(N * N) + 1];
		for (int i = 0; i <= (N * N); i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= (N * N); i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				int likeNum = Integer.parseInt(st.nextToken());
				adjList.get(num).add(likeNum);
			}
			order.add(num);
		}

		for (int num : order) {
			
			PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
				@Override
				public int compare(Point p1, Point p2) {
					if (p1.adjCnt == p2.adjCnt) {

						if (p1.emptyCnt == p2.emptyCnt) {
							if (p1.row == p2.row) {
								return p1.col - p2.col;
							}
							return p1.row - p2.row;
						}
						return -(p1.emptyCnt - p2.emptyCnt);

					}
					return -(p1.adjCnt - p2.adjCnt);
				}
			});

			ArrayList<Integer> likePeople = adjList.get(num);

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (classRoom[i][j] == 0) {
						int adjCnt = 0;
						int emptyCnt = 0;
						for (int k = 0; k < 4; k++) {
							int nrow = i + drow[k];
							int ncol = j + dcol[k];
							if (isRange(nrow, ncol)) {
								if (classRoom[nrow][ncol] == 0) {
									emptyCnt++;
								}
								for (int likeNum : likePeople) {
									if (classRoom[nrow][ncol] == likeNum)
										adjCnt++;
								}
							}
						}
						pq.add(new Point(i, j, emptyCnt, adjCnt));
					}
				}
			}

			Point pick = pq.poll();
			classRoom[pick.row][pick.col] = num;
			pq.clear();
		}
		int answer = solve();
		System.out.println(answer);

	}

	static int solve() {
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int happyCnt = 0;
				ArrayList<Integer> likePeople = adjList.get(classRoom[i][j]);

				for (int k = 0; k < 4; k++) {
					int nrow = i + drow[k];
					int ncol = j + dcol[k];
					if (isRange(nrow, ncol)) {
						for (int likeNum : likePeople) {
							if (classRoom[nrow][ncol] == likeNum)
								happyCnt++;
						}
					}
				}
				if (happyCnt == 1)
					answer += 1;
				else if (happyCnt == 2)
					answer += 10;
				else if (happyCnt == 3)
					answer += 100;
				else if (happyCnt == 4)
					answer += 1000;
			}
		}
		return answer;
	}

	static boolean isRange(int nrow, int ncol) {
		if (0 < nrow && nrow <= N && 0 < ncol && ncol <= N)
			return true;
		return false;
	}

}
