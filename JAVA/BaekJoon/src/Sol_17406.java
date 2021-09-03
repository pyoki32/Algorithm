import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_17406 {
    static class Info {
        int r;
        int c;
        int s;

        public Info(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int N, M, K;
    static int[][] initArr;
    static boolean[] visited;
    static int answer;
    static ArrayList<Info> RotateInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        initArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                initArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        RotateInfo = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            RotateInfo.add(new Info(r, c, s));

        }
        answer = Integer.MAX_VALUE;
        int[] pickIdx = new int[RotateInfo.size()];
        visited = new boolean[RotateInfo.size()];
        perm(pickIdx, 0);
        System.out.println(answer);
    }

    static void perm(int[] pickIdx, int depth) {
        if (depth > pickIdx.length - 1) {

            int[][] arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = initArr[i][j];
                }
            }

            for (int pi = 0; pi < pickIdx.length; pi++) {
                Info ri = RotateInfo.get(pickIdx[pi]);
                int r = ri.r;
                int c = ri.c;
                int s = ri.s;
                rotate(arr, r - s - 1, c - s - 1, 2 * s + 1, 2 * s + 1);
            }
            calSum(arr);
            return;
        }
        for (int i = 0; i < pickIdx.length; i++) {
            if (!visited[i]) {
                pickIdx[depth] = i;
                visited[i] = true;
                perm(pickIdx, depth + 1);
                visited[i] = false;
            }
        }
    }

    static void calSum(int[][] arr) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            answer = Math.min(sum, answer);
        }
    }

    static void rotate(int[][] arr, int startR, int startC, int row, int col) {
        if (row <= 1 || col <= 1) return;

        Deque<Integer> dq = new LinkedList<>();

        for (int i = startR; i < startR + row - 1; i++) dq.add(arr[i][startC]);
        for (int i = startC; i < startC + col - 1; i++) dq.add(arr[startR + row - 1][i]);
        for (int i = startR + row - 1; i > startR; i--) dq.add(arr[i][startC + col - 1]);
        for (int i = startC + col - 1; i > startC; i--) dq.add(arr[startR][i]);

        dq.addLast(dq.pollFirst());

        for (int i = startR; i < startR + row - 1; i++) arr[i][startC] = dq.poll();
        for (int i = startC; i < startC + col - 1; i++) arr[startR + row - 1][i] = dq.poll();
        for (int i = startR + row - 1; i > startR; i--) arr[i][startC + col - 1] = dq.poll();
        for (int i = startC + col - 1; i > startC; i--) arr[startR][i] = dq.poll();

        rotate(arr, startR + 1, startC + 1, row - 2, col - 2);

    }
}