import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_3085 {

    static int N, maxCnt;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        maxCnt = 0;
        calCnt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solve(i, j);
            }
        }
        System.out.println(maxCnt);
    }

    static void solve(int row, int col) {
        for (int k = 0; k < 4; k++) {
            int nrow = row + drow[k];
            int ncol = col + dcol[k];
            if (isRange(nrow, ncol) && map[row][col] != map[nrow][ncol]) {
                swapColor(row, col, nrow, ncol);
                calCnt();
                swapColor(nrow, ncol, row, col);
            }
        }
    }

    static void calCnt() {
        for (int i = 0; i < N; i++) {
            char startRowColor = map[i][0];
            char startColColor = map[0][i];
            int rowCnt = 1;
            int colCnt = 1;
            for (int j = 1; j < N; j++) {
                if (startRowColor == map[i][j]) {
                    rowCnt++;
                } else {
                    maxCnt = Math.max(maxCnt, rowCnt);
                    rowCnt = 1;
                    startRowColor = map[i][j];
                }

                if (startColColor == map[j][i]) {
                    colCnt++;
                } else {
                    maxCnt = Math.max(maxCnt, colCnt);
                    colCnt = 1;
                    startColColor = map[j][i];
                }
            }
            maxCnt = Math.max(maxCnt, rowCnt);
            maxCnt = Math.max(maxCnt, colCnt);
        }
    }

    static void swapColor(int row, int col, int nrow, int ncol) {
        char temp = map[row][col];
        map[row][col] = map[nrow][ncol];
        map[nrow][ncol] = temp;
    }

    static boolean isRange(int row, int col) {
        if (0 <= row && row < N && 0 <= col && col < N) return true;
        return false;
    }
}
