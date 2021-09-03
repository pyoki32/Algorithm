import java.util.LinkedList;
import java.util.Queue;

public class Sol_블록_이동하기 {
    static class Robot {
        int leftR;
        int leftC;
        int rightR;
        int rightC;
        int depth;

        public Robot(int leftR, int leftC, int rightR, int rightC, int depth) {
            this.leftR = leftR;
            this.leftC = leftC;
            this.rightR = rightR;
            this.rightC = rightC;
            this.depth = depth;
        }
    }

    static int[] drow = {0, -1, 0, 1};
    static int[] dcol = {1, 0, -1, 0};
    static int N;
    static int[][][][] minMoveCnt;

    public static void main(String[] args) {
        int[][] baord = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        int ans = solution(baord);
        System.out.println(ans);
    }

    public static int solution(int[][] board) {
        N = board.length;
        minMoveCnt = new int[N][N][N][N];
        for (int lr = 0; lr < N; lr++) {
            for (int lc = 0; lc < N; lc++) {
                for (int rr = 0; rr < N; rr++) {
                    for (int rc = 0; rc < N; rc++) {
                        minMoveCnt[lr][lc][rr][rc] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        moveRobot(new Robot(0, 0, 0, 1, 0), board);
        int answer = Integer.MAX_VALUE;
        for (int lr = 0; lr < N; lr++) {
            for (int lc = 0; lc < N; lc++) {
                answer = Math.min(minMoveCnt[lr][lc][N - 1][N - 1], answer);
            }
        }

        for (int rr = 0; rr < N; rr++) {
            for (int rc = 0; rc < N; rc++) {
                answer = Math.min(minMoveCnt[N - 1][N - 1][rr][rc], answer);
            }
        }
        return answer;
    }

    static void moveRobot(Robot bot, int[][] board) {

        Queue<Robot> rq = new LinkedList<>();
        rq.add(bot);

        while (!rq.isEmpty()) {
            Robot rb = rq.poll();
            int leftR = rb.leftR;
            int leftC = rb.leftC;
            int rightR = rb.rightR;
            int rightC = rb.rightC;
            int depth = rb.depth;
            for (int k = 0; k < 4; k++) {
                int nLeftR = leftR + drow[k];
                int nLeftC = leftC + dcol[k];
                int nRightR = rightR + drow[k];
                int nRightC = rightC + dcol[k];
                if (isRange(nLeftR, nLeftC) && isRange(nRightR, nRightC)) {
                    if (board[nLeftR][nLeftC] == 0 && board[nRightR][nRightC] == 0) {

                        if (minMoveCnt[nLeftR][nLeftC][nRightR][nRightC] > depth + 1) {
                            minMoveCnt[nLeftR][nLeftC][nRightR][nRightC] = depth + 1;
                            rq.add(new Robot(nLeftR, nLeftC, nRightR, nRightC, depth + 1));
                        }
                        //회전이동
                        if ((leftR != nRightR && leftC != nRightC) || (rightR != nLeftR && rightC != nRightC)) {
                            if (minMoveCnt[nRightR][nRightC][rightR][rightC] > depth + 1) {
                                minMoveCnt[nRightR][nRightC][rightR][rightC] = depth + 1;
                                rq.add(new Robot(nRightR, nRightC, rightR, rightC, depth + 1));
                            }
                            if (minMoveCnt[leftR][leftC][nLeftR][nLeftC] > depth + 1) {
                                minMoveCnt[leftR][leftC][nLeftR][nLeftC] = depth + 1;
                                rq.add(new Robot(leftR, leftC, nLeftR, nLeftC, depth + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean isRange(int nrow, int ncol) {
        if (0 <= nrow && nrow < N && 0 <= ncol && ncol < N) return true;
        return false;
    }
}
