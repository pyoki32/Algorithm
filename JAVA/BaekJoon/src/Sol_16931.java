import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_16931 {
	static int N, M;
	static int[] drow = { -1, 1, 0, 0 };
	static int[] dcol = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int surface = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int point = map[i][j];
				for (int h = 1; h <= point; h++) {
					int cnt = 4;
					for (int k = 0; k < 4; k++) {
						int nrow = i + drow[k];
						int ncol = j + dcol[k];
						if (isRange(nrow, ncol)) {
							if (map[nrow][ncol] >= h) {
								cnt--;
							}
						}
					}
					surface += cnt;
				}
			}
		}
		surface += 2 * (N * M);
		System.out.println(surface);

	}

	static boolean isRange(int nrow, int ncol) {

		if (0 <= nrow && nrow < N && 0 <= ncol && ncol < M)
			return true;
		return false;
	}

}
