import java.util.*;

public class Sol_리틀_프렌즈_사천성 {
    public static void main(String[] args) {
        int m = 1;
        int n = 2  ;
        String[] board = {"AA"};
        String answer = solution(m, n, board);
        System.out.println(answer);
    }

    static class Point {
        int row;
        int col;
        int dir;
        int curve;

        public Point(int row, int col, int dir, int curve) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.curve = curve;
        }
    }

    static ArrayList<ArrayList<Point>> tilePoint;
    static ArrayList<Integer> tiles;
    static ArrayList<String> result;
    static boolean[] visited;
    static char[][] initBoard;
    static char[][] usingBoard;
    static int r, c;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static String solution(int m, int n, String[] board) {
        result = new ArrayList<>();
        tilePoint = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            tilePoint.add(new ArrayList<>());
        }
        r = m;
        c = n;
        initBoard = new char[r][c];

        HashSet<Integer> tileIdx = new HashSet<>();
        tiles = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String temp = board[i];
            for (int j = 0; j < n; j++) {
                initBoard[i][j] = temp.charAt(j);
                int tile = temp.charAt(j) - 'A';
                if (0 <= tile && tile < 26) {
                    tilePoint.get(tile).add(new Point(i, j, -1, 0));
                    if (!tileIdx.contains(tile)){
                        tileIdx.add(tile);
                        tiles.add(tile);
                    }
                }
            }
        }
        usingBoard = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                usingBoard[i][j] = initBoard[i][j];
            }
        }
        int[] pick = new int[tileIdx.size()];
        visited = new boolean[tileIdx.size()];
        print(usingBoard);
        perm(pick, 0,tileIdx.size());



        Collections.sort(result);

//        for(String str :result){
//            System.out.println(str);
//        }

        String answer ="";
        if(result.isEmpty())answer ="IMPOSSIBLE";
        else answer = result.get(0);
        return answer;
    }

    static void perm(int[] pick, int depth,int len) {
        if (depth > pick.length - 1) {
                print(usingBoard);
               StringBuilder sb = new StringBuilder();
               for(int k : pick){
                   int strInt = tiles.get(k)+'A';
                   char ch = (char)strInt;
                   sb.append(ch);

               }
               if(!sb.toString().equals("")&&sb.toString().length()==len){
                   result.add(sb.toString());
               }


            return;
        }
        for (int i = 0; i < tiles.size(); i++) {
            if (!visited[i]&&playGame(i)) {
                visited[i] = true;
                pick[depth] = i;
                ArrayList<Point> targetPoint = tilePoint.get(tiles.get(i));
                int startR = targetPoint.get(0).row;
                int startC = targetPoint.get(0).col;
                int endR = targetPoint.get(1).row;
                int endC = targetPoint.get(1).col;
                char targetAlpha = usingBoard[startR][startC];
                usingBoard[startR][startC] ='.';
                usingBoard[endR][endC] ='.';
                print(usingBoard);
                perm(pick, depth + 1,len);
                usingBoard[startR][startC] =targetAlpha;
                usingBoard[endR][endC] =targetAlpha;
                visited[i] = false;
            }
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < r && 0 <= ncol && ncol < c) return true;
        return false;
    }
    static boolean playGame(int pickIdx) {
        boolean game = false;

            Queue<Point> q = new LinkedList<>();
            boolean[][] visited = new boolean[r][c];
            ArrayList<Point> targetPoint = tilePoint.get(tiles.get(pickIdx));
            int startR = targetPoint.get(0).row;
            int startC = targetPoint.get(0).col;
            int endR = targetPoint.get(1).row;
            int endC = targetPoint.get(1).col;
            char targetAlpha = usingBoard[startR][startC];
            visited[startR][startC] = true;
            q.add(new Point(startR, startC, -1, 0));

            while (!q.isEmpty()) {
                Point p = q.poll();
                int row = p.row;
                int col = p.col;
                int dir = p.dir;
                int curve = p.curve;
                if (row == endR && col == endC && curve < 2) {
                    game = true;
                    break;
                }
                if (dir == -1) {
                    for (int k = 0; k < 4; k++) {
                        int nrow = row + drow[k];
                        int ncol = col + dcol[k];
                        int ndir = k;
                        if (isRange(nrow, ncol)) {
                            if (!visited[nrow][ncol]) {
                                if (usingBoard[nrow][ncol] == '.' || usingBoard[nrow][ncol] == targetAlpha) {
                                    visited[nrow][ncol] = true;
                                    q.add(new Point(nrow, ncol, ndir, 0));
                                }
                            }
                        }
                    }
                } else {
                    for (int k = 0; k < 4; k++) {
                        int nrow = row + drow[k];
                        int ncol = col + dcol[k];
                        int ndir = k;
                        if (isRange(nrow, ncol)) {
                            if (!visited[nrow][ncol]) {
                                if (usingBoard[nrow][ncol] == '.' || usingBoard[nrow][ncol] == targetAlpha) {
                                    if (dir != ndir && curve == 0) {
                                        visited[nrow][ncol] = true;
                                        q.add(new Point(nrow, ncol, ndir, curve + 1));
                                    } else if (dir == ndir) {
                                        visited[nrow][ncol] = true;
                                        q.add(new Point(nrow, ncol, ndir, curve));
                                    }

                                }
                            }

                        }
                    }
                }
            }
        return game;
    }


    static void print(char [][] map){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                 System.out.print(map[i][j]+" ");
            }
            System.out.println();

        }
        System.out.println("---------------------");
        System.out.println("---------------------");
    }
}
