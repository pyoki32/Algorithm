import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_16927 {
    static int N, M, R;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N, M);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void solve(int startR, int startC, int row, int col) {
        if (row <= 1 || col <= 1) return;

        Deque<Integer> dq = new LinkedList<>();

        for (int i = startR; i < startR + row - 1; i++) dq.add(arr[i][startC]);
        for (int i = startC; i < startC + col - 1; i++) dq.add(arr[startR + row - 1][i]);
        for (int i = startR + row - 1; i > startR; i--) dq.add(arr[i][startC + col - 1]);
        for (int i = startC + col - 1; i > startC; i--) dq.add(arr[startR][i]);
        int calR = R%dq.size();
        for (int i = 0; i < calR; i++) dq.addFirst(dq.pollLast());

        for (int i = startR; i < startR + row - 1; i++) arr[i][startC] = dq.poll();
        for (int i = startC; i < startC + col - 1; i++) arr[startR + row - 1][i] = dq.poll();
        for (int i = startR + row - 1; i > startR; i--) arr[i][startC + col - 1] = dq.poll();
        for (int i = startC + col - 1; i > startC; i--) arr[startR][i] = dq.poll();

        solve(startR + 1, startC + 1, row - 2, col - 2);


    }

}
