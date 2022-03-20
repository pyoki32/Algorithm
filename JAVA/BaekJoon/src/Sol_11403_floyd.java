import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11403_floyd {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] ans = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ans[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //거쳐가는노드
        for (int k = 0; k < N; k++) {
            //출발 노드
            for (int i = 0; i < N; i++) {
                // 도착 노드
                for (int j = 0; j < N; j++) {
                    if ((ans[i][k] == 1 && ans[k][j]==1)|| ans[i][j]==1) {
                        ans[i][j] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                 sb.append(ans[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
