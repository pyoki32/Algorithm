import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_13460 {
    static class Point {
        int row;
        int col;

        public Point() {

        }

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static char[][] map;
    // up ,right,down,left
    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};
    static int answer;
    static Point goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Point red = new Point();
        Point blue = new Point();
        goal = new Point();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    red = new Point(i, j);
                } else if (map[i][j] == 'B') {
                    blue = new Point(i, j);
                } else if (map[i][j] == 'O') {
                    goal = new Point(i, j);
                }
            }
        }
        answer = Integer.MAX_VALUE;
        dfs(map, -1, 0, red, blue);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static void dfs(char[][] map, int dir, int depth, Point red, Point blue) {
        if (depth > 9) {
            return;
        }
        for (int k = 0; k < 4; k++) {
            if (dir != k) {
                char[][] nextMap = copyMap(map);
                boolean redGoal = false;
                boolean blueGoal = false;
                //red 먼저 이
                if (pickOrder(k, red, blue)) {
                    Point nextRed = moveBall(nextMap, k, red);
                    if (nextRed.row == goal.row && nextRed.col == goal.col) {
                        redGoal = true;
                        nextMap[red.row][red.col] = '.';
                    } else {
                        nextMap[red.row][red.col] = '.';
                        nextMap[nextRed.row][nextRed.col] = 'R';
                    }
                    Point nextBlue = moveBall(nextMap, k, blue);
                    if (nextBlue.row == goal.row && nextBlue.col == goal.col) {
                        blueGoal = true;
                        nextMap[blue.row][blue.col] = '.';
                    } else {
                        nextMap[blue.row][blue.col] = '.';
                        nextMap[nextBlue.row][nextBlue.col] = 'B';
                    }
                    //성공 게임종료
                    if (redGoal && !blueGoal) {
                        answer = Math.min(answer, depth + 1);
                        return;
                    }
                    //진행
                    else if (!redGoal && !blueGoal) {
                        dfs(nextMap, k, depth + 1, nextRed, nextBlue);
                    }
                } else {//블루 먼저 이동
                    Point nextBlue = moveBall(nextMap, k, blue);
                    if (nextBlue.row == goal.row && nextBlue.col == goal.col) {
                        blueGoal = true;
                        nextMap[blue.row][blue.col] = '.';
                    } else {
                        nextMap[blue.row][blue.col] = '.';
                        nextMap[nextBlue.row][nextBlue.col] = 'B';
                    }
                    Point nextRed = moveBall(nextMap, k, red);
                    if (nextRed.row == goal.row && nextRed.col == goal.col) {
                        redGoal = true;
                        nextMap[red.row][red.col] = '.';
                    } else {
                        nextMap[red.row][red.col] = '.';
                        nextMap[nextRed.row][nextRed.col] = 'R';
                    }
                    //성공 게임종료
                    if (redGoal && !blueGoal) {
                        answer = Math.min(answer, depth + 1);
                        return;
                    }
                    //진행
                    else if (!redGoal && !blueGoal) {
                        dfs(nextMap, k, depth + 1, nextRed, nextBlue);
                    }
                }
            }
        }
    }

    // up ,right,down,left
    static boolean pickOrder(int dir, Point red, Point blue) {

        boolean redFirst = false;

        switch (dir) {
            case 0:
                if (red.row < blue.row) redFirst = true;

                break;
            case 1:
                if (red.col > blue.col) redFirst = true;

                break;
            case 2:
                if (red.row > blue.row) redFirst = true;

                break;
            case 3:
                if (red.col < blue.col) redFirst = true;
                break;
        }
        return redFirst;
    }

    static Point moveBall(char[][] map, int dir, Point ball) {

        int ballRow = ball.row;
        int ballCol = ball.col;

        switch (dir) {
            case 0:
                for (int i = ballRow - 1; i >= 0; i--) {
                    if (isRange(i, ballCol)) {
                        if (map[i][ballCol] == '.') {
                            ballRow = i;
                        } else if (map[i][ballCol] == 'O') {
                            ballRow = i;
                            break;
                        } else break;
                    } else break;
                }
                break;
            case 1:
                for (int i = ballCol + 1; i < M; i++) {
                    if (isRange(ballRow, i)) {
                        if (map[ballRow][i] == '.') {
                            ballCol = i;
                        } else if (map[ballRow][i] == 'O') {
                            ballCol = i;
                            break;
                        } else break;
                    } else break;
                }
                break;
            case 2:
                for (int i = ballRow + 1; i < N; i++) {
                    if (isRange(i, ballCol)) {
                        if (map[i][ballCol] == '.') {
                            ballRow = i;
                        } else if (map[i][ballCol] == 'O') {
                            ballRow = i;
                            break;
                        } else break;
                    } else break;
                }
                break;
            case 3:
                for (int i = ballCol - 1; i >= 0; i--) {
                    if (isRange(ballRow, i)) {
                        if (map[ballRow][i] == '.') {
                            ballCol = i;
                        } else if (map[ballRow][i] == 'O') {
                            ballCol = i;
                            break;
                        } else break;
                    } else break;
                }
                break;
        }
        return new Point(ballRow, ballCol);
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < N && 0 <= ncol && ncol < M) return true;
        else return false;
    }

    static char[][] copyMap(char[][] map) {

        char[][] nextMap = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nextMap[i][j] = map[i][j];
            }
        }
        return nextMap;
    }
}
