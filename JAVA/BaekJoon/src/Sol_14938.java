import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14938 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] t = new int[n + 1];
        int[][] adjMatrix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    adjMatrix[i][j] = 10000;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = l;
            adjMatrix[b][a] = l;

        }
        //거처 가는 노드
        for (int k = 1; k <= n; k++) {
            //출발 노드
            for (int i = 1; i <= n; i++) {
                //도착 노드
                for (int j = 1; j <= n; j++) {
                    if ((adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j]))
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                }
            }
        }
        int maxItemCnt = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int getItemCnt = t[i];
            for (int j = 1; j <= n; j++) {
                if (i != j && adjMatrix[i][j] <= m) {
                    getItemCnt += t[j];
                }
            }
            maxItemCnt = Math.max(maxItemCnt, getItemCnt);
        }
        System.out.println(maxItemCnt);
    }
}
