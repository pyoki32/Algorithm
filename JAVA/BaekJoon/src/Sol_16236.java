import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_16236 {
    static class BabyShark {
        int row;
        int col;
        int size;
        int dist;

        public BabyShark(int row, int col, int size, int dist) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.dist = dist;
        }
    }

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dRow = {0, 0, -1, 1};
    static int[] dCol = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        int startR = 0;
        int startC = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startR = i;
                    startC = j;
                }
            }
        }
        solve(startR, startC);
    }

    static void solve(int startR, int startC) {
        int answer = 0;
        int eatFish = 0;
        map[startR][startC] = 0;
        BabyShark babyShark = new BabyShark(startR, startC, 2, 0);
        while (babyShark != null) {
            initVisited();
            babyShark = getNextPoint(babyShark);
            if (babyShark == null) break;
            int nextBabySharkSize = babyShark.size;
            map[babyShark.row][babyShark.col] = 0;
            eatFish++;
            if (eatFish == nextBabySharkSize) {
                nextBabySharkSize++;
                eatFish = 0;
            }
            answer += babyShark.dist;
            babyShark = new BabyShark(babyShark.row, babyShark.col, nextBabySharkSize, 0);
        }
        System.out.println(answer);
    }

    static BabyShark getNextPoint(BabyShark now) {
        PriorityQueue<BabyShark> pq = new PriorityQueue<>((bs1, bs2) -> {
            if (bs1.dist == bs2.dist) {
                if (bs1.row == bs2.row) return bs1.col - bs2.col;
                return bs1.row - bs2.row;
            }
            return bs1.dist - bs2.dist;
        });
        Queue<BabyShark> q = new LinkedList<>();
        visited[now.row][now.col] = true;
        q.add(now);
        while (!q.isEmpty()) {
            BabyShark bsq = q.poll();
            int row = bsq.row;
            int col = bsq.col;
            int size = bsq.size;
            int dist = bsq.dist;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol)) {
                    if (!visited[nRow][nCol] && map[nRow][nCol] <= size) {
                        visited[nRow][nCol] = true;
                        if (map[nRow][nCol] != 0 && map[nRow][nCol] < size)
                            pq.add(new BabyShark(nRow, nCol, size, dist + 1));
                        q.add(new BabyShark(nRow, nCol, size, dist + 1));
                    }
                }
            }
        }

        if (pq.size() == 0) return null;
        return pq.poll();
    }

    static boolean isRange(int nRow, int nCol) {
        if (0 <= nRow && nRow < N && 0 <= nCol && nCol < N) return true;
        else return false;
    }

    static void initVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }
}
