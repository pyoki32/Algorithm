import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_위클리챌린지_3 {
    static class Point {
        int row;
        int col;
        int dir;
        int beforeOrder;
        int order;

        public Point(int row, int col, int dir, int beforeOrder, int order) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.beforeOrder = beforeOrder;
            this.order = order;
        }
    }

    static class Puzzle {
        boolean used;
        ArrayList<ArrayList<Point>> puzzlePoint;

        public Puzzle(boolean used, ArrayList<ArrayList<Point>> puzzlePoint) {
            this.used = used;
            this.puzzlePoint = puzzlePoint;
        }
    }

    public static void main(String[] args) {
        int[][] game_board = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
        System.out.println(solution(game_board, table));
    }

    static int[] drow = {0, -1, 0, 1};
    static int[] dcol = {1, 0, -1, 0};
    static boolean[][] visited;
    static int N;
    static ArrayList<ArrayList<Puzzle>> availablePuzzle;

    public static int solution(int[][] game_board, int[][] table) {

        availablePuzzle = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            availablePuzzle.add(new ArrayList<>());
        }
        N = game_board.length;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && table[i][j] == 1) {
                    ArrayList<Point> temp = findPoint(table, new Point(i, j, -1, -1, 0));
                    makeRotatePuzzle(temp);
                }
            }
        }
        int answer = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!visited[i][j] && game_board[i][j] == 0) {
                    ArrayList<Point> emptySpace = findPoint(game_board, new Point(i, j, -1, -1, 0));
                    if (putPuzzle(emptySpace)) {
                        answer += emptySpace.size();
                    }
                }
            }
        }
        return answer;
    }

    static ArrayList<Point> findPoint(int[][] map, Point startP) {
        visited[startP.row][startP.col] = true;
        int target = map[startP.row][startP.col];
        ArrayList<Point> trace = new ArrayList<>();
        trace.add(startP);
        Queue<Point> q = new LinkedList<>();
        q.add(startP);
        int order = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            int pOrder = p.order;
            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];
                if (isRange(nrow, ncol)) {
                    if (!visited[nrow][ncol] && map[nrow][ncol] == target) {
                        visited[nrow][ncol] = true;
                        order++;
                        q.add(new Point(nrow, ncol, k, pOrder, order));
                        trace.add(new Point(nrow, ncol, k, pOrder, order));
                    }
                }
            }
        }
        return trace;
    }

    static void makeRotatePuzzle(ArrayList<Point> initPoint) {
        ArrayList<ArrayList<Point>> allDirPoint = new ArrayList<>();
        for (int k = 0; k < 4; k++) {
            allDirPoint.add(new ArrayList<>());
            allDirPoint.get(k).add(new Point(50, 50, -1, -1, 0));
        }
        for (int n = 0; n < 4; n++) {
            for (Point p : initPoint) {
                if (p.dir != -1) {
                    int dir = p.dir;
                    int chageDir = dir + n;
                    if (chageDir > 3) chageDir -= 4;
                    int beforeOrder = p.beforeOrder;
                    Point beforePoint = allDirPoint.get(n).get(beforeOrder);
                    int beforeRow = beforePoint.row;
                    int beforeCol = beforePoint.col;
                    int rotateR = beforeRow + drow[chageDir];
                    int rotateC = beforeCol + dcol[chageDir];
                    allDirPoint.get(n).add(new Point(rotateR, rotateC, chageDir, beforeOrder, p.order));
                }
            }
        }
        availablePuzzle.get(initPoint.size()).add(new Puzzle(false, allDirPoint));
    }

    static boolean putPuzzle(ArrayList<Point> emptySpace) {
        boolean[][] quadrant = new boolean[101][101];
        ArrayList<Point> moveEmptyPoints = new ArrayList<>();
        int intervalR = 50 - emptySpace.get(0).row;
        int intervalC = 50 - emptySpace.get(0).col;
        for (Point emptyPoint : emptySpace) {
            int row = emptyPoint.row;
            int col = emptyPoint.col;
            if (row == emptySpace.get(0).row && col == emptySpace.get(0).col) {
                quadrant[50][50] = true;
            } else quadrant[row + intervalR][col + intervalC] = true;
            moveEmptyPoints.add(new Point(row + intervalR, col + intervalC, -1, -1, -1));
        }
        ArrayList<Puzzle> puzzles = availablePuzzle.get(emptySpace.size());
        for (Point mEmptyPoint : moveEmptyPoints) {
            int startR = mEmptyPoint.row;
            int startC = mEmptyPoint.col;
            for (Puzzle puzzle : puzzles) {
                if (!puzzle.used) {
                    ArrayList<ArrayList<Point>> points = puzzle.puzzlePoint;
                    for (ArrayList<Point> dirP : points) {
                        int m_intervalR = startR - dirP.get(0).row;
                        int m_intervalC = startC - dirP.get(0).col;
                        boolean pointCheck = true;
                        for (Point p : dirP) {
                            int row = p.row;
                            int col = p.col;
                            if (!quadrant[row + m_intervalR][col + m_intervalC]) {
                                pointCheck = false;
                                break;
                            }
                        }
                        if (pointCheck) {
                            puzzle.used = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < N & 0 <= ncol && ncol < N) return true;
        return false;
    }
}