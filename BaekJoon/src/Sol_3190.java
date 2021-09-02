import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Sol_3190 {
    static class Snake {
        int row;
        int col;
        int dir;
        int time;

        public Snake(int row, int col, int dir, int time) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.time = time;

        }
    }

    static int N, K, L;
    static boolean[][] map;
    static boolean[][] snakeExistence;
    static char[] rotateDir;
    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N + 1][N + 1];
        snakeExistence = new boolean[N + 1][N + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int appleRow = Integer.parseInt(st.nextToken());
            int appleCol = Integer.parseInt(st.nextToken());
            map[appleRow][appleCol] = true;
        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        rotateDir = new char[10001];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            rotateDir[X] = C;
        }
        answer = 0;
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Deque<Snake> dq = new LinkedList<>();
        snakeExistence[1][1] = true;
        dq.add(new Snake(1, 1, 1, 0));

        while (!dq.isEmpty()) {
            Snake s = dq.pollFirst();
            int row = s.row;
            int col = s.col;
            int dir = s.dir;
            int time = s.time;
            if (rotateDir[time] != 0) dir = caldir(time, dir);
            int nrow = row + drow[dir];
            int ncol = col + dcol[dir];
            if (isRange(nrow, ncol)) {
                if (map[nrow][ncol] && !snakeExistence[nrow][ncol]) {
                    snakeExistence[nrow][ncol] = true;
                    map[nrow][ncol] = false;
                    dq.addFirst(s);
                    dq.addFirst(new Snake(nrow, ncol, dir, time + 1));
                } else if (!map[nrow][ncol] && !snakeExistence[nrow][ncol]) {
                    snakeExistence[nrow][ncol] = true;
                    dq.addFirst(s);
                    dq.addFirst(new Snake(nrow, ncol, dir, time + 1));
                    Snake snakeTail = dq.pollLast();
                    int stRow = snakeTail.row;
                    int stCol = snakeTail.col;
                    snakeExistence[stRow][stCol] = false;
                } else {
                    answer = time + 1;
                    return;
                }

            } else {
                answer = time + 1;
                return;
            }

        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 < nrow && nrow <= N && 0 < ncol && ncol <= N)
            return true;
        return false;
    }

    static int caldir(int time, int dir) {
        int ndir = 0;
        char cdir = rotateDir[time];
        switch (dir) {
            case 0:
                if (cdir == 'D') ndir = 1;
                else ndir = 3;
                break;
            case 1:
                if (cdir == 'D') ndir = 2;
                else ndir = 0;
                break;
            case 2:
                if (cdir == 'D') ndir = 3;
                else ndir = 1;
                break;
            case 3:
                if (cdir == 'D') ndir = 0;
                else ndir = 2;
                break;
        }

        return ndir;
    }
}
