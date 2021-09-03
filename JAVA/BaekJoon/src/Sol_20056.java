import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_20056 {
    static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    static ArrayList<FireBall>[][] map;
    static ArrayList<FireBall> fireBalls;
    static int[] drow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dcol = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireBalls = new ArrayList<>();
        map = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(r, c, m, s, d));
        }
        for (int k = 0; k < K; k++) solve();

        int answer = 0;
        for (FireBall fb : fireBalls) answer += fb.m;

        System.out.println(answer);

    }

    static void solve() {
        map = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        //move
        for (FireBall fb : fireBalls) {
            int r = fb.r;
            int c = fb.c;
            int m = fb.m;
            int s = fb.s;
            int d = fb.d;
            int nrow = r + (drow[d] * s) % N;
            int ncol = c + (dcol[d] * s) % N;
            if (isRange(nrow, ncol)) map[nrow][ncol].add(new FireBall(nrow, ncol, m, s, d));
            else {
                int[] getPoint = getOutRangePoint(nrow, ncol);
                map[getPoint[0]][getPoint[1]].add(new FireBall(getPoint[0], getPoint[1], m, s, d));
            }
        }

        fireBalls = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!map[i][j].isEmpty()) {
                    ArrayList<FireBall> point = map[i][j];
                    if (point.size() >= 2) {
                        int sumM = 0;
                        int sumS = 0;
                        boolean odd = true;
                        boolean even = true;
                        for (FireBall pfb : point) {
                            sumM += pfb.m;
                            sumS += pfb.s;
                            if (pfb.d % 2 == 0) odd = false;
                            else even = false;
                        }
                        int nextM = sumM / 5;
                        int nextS = sumS / point.size();
                        if (nextM != 0) {
                            if (odd || even)
                                for (int k = 0; k <= 6; k += 2) fireBalls.add(new FireBall(i, j, nextM, nextS, k));
                            else for (int k = 1; k <= 7; k += 2) fireBalls.add(new FireBall(i, j, nextM, nextS, k));
                        }
                    } else {
                        fireBalls.add(point.get(0));
                    }
                }
            }
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (1 <= nrow && nrow <= N && 1 <= ncol && ncol <= N) return true;
        return false;
    }

    static int[] getOutRangePoint(int nrow, int ncol) {
        int[] point = new int[2];
        int trow = nrow;
        int tcol = ncol;

        if (nrow < 1) trow = N + nrow;
        else if (nrow > N) trow = nrow - N;
        if (ncol < 1) tcol = N + ncol;
        else if (ncol > N) tcol = ncol - N;

        point[0] = trow;
        point[1] = tcol;
        return point;
    }

}
