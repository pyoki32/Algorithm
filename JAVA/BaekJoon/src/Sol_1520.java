import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_1520 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int M, N;
    static int[][] map;
    static int[] drow = {0, 0, -1, 1};
    static int[] dcol = {-1, 1, 0, 0};
    static int[][] inDegree;
    static boolean[][] visited;
    static int[][] routeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[M][N];
        movePossibleCheck();
        routeCnt = new int[M][N];
        routeCnt[0][0] = 1;
        topologicalSort();
        System.out.println(routeCnt[M-1][N-1]);
    }

    static void topologicalSort() {
        inDegree = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nrow = i + drow[k];
                    int ncol = j + dcol[k];
                    if (isRange(nrow, ncol)) {
                        if (visited[nrow][ncol] && map[i][j] < map[nrow][ncol]) {
                            inDegree[i][j]++;
                        }
                    }
                }
            }
        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];
                if (isRange(nrow, ncol)) {
                    if (map[row][col] > map[nrow][ncol]) {
                        routeCnt[nrow][ncol] += routeCnt[row][col];
                        inDegree[nrow][ncol]--;
                        if(inDegree[nrow][ncol]==0)q.add(new Point(nrow,ncol));
                    }
                }
            }

        }

    }

    static void movePossibleCheck() {
        Queue<Point> q = new LinkedList<>();
        visited[0][0] = true;
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];
                if (isRange(nrow, ncol)) {
                    if (map[nrow][ncol] < map[row][col] && !visited[nrow][ncol]) {
                        visited[nrow][ncol] = true;
                        q.add(new Point(nrow, ncol));
                    }
                }
            }
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < M && 0 <= ncol && ncol < N) return true;
        else return false;
    }
}
