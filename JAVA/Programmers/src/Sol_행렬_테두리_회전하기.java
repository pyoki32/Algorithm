import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Sol_행렬_테두리_회전하기 {
    public static void main(String[] argv) {

        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int[] answer = solution(rows, columns, queries);
        for (int k : answer) {
            System.out.print(k);
        }
    }

    static int[][] maxtrix;
    static Deque<Integer> dq;
    static ArrayList<Integer> minNums;

    public static int[] solution(int rows, int columns, int[][] queries) {

        maxtrix = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                maxtrix[i][j] = num;
                num++;
            }
        }
        dq = new LinkedList<>();
        minNums = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            rotateMatrix(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }

        int[] answer = new int[minNums.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = minNums.get(i);
        }
        return answer;
    }

    static void rotateMatrix(int r1, int c1, int r2, int c2) {

        int minNum = Integer.MAX_VALUE;
        for (int i = c1; i < c2; i++) {
            dq.add(maxtrix[r1][i]);
            minNum = Math.min(minNum, maxtrix[r1][i]);
        }
        for (int i = r1; i < r2; i++) {
            dq.add(maxtrix[i][c2]);
            minNum = Math.min(minNum, maxtrix[i][c2]);
        }
        for (int i = c2; i > c1; i--) {
            dq.add(maxtrix[r2][i]);
            minNum = Math.min(minNum, maxtrix[r2][i]);
        }
        for (int i = r2; i > r1; i--) {
            dq.add(maxtrix[i][c1]);
            minNum = Math.min(minNum, maxtrix[i][c1]);
        }
        dq.addFirst(dq.pollLast());
        for (int i = c1; i < c2; i++) {
            maxtrix[r1][i] = dq.poll();
        }
        for (int i = r1; i < r2; i++) {
            maxtrix[i][c2] = dq.poll();
        }
        for (int i = c2; i > c1; i--) {
            maxtrix[r2][i] = dq.poll();
        }
        for (int i = r2; i > r1; i--) {
            maxtrix[i][c1] = dq.poll();
        }
        minNums.add(minNum);
    }
}
