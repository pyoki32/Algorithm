import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14466 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][][] map;
    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};
    static ArrayList<Point> cows;
    static int N, K, R;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1][4];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            for (int k = 0; k < 4; k++) {
                if (drow[k] == r1 - r2 && dcol[k] == c1 - c2) {
                    map[r1][c1][k] = 1;
                }
                if (drow[k] == r2 - r1 && dcol[k] == c2 - c1) {
                    map[r2][c2][k] = 1;
                }
            }
        }
        cows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int cow_r = Integer.parseInt(st.nextToken());
            int cow_c = Integer.parseInt(st.nextToken());
            cows.add(new Point(cow_r, cow_c));
        }
        answer = 0;
        int[] pick = new int[2];
        pickPairIdx(pick, 0, 0);
        System.out.println(answer);
    }

    static void pickPairIdx(int[] pick, int start, int depth) {
        if (depth > 1) {
            boolean needBridge = bfs(cows.get(pick[0]), cows.get(pick[1]));
            if (needBridge) answer++;
            return;
        }
        for (int i = start; i < cows.size(); i++) {
            pick[depth] = i;
            pickPairIdx(pick, i + 1, depth + 1);
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 < nrow && nrow <= N && 0 < ncol && ncol <= N) return true;
        else return false;
    }

    static boolean bfs(Point startCow, Point endCow) {

        int startR = startCow.row;
        int startC = startCow.col;
        int endR = endCow.row;
        int endC = endCow.col;
        boolean needBridge = false;
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[startR][startC] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startR, startC));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];
                if (isRange(nrow, ncol)) {
                    if (!visited[nrow][ncol] && map[nrow][ncol][k] != 1) {
                        visited[nrow][ncol] = true;
                        q.add(new Point(nrow, ncol));
                    }
                }
            }
        }
        if (!visited[endR][endC]) needBridge = true;
        return needBridge;
    }
}
