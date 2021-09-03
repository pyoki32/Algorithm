import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_16234 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Country {
        int areaCnt;
        int total_pop;

        public Country(int areaCnt, int total_pop) {
            this.areaCnt = areaCnt;
            this.total_pop = total_pop;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[][] area;
    static int[] drow = {1, -1, 0, 0};
    static int[] dcol = {0, 0, 1, -1};
    static ArrayList<ArrayList<Country>> areaInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        areaInfo = new ArrayList<>();
        for (int i = 0; i <= N * N; i++) {
            areaInfo.add(new ArrayList<>());
        }
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        area = new int[N][N];
        visited = new boolean[N][N];
        int day = 0;
        while (day <= 2000) {
            boolean moveCheck = false;
            int areaNum = 1;
            initArr();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(new Point(i, j), areaNum);
                        Country tempC = areaInfo.get(areaNum).get(areaInfo.get(areaNum).size() - 1);
                        areaNum++;
                        if (tempC.areaCnt > 1) moveCheck = true;
                    }
                }
            }
            if (!moveCheck) break;
            day++;
            pop_move();
        }
        System.out.println(day);
    }

    static void bfs(Point startP, int areaNum) {

        Queue<Point> q = new LinkedList<>();
        q.add(startP);
        visited[startP.row][startP.col] = true;
        int cnt = 1;
        int total = map[startP.row][startP.col];
        area[startP.row][startP.col] = areaNum;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];
                if (isRange(nrow, ncol)) {

                    if (!visited[nrow][ncol] && borderLineOpenCheck(map[row][col], map[nrow][ncol])) {
                        visited[nrow][ncol] = true;
                        area[nrow][ncol] = areaNum;
                        cnt++;
                        total += map[nrow][ncol];
                        q.add(new Point(nrow, ncol));
                    }
                }
            }
        }
        areaInfo.get(areaNum).add(new Country(cnt, total));
    }

    static void pop_move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!areaInfo.get(area[i][j]).isEmpty()) {
                    Country c = areaInfo.get(area[i][j]).get(areaInfo.get(area[i][j]).size() - 1);
                    map[i][j] = c.total_pop / c.areaCnt;
                }
                area[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < N && 0 <= ncol && ncol < N) return true;
        return false;
    }

    static boolean borderLineOpenCheck(int population, int n_population) {
        int gap = Math.abs(population - n_population);
        if (L <= gap && gap <= R) return true;
        return false;
    }

    static void initArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }
}
