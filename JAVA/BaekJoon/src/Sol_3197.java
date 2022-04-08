import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_3197 {
    static class Point {
        int row;
        int col;
        int day;

        public Point(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] dayMap;
    static int[] dRow = { 0, 0, 1, -1 };
    static int[] dCol = { 1, -1, 0, 0 };
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dayMap = new int[R][C];
        ArrayList<Point> swan = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'L')
                    swan.add(new Point(i, j, -1));
                else if (map[i][j] == 'X')
                    dayMap[i][j] = 1600;
            }
        }

        visited = new boolean[R + 2][C + 2];
        bfs();

        // init visited
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = false;
            }
        }
        System.out.println(solve(swan));
    }

    static void bfs() {

        Deque<Point> dq = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X' && !visited[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int nRow = i + dRow[k];
                        int nCol = j + dCol[k];
                        if (isRange(nRow, nCol)) {
                            if (map[nRow][nCol] != 'X') {
                                visited[i][j] = true;
                                dayMap[i][j] = 1;
                                dq.addFirst(new Point(i, j, 1));
                            }
                        }
                    }
                }
            }
        }

        while (!dq.isEmpty()) {
            Point p = dq.poll();
            int row = p.row;
            int col = p.col;
            int day = p.day;
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol) && !visited[nRow][nCol]) {
                    if (map[nRow][nCol] == 'X' && dayMap[nRow][nCol] > day + 1) {
                        dayMap[nRow][nCol] = day + 1;
                        dq.addLast(new Point(nRow, nCol, day + 1));
                    }
                }
            }
        }
    }

    static boolean isRange(int nRow, int nCol) {
        if (0 <= nRow && nRow < R && 0 <= nCol && nCol < C)
            return true;
        else
            return false;
    }

    static int solve(ArrayList<Point> swan) {
        int answer = -1;
        Point swan1 = swan.get(0);
        Point swan2 = swan.get(1);

        Deque<Point> dq = new LinkedList<>();
        dq.add(new Point(swan1.row, swan1.col, 0));
        visited[swan1.row][swan1.col] = true;
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            int row = p.row;
            int col = p.col;
            int day = p.day;
            if (row == swan2.row && col == swan2.col) {
                answer = day;
                return answer;
            }
            for (int k = 0; k < 4; k++) {
                int nRow = row + dRow[k];
                int nCol = col + dCol[k];
                if (isRange(nRow, nCol) && !visited[nRow][nCol]) {
                    if (dayMap[nRow][nCol] <= day) {
                        visited[nRow][nCol] = true;
                        dq.addFirst(new Point(nRow, nCol, day));
                    } else if (dayMap[nRow][nCol] > day) {
                        visited[nRow][nCol] = true;
                        dq.addLast(new Point(nRow, nCol, day + 1));
                    }
                }
            }
        }
        return answer;
    }
}
