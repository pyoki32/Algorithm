import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1504 {

    static int[][] graph;
    static final int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
        }
        // 거쳐가는 노드
        for (int k = 1; k <= N; k++) {
            // 출발
            for (int i = 1; i <= N; i++) {
                // 도착
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int MIN = INF;

        if (v1 != v2 && v1 != N && v2 != 1) {
            MIN = Math.min(MIN, graph[1][v1] + graph[v1][v2] + graph[v2][N]);
            MIN = Math.min(MIN, graph[1][v2] + graph[v2][v1] + graph[v1][N]);
        }
        if (MIN == INF) System.out.println(-1);
        else System.out.println(MIN);
    }
}
